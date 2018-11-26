package netty.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.ByteBuf;  
import io.netty.buffer.Unpooled;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  
import io.netty.handler.timeout.IdleState;  
import io.netty.handler.timeout.IdleStateEvent;  
import io.netty.util.CharsetUtil;  
import io.netty.util.ReferenceCountUtil;  
  
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {  
  
      
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("ping\n",  
            CharsetUtil.UTF_8));  
      
    private static final int TRY_TIMES = 3;  
      
    private int currentTime = 0;  
    
    private int i = 0;
      
    @Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
//        System.out.println("激活时间是："+new Date());  
//        System.out.println("HeartBeatClientHandler channelActive");  
        ctx.fireChannelActive();  
    }  
  
    @Override  
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {  
//        System.out.println("停止时间是："+new Date());  
//        System.out.println("HeartBeatClientHandler channelInactive");  
    } 
  
    @Override  
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {  
//        System.out.println("循环触发时间："+new Date());  
        if (evt instanceof IdleStateEvent) {  
            IdleStateEvent event = (IdleStateEvent) evt;  
            if (event.state() == IdleState.WRITER_IDLE) {  
//                if(currentTime <= TRY_TIMES){  
//                    System.out.println("currentTime:"+currentTime);  
//                    currentTime++;  
                    ctx.channel().writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());  
//                }  
            }  
        }  
    }  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        String message = (String) msg;  
        if(!"pong".equals(message)){
        	System.out.println("客户端接收： "+message);  
        }
        
        if(i==0){
        	Map map = new HashMap();
            map.put("type", "login");
            map.put("requestId", "111111");
            Map login = new HashMap();
            login.put("equno", "456789");
//            login.put("equno", "k2160100001");
//            login.put("equno", "987685");
            map.put("data", login);
            String push = JSON.toJSONString(map);  
            System.out.println(push);
//            ctx.writeAndFlush(push+"\n");  
            
//            orderresults(ctx);
        }else if(i>0){
        	
        	if(message.equals("pong")){
        		return;
        	}
        	
        	JSONObject obj = JSONObject.parseObject(message);
        	if("login".equals(obj.getString("type"))&&"1".equals(obj.getString("code"))){
        		//登录成功，创建订单
//        		createOrder(ctx);
//        		logout(ctx);
        		
        	}else if("order".equals(obj.getString("type"))&&"1".equals(obj.getString("code"))){
//        		//订单创建成功，上报订单成绩
//        		orderresults(ctx);
        		
//        		orderStart(ctx, "5O326S7G");
        	}else if("orderpay".equals(obj.getString("type"))&&"1".equals(obj.getString("code"))){
        		//支付成功,游戏开始
//        		System.out.println(obj.getJSONObject("data").getString("order_no"));
//        		orderStart(ctx,obj.getJSONObject("data").getString("order_no"));
        	}
    	}
        i++;
        
        /*if (message.equals("Heartbeat")) {  
            ctx.write("has read message from server");  
            ctx.flush();  
        }  */
        ReferenceCountUtil.release(msg);  
    }  
    
    private void logout(ChannelHandlerContext ctx){
    	Map map = new HashMap();
        map.put("type", "logout");
        map.put("requestId", "6666");
        String push = JSON.toJSONString(map);  
        System.out.println(push);
        ctx.writeAndFlush(push+"\n");
    }
    
    private void createOrder(ChannelHandlerContext ctx){
    	Map map = new HashMap();
        map.put("type", "order");
        map.put("requestId", "6666");
        Map order = new HashMap();
        order.put("game_code", "s001g006");
        order.put("game_mode", "1");
        map.put("data", order);
        String push = JSON.toJSONString(map);  
        System.out.println(push);
        ctx.writeAndFlush(push+"\n");  
    }
    
    private void orderStart(ChannelHandlerContext ctx,String orderNo){
    	Map map = new HashMap();
        map.put("type", "orderstart");
        map.put("requestId", "777");
        Map order = new HashMap();
        order.put("order_no", orderNo);
        map.put("data", order);
        String push = JSON.toJSONString(map);  
        System.out.println(push);
        ctx.writeAndFlush(push+"\n");  
    }
    
    private void orderresults(ChannelHandlerContext ctx){
    	Map orderRes = new HashMap();
    	orderRes.put("type", "orderresults");
    	orderRes.put("requestId", "12321");
    	
    	Map data = new HashMap();
    	data.put("order_no", "SHOIK28R");
        
        List results = new ArrayList<>();
        results.add(getGroup1());
        results.add(getGroup2());
        data.put("results", results);
        
        orderRes.put("data", data);
        String push = JSON.toJSONString(orderRes);  
        System.out.println(push);
        ctx.writeAndFlush(push+"\n");  
    }
    
    private Map getGroup1(){
    	Map group1 = new HashMap();
        group1.put("groupId", 1);
        group1.put("groupScore", 0);
        
        List playerList = new ArrayList<>();
        Map player1 = new HashMap();
        player1.put("PPD", 50.166668);
        player1.put("PPR", 150.5);
        player1.put("MPR", -1);
        player1.put("hitNum", 6);
        player1.put("playerId", 1);
        player1.put("playerName", "player1");
        player1.put("playerScore", 301);
        playerList.add(player1);
        
        group1.put("playerList", playerList);
        
        List roundList = new ArrayList<>();
        Map round1 = new HashMap();
        round1.put("hitNum", 3);
        round1.put("playerId", 1);
        round1.put("roundScore", 180);
        
        List hitDataList = new ArrayList<>();
        Map hitData1 = new HashMap();
        hitData1.put("area", 1);
        hitData1.put("score", 20);
        hitDataList.add(hitData1);
        Map hitData2 = new HashMap();
        hitData2.put("area", 1);
        hitData2.put("score", 20);
        hitDataList.add(hitData2);
        Map hitData3 = new HashMap();
        hitData3.put("area", 1);
        hitData3.put("score", 20);
        hitDataList.add(hitData3);
        round1.put("hitData", hitDataList);
        roundList.add(round1);
        
        
        Map round2 = new HashMap();
        round2.put("hitNum", 3);
        round2.put("playerId", 1);
        round2.put("roundScore", 121);
        
        List hitDataList2 = new ArrayList<>();
        Map hitData21 = new HashMap();
        hitData21.put("area", 1);
        hitData21.put("score", 20);
        hitDataList2.add(hitData21);
        Map hitData22 = new HashMap();
        hitData22.put("area", 1);
        hitData22.put("score", 7);
        hitDataList2.add(hitData22);
        Map hitData23 = new HashMap();
        hitData23.put("area", 3);
        hitData23.put("score", 20);
        hitDataList2.add(hitData23);
        round2.put("hitData", hitDataList2);
        roundList.add(round2);
        
        group1.put("roundList", roundList);
        
        return group1;
    }
    
    private Map getGroup2(){
    	Map group1 = new HashMap();
    	group1.put("groupId", 2);
    	group1.put("groupScore", 301);
    	
    	List playerList = new ArrayList<>();
    	Map player1 = new HashMap();
    	player1.put("PPD", 0.0);
    	player1.put("PPR", 0.0);
    	player1.put("MPR", -1);
    	player1.put("hitNum", 3);
    	player1.put("playerId", 2);
    	player1.put("playerName", "player2");
    	player1.put("playerScore", 0);
    	playerList.add(player1);
    	
    	group1.put("playerList", playerList);
    	
    	List roundList = new ArrayList<>();
    	Map round1 = new HashMap();
    	round1.put("hitNum", 3);
    	round1.put("playerId", 2);
    	round1.put("roundScore", 0);
    	
    	List hitDataList = new ArrayList<>();
    	Map hitData1 = new HashMap();
    	hitData1.put("area", 0);
    	hitData1.put("score", 0);
    	hitDataList.add(hitData1);
    	Map hitData2 = new HashMap();
    	hitData2.put("area", 0);
    	hitData2.put("score", 0);
    	hitDataList.add(hitData2);
    	Map hitData3 = new HashMap();
    	hitData3.put("area", 0);
    	hitData3.put("score", 0);
    	hitDataList.add(hitData3);
    	round1.put("hitData", hitDataList);
    	roundList.add(round1);
    	
    	
    	group1.put("roundList", roundList);
    	
    	return group1;
    }
}  
