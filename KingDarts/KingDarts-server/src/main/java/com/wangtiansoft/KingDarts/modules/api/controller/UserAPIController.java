package com.wangtiansoft.KingDarts.modules.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.api.token.Token;
import com.wangtiansoft.KingDarts.modules.api.token.TokenManager;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.modules.weixin.utils.AppAccessKey;
import com.wangtiansoft.KingDarts.modules.weixin.utils.AppAccessTokenApi;
import com.wangtiansoft.KingDarts.results.core.UserOpenidResult;
import com.wangtiansoft.KingDarts.results.core.UserResult;

@CrossOrigin
@Controller
@RequestMapping("/api/user/")
public class UserAPIController  extends BaseController{

	private final static Logger logger = LoggerFactory.getLogger(UserAPIController.class);

	@Autowired
    private TokenManager tokenManager;
	@Resource
    private UserService userService;

	/**
	 * 小程序登录
	 * @param code
	 * @return
	 */
	@RequestMapping("/login")
	public @ResponseBody ApiResult login(final String code,final String type) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				if(StringUtils.isEmpty(code)){
					throw new AppRuntimeException(Constants.kCode_Null,"code is null");
				}
				logger.info("小程序登录 code = "+code);

				String appId= PropKit.get("app1.appId");
				String secret= PropKit.get("app1.appSecret");
				if(Constants.merchant.equals(type)){
					appId= PropKit.get("app2.appId");
					secret= PropKit.get("app2.appSecret");
				}
				//通过code小程序登录者openId
				AppAccessKey appAccessKey = AppAccessTokenApi.getAppAccessKey(appId,secret,code);

				//查询用户是否存在，如果存在返回用户信息
				UserResult user = null;
				if(StringUtils.isNotEmpty(appAccessKey.getUnionid())){
					user = userService.getUserByUnionid(appAccessKey.getUnionid());
				}else if(StringUtils.isNotEmpty(appAccessKey.getOpenid())){
					user = userService.getUserByOpenid(appAccessKey.getOpenid());
				}
				
				Map<String,Object> map = new HashMap<>();
				if(user==null){//用户未注册，需要调用getUserInfo
					map.put("hasUser", "false");
				}else{
					userService.addOrUpdateUserOpenId(user, appAccessKey.getOpenid(), appId);
					
					String wxappid = userService.hasWxOpenid(user);
					if(StringUtils.isNotEmpty(wxappid)){
						map.put("hasWx", true);
					}else{
						map.put("hasWx", false);
					}
					//生成token
					Token token = tokenManager.createToken(user.getUuid());
					map.put("token", token.getUserId()+"_"+token.getToken());
				}
				map.put("appAccessKey", appAccessKey);
				return map;
			}
		});
		return result;
	}

	/**
	 * 用户注册
	 * 解密用户敏感数据获取用户信息
	 * @param sessionKey 数据进行加密签名的密钥 
	 * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据 
	 * @param iv 加密算法的初始向量 
	 * @return
	 */
	@RequestMapping("/register")
	public @ResponseBody ApiResult register(final String encryptedData,final String sessionKey,final String iv) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				if(StringUtils.isEmpty(encryptedData)){
					throw new AppRuntimeException("encryptedData is null");
				}
				String result = AppAccessTokenApi.getUserInfo(encryptedData, sessionKey, iv);
				JSONObject userInfo = JSONObject.parseObject(result);
				if(userInfo.get("openId")==null){
					throw new AppRuntimeException("注册失败！");
				}
				
				String  openId = userInfo.get("openId").toString();
				UserResult ur = userService.getUserByOpenid(openId);
				if(ur!=null&&StringUtils.isNotEmpty(ur.getUnionid())){
					//用户存在，而且已经获得用户Unionid
					throw new AppRuntimeException("用户已注册！");
				}else{
					String nickName=userInfo.getString("nickName");
					//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
					int gender=userInfo.getIntValue("gender");
					String city=userInfo.getString("city");//城市
					String province=userInfo.getString("province");//省份
					String country=userInfo.getString("country");//国家
					String headimgurl=userInfo.getString("avatarUrl");
					String unionid=userInfo.getString("unionId");
					
					JSONObject watermark = (JSONObject)userInfo.get("watermark");
					String appid = watermark.getString("appid");
					
					Map hisUser = userService.getHisUserByUuid(unionid);
					
					userService.addOrUpdateFromWeiXin(appid,openId, nickName, unionid, headimgurl, country, city, province, gender, ur!=null?ur.getId():null,hisUser);
					
					UserResult user = userService.getUserByOpenid(openId);
					//生成token
					Map<String,Object> map = new HashMap<>();
					Token token = tokenManager.createToken(user.getUuid());
					map.put("token", token.getUserId()+"_"+token.getToken());
					map.put("userInfo", userInfo);
					return map;
				}
			}
		});
		return result;
	}
	
	/**
	 * 小程序登录测试
	 * @param code
	 * @return
	 */
	@RequestMapping("/login/test")
	public @ResponseBody ApiResult logintest(final String openid) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				if(StringUtils.isEmpty(openid)){
					throw new AppRuntimeException(Constants.kCode_Null,"openid is null");
				}
				
//				String unionid = "o4bNf1r7f8Gybj1--zotMEGtR5uI";
				//查询用户是否存在，如果存在返回用户信息
				UserResult user = userService.getUserByOpenid(openid);
//				UserResult user = userService.getUserByUnionid(unionid);
				
				Map<String,Object> map = new HashMap<>();
				if(user==null){//用户未注册，需要调用getUserInfo
					map.put("hasUser", "false");
				}else{
//					userService.addOrUpdateUserOpenId(user, appAccessKey.getOpenid(), appId);
					
					String wxappid = userService.hasWxOpenid(user);
					if(StringUtils.isNotEmpty(wxappid)){
						map.put("hasWx", true);
					}else{
						map.put("hasWx", false);
					}
					//生成token
					Token token = tokenManager.createToken(user.getUuid());
					map.put("token", token.getUserId()+"_"+token.getToken());
				}
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 绑定老系统用户(未使用)
	 * @param openid
	 * @param unionId
	 * @return
	 */
	@RequestMapping("/bindhisuser")
	public @ResponseBody ApiResult bindhisuser(final String openid,final String unionid) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String uuid) throws Exception {
				if(StringUtils.isNotEmpty(openid)
						&&StringUtils.isNotEmpty(unionid)){
					userService.updateHisUser(openid, unionid);
				}
				
				Map<String,Object> map = new HashMap<>();
				map.put("uuid", uuid);
				return map;
			}
		});
		return result;
	}
	
	

}
