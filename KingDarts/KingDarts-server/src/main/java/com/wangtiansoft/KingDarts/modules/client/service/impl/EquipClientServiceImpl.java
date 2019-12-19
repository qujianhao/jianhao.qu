package com.wangtiansoft.KingDarts.modules.client.service.impl;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.RandomUtil;
import com.wangtiansoft.KingDarts.config.netty.ChannelService;
import com.wangtiansoft.KingDarts.config.netty.common.BaseService;
import com.wangtiansoft.KingDarts.config.netty.model.NettyMessage;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.bean.OnlineChannel;
import com.wangtiansoft.KingDarts.modules.client.service.EquipClientService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquLineService;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;
import com.wangtiansoft.KingDarts.persistence.entity.EquLine;
import com.wangtiansoft.KingDarts.results.core.EquInfoResult;

import io.netty.channel.socket.SocketChannel;

@Service("equipClientService")
public class EquipClientServiceImpl extends BaseService implements EquipClientService{

	private static final Logger log = LoggerFactory.getLogger(EquipClientServiceImpl.class);
			
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private EquInfoService equInfoService;

	@Override
	public NettyMessage equipLogin(final Map params,final SocketChannel channel){
		NettyMessage result = this.buildResponse(new IResponseHandler() {
			@Override
			public Object execute() throws Exception {
				String id= channel.id().asShortText();
				//校验参数,判断设备是否已经登录
				String equno = params.get("equno")!=null?params.get("equno").toString():"";
				
				System.out.println("登录equno"+equno);
				
				
				
				if(!channelService.getHasInit()){
					throw new AppRuntimeException(Constants.kCode_NotInit, "服务器初始化中");
				}
				
				Object obj = redisTemplate.opsForHash().get(Constants.online_channel, equno);
				if(obj!=null){
					log.info(equno+" 设备已登录");
					throw new AppRuntimeException(Constants.kCode_HasLogin, "设备已登录，不能重复登录");
				}
				//判断设备是否存在
				EquInfoResult equInfo = equInfoService.getEquInfoResultByNo(equno);
				if(equInfo==null) {
					throw new AppRuntimeException(Constants.kCode_NoEqu, "SN码不存在");
				}
				if(equInfo!=null&&equInfo.getEquAuth()==null) {
					throw new AppRuntimeException(Constants.kCode_NoEqu, "SN码不存在");
				}
				if(!equInfo.getEquAuth().getMerchant().toString().equals("1")){
					throw new AppRuntimeException(Constants.kCode_NoEqu, "设备未授权给俱乐部");
				}
				if(equInfo.getIsactivation()==null||"0".equals(equInfo.getIsactivation().toString())){
					//首次登录，标记激活状态
					EquInfo entity = new EquInfo();
					entity.setId(equInfo.getId());
					entity.setIsactivation(1);//已激活
					equInfoService.updateByIdSelective(entity);
				}

				//校验成功
				Map<String,String> map = new HashMap<>();
				System.out.println("来登录111");
				
				if(!channel.isShutdown()){
					System.out.println("来登录222");
					channelService.addGatewayChannel(id, channel);
					OnlineChannel onlineChannel = new OnlineChannel();
					onlineChannel.setClientId(id);
					onlineChannel.setEquno(equno);
					InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();
					onlineChannel.setIpAddress(address.getHostName());
					InetAddress addr = InetAddress.getLocalHost();
					onlineChannel.setServerIpAddress(addr.getHostAddress());
					onlineChannel.setLineTime(new Date());
					onlineChannel.setToken(RandomUtil.createUUID());
					redisTemplate.opsForHash().put(Constants.online_channel, equno, onlineChannel);//缓存设备登录状态信息
					redisTemplate.opsForHash().put(Constants.channel_equno, id, equno);//设备编码与通道ID关系

					//记录设备登录日志，可以采用缓存批量写入
					equInfoService.equLogin(equInfo.getId(), equno, onlineChannel.getIpAddress(), onlineChannel.getServerIpAddress());
					
					map.put("token", onlineChannel.getToken());
				}else{
					System.out.println("连接已经断开");
				}
				map.put("msg", "登录成功");
				return map;
			}
		});
		return result;
	}

	@Override
	public NettyMessage equipLogout(final SocketChannel channel,final int offType){
		NettyMessage result = this.buildResponse(new IResponseHandler() {
			@Override
			public Object execute() throws Exception {
				if(channel==null) return null;
				
				String id= channel.id().asShortText();
				Object equnoObj = redisTemplate.opsForHash().get(Constants.channel_equno, id);
				if(equnoObj==null){
					//此通过未登录，或者已经退出
					channelService.removeGatewayChannel(id);
					channel.close();
					return null;
				}
				String equno = equnoObj.toString();

				//记录设备退出日志
				equInfoService.equLogout(equno, offType);

				redisTemplate.opsForHash().delete(Constants.online_channel, equno);
				redisTemplate.opsForHash().delete(Constants.channel_equno, id);

				channelService.removeGatewayChannel(id);
				channel.close();
				Map<String,String> map = new HashMap<>();
				map.put("msg", "断开成功");
				return map;
			}
		});
		return result;
	}

	@Override
	public NettyMessage pong(final SocketChannel channel){
		final String id = channel.id().asShortText();
		NettyMessage result = this.buildAuthResponse(id,new IEResponseHandler() {
			@Override
			public Object execute(String equno) throws Exception {
				Object obj = redisTemplate.opsForHash().get(Constants.online_channel, equno);
				if(obj==null){
					
					throw new AppRuntimeException("设备登录状态错误6666");
				}
				OnlineChannel onlineChannel = (OnlineChannel)obj;
				onlineChannel.setHeartTime(new Date());
				redisTemplate.opsForHash().put(Constants.online_channel, equno, onlineChannel);//更新心跳时间
				return null;
			}
		});
		return result;
	}
	
	@Override
	public void offline(String equno){
		Object obj = redisTemplate.opsForHash().get(Constants.online_channel, equno);
		if(obj==null){
			//throw new AppRuntimeException("设备不在线");
			equInfoService.equLogout(equno, 4);
		}else{
			OnlineChannel onlineChannel = (OnlineChannel)obj;
			
			SocketChannel socketChannel = channelService.getGatewayChannel(onlineChannel.getClientId());
			if(socketChannel==null){
				redisTemplate.opsForHash().delete(Constants.online_channel, equno);
				equInfoService.equLogout(equno, 4);
			}else{
				this.equipLogout(socketChannel,4);//强制离线
			}
			
		}
		
	}
	
}
