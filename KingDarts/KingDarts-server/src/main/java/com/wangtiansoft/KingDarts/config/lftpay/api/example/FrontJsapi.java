package com.wangtiansoft.KingDarts.config.lftpay.api.example;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.jfinal.kit.PropKit;
import com.wangtiansoft.KingDarts.config.lftpay.api.constant.ComConstant;
import com.wangtiansoft.KingDarts.config.lftpay.api.util.SignUtil;
import com.wangtiansoft.KingDarts.config.utils.TenpayUtil;

/**
 *公众账号支付
 * Created by LVHUIHUI on 2018-6-1
 *
 */
public class FrontJsapi {
	/**
	 * 公众号组装请求参数
	 * @return
	 */
	public static Map getReqparas(HttpServletRequest request,String subject,String total_amount,String open_id){
		//当前时间 yyyyMMddHHmmss  
		String currTime = TenpayUtil.getCurrTime(); 
		//8位日期  
		String strTime = currTime.substring(8, currTime.length());  
		//四位随机数  
		String strRandom = TenpayUtil.buildRandom(4) + "";
		//生成订单号
		String out_trade_no = "KDS_"+strTime + strRandom;
		//支付渠道
		String channel_type = "WX";
		//支付设备IP地址
		String spbill_create_ip = request.getRemoteAddr();
		//订单金额
		//String total_amount = "0.1";
				
		HashMap<String, HashMap<String, String>> requestJson = new HashMap<String, HashMap<String, String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		HashMap<String, String> body = new HashMap<String, String>();
		HashMap<String, String> head = new HashMap<String, String>();
    	Map<String, String> paras = new HashMap<String, String>();
    	head.put("service", "front.jsapi");//扫码--公众号支付
    	head.put("version", ComConstant.VERSION_1);//默认版本号
    	head.put("partner_id", PropKit.get("partner_id"));//联富通线下提供pid
    	head.put("core_merchant_no", PropKit.get("core_merchant_no"));//联富通后台核心商户编号
    	head.put("input_charset", ComConstant.INPUT_CHARSET);//编码格式，默认只支持UTF-8
		
    	body.put("merchant_no", PropKit.get("merchant_no"));//门店商户编号
    	body.put("channel_type", channel_type);//支付渠道
    	body.put("out_trade_no", out_trade_no);//商户订单号	
    	body.put("total_amount", total_amount);//订单总金额
    	body.put("subject", subject);//商品标题
    	body.put("spbill_create_ip", getIpAdrress(request));//终端IP
    	body.put("device_info", "公众号支付");//设备号
    	body.put("open_id", open_id);//对应的appid获取的用户openid
//    	body.put("sub_appid", "wxe3fded3631b24d8c");//联富通后台门店进件时填写的appid,传递参数时针对于所有的商户全部都为sub_appid
    	body.put("sub_appid", PropKit.get("appId"));//联富通后台门店进件时填写的appid,传递参数时针对于所有的商户全部都为sub_appid
    	body.put("notify_url", "http://dailishanghu.com");//暂未开通，商户自己服务器被动接收支付结果的服务器地址
		
   
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
	public static Map getWechatReqparas(HttpServletRequest request,String subject,String total_amount,String open_id){
		//当前时间 yyyyMMddHHmmss  
		String currTime = TenpayUtil.getCurrTime(); 
		//8位日期  
		String strTime = currTime.substring(8, currTime.length());  
		//四位随机数  
		String strRandom = TenpayUtil.buildRandom(4) + "";
		//生成订单号
		String out_trade_no = "KDS_"+strTime + strRandom;
		//支付渠道
		String channel_type = "WX";
		//支付设备IP地址
		String spbill_create_ip = request.getRemoteAddr();
		//订单金额
		//String total_amount = "0.1";
				
		HashMap<String, HashMap<String, String>> requestJson = new HashMap<String, HashMap<String, String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		HashMap<String, String> body = new HashMap<String, String>();
		HashMap<String, String> head = new HashMap<String, String>();
    	Map<String, String> paras = new HashMap<String, String>();
    	head.put("service", "front.minijsapi");//扫码--公众号支付
    	head.put("version", ComConstant.VERSION_1);//默认版本号
    	head.put("partner_id",  PropKit.get("app_partner_id"));//联富通线下提供pid
    	head.put("core_merchant_no", PropKit.get("app_core_merchant_no"));//联富通后台核心商户编号
    	head.put("input_charset", ComConstant.INPUT_CHARSET);//编码格式，默认只支持UTF-8
		
    	body.put("merchant_no", PropKit.get("app_merchant_no"));//门店商户编号
    	body.put("channel_type", channel_type);//支付渠道
    	body.put("out_trade_no", out_trade_no);//商户订单号	
    	body.put("total_amount", total_amount);//订单总金额
    	body.put("subject", subject);//商品标题
    	body.put("spbill_create_ip", getIpAdrress(request));//终端IP
    	body.put("device_info", "小程序支付");//设备号
    	body.put("open_id", open_id);//对应的appid获取的用户openid
    	body.put("sub_appid", PropKit.get("app1.appId"));//联富通后台门店进件时填写的appid,传递参数时针对于所有的商户全部都为sub_appid
//    	body.put("sub_appid", "wxb2dd93232ea07fc3");//测试用
    	body.put("notify_url", "http://dailishanghu.com");//暂未开通，商户自己服务器被动接收支付结果的服务器地址
		
   
    	requestJson.put("body", body);
        requestJson.put("head", head);
        map.putAll(body);
        map.putAll(head);
 	    head.put("sign", SignUtil.createSign(map,PropKit.get("app_merchant_parter_key"),ComConstant.INPUT_CHARSET));
 	    head.put("sign_type", ComConstant.SIGN_TYPE);
 	    paras.put("requestJson", JSON.toJSONString(requestJson));
		
	    return paras;
   }
	
	/**
     * 获取Ip地址
     * @param request
     * @return
     */
    private static String getIpAdrress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
	
	/*public static void main(String[] args)  { 
		System.out.println("公众账号支付===服务器端返回res====="+Main.requestAsPost(FrontJsapi.getReqparas()));
	}*/
	
}
