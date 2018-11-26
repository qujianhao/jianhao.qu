package com.wangtiansoft.KingDarts.config.lftpay.api.example;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.jfinal.kit.PropKit;
import com.wangtiansoft.KingDarts.config.lftpay.api.constant.ComConstant;
import com.wangtiansoft.KingDarts.config.lftpay.api.util.SignUtil;
import com.wangtiansoft.KingDarts.config.utils.TenpayUtil;

/**
 *订单查询API
 * Created by LVHUIHUI on 2018-6-1
 *
 */
public class FrontQuery {
	/**
	 * 公众号组装请求参数
	 * @return
	 */
	public static Map getReqparas(String out_trade_no,String trade_no){
		//当前时间 yyyyMMddHHmmss  
		String currTime = TenpayUtil.getCurrTime(); 
		//8位日期  
		String strTime = currTime.substring(8, currTime.length());  
		//四位随机数  
		String strRandom = TenpayUtil.buildRandom(6) + "";
		HashMap<String, HashMap<String, String>> requestJson = new HashMap<String, HashMap<String, String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		HashMap<String, String> body = new HashMap<String, String>();
		HashMap<String, String> head = new HashMap<String, String>();
    	Map<String, String> paras = new HashMap<String, String>();
    	head.put("service", "front.query");//订单查询API
    	head.put("version", ComConstant.VERSION_1);//默认版本号
    	head.put("partner_id", ComConstant.PARTNER_ID);//联富通线下提供pid
    	head.put("core_merchant_no", ComConstant.CORE_MERCHANT_NO);//联富通后台核心商户编号
    	head.put("input_charset", ComConstant.INPUT_CHARSET);//编码格式，默认只支持UTF-8
		
    	body.put("out_trade_no", out_trade_no);//商户订单号	
    	body.put("trade_no", trade_no);//交易流水号,支付请求返回结果参数
		
    	requestJson.put("body", body);
        requestJson.put("head", head);
        map.putAll(body);
        map.putAll(head);
 	    head.put("sign", SignUtil.createSign(map,PropKit.get("merchant_parter_key"),ComConstant.INPUT_CHARSET));
 	    head.put("sign_type", ComConstant.SIGN_TYPE);
 	    paras.put("requestJson", JSON.toJSONString(requestJson));
		
	    return paras;
   }
	
	/**
	 * 小程序组装请求参数
	 * @return
	 */
	public static Map getWechatReqparas(String out_trade_no,String trade_no){
		//当前时间 yyyyMMddHHmmss  
		String currTime = TenpayUtil.getCurrTime(); 
		//8位日期  
		String strTime = currTime.substring(8, currTime.length());  
		//四位随机数  
		String strRandom = TenpayUtil.buildRandom(6) + "";
		HashMap<String, HashMap<String, String>> requestJson = new HashMap<String, HashMap<String, String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		HashMap<String, String> body = new HashMap<String, String>();
		HashMap<String, String> head = new HashMap<String, String>();
    	Map<String, String> paras = new HashMap<String, String>();
    	head.put("service", "front.query");//订单查询API
    	head.put("version", ComConstant.VERSION_1);//默认版本号
    	head.put("partner_id", ComConstant.PARTNER_ID);//联富通线下提供pid
    	head.put("core_merchant_no", ComConstant.CORE_MERCHANT_NO);//联富通后台核心商户编号
    	head.put("input_charset", ComConstant.INPUT_CHARSET);//编码格式，默认只支持UTF-8
		
    	body.put("out_trade_no", out_trade_no);//商户订单号	
    	body.put("trade_no", trade_no);//交易流水号,支付请求返回结果参数
		
    	requestJson.put("body", body);
        requestJson.put("head", head);
        map.putAll(body);
        map.putAll(head);
 	    head.put("sign", SignUtil.createSign(map,PropKit.get("merchant_parter_key") ,ComConstant.INPUT_CHARSET));
 	    head.put("sign_type", ComConstant.SIGN_TYPE);
 	    paras.put("requestJson", JSON.toJSONString(requestJson));
		
	    return paras;
   }
	
	/*public static void main(String[] args)  { 
		System.out.println("订单查询API===服务器端返回res====="+Main.requestAsPost(FrontQuery.getReqparas()));
	}*/
	
}
