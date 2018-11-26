package com.wangtiansoft.KingDarts.modules.api.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.config.lftpay.api.example.FrontJsapi;
import com.wangtiansoft.KingDarts.config.lftpay.api.example.Main;
import com.wangtiansoft.KingDarts.config.netty.model.NettyMessage;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.client.service.GameClientService;
import com.wangtiansoft.KingDarts.modules.game.service.GameOrderService;
import com.wangtiansoft.KingDarts.modules.pay.service.LftPayService;
import com.wangtiansoft.KingDarts.modules.race.service.RaceInfoService;
import com.wangtiansoft.KingDarts.modules.rechargerule.service.LftRechargeRuleService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.persistence.entity.LftPay;
import com.wangtiansoft.KingDarts.persistence.entity.LftRechargeRule;
import com.wangtiansoft.KingDarts.persistence.entity.RaceInfo;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/api/pay")
public class PayAPIController  extends BaseController{
	
	@Resource
	private LftPayService lftPayService;
	@Resource
	private LftRechargeRuleService lftRechargeRuleService;
	@Resource
	private UserService userService;
	@Resource
	private GameClientService gameClientService;
	@Resource
	private GameOrderService gameOrderService;
	@Resource
	private RaceInfoService raceInfoService;
	
	@Autowired
	private RedisTemplate redisTemplate;
	/**
	 * 获取充值规则
	 * @return
	 */
	@RequestMapping(value="/rechargeRule")
	public @ResponseBody ApiResult  rechargeRule() {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					List<LftRechargeRule> list = lftRechargeRuleService.getAllRechargeRule();

					UserResult ur = userService.getUserByUuid(uuid);
					
					Map<String ,Object> map = new HashMap<>();
					map.put("reclist", list);
					map.put("user", ur);
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 游戏支付
	 * @return
	 */
	@RequestMapping(value="/consume")
	public @ResponseBody ApiResult  consume(final String orderNo,final String type) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String uuid) throws Exception {
				if(StringUtils.isEmpty(orderNo)){
					throw new AppRuntimeException("订单不能为空");
				}
				
				//查询订单
				GameOrder gameOrder = new GameOrder();
				gameOrder.setOrder_no(orderNo);
				gameOrder.setPay_status( Constants.gorder_paystatus_nopay);
				gameOrder = gameOrderService.getGameOrderByNo(gameOrder);
		        if(gameOrder == null){
		        	throw new AppRuntimeException("订单不存在或已经支付");
		        }
		        
				//订单类型
		        Integer orderType = Constants.gorder_type_single;
		    	if(Constants.consume_type_booked.equals(type) ){
		    		orderType = Constants.gorder_type_booked;
		    	}else if(Constants.consume_type_single.equals(type)){
		    		orderType = Constants.gorder_type_single;
		    	}else{
		    		throw new AppRuntimeException("类型错误");
		    	}
		    	
				//消费扣款
				userService.consume(uuid,gameOrder.getId(), gameOrder.getCost(),orderType,gameOrder.getGame_code()
						,gameOrder.getGame_mode(),gameOrder.getEquno());
				
				UserResult u = userService.getUserByUuid(uuid);
				Map<String,Object> map = new HashMap<>();
				map.put("balance", u.getBalance());
				map.put("give_balance", u.getGive_balance());
				
				//通知设备
				NettyMessage message = gameClientService.pushGameOrder(gameOrder.getEquno(),null, orderNo,orderType
						,u.getPoints(),u.getBalance().add(u.getGive_balance()).toString(),gameOrder.getGame_code());
				if(!message.getCode().equals(com.wangtiansoft.KingDarts.config.netty.constants.Constants.code_Success)){
					throw new AppRuntimeException("扣款成功，通知设备失败");
				}
				
				return map;
			}
		});
		return result;
	}
	/**
	 * 获取支付参数
	 * @return
	 */
	@RequestMapping(value="/payinfo", method = RequestMethod.POST)
	public @ResponseBody ApiResult  payinfo(final String openid,final BigDecimal totalMoney
			,final BigDecimal game_balance,final BigDecimal give_game_balance,final String order_no,final String pay_equno
			,final String raceno,final String des) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String uuid) throws Exception {
				if(StringUtils.isEmpty(openid)){
					throw new AppRuntimeException("用户授权失败");
				}
				UserResult user = userService.getUserByOpenid(openid);
				if(user==null){
					throw new AppRuntimeException("用户授权失败");
				}
				if(user!=null) {
					if(user.getUnionid()==null) {
						throw new AppRuntimeException("用户授权失败");
					}
				}
				
				if(totalMoney==null||totalMoney.compareTo(new BigDecimal(0)) != 1){
					throw new AppRuntimeException("充值金额错误");
				}
				if(raceno==null) {
					if(game_balance==null||game_balance.compareTo(new BigDecimal(0)) != 1){
						throw new AppRuntimeException("充值金额错误");
					}
				}
				if(give_game_balance==null||give_game_balance.compareTo(new BigDecimal(0)) == -1){
					throw new AppRuntimeException("充值金额错误");
				}
				String subject=null;
				//判断比赛充值金额是否正确
				if(raceno!=null) {
					Example example1=new Example(RaceInfo.class);
			        Criteria cr1= example1.createCriteria();
			        cr1.andEqualTo("raceno", raceno);
			        RaceInfo raceInfo = raceInfoService.findOneByExample(example1);
			        if(raceInfo.getMinimum_num()==8) {
			        	if(new BigDecimal(16).compareTo(totalMoney)!=0) {
							throw new AppRuntimeException("报名金额错误");
				        }
			        }
			        if(raceInfo.getMinimum_num()==16) {
			        	if(new BigDecimal(20).compareTo(totalMoney)!=0) {
							throw new AppRuntimeException("报名金额错误");
				        }
			        }
			        if(raceInfo.getMinimum_num()==32) {
			        	if(new BigDecimal(30).compareTo(totalMoney)!=0) {
							throw new AppRuntimeException("报名金额错误");
				        }
			        }
			        //商品描述
					subject = "比赛报名";
			        
				}else {
					//商品描述
					subject = "游戏点充值";
				}
				//TODO 测试
				//String openid = "olggc5M7q_VstNvzZSDqdOppxbiY";//调试用
				
				if(StringUtils.isNotEmpty(des)){
					subject = des;
				}
				String res = Main.requestAsPost(FrontJsapi.getWechatReqparas(request,subject,String.valueOf(totalMoney),openid));
				//String res = Main.requestAsPost(FrontJsapi.getWechatReqparas(request,subject,"0.01",openid));
				System.out.println(res);
				//解析保存
				LftPay lftPay = new LftPay();
				JSONObject job = JSONObject.parseObject(res);
				JSONObject head = JSONObject.parseObject(job.get("head").toString());		
				JSONObject body = JSONObject.parseObject(job.get("body").toString());
				if(!"S".equals(body.get("is_success"))){
					throw new AppRuntimeException("订单创建错误");
				}
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
				lftPay.setOpen_id(openid);
				lftPay.setGame_balance(game_balance);
				lftPay.setGive_game_balance(give_game_balance);
				lftPay.setOrder_no(order_no);
				lftPay.setEquno(pay_equno);
				lftPay.setRaceno(raceno);
				lftPayService.save(lftPay);
				
				//加入缓存
				redisTemplate.opsForHash().put(Constants.pay_order_onpay_app, lftPay.getTrade_no(), JSON.toJSONString(lftPay));
				
				Map<String ,Object> map = new HashMap<>();
				map.put("pay_info", body.get("pay_info"));
				map.put("lftPayId", lftPay.getId());
				return map;
			}
		});
		return result;
	}
	
	
}
