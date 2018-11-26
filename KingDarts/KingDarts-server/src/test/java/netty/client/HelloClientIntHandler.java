package netty.client;

import io.netty.buffer.ByteBuf;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;  
  
public class HelloClientIntHandler extends ChannelInboundHandlerAdapter {  
    private static Logger logger = LoggerFactory.getLogger(HelloClientIntHandler.class);  
  
    private int i = 0;
    // 接收server端的消息，并打印出来  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
//        logger.info("HelloClientIntHandler.channelRead");  
        ByteBuf result = (ByteBuf) msg;  
        byte[] result1 = new byte[result.readableBytes()];  
        result.readBytes(result1);  
        String str = new String(result1);
        System.out.println("Server said:" + str);
        if(i==0){
        	Map map = new HashMap();
            map.put("type", "login");
            map.put("requestId", "111111");
            Map login = new HashMap();
            login.put("equno", "123456");
            map.put("data", login);
            String push = JSON.toJSONString(map);  
            System.out.println(push);
            ByteBuf encoded = ctx.alloc().buffer(4 * push.length());  
            encoded.writeBytes(push.getBytes());  
            ctx.write(encoded);  
            ctx.flush();  
            result.release();
        }else if(i==1){
    		Map map = new HashMap();
            map.put("type", "order");
            map.put("requestId", "2222");
            Map order = new HashMap();
            order.put("game_code", "s001g001");
            map.put("data", order);
            String push = JSON.toJSONString(map);  
            System.out.println(push);
            ByteBuf encoded = ctx.alloc().buffer(4 * push.length());  
            encoded.writeBytes(push.getBytes());  
            ctx.write(encoded);  
            ctx.flush();  
            result.release();
    	}
        
        i++;
        
        /*Map map = new HashMap();
        map.put("type", "logout");
        String logout = JSON.toJSONString(map);  
        ByteBuf encoded1 = ctx.alloc().buffer(4 * logout.length());  
        encoded1.writeBytes(logout.getBytes());  
        ctx.write(encoded1);  
        ctx.flush();  */
    }  
  
    // 连接成功后，向server发送消息  
    @Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
        logger.info("HelloClientIntHandler.channelActive");  
//        String msg = "你好";  
        /*Map map = new HashMap();
        map.put("type", "login");
        Map login = new HashMap();
        login.put("equno", "123456");
        map.put("data", login);
        String msg = JSON.toJSONString(map);  
        System.out.println(msg);
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());  
        encoded.writeBytes(msg.getBytes());  
        ctx.write(encoded);  
        ctx.flush();  */
    }  
}
