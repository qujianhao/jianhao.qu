package com.wangtiansoft.KingDarts.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.config.netty.ChannelService;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.utils.GameUtil;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;

@Component
public class DefCommandLineRunner implements CommandLineRunner{

	@Value("${weixin.wxconfig}")  
	private String wxconfig;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private EquInfoService equInfoService;
	@Autowired
	private RedisTemplate redisTemplate;
	
    @Override
    public void run(String... var1) throws Exception{
        System.out.println("=============初始化加载数据==============");
//        System.out.println(GameUtil.getGameName("s001g001"));
        PropKit.use(wxconfig);
    	ApiConfig ac = new ApiConfig();

		// 配置微信 API 相关常量
		ac.setToken(PropKit.get("token"));
		ac.setAppId(PropKit.get("appId"));
		ac.setAppSecret(PropKit.get("appSecret"));

		/**
		 * 是否对消息进行加密，对应于微信平台的消息加解密方式： 1：true进行加密且必须配置 encodingAesKey
		 * 2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
    	ApiConfigKit.putApiConfig(ac);
    	
    	/*if(StringUtils.isNotEmpty(PropKit.get("wx.appid"))){
			ApiConfig ac1 = new ApiConfig();
			ac1.setToken(PropKit.get("token"));
			ac1.setAppId(PropKit.get("wx.appid"));
			ac1.setAppSecret(PropKit.get("wx.appsecret"));
			
			ApiConfigKit.putApiConfig(ac1);
		}*/
    	
    	
    	//清空所有在线设备
    	equInfoService.offLine();
    	
    	redisTemplate.delete(Constants.online_channel);
    	redisTemplate.delete(Constants.channel_equno);
    	redisTemplate.delete(Constants.challenge_orderno);
    	redisTemplate.delete(Constants.challenge_equno_wait);
    	redisTemplate.delete(Constants.equno_net);
    	String[] netCodes = GameUtil.netCodes;
		for(int i = 0;i<netCodes.length;i++){
			String key = Constants.game_equno_wait+netCodes[i];
			redisTemplate.delete(key);
		}
		
		channelService.setHasInit();
    }
}
