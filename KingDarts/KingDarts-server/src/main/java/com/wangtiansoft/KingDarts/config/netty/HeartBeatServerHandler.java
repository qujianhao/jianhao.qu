package com.wangtiansoft.KingDarts.config.netty;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.modules.client.service.EquipClientService;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;  
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;  

@Component
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {  
  
	private static final Logger blog = LoggerFactory.getLogger(HeartBeatServerHandler.class);
	
	private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat",  
            CharsetUtil.UTF_8));  
      
    private static final int TRY_TIMES = 3;  
      
    private int loss_connect_time = 0;  
    
    @Autowired
	private RedisTemplate redisTemplate;
    
    
    @Override  
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {  
        if (evt instanceof IdleStateEvent) {  
            IdleStateEvent event = (IdleStateEvent) evt;  
            if (event.state() == IdleState.READER_IDLE) {  
                loss_connect_time++;  
//                System.out.println("5 秒没有接收到客户端的信息了");  
                if (loss_connect_time > 2) {  
                	String id = ctx.channel().id().asShortText();
                	ctx.channel().close();
                	Object equno = "";
                	if(id!=null){
                		equno = redisTemplate.opsForHash().get(Constants.channel_equno, id);
                	}
                	blog.info("关闭equno="+equno+"不活跃的channel");
                      
                }  
            }  
        } else {  
            super.userEventTriggered(ctx, evt);  
        }  
    }  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        System.out.println("server channelRead..");  
        System.out.println(ctx.channel().remoteAddress() + "->Server :" + msg.toString());  
    }  
  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        cause.printStackTrace();  
        ctx.close();  
    }  
  
}  
