package com.wangtiansoft.KingDarts.modules.weixin.utils;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.Base64;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.wangtiansoft.KingDarts.common.utils.HttpUtil;

public class AppAccessTokenApi extends SnsAccessTokenApi{

	private static String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";
	

	/***
	 * 获取微信小程序APP登录者openId
	 * @param code
	 * @return
	 */
	public static AppAccessKey getAppAccessKey(String appId,String secret,String jscode) {
		final String accessTokenUrl = url.replace("{appid}", appId).replace("{secret}", secret).replace("{code}", jscode);
		try {
			/*String app_appid = PropKit.get("appId");
			String app_appSecret = PropKit.get("appSecret");
			String str = HttpUtil.get( "https://api.weixin.qq.com/sns/jscode2session?appid=" + app_appid + "&secret="
			+ app_appSecret + "&js_code=" + jscode + "&grant_type=authorization_code");*/
			System.out.println(accessTokenUrl);
			String str = HttpUtil.get(accessTokenUrl);
			System.out.println(str);
			return new AppAccessKey(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getUserInfo(String encryptedData,String sessionKey,String iv){
		// 被加密的数据
		byte[] dataByte = Base64.decodeFast(encryptedData);
		// 加密秘钥
		byte[] keyByte = Base64.decodeFast(sessionKey);
		// 偏移量
		byte[] ivByte = Base64.decodeFast(iv);

		try {
			// 如果密钥不足16位，那么就补足.  这个if 中的内容很重要  
			int base = 16;  
			if (keyByte.length % base != 0) {  
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);  
				byte[] temp = new byte[groups * base];  
				Arrays.fill(temp, (byte) 0);  
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);  
				keyByte = temp;  
			}  
			// 初始化  
			Security.addProvider(new BouncyCastleProvider());  
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");  
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");  
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");  
			parameters.init(new IvParameterSpec(ivByte));  
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化  
			byte[] resultByte = cipher.doFinal(dataByte);  
			if (null != resultByte && resultByte.length > 0) {  
				String result = new String(resultByte, "UTF-8");  
				return result;  
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
