package com.wangtiansoft.KingDarts.config.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer; 
import io.netty.channel.ChannelPipeline; 
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder; 
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Qualifier("springProtocolInitializer")
public class StringProtocolInitalizer extends ChannelInitializer<SocketChannel>  {

	@Autowired 
	StringDecoder stringDecoder; 

	@Autowired 
	StringEncoder stringEncoder; 

	@Autowired 
	ServerHandler serverHandler; 
	
	@Value("${netty.heartbeattime}") 
	private int heartBeatTime; 
	
	@Override 
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline(); 
		pipeline.addLast(new IdleStateHandler(heartBeatTime, 0, 0, TimeUnit.SECONDS)); 
		//设置特殊分隔符
        ByteBuf buf = Unpooled.copiedBuffer("\n".getBytes());
        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, buf));
		pipeline.addLast("decoder", stringDecoder); 
		pipeline.addLast("handler", serverHandler); 
		pipeline.addLast("encoder", stringEncoder); 
		pipeline.addLast(new HeartBeatServerHandler()); 
	} 

	public StringDecoder getStringDecoder() {
		return stringDecoder; 
	} 

	public void setStringDecoder(StringDecoder stringDecoder) {
		this.stringDecoder = stringDecoder; 
	} 

	public StringEncoder getStringEncoder() { 
		return stringEncoder; 
	} 

	public void setStringEncoder(StringEncoder stringEncoder) { 
		this.stringEncoder = stringEncoder; 
	}

	public ServerHandler getServerHandler() { 
		return serverHandler;
	} 

	public void setServerHandler(ServerHandler serverHandler) { 
		this.serverHandler = serverHandler;
	}

}
