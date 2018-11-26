package com.wangtiansoft.KingDarts.modules.weixin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.MenuApi;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.core.utils.AuthUtil;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.modules.weixin.utils.XMLParser;
import com.wangtiansoft.KingDarts.results.core.UserResult;

@Controller
@RequestMapping("/wx/api")
public class WeixinApiController  extends BaseController{

/*	@Resource
	private UserService userService;
	
	@RequestMapping("/synuser")
	public @ResponseBody ApiResult synuser(final String openid) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				if(StringUtils.isNotEmpty(openid)){
					return syn(openid);
				}
				
				
				return null;
			}
		});
		return result;
	}
	
	private String syn(String openid){
		UserResult ur = userService.getUserByOpenid(openid);
		if(ur!=null&&StringUtils.isNotEmpty(ur.getUnionid())){
			//用户存在，而且已经获得用户Unionid
			return "用户已经存在";
		}
		ApiConfig ac = new ApiConfig();
		ac.setAppId(PropKit.get("wx.appid"));
		AccessTokenApi.getAccessToken(ac);
		
		return "";
	}*/
	
	@RequestMapping("/getMenu")
	public @ResponseBody ApiResult getMenu() {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				return MenuApi.getMenu();
			}
		});
		return result;
	}
	
	@RequestMapping("/createMenu")
	public @ResponseBody ApiResult createMenu() {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				List<Map<String,String>> bnt1s = new ArrayList<>();
				
				Map<String,String> bnt11 = new HashMap<>();
				bnt11.put("type", "view");
				bnt11.put("name", "支付个人");
				bnt11.put("url", "http://test.yaoxunsoft.com/DartsWxLoginController/beginAuthorize?url=http://test.yaoxunsoft.com/wchat/vip/singlescan");
				bnt1s.add(bnt11);
				
				Map<String,String> bnt12 = new HashMap<>();
				bnt12.put("type", "view");
				bnt12.put("name", "支付多人");
				bnt12.put("url", "http://test.yaoxunsoft.com/DartsWxLoginController/beginAuthorize?url=http://test.yaoxunsoft.com/wchat/vip/morescan");
				bnt1s.add(bnt12);
				
				Map<String,String> bnt13 = new HashMap<>();
				bnt13.put("type", "view");
				bnt13.put("name", "微信充值");
				bnt13.put("url", "http://test.yaoxunsoft.com/DartsWxLoginController/beginAuthorize?url=http://test.yaoxunsoft.com/wchat/vip/cardprepaid/0/0");
				bnt1s.add(bnt13);
				
				Map<String,Object> bnt1 = new HashMap<>();
				bnt1.put("name", "扫码游戏");
				bnt1.put("sub_button", bnt1s);
				
				List<Map<String,String>> bnt2s = new ArrayList<>();
				
				Map<String,String> bnt21 = new HashMap<>();
				bnt21.put("type", "view");
				bnt21.put("name", "i镖风云榜");
				bnt21.put("url", "http://test.yaoxunsoft.com/DartsWxLoginController/beginAuthorize?url=http://test.yaoxunsoft.com/wchat/ranklist/person");
				bnt2s.add(bnt21);
				
				Map<String,String> bnt22 = new HashMap<>();
				bnt22.put("type", "view");
				bnt22.put("name", "i比赛");
				bnt22.put("url", "http://test.yaoxunsoft.com/DartsWxLoginController/beginAuthorize?url=http://test.yaoxunsoft.com/wchat/home/match");
				bnt2s.add(bnt22);
				
				Map<String,Object> bnt2 = new HashMap<>();
				bnt2.put("name", "i比赛");
				bnt2.put("sub_button", bnt2s);
				
				List<Map<String,String>> bnt3s = new ArrayList<>();
				
				Map<String,String> bnt31 = new HashMap<>();
				bnt31.put("type", "view");
				bnt31.put("name", "俱乐部");
				bnt31.put("url", "http://test.yaoxunsoft.com/DartsWxLoginController/beginAuthorize?url=http://test.yaoxunsoft.com/wchat/vip/club");
				bnt3s.add(bnt31);
				
				Map<String,String> bnt32 = new HashMap<>();
				bnt32.put("type", "view");
				bnt32.put("name", "商城");
				bnt32.put("url", "https://shop19091532.youzan.com/v2/feature/mjupn1vi?sf=wx_sm");
				bnt3s.add(bnt32);
				
				Map<String,String> bnt33 = new HashMap<>();
				bnt33.put("type", "view");
				bnt33.put("name", "会员卡");
				bnt33.put("url", "http://test.yaoxunsoft.com/DartsWxLoginController/beginAuthorize?url=http://test.yaoxunsoft.com/wchat/vip/cardlist");
				bnt3s.add(bnt33);
				
				Map<String,String> bnt34 = new HashMap<>();
				bnt34.put("type", "view");
				bnt34.put("name", "俱乐部登陆");
				bnt34.put("url", "http://test.yaoxunsoft.com/DartsWxLoginController/beginAuthorize?url=http://test.yaoxunsoft.com/wchat/vip/club/login");
				bnt3s.add(bnt34);
				
				Map<String,Object> bnt3 = new HashMap<>();
				bnt3.put("name", "i之家");
				bnt3.put("sub_button", bnt3s);
				

				List<Map<String,Object>> bnts = new ArrayList<>();
				bnts.add(bnt1);
				bnts.add(bnt2);
				bnts.add(bnt3);

				Map button = new HashMap();
				button.put("button", bnts);
				
//				return MenuApi.createMenu(JSONObject.toJSONString(button));
				return null;
			}
		});
		return result;
	}
	
	@RequestMapping("/message")
	public@ResponseBody String message(String signature,String timestamp,String nonce,String echostr){
		try {
			String retStr = IOUtils.toString(request.getInputStream(), "UTF-8");
			System.out.println(retStr);
			
			Map map = XMLParser.getMapFromXML(retStr);
			System.out.println(map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*if(SignUtil.checkSignature("vNzNcFhyM4PGY2yBrrIn1sKWBa", signature, timestamp, nonce)){
			System.out.println("true");
			return echostr;
		}*/
		return echostr;
	}
	
}
