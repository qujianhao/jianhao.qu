package com.wangtiansoft.KingDarts.modules.client.service;

import java.util.Map;

import com.wangtiansoft.KingDarts.config.netty.model.NettyMessage;

import io.netty.channel.socket.SocketChannel;

public interface GameClientService {

	/**
	 * 创建游戏订单
	 * @param params
	 * @param channel
	 * @return
	 */
	NettyMessage gameOrder(Map params, SocketChannel channel);

	/**
	 * 游戏开始
	 * @param params
	 * @param channel
	 * @return
	 */
	NettyMessage gameOrderStart(Map params, SocketChannel channel);

	/**
	 * 游戏订单支付成功推送
	 * @param equno
	 * @param clientId
	 * @param orderNo
	 * @param orderType
	 * @param points
	 * @return
	 */
	NettyMessage pushGameOrder(String equno, String clientId, String orderNo, Integer orderType, Integer points,
			String balance, String gameCode);

	/**
	 * 订单结果返回
	 * @param params
	 * @param channel
	 * @return
	 */
	NettyMessage gameOrderResults(Map params, SocketChannel channel);

	/**
	 * 网络对账匹配成功推送通知
	 * @param player1
	 * @param player2
	 * @param orderNo1
	 * @param orderNo2
	 * @return
	 */
	boolean gameStartPush(String player1, String player2, String orderNo1, String orderNo2);

	/**
	 * 网络对战飞镖数据
	 * @param params
	 * @param channel
	 * @return
	 */
	NettyMessage pushHitData(Map params, SocketChannel channel);

	
	/**
	 * 对战结束发红包连接
	 * @param params
	 * @param channel
	 * @return
	 */
	
	
	NettyMessage pushPacketsUrl(Map data, SocketChannel channel);



	

}

