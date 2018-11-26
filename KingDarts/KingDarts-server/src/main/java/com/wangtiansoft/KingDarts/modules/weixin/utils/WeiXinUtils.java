package com.wangtiansoft.KingDarts.modules.weixin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.JsTicket;
import com.jfinal.weixin.sdk.api.JsTicketApi;
import com.jfinal.weixin.sdk.api.JsTicketApi.JsApiType;
import com.jfinal.weixin.sdk.api.TemplateData;
import com.jfinal.weixin.sdk.api.TemplateMsgApi;

public class WeiXinUtils {
	
	public static String getOauthPageUrl(String redirectUrl,String state,String scope) {
		String url = "";
		String stateStr = "";
		if(StringUtils.isEmpty(scope))	scope = "snsapi_base";
		try {
			url = URLEncoder.encode(PropKit.get("domain") + redirectUrl, "UTF-8");
			if(StringUtils.isNotEmpty(state))
				stateStr = URLEncoder.encode(state, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuilder oauthUrl = new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?");
		oauthUrl.append("appid=").append(ApiConfigKit.getApiConfig().getAppId()).append("&redirect_uri=").append(url)
		.append("&response_type=code&scope=").append(scope).append("&state=").append(stateStr).append("#wechat_redirect");
		return oauthUrl.toString();
	}
	
	public static Map<String,String> jssdk(String url){
		JsTicket jsApiTicket = JsTicketApi.getTicket(JsApiType.jsapi);
		String jsapi_ticket = jsApiTicket.getTicket();
		String nonce_str = create_nonce_str();
		// 注意 URL 一定要动态获取，不能 hardcode.
		
		String timestamp = create_timestamp();
		// 这里参数的顺序要按照 key 值 ASCII 码升序排序
		//注意这里参数名必须全部小写，且必须有序
		String  str = "jsapi_ticket=" + jsapi_ticket +
        "&noncestr=" + nonce_str +
        "&timestamp=" + timestamp +
        "&url=" + url;

		String signature = HashKit.sha1(str);
		
		Map<String,String> map = new HashMap<>();
		map.put("appId", ApiConfigKit.getApiConfig().getAppId());
		map.put("nonceStr", nonce_str);
		map.put("timestamp", timestamp);
		map.put("url", url);
		map.put("signature", signature);
		map.put("jsapi_ticket", jsapi_ticket);
		
		return map;
	}
	
	public static String filterWeixinEmoji(String source){
		if (containsEmoji(source)) {
			source = filterEmoji(source);
		}
		return source;
	}

	/**
	 * 检测是否有emoji字符
	 * 
	 * @param source
	 * @return 一旦含有就抛出
	 */
	public static boolean containsEmoji(String source) {
		if (StrKit.isBlank(source)) {
			return false;
		}

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				// do nothing，判断到了这里表明，确认有表情字符
				return true;
			}
		}

		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {

		if (!containsEmoji(source)) {
			return source;// 如果不包含，直接返回
		}
		// 到这里铁定包含
		StringBuilder buf = null;

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}

				buf.append(codePoint);
			} else {
			}
		}

		if (buf == null) {
			return source;// 如果没有找到 emoji表情，则返回源字符串
		} else {
			if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}
	}
	
	/** 
     * emoji表情转换(hex -> utf-16) 
     *  
     * @param hexEmoji 
     * @return 
     */  
    public static String emoji(int hexEmoji) {  
        return String.valueOf(Character.toChars(hexEmoji));  
    }
	
	/**
	 * 发送模板消息
	 * @param orderId
	 * @param price
	 * @param couresName
	 * @param teacherName
	 * @param openId
	 * @param url
	 * @return
	 */
	public static ApiResult sendTemplateMessage_2(String orderId,String price,String couresName,String teacherName,String openId,String url){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
		String time=sdf.format(new Date());
		ApiResult result = TemplateMsgApi.send(TemplateData.New()
				.setTemplate_id("7y1wUbeiYFsUONKH1IppVi47WwViICAjREZSdR3Zahc")
				.setTouser(openId)
				.setUrl(url)
				.add("first", "您好,你已成功购买课程", "#000000")
				.add("keyword1", orderId, "#FF0000")
				.add("keyword2", price+"元", "#c4c400")
				.add("keyword3", couresName, "#c4c400")
				.add("keyword4",teacherName, "#c4c400")
				.add("keyword5",time, "#0000FF")
				.add("remark", "\n 请点击详情直接看课程直播，祝生活愉快", "#008000")
				.build());
		
		
		return result;
	}
	
	public static ApiResult sendTemplateMessageByOpen(String orderId,String price,String couresName,String teacherName,String openId,String url){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
		String time=sdf.format(new Date());
		ApiResult result = TemplateMsgApi.send(TemplateData.New()
				.setTemplate_id("7y1wUbeiYFsUONKH1IppVi47WwViICAjREZSdR3Zahc")
				.setTouser(openId)
				.setUrl(url)
				.add("first", "您好,你已成功购买课程", "#000000")
				.add("keyword1", orderId, "#FF0000")
				.add("keyword2", price+"元", "#c4c400")
				.add("keyword3", couresName, "#c4c400")
				.add("keyword4",teacherName, "#c4c400")
				.add("keyword5",time+"\n我们的专业客服人员会在24小时内与您联系，请注意接听我们的电话，再次感谢您的支持！", "#000000")
				.add("remark", "\n 请点击详情直接看课程直播，祝生活愉快", "#008000")
				.build());
		
		return result;
	}
	
	public static ApiResult sendTemplateMessageByPrivate(String orderId,String price,String couresName,String teacherName,String openId,String url){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
		String time=sdf.format(new Date());
		ApiResult result = TemplateMsgApi.send(TemplateData.New()
				.setTemplate_id("7y1wUbeiYFsUONKH1IppVi47WwViICAjREZSdR3Zahc")
				.setTouser(openId)
				.setUrl(url)
				.add("first", "您好,你已成功购买课程", "#000000")
				.add("keyword1", orderId, "#FF0000")
				.add("keyword2", price+"元", "#c4c400")
				.add("keyword3", couresName, "#c4c400")
				.add("keyword4",teacherName, "#c4c400")
				.add("keyword5",time, "#000000")
				.add("remark", "\n我们的专业客服人员会在24小时内与您联系，请注意接听我们的电话，再次感谢您的支持！", "#008000")
				.build());
		
		return result;
	}
	
	
	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}
}