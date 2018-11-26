package com.wangtiansoft.KingDarts.config.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import io.netty.channel.socket.SocketChannel;

@Service
public class ChannelService {

	private static Map<String, SocketChannel> map = new ConcurrentHashMap<>();
	
	private static Map<String, SocketChannel> activeChannel = new ConcurrentHashMap<>();
	
	private static boolean hasInit = false;

	/**
	 * 客户的登录
	 * @param id
	 * @param gateway_channel
	 */
	public void addGatewayChannel(String id, SocketChannel gateway_channel){
		map.put(id, gateway_channel);
	}

	public Map<String, SocketChannel> getChannels(){
		return map;
	}

	public SocketChannel getGatewayChannel(String id){
		return map.get(id);
	}

	public void removeGatewayChannel(String id){
		map.remove(id);
	}
	
	public boolean getHasInit(){
		return  hasInit;
	}
	public void setHasInit(){
		hasInit = true;
	}

	//未登录通道处理
	public void addActiveChannel(String id, SocketChannel gateway_channel){
		activeChannel.put(id, gateway_channel);
	}
	public SocketChannel getActiveChannel(String id){
		return activeChannel.get(id);
	}

	public void removeActiveChannel(String id){
		activeChannel.remove(id);
	}
}
