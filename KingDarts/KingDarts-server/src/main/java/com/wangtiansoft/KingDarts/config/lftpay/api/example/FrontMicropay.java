package com.wangtiansoft.KingDarts.config.lftpay.api.example;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.wangtiansoft.KingDarts.config.lftpay.api.constant.ComConstant;
import com.wangtiansoft.KingDarts.config.lftpay.api.util.SignUtil;

/**
 * 扫码--被扫支付
 * Created by LVHUIHUI on 2018-6-1
 *
 */
public class FrontMicropay {
	/**
	 * 组装请求参数
	 * @return
	 */
	public static Map getReqparas(){
		HashMap<String, HashMap<String, String>> requestJson = new HashMap<String, HashMap<String, String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		HashMap<String, String> body = new HashMap<String, String>();
		HashMap<String, String> head = new HashMap<String, String>();
    	Map<String, String> paras = new HashMap<String, String>();
    	head.put("service", "front.micropay");//扫码--被扫支付
    	head.put("version", ComConstant.VERSION_1);//默认版本号
    	head.put("partner_id", ComConstant.PARTNER_ID);//联富通线下提供pid
    	head.put("core_merchant_no", ComConstant.CORE_MERCHANT_NO);//联富通后台核心商户编号
    	head.put("input_charset", ComConstant.INPUT_CHARSET);//编码格式，默认只支持UTF-8
		
    	body.put("merchant_no", "EW_N8366110628");//门店商户编号
    	body.put("channel_type", "WX");//支付渠道
    	body.put("out_trade_no", "EW_N8366110628_0001aaa23");//商户订单号	
    	body.put("total_amount", "0.01");//订单总金额
    	body.put("subject", "玉玲兰麓谷店");//商品标题
    	body.put("spbill_create_ip", "123.0.0.1");//终端IP
    	body.put("device_info", "设备测试账号1");//设备号
    	body.put("auth_code", "134793993776263984");//支付授权码
	
    
    	requestJson.put("body", body);
        requestJson.put("head", head);
        map.putAll(body);
        map.putAll(head);
 	    head.put("sign", SignUtil.createSign(map,ComConstant.PARTER_KEY ,ComConstant.INPUT_CHARSET));
 	    head.put("sign_type", ComConstant.SIGN_TYPE);
 	    paras.put("requestJson", JSON.toJSONString(requestJson));
		
	    return paras;
   }
	
	public static void main(String[] args)  { 
		System.out.println("扫码--被扫支付===服务器端返回res====="+Main.requestAsPost(FrontMicropay.getReqparas()));
	}
}
