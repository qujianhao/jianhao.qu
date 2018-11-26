package com.wangtiansoft.KingDarts.config.lftpay.api.example;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.wangtiansoft.KingDarts.config.lftpay.api.constant.ComConstant;
import com.wangtiansoft.KingDarts.config.lftpay.api.util.SignUtil;

/**
 *订单退款API
 * Created by LVHUIHUI on 2018-6-1
 *
 */
public class FrontRefund {
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
    	head.put("service", "front.refund");//订单退款API
    	head.put("version", ComConstant.VERSION_1);//默认版本号
    	head.put("partner_id", ComConstant.PARTNER_ID);//联富通线下提供pid
    	head.put("core_merchant_no", ComConstant.CORE_MERCHANT_NO);//联富通后台核心商户编号
    	head.put("input_charset", ComConstant.INPUT_CHARSET);//编码格式，默认只支持UTF-8
		
    	body.put("out_trade_no", "EW_N8366110628_0001aaa23");//商户订单号	
    	body.put("merchant_no", "EW_N8366110628");//门店商户编号
    	body.put("trade_no", "00060011806051612211970235633323");//原交易流水号
    	body.put("out_refund_no", "EW_N8366110628_222");//退款订单号
    	body.put("refund_fee", "0.01");//退款金额
    	body.put("refund_reason", "商品已售完");//退款的原因说明
    	body.put("spbill_create_ip", "123.12.12.123");//终端IP
    	
    	
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
		System.out.println("订单退款API===服务器端返回res====="+Main.requestAsPost(FrontRefund.getReqparas()));
	}
	
}
