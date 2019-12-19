package com.wangtiansoft.KingDarts.modules.api.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.odps.udf.CodecCheck.A;
import com.jfinal.kit.PropKit;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.config.netty.ChannelService;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.bean.OnlineChannel;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.api.token.Token;
import com.wangtiansoft.KingDarts.modules.api.token.TokenManager;
import com.wangtiansoft.KingDarts.modules.coupon.service.CouponService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquAuthService;
import com.wangtiansoft.KingDarts.modules.game.service.GameOrderService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.modules.weixin.utils.AppAccessKey;
import com.wangtiansoft.KingDarts.modules.weixin.utils.AppAccessTokenApi;
import com.wangtiansoft.KingDarts.persistence.entity.Coupon;
import com.wangtiansoft.KingDarts.persistence.entity.EquAuth;
import com.wangtiansoft.KingDarts.results.core.NineGameResult;
import com.wangtiansoft.KingDarts.results.core.UserOpenidResult;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@CrossOrigin
@Controller
@RequestMapping("/api/user/")
public class UserAPIController  extends BaseController{

	private final static Logger logger = LoggerFactory.getLogger(UserAPIController.class);

	@Autowired
    private TokenManager tokenManager;
	@Resource
    private UserService userService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private GameOrderService gameOrderService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private EquAuthService equAuthService;
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



	@RequestMapping("/transmit")  //游戏新老用户点击 获取积分
	public @ResponseBody ApiResult transmit(final String userId,final String newUserId,final String token) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			
			

			@Override
			public Object execute(String uuid) throws Exception {
				System.out.println("进入方法"+"transmit");
				
                userService.transmit(userId,newUserId,token);
            	System.out.println("进入方法"+userId);
                
				Map<String,Object> map = new HashMap<>();
				return map;
			}
		});
		return result;
	}

	@RequestMapping("/video")  //看视频加积分
	public @ResponseBody ApiResult video(final String userId) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String uuid) throws Exception {
				
				System.out.println("进入方法2222"+"video");
				userService.video(userId);
				Map<String,Object> map = new HashMap<>();
				return map;
			}
		});
		return result;
	}






	/**
	 * 绑定老系统用户(未使用)
	 * 
	 * @param openid
	 * @param unionId
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/APPLoginTest")
	public @ResponseBody String APPLoginTest( HttpServletRequest request,String map2) throws IOException {
	
		System.out.println(String.format("[REQUEST_INFO]请求方法[%s],请求路径[%s],请求参数[%s]",request.getMethod(),request.getServletPath(), JSON.toJSONString(request.getParameterMap())));

		System.out.println(	"请求参数"+	JSON.toJSONString(request.getParameterMap()));


		String jsonStr=JSON.toJSONString(request.getParameter("map2")); 
//		String parameter = request.getParameter("map2");
		
		
		System.out.println(jsonStr.substring(1,jsonStr.length()-1).replaceAll("\\\\",""));

		net.sf.json.JSONObject json_test = net.sf.json.JSONObject.fromObject(jsonStr.substring(1,jsonStr.length()-1).replaceAll("\\\\",""));

		System.out.println("json_test"	+json_test);

	            net.sf.json.JSONObject obj2 = json_test.getJSONObject("data");  
	           
	            String equipment_number = obj2.getString("equipment_number");  

	            System.out.println("equipment_number"	+equipment_number);
	            


		 String equno=equipment_number;
		
		
		
		
//		String equno = "K3160100250";
		Map map = new HashMap();
		map.put("status", json_test.getString("status"));
		map.put("message", json_test.getString("message"));
		map.put("type", "thirdLogin");
		Map data1 = new HashMap();
		data1.put("nickName", obj2.getString("nickName"));
		data1.put("avatar", obj2.getString("avatar"));
		data1.put("companyNmae", obj2.getString("companyNmae"));
		data1.put("uid", obj2.getString("uid"));
		data1.put("equipment_number", equno);
		map.put("data", data1);
//		
		
  

		SocketChannel targetChannel = getSocketChannel(equno.toString());

		targetChannel.writeAndFlush(JSON.toJSONString(map) + "\n");

//		targetChannel.writeAndFlush(JSON.toJSONString(request.getParameterMap()) + "\n");


		Object obj6 = redisTemplate.opsForHash().get(Constants.online_channel, equno);
		if (obj6 == null) {
			throw new AppRuntimeException("设备登录状态错误");
		}
		OnlineChannel onlineChannel = (OnlineChannel) obj6;
		String cid = onlineChannel.getClientId();
		SocketChannel channel = channelService.getGatewayChannel(cid);
		System.out.println(channel);
		return JSON.toJSONString(map);
	}
	
	
	

	public SocketChannel getSocketChannel(String equno) {
		Object obj = redisTemplate.opsForHash().get(Constants.online_channel, equno);
		System.out.println(1111);
		System.out.println("getSocketChannel"+obj);
		if (obj == null) {
			throw new AppRuntimeException("设备登录状态错误21313");
		}
		OnlineChannel onlineChannel = (OnlineChannel) obj;
		String cid = onlineChannel.getClientId();
		System.out.println("cid"+cid);
		return channelService.getGatewayChannel(cid);
	}

	/**
	 * 绑定老系统用户(未使用)
	 * 
	 * @param openid
	 * @param unionId
	 * @return
	 */
//	@RequestMapping("/ngplacing")
//	public @ResponseBody Map ngplacing(String map2) {
//		// AppResult result = this.buildMobileAuthAjaxResponseDD(new
//		// IWebAuthResponseHandler() {
//		System.out.println(map2);
//
//		ChannelHandlerContext ctx = null;
//		List<NineGameResult> list = new ArrayList<NineGameResult>();
//		System.out.println("sadadsa" + list);
//		List<NineGameResult> list1 = new ArrayList<NineGameResult>();
//		// NineGameResult list2=new NineGameResult();
//		System.out.println(list);
//		// System.out.println(list.toString());
////		for (int i = 0; i < list.size(); i++) {
////			NineGameResult list2 = new NineGameResult();
////			list2.setHeadimgurl(list.get(i).getHeadimgurl());
////			list2.setPlayer_score(list.get(i).getPlayer_score());
////			list2.setUsername(list.get(i).getUsername());
////			 list2.setPlayer_order(i+1);
////			
////			 list2.setPlayer_orderRate(i+1+"/"+list.size());
////			list1.add(list2);
////
////		}
//		// list1.add(list2);
//		System.out.println(list1);
//		// System.out.println(list);
//		Map<String, Object> map = new HashMap<>();
//		map.put("placing", list1);
//
//		return map;
//	}
	
	/**
	 * 获取云川十一之后分数排行榜前四十名的信息
	 * @return
	 */
	@RequestMapping("/yunchuanrank")
	@ResponseBody
	public ApiResult<JSONArray> getYunChuanRank() {
		ApiResult<JSONArray> result = new ApiResult<JSONArray>(); 
		JSONArray array = new JSONArray();
		try {
			result.setCode("0");
			array = userService.getYunChuanRank("10019257", "2019-10-01");
			result.setData(array);
			result.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("1");
			result.setMsg("fail");
			
			
		}
		
		return result;	
	}
	
	/**
	   *      根据机器编码获取俱乐部积分排行
	 * @param equno
	 * @return
	 */
	@RequestMapping("/clubRank")
	@ResponseBody
	public ApiResult<JSONArray> getClubRank(final String equno) {
		ApiResult<JSONArray> result = new ApiResult<JSONArray>(); 
		JSONArray array = new JSONArray();
		try {
			
			// 获取该机器属于哪个俱乐部
			EquAuth equAuth = equAuthService.getEquAuthByNo(equno);
			Date time = equAuth.getAuth_time();
			Timestamp timestamp =new Timestamp(time.getTime());
			String satrtTimeStr = timestamp+"";
			String startTime = satrtTimeStr.substring(0, 10);
			array = userService.getYunChuanRank(equAuth.getAuth_no(), startTime);
			result.setCode("0");
			result.setData(array);
			result.setMsg("success");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result.setCode("1");
			result.setMsg("fail");

		}
		
		return result;	
	}
	
	
	
	@Transactional
	@RequestMapping(value="useCouponTest", method = RequestMethod.GET)
	public @ResponseBody String  useCoupon(final String couponno) {
				
					String uuid = "4613a2ec5b5d4c5093e70385b03bd0eb";
					Map<String,Object> map = new HashMap<>();
					Example example2=new Example(Coupon.class);
			        Criteria cr2= example2.createCriteria();
			        cr2.andEqualTo("couponno", couponno);
			        Coupon coupon = couponService.findOneByExample(example2);
			        Boolean canUseCoupon = couponService.canUseCoupon(uuid, coupon.getId()+"");
			        //判断优惠券是否被使用及优惠券是否存在
			        if (coupon==null || !canUseCoupon) {
			        	throw new AppRuntimeException("优惠券无效或已使用过该优惠券！");
					}
			        couponService.recordUse(uuid, coupon.getId()+"");
					Integer count = couponService.useCouponByUser(couponno, uuid);
					map.put("count", count);
					return map.toString();						
	} 
	
	
}







	
	
	






