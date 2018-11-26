package com.wangtiansoft.KingDarts.modules.weixin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.SystemOutLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.SnsApi;
import com.jfinal.weixin.sdk.api.UserApi;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.core.utils.AuthUtil;
import com.wangtiansoft.KingDarts.modules.user.service.NewUserGiveBalanceService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.modules.weixin.utils.WeiXinUtils;
import com.wangtiansoft.KingDarts.persistence.entity.NewUserGiveBalance;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Controller
@RequestMapping("/wx/oauth")
public class WeiXinOauthController  extends BaseController{

	private final static Logger logger = LoggerFactory.getLogger(WeiXinOauthController.class);

	@Resource
	private UserService userService;
	
	@Resource
	private NewUserGiveBalanceService newUserGiveBalanceService;

	/**
	 * 微信公众号授权跳转
	 * @param trade_no 游戏订单编号或者设备编号
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("")
	public String index(String trade_no,HttpServletResponse response) throws Exception{
		String url = WeiXinUtils.getOauthPageUrl("/wx/oauth/idx", trade_no, "snsapi_userinfo");
		return "redirect:"+url;
	}

	/**
	 * 微信公众号网页授权登录
	 * @param code
	 * @param state
	 * @return
	 */
	@RequestMapping("idx")
	public String idx(final String code,final String state) {
		int  subscribe=0;
		String resurl = "redirect:https://"+request.getServerName()+"/wx/fltpay/";
		try {
			String openid= "";
			String token= "";
			String appId= PropKit.get("appId");
			request.getSession().setAttribute("order_no", null);//清空session游戏订单编号
			request.getSession().setAttribute("pay_equno", null);//清空session设备编号
			request.getSession().setAttribute("isNewUser", null);//情况sessino新用户标识
			if(StringUtils.isNotEmpty(state)){
				if(state.startsWith("equno_")){//通过扫描设备二维码进入
					request.getSession().setAttribute("pay_equno", state.replace("equno_", ""));
				}else{//通过扫描游戏订单二维码进入
					request.getSession().setAttribute("order_no", state);
				}
			}
			if(StringUtils.isEmpty(code)){
				//测试用
//				openid = "oIYP-0hqjxch5RE0acPcBWTISK4Y";
				//参数错误
				request.setAttribute("errorMsg", "参数错误");
				return "/wx/recharge";
			}else{
				String secret= PropKit.get("appSecret");
				//通过code换取网页授权access_token
				SnsAccessToken snsAccessToken=SnsAccessTokenApi.getSnsAccessToken(appId,secret,code);
				token= snsAccessToken.getAccessToken();
				openid= snsAccessToken.getOpenid();
			}
			request.getSession().setAttribute(Constants.session_openid, openid);
			
			
			//判断是否是存在原系统
			Map hisUser = userService.getHisUserByOpenid(openid);
			//根据openid查询用户
			UserResult ur = userService.getUserByOpenid(openid);
			
			if(hisUser!=null&&(hisUser.get("unionid")==null||"".equals(hisUser.get("unionid").toString()))){
				//需要同步历史用户记录
				addOrUpdateUser(appId, token, openid, ur!=null?ur.getId():null, hisUser);
				
			}else{
				if(ur==null){
					//需要新增用户
					addOrUpdateUser(appId, token, openid, ur!=null?ur.getId():null, null);
				}else{
					AuthUtil.setLoginMember(request, ur);
					return resurl;
				}
			}
			
			AuthUtil.setLoginMember(request, userService.getUserByOpenid(openid));
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			request.setAttribute("errorMsg", "获取微信用户用户信息失败");
			
		}
		return resurl;
	}
	
	/**
	 * 微信公众号新增或更新用户
	 * @param appId
	 * @param token
	 * @param openid
	 * @param userId
	 * @param hisUser
	 */
	private void addOrUpdateUser(String appId,String token,String openid,Integer userId,Map hisUser){
		//拉取用户信息(需scope为 snsapi_userinfo)
		com.jfinal.weixin.sdk.api.ApiResult apiResult=SnsApi.getUserInfo(token, openid);
		logger.warn("getUserInfo:"+apiResult.getJson());
		if (apiResult.isSucceed()) {
			JSONObject jsonObject=JSON.parseObject(apiResult.getJson());
			String nickName=jsonObject.getString("nickname");
			//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
			int gender=jsonObject.getIntValue("sex");
			String city=jsonObject.getString("city");//城市
			String province=jsonObject.getString("province");//省份
			String country=jsonObject.getString("country");//国家
			String headimgurl=jsonObject.getString("headimgurl");
			String unionid=jsonObject.getString("unionid");
			//获取用户信息判断是否关注
			/*com.jfinal.weixin.sdk.api.ApiResult userInfo = UserApi.getUserInfo(openid);
			logger.warn(JsonKit.toJson("is subsribe>>"+userInfo));
			if (userInfo.isSucceed()) {
				String userStr = userInfo.toString();
//				subscribe=JSON.parseObject(userStr).getIntValue("subscribe");
			}*/
			
			//更新hisUser.unionid
			if(hisUser!=null){
				userService.updateHisUser(openid, unionid);
			}
			//保存或更新用户
			userService.addOrUpdateFromWeiXin(appId,openid, WeiXinUtils.filterWeixinEmoji(nickName), unionid, headimgurl, country, city, province, gender, userId,hisUser);
			
			if(userId==null){
				//查询新用户赠送游戏点规则
				Example example=new Example(NewUserGiveBalance.class);
	            Criteria cr= example.createCriteria();
	            cr.andEqualTo("is_publish", 1);
	            cr.andEqualTo("isvalid", 1);
	            NewUserGiveBalance newUserGiveBalance=newUserGiveBalanceService.findOneByExample(example);
	            
	            if(newUserGiveBalance!=null) {
	            	//将时间转换成Int类型再做比较
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
					String nowD = dateFormat.format(new Date());
					String starttime = dateFormat.format(newUserGiveBalance.getStart_time());
					String endtime = dateFormat.format(newUserGiveBalance.getEnd_time());
					int nowD1 = Integer.parseInt(nowD);//当前时间
					int start1 = Integer.parseInt(starttime);//奖励开始时间
					int end1 = Integer.parseInt(endtime);//奖励结束时间
					if(start1<=nowD1&&end1>=nowD1) {
						//当前时间在奖励时间内
						request.getSession().setAttribute("give_game_balance", newUserGiveBalance.getGive_game_balance());
						request.getSession().setAttribute("isNewUser", "1");
					}
	            }
			}
		}else{
			//注册失败
			request.setAttribute("errorMsg", "注册失败");
		}
	}

	
	
	
	/**
	 * 获取页面签名算法（未使用）
	 * @param url
	 * @return
	 */
	@RequestMapping("/sign")
	public@ResponseBody ApiResult sign(final String url) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				if(StringUtils.isEmpty(url)){
					throw new AppRuntimeException(Constants.kCode_Null,"url is null");
				}
				Map<String,String> map = WeiXinUtils.jssdk(url);
				System.out.println(map);
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 跳转到授权欢迎页（小程序首次登录使用）
	 * @param trade_no
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/w")
	public String indexw(String trade_no,HttpServletResponse response) throws Exception{
		String url = WeiXinUtils.getOauthPageUrl("/wx/oauth/info", trade_no, "snsapi_base");
		return "redirect:"+url;
	}

	/**
	 * 欢迎页面（小程序首次登录使用）
	 * @param code
	 * @param state
	 * @param openId
	 * @return
	 */
	@RequestMapping("info")
	public String info(final String code,final String state,String openId) {
		String resurl = "/wx/welcome";
		try {
			String openid= openId;
			String token= "";
			String appId= PropKit.get("appId");
			if(StringUtils.isEmpty(code)){
				if(StringUtils.isEmpty(openId)){
					//参数错误
					request.setAttribute("errorMsg", "参数错误");
					return resurl;
				}
			}else{
				String secret= PropKit.get("appSecret");
				//通过code换取网页授权access_token
				SnsAccessToken snsAccessToken=SnsAccessTokenApi.getSnsAccessToken(appId,secret,code);
				token= snsAccessToken.getAccessToken();
				openid= snsAccessToken.getOpenid();
			}
			request.getSession().setAttribute(Constants.session_openid, openid);
			
			//根据openid查询用户
			UserResult ur = userService.getUserByOpenid(openid);
			if(ur!=null&&StringUtils.isNotEmpty(ur.getUnionid())){
				//用户存在，而且已经获得用户Unionid
				AuthUtil.setLoginMember(request, ur);
				return resurl;
			}else{
				logger.info("新用户openid:"+openid);
			}
			
			//拉取用户信息(需scope为 snsapi_userinfo)
			com.jfinal.weixin.sdk.api.ApiResult apiResult=SnsApi.getUserInfo(token, openid);
//			com.jfinal.weixin.sdk.api.ApiResult apiResult = UserApi.getUserInfo(openid);
			logger.warn("getUserInfo:"+apiResult.getJson());
			if (apiResult.isSucceed()) {
				JSONObject jsonObject=JSON.parseObject(apiResult.getJson());
				String nickName=jsonObject.getString("nickname");
				//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
				int gender=jsonObject.getIntValue("sex");
				String city=jsonObject.getString("city");//城市
				String province=jsonObject.getString("province");//省份
				String country=jsonObject.getString("country");//国家
				String headimgurl=jsonObject.getString("headimgurl");
				String unionid=jsonObject.getString("unionid");
				//获取用户信息判断是否关注
//				com.jfinal.weixin.sdk.api.ApiResult userInfo = UserApi.getUserInfo(openid);
//				logger.warn(JsonKit.toJson("is subsribe>>"+userInfo));
//				if (userInfo.isSucceed()) {
//					String userStr = userInfo.toString();
//					subscribe=JSON.parseObject(userStr).getIntValue("subscribe");
//				}
				if(StringUtils.isEmpty(unionid)){
					request.setAttribute("errorMsg", "注册失败");
					return resurl;
				}
				
				//判断是否是存在原系统
				Map hisUser = userService.getHisUserByOpenid(openid);
				
				//保存或更新用户
				userService.addOrUpdateFromWeiXin(appId,openid, WeiXinUtils.filterWeixinEmoji(nickName), unionid, headimgurl, country, city, province, gender, ur!=null?ur.getId():null,hisUser);
				
			}else{
				//注册失败
				request.setAttribute("errorMsg", "注册失败");
			}
			
			AuthUtil.setLoginMember(request, userService.getUserByOpenid(openid));

			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			request.setAttribute("errorMsg", "系统错误，请联系管理员");
			
		}
		return resurl;
		
//		com.jfinal.weixin.sdk.api.ApiResult apiResult = UserApi.getUserInfo(openId);
//		System.out.println(apiResult.getJson());
//		request.setAttribute("str",apiResult.getJson());
//		return "/wx/welcome";
	}

	/**
	 * 测试用
	 */
	@RequestMapping("/t")
	public String indext(String t,HttpServletResponse response) throws Exception{
		String url = WeiXinUtils.getOauthPageUrl("/wx/oauth/t/info", t, "snsapi_base");
		return "redirect:"+url;
	}
	/**
	 * 欢迎页面（小程序首次登录使用）
	 * @param code
	 * @param state
	 * @param openId
	 * @return
	 */
	@RequestMapping("/t/info")
	public String tinfo(final String code,final String state,String openId) {
		String resurl = "/wx/test";
		try {
			
			//获取微信页面授权
			StringBuffer requestUrl = request.getRequestURL();
			String queryString = request.getQueryString();
			if(queryString!=null){
				requestUrl.append("?").append(queryString);
			}
			System.out.println(requestUrl.toString());
			Map<String,String> map = WeiXinUtils.jssdk(requestUrl.toString());
			for (Map.Entry<String, String> entry : map.entrySet()) { 
				request.setAttribute(entry.getKey(), entry.getValue());
			}
			
			String openid= openId;
			String token= "";
			String appId= PropKit.get("appId");
			if(StringUtils.isEmpty(code)){
				if(StringUtils.isEmpty(openId)){
					//参数错误
					request.setAttribute("errorMsg", "参数错误");
					return resurl;
				}
			}else{
				String secret= PropKit.get("appSecret");
				//通过code换取网页授权access_token
				SnsAccessToken snsAccessToken=SnsAccessTokenApi.getSnsAccessToken(appId,secret,code);
				token= snsAccessToken.getAccessToken();
				openid= snsAccessToken.getOpenid();
			}
			request.getSession().setAttribute(Constants.session_openid, openid);
			
			//根据openid查询用户
			UserResult ur = userService.getUserByOpenid(openid);
			if(ur!=null&&StringUtils.isNotEmpty(ur.getUnionid())){
				//用户存在，而且已经获得用户Unionid
				AuthUtil.setLoginMember(request, ur);
				return resurl;
			}else{
				logger.info("新用户openid:"+openid);
			}
			
			//拉取用户信息(需scope为 snsapi_userinfo)
			com.jfinal.weixin.sdk.api.ApiResult apiResult=SnsApi.getUserInfo(token, openid);
//			com.jfinal.weixin.sdk.api.ApiResult apiResult = UserApi.getUserInfo(openid);
			logger.warn("getUserInfo:"+apiResult.getJson());
			if (apiResult.isSucceed()) {
				JSONObject jsonObject=JSON.parseObject(apiResult.getJson());
				String nickName=jsonObject.getString("nickname");
				//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
				int gender=jsonObject.getIntValue("sex");
				String city=jsonObject.getString("city");//城市
				String province=jsonObject.getString("province");//省份
				String country=jsonObject.getString("country");//国家
				String headimgurl=jsonObject.getString("headimgurl");
				String unionid=jsonObject.getString("unionid");
				//获取用户信息判断是否关注
//				com.jfinal.weixin.sdk.api.ApiResult userInfo = UserApi.getUserInfo(openid);
//				logger.warn(JsonKit.toJson("is subsribe>>"+userInfo));
//				if (userInfo.isSucceed()) {
//					String userStr = userInfo.toString();
//					subscribe=JSON.parseObject(userStr).getIntValue("subscribe");
//				}
				if(StringUtils.isEmpty(unionid)){
					request.setAttribute("errorMsg", "注册失败");
					return resurl;
				}
				
				//判断是否是存在原系统
				Map hisUser = userService.getHisUserByOpenid(openid);
				
				//保存或更新用户
				userService.addOrUpdateFromWeiXin(appId,openid, WeiXinUtils.filterWeixinEmoji(nickName), unionid, headimgurl, country, city, province, gender, ur!=null?ur.getId():null,hisUser);
				
			}else{
				//注册失败
				request.setAttribute("errorMsg", "注册失败");
			}
			
			AuthUtil.setLoginMember(request, userService.getUserByOpenid(openid));

			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			request.setAttribute("errorMsg", "系统错误，请联系管理员");
			
		}
		return resurl;
		
	}
}
