package com.wangtiansoft.KingDarts.modules.pay.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.kit.IpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.config.lftpay.api.example.FrontClose;
import com.wangtiansoft.KingDarts.config.lftpay.api.example.FrontQuery;
import com.wangtiansoft.KingDarts.config.lftpay.api.example.Main;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.game.service.GameOrderService;
import com.wangtiansoft.KingDarts.modules.pay.service.LftPayService;
import com.wangtiansoft.KingDarts.modules.race.service.RaceEnterforUserService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.LftPayMapper;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.persistence.entity.LftPay;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.UserResult;

@Transactional
@Service("lftPayService")
public class LftPayServiceImpl extends BaseService<LftPay, Integer> implements LftPayService {

	private static final Logger log = LoggerFactory.getLogger(LftPayServiceImpl.class);
	
	@Autowired
	private LftPayMapper lftPayMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private GameOrderService gameOrderService;
	@Autowired
	private RaceEnterforUserService raceEnterforUserService;
	@Autowired
	private RedisTemplate redisTemplate;

	private Long EXPIRES_SECONDS = new Long(120); //超时时间120秒

	@Override
	public BaseMapper getBaseMapper() {
		return lftPayMapper;
	}

	@Override
	public void updateLftPayOutTradeNoAndTradeNo(LftPay lftPay) {
		lftPayMapper.updateLftPayOutTradeNoAndTradeNo(lftPay);
	}

	@Override
	public List<LftPay> getLftPayByPayStatus(Integer pay_status){
		return lftPayMapper.getLftPayByPayStatus(pay_status);
	}

	@Override
	public boolean synPayStatus(String out_trade_no,String trade_no,String merchant_no,Date time_start){
		//返回true停止状态同步，返回false继续同步
		String res = "";
		try {
			System.out.println("1----------------公众号支付状态查询，out_trade_no="+out_trade_no);
			res = Main.requestAsPost(FrontQuery.getReqparas(out_trade_no,trade_no)); 
			JSONObject job = JSONObject.parseObject(res);
			JSONObject body = JSONObject.parseObject(job.get("body").toString());
			if(body.get("is_success").equals("S")) {
				if(body.get("trade_status").equals("success")) {
					System.out.println("支付成功");
					this.paySuccess(out_trade_no);
					return true;
				}else if(body.get("trade_status").equals("fail")) {
					System.out.println("支付失败");
					this.payFail(out_trade_no,trade_no);
					return true;
				}else if(body.get("trade_status").equals("closed")) {
					System.out.println("已关单");
				}else if(body.get("trade_status").equals("cancel")) {
					System.out.println("已撤销");
				}else if(body.get("trade_status").equals("paying")) {
					System.out.println("支付中");
				}else{
					System.out.println("状态错误");
					this.payErrorTimeout(out_trade_no,merchant_no);
					return true;
				}

				//根据支付时间判断是否超时(超时1分钟)
				Date d = new Date();
				if(d.getTime()-time_start.getTime()>=120000) {
					String closeMessage = Main.requestAsPost(FrontClose.getReqparas(out_trade_no,merchant_no));

					//String json1 = StringUtils.substringAfter(closeMessage,"\n");
					JSONObject job1 = JSONObject.parseObject(closeMessage);
					JSONObject body1 = JSONObject.parseObject(job1.get("body").toString());
					if(body1.get("is_success").equals("S")) {
						System.out.println("支付超时，请重新支付");
					}else{
						System.out.println("支付超时，但关闭支付订单不成功");
					}
					
					this.payErrorTimeout(out_trade_no,merchant_no);
					return true;
				}else{
					System.out.println("订单未超时");
				}
				return false;
			}else{
				System.out.println("状态错误");
				this.payErrorTimeout(out_trade_no,merchant_no);
				return true;
			}
		} catch (Exception e) {
			log.error("out_trade_no="+out_trade_no+" trade_no="+trade_no+" 支付状态错误，"+res,e);
			this.payErrorTimeout(out_trade_no,merchant_no);
			return true;
		}
	}
	
	@Override
	public boolean synWechatPayStatus(String out_trade_no,String trade_no,String merchant_no,Date time_start){
		//返回true停止状态同步，返回false继续同步
		String res = "";
		try {
			System.out.println("2+++++++++++++++小程序支付状态查询，out_trade_no="+out_trade_no);
			res = Main.requestAsPost(FrontQuery.getWechatReqparas(out_trade_no,trade_no)); 
			JSONObject job = JSONObject.parseObject(res);
			JSONObject body = JSONObject.parseObject(job.get("body").toString());
			if(body.get("is_success").equals("S")) {
				if(body.get("trade_status").equals("success")) {
					System.out.println("支付成功");
					this.paySuccess(out_trade_no);
					return true;
				}else if(body.get("trade_status").equals("fail")) {
					System.out.println("支付失败");
					this.payFail(out_trade_no,trade_no);
					return true;
				}else if(body.get("trade_status").equals("closed")) {
					System.out.println("已关单");
				}else if(body.get("trade_status").equals("cancel")) {
					System.out.println("已撤销");
				}else if(body.get("trade_status").equals("paying")) {
					System.out.println("支付中");
				}else{
					System.out.println("状态错误");
					this.payErrorTimeout(out_trade_no,merchant_no);
					return true;
				}

				//根据支付时间判断是否超时(超时1分钟)
				Date d = new Date();
				if(d.getTime()-time_start.getTime()>=120000) {
					String closeMessage = Main.requestAsPost(FrontClose.getWechatReqparas(out_trade_no,merchant_no));

					//String json1 = StringUtils.substringAfter(closeMessage,"\n");
					JSONObject job1 = JSONObject.parseObject(closeMessage);
					JSONObject body1 = JSONObject.parseObject(job1.get("body").toString());
					if(body1.get("is_success").equals("S")) {
						System.out.println("支付超时，请重新支付");
					}else{
						System.out.println("支付超时，但关闭支付订单不成功");
					}
					this.payErrorTimeout(out_trade_no,merchant_no);
					return true;
				}else{
					System.out.println("订单未超时");
				}
				return false;
			}else{
				System.out.println("状态错误");
				this.payErrorTimeout(out_trade_no,merchant_no);
				return true;
			}
		} catch (Exception e) {
			log.error("out_trade_no="+out_trade_no+" trade_no="+trade_no+" 支付状态错误，"+res,e);
			this.payErrorTimeout(out_trade_no,merchant_no);
			return true;
		}
	}
	
	private void payErrorTimeout(String out_trade_no,String merchant_no){
		LftPay lftPay1 = new LftPay();
		lftPay1.setOut_trade_no(out_trade_no);
		lftPay1.setMerchant_no(merchant_no);
		lftPay1.setPay_status(4);
		lftPay1.setTime_expire(new Date());
		lftPay1.setUpdate_time(new Date());
		lftPayMapper.updateLftPayOutTradeNoAndTradeNo(lftPay1);
		

		//通知页面支付状态
		LftPay lftPay = new LftPay();
		lftPay.setOut_trade_no(out_trade_no);
		LftPay lp = lftPayMapper.selectOne(lftPay);
		if(StringUtils.isNotEmpty(lp.getOrder_no())){
			redisTemplate.boundValueOps(Constants.pay_order_notice+lp.getId()).set("timeout", EXPIRES_SECONDS, TimeUnit.SECONDS);
		}
	}
	
	private void payFail(String out_trade_no,String trade_no){
		LftPay lftPay1 = new LftPay();
		lftPay1.setOut_trade_no(out_trade_no);
		lftPay1.setTrade_no(trade_no);
		lftPay1.setPay_status(3);
		lftPay1.setTime_expire(new Date());
		lftPay1.setUpdate_time(new Date());
		lftPayMapper.updateLftPayOutTradeNoAndTradeNo(lftPay1);

		//通知页面支付状态
		LftPay lftPay = new LftPay();
		lftPay.setOut_trade_no(out_trade_no);
		LftPay lp = lftPayMapper.selectOne(lftPay);
		redisTemplate.boundValueOps(Constants.pay_order_notice+lp.getId()).set("fail", EXPIRES_SECONDS, TimeUnit.SECONDS);
	}
	private void paySuccess(String out_trade_no){
		LftPay lftPay = new LftPay();
		lftPay.setOut_trade_no(out_trade_no);
		LftPay lp = lftPayMapper.selectOne(lftPay);

		//判断订单状态
		lftPay.setId(lp.getId());
		lftPay.setPay_status(2);
		lftPay.setTime_expire(new Date());
		lftPay.setUpdate_time(new Date());
		lftPayMapper.updateByPrimaryKeySelective(lftPay);

		//给用户钱包充值
		UserResult u = userService.getUserByOpenid(lp.getOpen_id());
		if(u==null){
			return;
		}
		if(StringUtils.isNotEmpty(lp.getOrder_no())){	//通过扫描订单充值
			GameOrder gameOrder = new GameOrder();
			gameOrder.setOrder_no(lp.getOrder_no());
			gameOrder = gameOrderService.getGameOrderByNo(gameOrder);

			if(gameOrder!=null){
				//如果是扫码支付，有机器编码和所属俱乐部
				userService.recharge(u.getUuid(), lp.getId()
						, lp.getGame_balance(),lp.getGive_game_balance(), gameOrder.getId(), gameOrder.getEquno(),"微信充值");
			}else{
				userService.recharge(u.getUuid(), lp.getId(), lp.getGame_balance(),lp.getGive_game_balance(),null,null,"微信充值");
			}

		}else if(StringUtils.isNotEmpty(lp.getEquno())){	//通过扫描设备充值
			userService.recharge(u.getUuid(), lp.getId(), lp.getGame_balance(),lp.getGive_game_balance(),null,lp.getEquno(),"微信充值");
		}else{
			//如果是直接小程序充值，没有机器编码和俱乐部，无需分成
			if(StringUtils.isNotEmpty(lp.getRaceno())){//比赛充值，调用比赛报名成功接口
				userService.recharge(u.getUuid(), lp.getId(), lp.getGame_balance(),lp.getGive_game_balance(),null,null,"报名比赛");
			}else {
				userService.recharge(u.getUuid(), lp.getId(), lp.getGame_balance(),lp.getGive_game_balance(),null,null,"微信充值");
			}
			
		}
		
		if(StringUtils.isNotEmpty(lp.getRaceno())){//比赛充值，调用比赛报名成功接口
			raceEnterforUserService.addEnterfor(lp.getRaceno(), u.getUuid());
		}

		//通知页面支付状态
		redisTemplate.boundValueOps(Constants.pay_order_notice+lp.getId()).set("success", EXPIRES_SECONDS, TimeUnit.SECONDS);
	}

	@Override
	public void testPay(String out_trade_no){
		System.out.println("手动调用支付成功");
		this.paySuccess(out_trade_no);
		/*LftPay lftPay = new LftPay();
		lftPay.setOut_trade_no(out_trade_no);
		LftPay lp = lftPayMapper.selectOne(lftPay);

		lftPay.setId(lp.getId());
		lftPay.setPay_status(2);
		lftPay.setTime_expire(new Date());
		lftPay.setUpdate_time(new Date());
		lftPayMapper.updateByPrimaryKeySelective(lftPay);

		//给用户钱包充值
		UserResult u = userService.getUserByOpenid(lp.getOpen_id());
		if(StringUtils.isNotEmpty(lp.getOrder_no())){
			GameOrder gameOrder = new GameOrder();
			gameOrder.setOrder_no(lp.getOrder_no());
			gameOrder = gameOrderService.getGameOrderByNo(gameOrder);

			//如果是扫码支付，有机器编码和所属俱乐部
			userService.recharge(u.getUuid(), gameOrder.getId()
					, lp.getGame_balance(),lp.getGive_game_balance(), lp.getId(), gameOrder.getEquno(),"微信充值");

		}else{
			//如果是直接小程序充值，没有机器编码和俱乐部，无需分成
			userService.recharge(u.getUuid(), lp.getId(), lp.getGame_balance(),lp.getGive_game_balance(),null,null,"微信充值");
		}*/
	}

	@Override
	public boolean transfers(String openid ,String ip,String userName,String amount){
		Map<String, String> params = new HashMap<String, String>();
		// 商户相关资料
		String appid = PropKit.get("app2.appId");//商户小程序id
		// 微信支付分配的商户号
		String partner = PropKit.get("mch_id");
		// API密钥
		String paternerKey = PropKit.get("paternerKey");
		String transfer_url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
		String certPath = PropKit.get("cert_path");
		if(StringUtils.isBlank(certPath)){
			certPath = Thread.currentThread().getContextClassLoader().getResource("apiclient_cert.p12").getPath();
		}
		
		// 订单号
		String orderNo = System.currentTimeMillis()+"";
		// 真实姓名（可选）
		String reUserName = userName;
		// 金额 单位：分
		params.put("amount", amount);
		// 是否验证姓名
		// NO_CHECK：不校验真实姓名
		// FORCE_CHECK：强校验真实姓名（未实名认证的用户会校验失败，无法转账）
		// OPTION_CHECK：针对已实名认证的用户才校验真实姓名（未实名认证用户不校验，可以转账成功）
		params.put("check_name", "NO_CHECK");
		// 描述
		params.put("desc", "微信提现");
		params.put("mch_appid", appid);
		params.put("mchid", partner);
		// 随机字符串
		params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
		params.put("openid", openid);
		params.put("partner_trade_no", orderNo);
		// 收款用户真实姓名。
		// 如果check_name设置为FORCE_CHECK或OPTION_CHECK，则必填用户真实姓名
		params.put("re_user_name", reUserName);
//		String ip = IpKit.getRealIp(request);
//		if (StrKit.isBlank(ip)) {
//			ip = "127.0.0.1";
//		}
		params.put("spbill_create_ip", ip);
		String sign = PaymentKit.createSign(params, paternerKey);
		params.put("sign", sign);
		String xml = PaymentKit.toXml(params);
		System.out.println(xml);
		String xmlResult = HttpUtils.postSSL(transfer_url, xml, certPath, partner);
		System.out.println(xmlResult);
		Map<String, String> resultXML = PaymentKit.xmlToMap(xmlResult.toString());
		String return_code = resultXML.get("return_code");
		String return_msg = resultXML.get("return_msg");
		String err_code_des = resultXML.get("err_code_des");
		if (StrKit.isBlank(return_code) || !"SUCCESS".equals(return_code)) {
			throw new AppRuntimeException(return_msg+" "+err_code_des);
		}
		String result_code = resultXML.get("result_code");
		if (StrKit.isBlank(result_code) || !"SUCCESS".equals(result_code)) {
			throw new AppRuntimeException(err_code_des);
		}
		return true;
	}

}
