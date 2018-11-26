package pay;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.wangtiansoft.KingDarts.common.utils.CollectionsUtil;
import com.wangtiansoft.KingDarts.common.utils.HttpUtil;
import com.wangtiansoft.KingDarts.common.utils.SmsUtil;
import com.wangtiansoft.KingDarts.core.utils.GameUtil;


public class PayDemo {

	@Test
	public void ssssss(){  
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("apiclient_cert.p12").getPath());
//		System.out.println(GameUtil.getGameName("s001g004"));
		/*try {
			SendSmsResponse response = SmsUtil.sendCodeSms("18173116167",123456+"");
//			SendSmsResponse response = SmsUtil.sendOffSms("18173116167");
			System.out.println("短信接口返回的数据----------------");
			System.out.println("Code=" + response.getCode());
			System.out.println("Message=" + response.getMessage());
			System.out.println("RequestId=" + response.getRequestId());
			System.out.println("BizId=" + response.getBizId());
		} catch (ClientException e) {
			e.printStackTrace();
		}*/
	}
	
	@Test
	public void testPay(){  
		try {
        	
        	String url = "http://online.thomasbk.com/appjiekou/login_ajax.asp";
        	Map<String, String> params = new HashMap<>();
        	params.put("Login_Name", "120200004");
        	params.put("Login_Pass", "120200004");
        	
        	Map<String, Object> map = new HashMap<>();
        	map.put("params", params);
        	/*String body = JSONObject.toJSONString(map);
        	System.out.println("---- "+body);*/
//            String resultStr = HttpUtil.post(url, body);
        	Map<String, String> headers = new HashMap<>();
        	headers.put("service", "front.jsapi");
        	headers.put("version", "1.0");
        	headers.put("partner_id", "");
        	headers.put("core_merchant_no", "");
        	headers.put("sign_type", "MD5");
        	headers.put("input_charset", "UTF-8");
        	
        	String signstr = CollectionsUtil.map2StrSort(headers);
        	
//        	headers.put("sign", "");
            String resultStr = HttpUtil.postForm(url, params, headers, null, null);
            System.out.println("========================="+resultStr);
            
            /*JSONObject resultRecord = Record.parseObject(resultStr);
			System.out.println(resultRecord.toJSONString());*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
