//package com.wangtiansoft.KingDarts.config.netty.common;
//
//import java.net.InetAddress;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import com.alibaba.fastjson.JSONObject;
//import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
//import com.wangtiansoft.KingDarts.config.netty.ChannelService;
//import com.wangtiansoft.KingDarts.config.netty.model.NettyMessage;
//import com.wangtiansoft.KingDarts.constants.Constants;
//
//import io.netty.channel.socket.SocketChannel;
//
//public class BaseService {
//
//	private static final Logger blog = LoggerFactory.getLogger(BaseService.class);
//	@Autowired
//	private RedisTemplate redisTemplate;
//	@Autowired
//	private ChannelService channelService;
//	
//	protected NettyMessage buildResponse(IResponseHandler invokeInterface) {
//		try {
//			Object data = invokeInterface.execute();
//			if(data!=null&&data.getClass().getName().contains("HashMap")){
//				Map m = (Map)data;
//				if(m.containsKey("msg")){
//					String msg = m.get("msg").toString();
//					m.remove("msg");
//					return NettyMessage.success(data,msg);
//				}
//			}
//			return NettyMessage.success(data);
//		} catch (AppRuntimeException e) {
//			blog.info(e.getMsg());
//			if(e.getCode()!=null&&!e.getCode().equals("1")){
//				return NettyMessage.fail(e.getCode(), e.getMsg());
//			}
//			return NettyMessage.fail(e.getMsg());
//		} catch (Exception e) {
//			blog.error(e.getMessage(),e);
//			return NettyMessage.fail("系统异常，请联系管理员!");
//		}
//	}
//	
//	protected NettyMessage buildAuthResponse(String id,IEResponseHandler invokeInterface) {
//		try {
//			Object equnoObj = redisTemplate.opsForHash().get(Constants.channel_equno, id);
//			if(equnoObj==null){
//				//记录未登录次数
//				int outTimeNum = 0;
//				Object num = redisTemplate.boundValueOps(Constants.channel_unlogin+id).get();
//				if(num!=null){
//					outTimeNum = (Integer)num;
//					System.out.println("timeout "+num);
//					if(outTimeNum - Constants.channel_unlogin_num > 0){
//						SocketChannel socketChannel = channelService.getActiveChannel(id);
//						if(socketChannel!=null){
//							socketChannel.close();
//							redisTemplate.delete(Constants.channel_unlogin+id);
//						}
//					}
//				}
//				outTimeNum++;
//				
//				redisTemplate.boundValueOps(Constants.channel_unlogin+id).set(outTimeNum, Constants.channel_unlogin_time, TimeUnit.SECONDS);
//				
//				throw new AppRuntimeException(Constants.kCode_SessionError,"通道未登录");
//			}
//			Object data = invokeInterface.execute(equnoObj.toString());
//			return NettyMessage.success(data);
//		} catch (AppRuntimeException e) {
//			blog.info(e.getMsg());
//			if(e.getCode()!=null&&!e.getCode().equals("1")){
//				return NettyMessage.fail(e.getCode(), e.getMsg());
//			}
//			return NettyMessage.fail(e.getMsg());
//		} catch (Exception e) {
//			blog.error(e.getMessage(),e);
//			return NettyMessage.fail("系统异常，请联系管理员!");
//		}
//	}
//
//	protected interface IResponseHandler {
//		Object execute() throws Exception;
//	}
//	protected interface IEResponseHandler {
//		Object execute(String equno) throws Exception;
//	}
//
//}


























package com.wangtiansoft.KingDarts.config.netty.common;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.config.netty.ChannelService;
import com.wangtiansoft.KingDarts.config.netty.model.NettyMessage;
import com.wangtiansoft.KingDarts.constants.Constants;

import io.netty.channel.socket.SocketChannel;

public class BaseService {

	private static final Logger blog = LoggerFactory.getLogger(BaseService.class);
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private ChannelService channelService;
	
	protected NettyMessage buildResponse(IResponseHandler invokeInterface) {
		try {
			Object data = invokeInterface.execute();
			if(data!=null&&data.getClass().getName().contains("HashMap")){
				Map m = (Map)data;
				if(m.containsKey("msg")){
					String msg = m.get("msg").toString();
					m.remove("msg");
					return NettyMessage.success(data,msg);
				}
			}
			return NettyMessage.success(data);
		} catch (AppRuntimeException e) {
			blog.info(e.getMsg());
			if(e.getCode()!=null&&!e.getCode().equals("1")){
				return NettyMessage.fail(e.getCode(), e.getMsg());
			}
			return NettyMessage.fail(e.getMsg());
		} catch (Exception e) {
			blog.error(e.getMessage(),e);
			return NettyMessage.fail("系统异常，请联系管理员!");
		}
	}
	
	protected NettyMessage buildAuthResponse(String id,IEResponseHandler invokeInterface) {
		try {
			Object equnoObj = redisTemplate.opsForHash().get(Constants.channel_equno, id);
			System.out.println("equnoObj"+equnoObj);
			
			if(equnoObj==null){
				//记录未登录次数
				int outTimeNum = 0;
				Object num = redisTemplate.boundValueOps(Constants.channel_unlogin+id).get();
				if(num!=null){
					outTimeNum = (Integer)num;
					System.out.println("timeout "+num);
					if(outTimeNum - Constants.channel_unlogin_num > 0){
						SocketChannel socketChannel = channelService.getActiveChannel(id);
						if(socketChannel!=null){
							socketChannel.close();
							redisTemplate.delete(Constants.channel_unlogin+id);
						}
					}
				}
				outTimeNum++;
				
				redisTemplate.boundValueOps(Constants.channel_unlogin+id).set(outTimeNum, Constants.channel_unlogin_time, TimeUnit.SECONDS);
				
				throw new AppRuntimeException(Constants.kCode_SessionError,"通道未登录121");
			}
			Object data = invokeInterface.execute(equnoObj.toString());
			return NettyMessage.success(data);
		} catch (AppRuntimeException e) {
			blog.info(e.getMsg());
			if(e.getCode()!=null&&!e.getCode().equals("1")){
				return NettyMessage.fail(e.getCode(), e.getMsg());
			}
			return NettyMessage.fail(e.getMsg());
		} catch (Exception e) {
			blog.error(e.getMessage(),e);
			return NettyMessage.fail("系统异常，请联系管理员!");
		}
	}

	protected interface IResponseHandler {
		Object execute() throws Exception;
	}
	protected interface IEResponseHandler {
		Object execute(String equno) throws Exception;
	}

}
