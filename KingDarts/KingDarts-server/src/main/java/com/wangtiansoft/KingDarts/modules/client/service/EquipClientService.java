package com.wangtiansoft.KingDarts.modules.client.service;

import java.util.Map;

import com.wangtiansoft.KingDarts.config.netty.model.NettyMessage;

import io.netty.channel.socket.SocketChannel;

public interface EquipClientService {

	/**
	 * 设备登录
	 * @param params  	equno设备编码
	 * @param channel	长连接通道
	 * @return
	 */
	NettyMessage equipLogin(Map params, SocketChannel channel);

	/**
	 * 设备退出
	 * @param channel	长连接通道
	 * @param channel	离线类型
	 * @return
	 */
	NettyMessage equipLogout(SocketChannel channel, int offType);

	/**
	 * 心跳检测
	 * @param channel
	 * @return
	 */
	NettyMessage pong(SocketChannel channel);

	/**
	 * 强制离线
	 * @param equno
	 */
	void offline(String equno);

	

}
