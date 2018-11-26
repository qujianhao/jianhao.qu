package com.wangtiansoft.KingDarts.config.lftpay.api.util;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public class HttpClientUtil{
	/**
	 * 默认连接超时时间
	 */
	private final static int DEFAULT_CONNECTION_TIME_OUT=3000;
	/**
	 *
	 */
	private final static int DEFAULT_SO_TIME_OUT=10000;
	
	/***
	 * 默认字符编码
	 */
	private final static String DEFAULT_CHARSET_UTF_8="UTF-8";
	
	/**
	 * Post 方式http请求
	 * @param urlvalue url 如：http://www.51book.com/liantuo/manage/pay4Alipay.in 
	 * @param paras 参数
	 * @param charset 字符编码 默认 utf-8
	 * @param connectionTimeout 建立连接超时时间 默认3000毫秒
	 * @param soTimeout 处理超时时间 默认10000毫秒
	 * @return
	 */
	public static String requestAsHttpPOST(String urlvalue,
			Map<String, String> paras , String charset,Integer connectionTimeout,Integer soTimeout) {
		HttpClient http = new HttpClient();
		HttpConnectionManagerParams managerParams = http.getHttpConnectionManager().getParams();
        if(connectionTimeout!=null && connectionTimeout.intValue()!=0){
        	managerParams.setConnectionTimeout(connectionTimeout);
        }else{
        	managerParams.setConnectionTimeout(DEFAULT_CONNECTION_TIME_OUT);
        }
        if(soTimeout!=null && soTimeout.intValue()!=0){
        	managerParams.setSoTimeout(soTimeout);
        }else{
        	managerParams.setSoTimeout(DEFAULT_SO_TIME_OUT);
        }
		PostMethod method = new PostMethod(urlvalue);
		if(StringUtil.isEmpty(charset)){
            method.getParams().setHttpElementCharset(DEFAULT_CHARSET_UTF_8);
            method.getParams().setContentCharset(DEFAULT_CHARSET_UTF_8);
            method.getParams().setCredentialCharset(DEFAULT_CHARSET_UTF_8);
		}else{
            method.getParams().setHttpElementCharset(charset);
            method.getParams().setContentCharset(charset);
            method.getParams().setCredentialCharset(charset);
		}
		if(paras!=null){
			for (String key : paras.keySet()) {
				method.addParameter(key, paras.get(key));
			}
		}
		String result = "";
		try {
			http.executeMethod(method);
			result = method.getResponseBodyAsString();
		} catch (IOException e) {
			result = "";
			e.printStackTrace();
		}finally{  
			  method.releaseConnection();  
		}  
		return result;
	}
}
