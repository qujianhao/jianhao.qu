package weixin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.common.utils.HttpUtil;
import com.wangtiansoft.KingDarts.common.utils.RandomUtil;
import com.wangtiansoft.KingDarts.modules.weixin.utils.AppAccessTokenApi;


public class AppTest {

	private static String baseUrl = "http://127.0.0.1";
//	private static String baseUrl = "https://kingdarts.lovedarts.cn";
	
	private static String AUTHORIZATION = "x-access-token";
	private static String accessToken = "e9d4b4f0f1b848948f44bb83672aabf1_01a75cabfa8a430fb6079d8792dfdcc5";
	
	@Test
	public void login(){
		try {
			String url = baseUrl+"/api/user/login/test";
			Map<String, String> params = new HashMap<>();
//			params.put("openid", "oIYP-0hqjxch5RE0acPcBWTISK4Y");
//			params.put("openid", "oni_M4vTsxuhULAhe4HFliNBFNME");//Flavi正式
			params.put("openid", "olggc5OUk33jE3Y7lhqeovmnA6nU");//小程序openID
			Map<String, String> headers = new HashMap<>();
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*********************************我的*************************************************/
	@Test
	public void userPayList(){
		try {
			String url = baseUrl+"/api/userinfo/userPayList";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.get(url, params, headers);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void balanceList(){
		try {
			String url = baseUrl+"/api/userinfo/balanceList";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.get(url, params, headers);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*********************************小程序支付*************************************************/
	
	@Test
	public void consume(){
		try {
			String url = baseUrl+"/api/pay/consume";
			Map<String, String> params = new HashMap<>();
			params.put("orderNo", "JCK8V94P");
			params.put("type", "single");
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void rechargeRule(){
		try {
			String url = baseUrl+"/api/pay/rechargeRule";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.get(url, params, headers);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void payinfo(){
		try {
			String url = baseUrl+"/api/pay/payinfo";
			Map<String, String> params = new HashMap<>();
//			params.put("openid", "oIYP-0hqjxch5RE0acPcBWTISK4Y");
			params.put("openid", "olggc5OUk33jE3Y7lhqeovmnA6nU");
			params.put("totalMoney", "0.01");
			params.put("game_balance", "0.01");
			params.put("give_game_balance", "0.01");
//			params.put("order_no", "");
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*********************************首页*************************************************/
	@Test
	public void clubMapList(){
		try {
			String url = baseUrl+"/api/userindex/clubMapList";
			Map<String, String> params = new HashMap<>();
			params.put("longitude", "112.98991");
			params.put("latitude", "28.1127");
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.get(url, params, headers);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void userindex(){
		try {
			String url = baseUrl+"/api/userindex/index";
			Map<String, String> params = new HashMap<>();
			params.put("longitude", "113.023766");
			params.put("latitude", "28.190092");
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.get(url, params, headers);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*********************************约战*************************************************/
	
	@Test
	public void bookedlist(){
		try {
			String url = baseUrl+"/api/equ/booked/list";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void bookedcancel(){
		try {
			String url = baseUrl+"/api/equ/booked/cancel";
			Map<String, String> params = new HashMap<>();
			params.put("equno", "222222");
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void game_type(){
		try {
			String url = baseUrl+"/api/challenge/game_type";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void game(){
		try {
			String url = baseUrl+"/api/challenge/game";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 约战对象
	 */
	@Test
	public void target_info(){
		try {
			String url = baseUrl+"/api/challenge/target_info";
			Map<String, String> params = new HashMap<>();
			params.put("useraccount", "eb94822e8bc446f8b3648033b8c3fc21");
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 发起约战
	 */
	@Test
	public void challengesave(){
		try {
			String url = baseUrl+"/api/challenge/save";
			Map<String, String> params = new HashMap<>();
			params.put("receiver_useraccount", "2925804a8e284506af5db35f60050d7a");//约战对象用户id
			params.put("start_time", "2018-08-07 13:23:00");
			params.put("float_time", "15");
			params.put("game_type_id", "s001g006");
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 有人约战
	 */
	@Test
	public void my_receiver(){
		try {
			String url = baseUrl+"/api/challenge/my_receiver";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 约战状态设置
	 */
	@Test
	public void set_receive_status(){
		try {
			String url = baseUrl+"/api/challenge/set_receive_status";
			Map<String, String> params = new HashMap<>();
			params.put("id", "54");
			params.put("receiveStatus", "3");//应战
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 我的约战
	 */
	@Test
	public void my_all(){
		try {
			String url = baseUrl+"/api/challenge/my_all";
			Map<String, String> params = new HashMap<>();
			params.put("rows", "1");
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 爽约次数
	 */
	@Test
	public void miss_times(){
		try {
			String url = baseUrl+"/api/challenge/miss_times";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**********************************************************************************/
	
	/**
	 * 获取广告
	 */
	@Test
	public void advert(){
		try {
			String url = baseUrl+"/api/medal/advert";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
//			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取资源
	 */
	@Test
	public void getResource(){
		try {
			String url = baseUrl+"/api/resource/getResource";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
//			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.get(url);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取版本
	 */
	@Test
	public void getAppVersion(){
		try {
			String url = baseUrl+"/api/resource/getAppVersion";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
//			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.get(url);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**********************************************************************************/
	
	
	/**
	 * 俱乐部登录
	 */
	@Test
	public void wxlogin(){
		try {
			String url = baseUrl+"/api/wx/login";
			Map<String, String> params = new HashMap<>();
			params.put("openid", "oIYP-0hqjxch5RE0acPcBWTISK4Y");
			params.put("loginName", "10018910");
			params.put("password", "59635");
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 微信文件上传
	 */
	@Test
	public void fileupload(){
		try {
			String url = baseUrl+"/api/file/upload";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, "10018910_0ffb8eb43c5b486f9aa413464dba597b");
			
			List<String> files = new ArrayList<>();
			files.add("d:\\11.jpg");
			String resultStr = HttpUtil.postFileForm(url, params, headers, files, "files");
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**********************************************************************************/
	
	@Test
	public void test1(){
		try {
			String url = baseUrl+"/api/user/test";
			Map<String, String> params = new HashMap<>();
			Map<String, String> headers = new HashMap<>();
			headers.put(AUTHORIZATION, accessToken);
			String resultStr = HttpUtil.postForm(url, params, headers, null, null);
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void getUserInfo(){  
		try {
        	String encryptedData = "bgJ6SHo5t28Om1uM+n8cR+o993PTCwU2DGXlUVQ0kaotBL86Anztzw6IIZOtyFSKrtGKnRE19fjPZaL5b+xUQD9iGc3x+XrEVnK3kO+d3t+oJUPQThBHlbwmARBHVSjtLRmWbfvEPKFtaWEci07QmQ4OBgYCBdL2PeO6rp5vNtLDYDUsbxCR0vcPvh2cov0h5d7NTea3P0ygKkoiQ7eGuN66JxGinF5Lb84bkih148I9rFSsaTtLsqgLg23v1SgUyIRfGRuAcaNFUZwiA7OZXraSL7KWoh4BZaDc2VfYLvVY89g5lRJjN2iTEDWHlDktleH+bMsGGYtYbXOlfWvSTFEvAhdcRyphtZvJbrM/kTVysYN6/XSMrnYZ4DsA2luUNdFeuaNWqnj9QAkTqwKb3Z+IKukQDtCKP8EnVQgecA2uCHSBO+2/YIP2kIBkCvO16dajrDmOeQPSHNrxCcpXqQS528iLy6GsWHn26qzbp8/1O7Pih5n7afqsaXFxem6fa0UdYNaXJPBjgQ3yLQqRfg==";
        	String sessionKey = "438Y0+VyCJik7G3a2T1OJw==";
        	String iv = "rj99U8r6UHv4A1ENdCAVog==";
			System.out.println(AppAccessTokenApi.getUserInfo(encryptedData, sessionKey, iv));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
