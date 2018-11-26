package com.wangtiansoft.KingDarts.modules.weixin.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.common.utils.date.DateUtil;
import com.wangtiansoft.KingDarts.config.lftpay.api.example.FrontJsapi;
import com.wangtiansoft.KingDarts.config.lftpay.api.example.Main;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.core.utils.AuthUtil;
import com.wangtiansoft.KingDarts.modules.pay.service.LftPayService;
import com.wangtiansoft.KingDarts.modules.rechargerule.service.LftRechargeRuleService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.modules.weixin.utils.WeiXinUtils;
import com.wangtiansoft.KingDarts.persistence.entity.LftPay;
import com.wangtiansoft.KingDarts.persistence.entity.LftRechargeRule;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import tk.mybatis.mapper.entity.Example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/wx/fltpay")
public class WeixinLftPayController extends BaseController{

	private final static Logger logger = LoggerFactory.getLogger(WeixinLftPayController.class);

	@Resource
	private LftPayService lftPayService;
	@Resource
	private LftRechargeRuleService lftRechargeRuleService;
	@Resource
	private UserService userService;
	
	@Autowired
	private RedisTemplate redisTemplate;

	/**
     * 游戏点数支付充值
     * @param totalMoney 充值金额
     * @param game_balance 充值游戏点
     * @param give_game_balance 赠送游戏点
     * @param order_no 扫描设备订单编号
     * @return
     */
	@RequestMapping("/")
	public String index() {
		/*String openid = "oIYP-0hqjxch5RE0acPcBWTISK4Y";
		request.getSession().setAttribute(Constants.session_openid, openid);
		request.getSession().setAttribute("order_no", "X0434PDF");
		UserResult ur = userService.getUserByOpenid(openid);
		AuthUtil.setLoginMember(request, ur);*/
		
		List<LftRechargeRule> list = lftRechargeRuleService.getAllRechargeRule();
		request.setAttribute("reclist", list);
		
		//获取微信页面授权
		StringBuffer requestUrl = request.getRequestURL();
		String queryString = request.getQueryString();
		if(queryString!=null){
			requestUrl.append("?").append(queryString);
		}
		System.out.println(requestUrl.toString());
		Map<String,String> map = WeiXinUtils.jssdk(requestUrl.toString().replace("http", "https"));
		for (Map.Entry<String, String> entry : map.entrySet()) { 
			request.setAttribute(entry.getKey(), entry.getValue());
		}
		return "/wx/recharge";
	}
	
	@RequestMapping("/notice")
	public@ResponseBody ApiResult notice(final String lftPayId) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				String openId = AuthUtil.getOpenId(request);
				
				int maxcount = 10;//等待次数
				Map<String,Object> map = new HashMap<>();
				
				for(int i=0;i<maxcount;i++){
//					System.out.println("========== "+lftPayId);
					Thread.sleep(3000); // 休眠3000毫秒，等待支付状态查询结果
					
					Object msg = redisTemplate.boundValueOps(Constants.pay_order_notice+lftPayId).get();
//					System.out.println("==========: "+msg);
					
					if(msg != null){//有结果返回
						String message = msg.toString();
						map.put("trade_status", message);
						if("success".equals(message)){
							map.put("paymsg", "支付成功");
							
							UserResult ur = userService.getUserByOpenid(openId);
							AuthUtil.setLoginMember(request, ur);
							
							map.put("balance", ur.getBalance().add(ur.getGive_balance()));
							
						}else if("fail".equals(message)){
							map.put("paymsg", "支付失败");
						}else{
							map.put("paymsg", "支付超时");
						}
						redisTemplate.delete(Constants.pay_order_notice+lftPayId);
						return map;
					}
				}
				
				return map;
			}
		});
		return result;
	}
	@RequestMapping("/notice/test")
	public@ResponseBody ApiResult sign(final String lftPayId) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
//				String openId = AuthUtil.getOpenId(request);
				System.out.println("lftPayId =    "+lftPayId);
				redisTemplate.boundValueOps(Constants.pay_order_notice+lftPayId).set("success", new Long(60), TimeUnit.SECONDS);
				
				Map<String,Object> map = new HashMap<>();
				return map;
			}
		});
		return result;
	}
	
	@RequestMapping("/pay")
	public @ResponseBody ApiResult pay(final BigDecimal totalMoney,final BigDecimal game_balance
			,final BigDecimal give_game_balance,final String order_no,final String pay_equno){
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				String openId = AuthUtil.getOpenId(request);
				
				if(StringUtils.isEmpty(openId)){
					//测试用
//					openId = "oIYP-0hqjxch5RE0acPcBWTISK4Y";
//					game_balance = new BigDecimal(200);
//					give_game_balance = new BigDecimal(200);
					throw new AppRuntimeException("用户授权失败");
				}
				UserResult user = userService.getUserByOpenid(openId);
				if(user==null){
					throw new AppRuntimeException("用户授权失败");
				}
				
				if(totalMoney==null||totalMoney.compareTo(new BigDecimal(0)) != 1){
					throw new AppRuntimeException("充值金额错误");
				}
				if(game_balance==null||game_balance.compareTo(new BigDecimal(0)) != 1){
					throw new AppRuntimeException("充值金额错误");
				}
				if(give_game_balance==null||give_game_balance.compareTo(new BigDecimal(0)) == -1){
					throw new AppRuntimeException("充值金额错误");
				}
				
				//商品描述
				String subject = "游戏点充值";
				String res = Main.requestAsPost(FrontJsapi.getReqparas(request,subject,String.valueOf(totalMoney),openId));
				System.out.println(res);
				//解析保存
				LftPay lftPay = new LftPay();
				JSONObject job = JSONObject.parseObject(res);
				JSONObject head = JSONObject.parseObject(job.get("head").toString());		
				JSONObject body = JSONObject.parseObject(job.get("body").toString());
				lftPay.setMerchant_no(body.get("merchant_no").toString());
				lftPay.setSign(head.get("sign").toString());
				lftPay.setChannel_type(body.get("channel_type").toString());
				lftPay.setOut_trade_no(body.get("out_trade_no").toString());
				lftPay.setTrade_no(body.get("trade_no").toString());
				lftPay.setTotal_amount(totalMoney);
				lftPay.setFee_type("CNY");
				lftPay.setSpbill_create_ip(request.getRemoteAddr());
				lftPay.setSubject(subject);
				lftPay.setTime_start(new Date());
				lftPay.setPay_status(1);
				lftPay.setOpen_id(openId);
		        lftPay.setGame_balance(game_balance);
		        lftPay.setGive_game_balance(give_game_balance);
		        lftPay.setOrder_no(order_no);
		        lftPay.setEquno(pay_equno);
				lftPayService.save(lftPay);
				
				//测试，直接支付成功
//				lftPayService.testPay(lftPay.getOut_trade_no(), lftPay.getTrade_no());

				//加入缓存
				redisTemplate.opsForHash().put(Constants.pay_order_onpay, lftPay.getTrade_no(), JSON.toJSONString(lftPay));

				Map<String ,Object> map = new HashMap<>();
				map.put("pay_info", body.get("pay_info"));
				map.put("lftPayId", lftPay.getId());
				map.put("tradeNo", lftPay.getTrade_no());
				return map;
			}
		});
		return result;
	}
	
	@RequestMapping("/haspay")
	public@ResponseBody ApiResult haspay(final String tradeNo) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				//标记支付时间
				redisTemplate.opsForHash().put(Constants.pay_order_jspay, tradeNo, DateUtil.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
				
				Map<String,Object> map = new HashMap<>();
				return map;
			}
		});
		return result;
	}
	
	@RequestMapping("/tpay")
	public@ResponseBody ApiResult tpay(final String outTradeNo) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				//收到支付成功
				lftPayService.testPay(outTradeNo);
				
				Map<String,Object> map = new HashMap<>();
				return map;
			}
		});
		return result;
	}
	
	@RequestMapping("/querypay")
	public@ResponseBody ApiResult querypay(final String outTradeNo) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				Example example=new Example(LftPay.class);
				example.createCriteria().andEqualTo("out_trade_no",outTradeNo);
				LftPay lftPay = lftPayService.findOneByExample(example);
				lftPayService.synPayStatus(lftPay.getOut_trade_no(),lftPay.getTrade_no(),lftPay.getMerchant_no(),lftPay.getTime_start());
				
				Map<String,Object> map = new HashMap<>();
				return map;
			}
		});
		return result;
	}
}
