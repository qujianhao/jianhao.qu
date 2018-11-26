package com.wangtiansoft.KingDarts.config.lftpay.api.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.liantuo.front.client.sign.util.MD5Util;
/**
 * 加密工具
 * Created by LVHUIHUI on 2018-6-5
 *
 */
public class SignUtil {

	   public static String createSign(Map<String, String> paras, String key,
	             String enc) {
	   SortedMap<String, String> paraMap = new TreeMap<String, String>();
	   paraMap.putAll(paras);
	   StringBuffer sb = new StringBuffer();
	   Set es = paraMap.entrySet();
	   Iterator it = es.iterator();
	   while (it.hasNext()) {
	   Map.Entry entry = (Map.Entry) it.next();
	   String k = (String) entry.getKey();
	   String v = (String) entry.getValue();
	   if (null != v && !"".equals(v) && !"sign".equals(k)&& !"key".equals(k)) {
	     sb.append(k + "=" + v + "&");
	      }
	   }
	   String signString = sb.toString().replaceAll("&$", key);
//	   System.out.println("加密参数是:"+signString);
	   return MD5Util.MD5Encode(signString, enc).toLowerCase();
	  }
	
}
