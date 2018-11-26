package netty.client;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.wangtiansoft.KingDarts.config.netty.constants.Constants;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;  
import io.netty.channel.ChannelInitializer;  
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.SocketChannel;  
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;  

public class HelloClient {

	public void connect(String host, int port) throws Exception {  
		EventLoopGroup workerGroup = new NioEventLoopGroup();  

		try {  
			Bootstrap b = new Bootstrap();  
			b.group(workerGroup);  
			b.channel(NioSocketChannel.class);  
			b.option(ChannelOption.SO_KEEPALIVE, true);  
			b.handler(new ChannelInitializer<SocketChannel>() {  
				@Override  
				public void initChannel(SocketChannel ch) throws Exception {  
					ChannelPipeline p = ch.pipeline();  
					p.addLast("ping", new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));  
					//设置特殊分隔符
			        ByteBuf buf = Unpooled.copiedBuffer("\n".getBytes());
			        p.addLast(new DelimiterBasedFrameDecoder(1024, buf));
					p.addLast("decoder", new StringDecoder());  
					p.addLast("encoder", new StringEncoder());  
					p.addLast(new HeartBeatClientHandler());  
					  
				}  
			});  

			// Start the client.  
			ChannelFuture f = b.connect(host, port).sync();  

			// Wait until the connection is closed.  
			f.channel().closeFuture().sync();  
		} finally {  
			workerGroup.shutdownGracefully();  
		}  

	}  

	public static void main(String[] args) throws Exception {  
		HelloClient client = new HelloClient();  
//		client.connect("47.105.62.156", 8000);
		client.connect("127.0.0.1", 8000);
//		client.connect("118.190.217.193", 8000);
//		client.connect("ab.lovedarts.cn", 8000);
	}  

}
