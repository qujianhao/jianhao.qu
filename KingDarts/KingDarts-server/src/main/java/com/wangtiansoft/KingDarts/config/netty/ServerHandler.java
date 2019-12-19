package com.wangtiansoft.KingDarts.config.netty;

import io.netty.channel.ChannelHandler; 
import io.netty.channel.ChannelHandlerContext; 
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.wangtiansoft.KingDarts.common.utils.date.DateUtil;
import com.wangtiansoft.KingDarts.config.netty.constants.Constants;
import com.wangtiansoft.KingDarts.config.netty.model.NettyMessage;
import com.wangtiansoft.KingDarts.config.netty.utils.ConvertMessage;
import com.wangtiansoft.KingDarts.modules.client.service.EquipClientService;
import com.wangtiansoft.KingDarts.modules.client.service.GameClientService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
@Qualifier("serverHandler")
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String>{

	private static final Logger log = LoggerFactory.getLogger(ServerHandler.class); 
	
	ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

	@Autowired
	private EquipClientService equipClientService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private GameClientService gameClientService;

	@Override 
	public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		log.info(msg);
		if(!Constants.message_type_ping.equals(msg)){
			log.info(msg);
		}
		
		//消息格式转换
		NettyMessage message = ConvertMessage.jsonToMessage(msg);
		
		/*Map login = new HashMap();
        login.put("equno", "123456");
		message.setType("login");
		message.setData(login);*/
		//业务处理
		processing(ctx,msg,message);
		//释放资源
		ReferenceCountUtil.release(msg);  
	} 

	@Override 
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		SocketChannel channel = (SocketChannel)ctx.channel();
		log.info("RamoteAddress : " + channel.remoteAddress() + " active !");
		
		channelService.addActiveChannel(channel.id().asShortText(), channel);
		
		NettyMessage message = new NettyMessage();
		message.setType(Constants.message_type_active);
		message.setCode(Constants.code_Success);
		message.setMsg("连接成功");
		ctx.channel().writeAndFlush(JSON.toJSONString(message)+"\n");
	} 

	@Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace(); 
		ctx.close();
	} 

	@Override public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("\nChannel is disconnected"); 
		channelService.removeActiveChannel(ctx.channel().id().asShortText());
		equipClientService.equipLogout((SocketChannel)ctx.channel(),com.wangtiansoft.KingDarts.constants.Constants.off_type_kill);
		super.channelInactive(ctx);
	}

	/**
	 * 业务处理
	 * @param ctx
	 * @param msg
	 * @param message
	 */
	private void processing(final ChannelHandlerContext ctx,final String msg, final NettyMessage message){
		fixedThreadPool.execute(new Runnable() {  
			@Override
			public void run() {  
				NettyMessage result = null;
				if(Constants.message_type_login.equals(message.getType())){
					//登录
					result = equipClientService.equipLogin((Map)message.getData(),(SocketChannel)ctx.channel());
					result.setType(Constants.message_type_login);
					result.setRequestId(message.getRequestId());
					ctx.channel().writeAndFlush(JSON.toJSONString(result)+"\n"); 
					if(result.getCode().equals(Constants.code_Fail)){ 
						ctx.channel().close(); 
					}
				}else if(Constants.message_type_ping.equals(message.getType())){
					//心跳
					ctx.fireChannelActive();
					equipClientService.pong((SocketChannel)ctx.channel());
					ctx.channel().writeAndFlush(Constants.message_pong+"\n");
				}else if(Constants.message_type_logout.equals(message.getType())){
					//注销
					equipClientService.equipLogout((SocketChannel)ctx.channel(),com.wangtiansoft.KingDarts.constants.Constants.off_type_normal);
				}else if(Constants.message_type_order.equals(message.getType())){
					//创建订单
					result = gameClientService.gameOrder((Map)message.getData(),(SocketChannel)ctx.channel());
					result.setType(Constants.message_type_order);
					result.setRequestId(message.getRequestId());
					ctx.channel().writeAndFlush(JSON.toJSONString(result)+"\n"); 
					if(result.getData()!=null){
						pushGameOrder(ctx,(Map)result.getData());
					}
				}else if(Constants.message_type_orderstart.equals(message.getType())){
					//游戏开始
					result = gameClientService.gameOrderStart((Map)message.getData(),(SocketChannel)ctx.channel());
					result.setType(Constants.message_type_orderstart);
					result.setRequestId(message.getRequestId());
					ctx.channel().writeAndFlush(JSON.toJSONString(result)+"\n"); 
				}else if(Constants.message_type_hit.equals(message.getType())){
					//击打数据上传
					result = gameClientService.pushHitData((Map)message.getData(),(SocketChannel)ctx.channel());
					result.setType(Constants.message_type_hit);
					result.setRequestId(message.getRequestId());
					ctx.channel().writeAndFlush(JSON.toJSONString(result)+"\n"); 
				}else if(Constants.message_type_packets.equals(message.getType())){
					
				//红包上传二维码
					result = gameClientService.pushPacketsUrl((Map)message.getData(),(SocketChannel)ctx.channel());
					result.setType(Constants.message_type_packets);
					result.setRequestId(message.getRequestId());
					ctx.channel().writeAndFlush(JSON.toJSONString(result)+"\n"); 	
					
					
				}else{
					if(Constants.code_MessageError.equals(message.getCode())){
						log.info(msg+",消息格式错误");
						ctx.channel().writeAndFlush(JSON.toJSONString(message)+"\n"); 
//						ctx.channel().close();
					}else{
						log.info(msg+",消息类型或转换错误");
						ctx.channel().writeAndFlush(JSON.toJSONString(message)+"\n");
					}
				}
			}
		});
		
		
	}

	private void pushGameOrder(ChannelHandlerContext ctx,Map m){
			if(m.containsKey("order_type")&&m.containsKey("points")
					&&m.get("order_type").equals(com.wangtiansoft.KingDarts.constants.Constants.gorder_type_booked+"")){
				//如果订单是包机类型，已经扣款，需要通知客户端
				gameClientService.pushGameOrder(null,ctx.channel().id().asShortText()
						, m.get("order_no").toString(),com.wangtiansoft.KingDarts.constants.Constants.gorder_type_booked 
						, (Integer)m.get("points"),(String)m.get("balance"),(String)m.get("game_code"));
			}
	}
}

