package com.wangtiansoft.KingDarts.config.lftpay.api.example;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.wangtiansoft.KingDarts.config.lftpay.api.constant.ComConstant;
import com.wangtiansoft.KingDarts.config.lftpay.api.util.SignUtil;

/**
 *订单撤销API
 * Created by LVHUIHUI on 2018-6-1
 *
 */
public class FrontReverse {
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
    	head.put("service", "front.reverse");//订单撤销API
    	head.put("version", ComConstant.VERSION_1);//默认版本号
    	head.put("partner_id", ComConstant.PARTNER_ID);//联富通线下提供pid
    	head.put("core_merchant_no", ComConstant.CORE_MERCHANT_NO);//联富通后台核心商户编号
    	head.put("input_charset", ComConstant.INPUT_CHARSET);//编码格式，默认只支持UTF-8
		
    	body.put("out_trade_no", "EW_N8366110628_01aa");//商户订单号	
    	body.put("merchant_no", "EW_N8366110628");//门店商户编号
    	
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
		System.out.println("订单撤销API===服务器端返回res====="+Main.requestAsPost(FrontReverse.getReqparas()));
	}
	
}
