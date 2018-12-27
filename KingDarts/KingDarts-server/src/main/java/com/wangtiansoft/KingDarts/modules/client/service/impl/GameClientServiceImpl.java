package com.wangtiansoft.KingDarts.modules.client.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jfinal.kit.PropKit;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.common.utils.RandomUtil;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.common.utils.date.DateUtil;
import com.wangtiansoft.KingDarts.config.netty.ChannelService;
import com.wangtiansoft.KingDarts.config.netty.common.BaseService;
import com.wangtiansoft.KingDarts.config.netty.model.NettyMessage;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.stubs.PluginManager;
import com.wangtiansoft.KingDarts.core.stubs.file.BaseFilePluginStub;
import com.wangtiansoft.KingDarts.core.support.bean.OnlineChannel;
import com.wangtiansoft.KingDarts.core.utils.GameUtil;
import com.wangtiansoft.KingDarts.modules.boss.service.BossEntityService;
import com.wangtiansoft.KingDarts.modules.challenge.service.ChallengeService;
import com.wangtiansoft.KingDarts.modules.client.service.GameClientService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;
import com.wangtiansoft.KingDarts.modules.game.service.GameOrderService;
import com.wangtiansoft.KingDarts.modules.game.service.GameResPlayerService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.entity.Challenge;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.ChallengeResult;
import com.wangtiansoft.KingDarts.results.core.EquAuthResult;
import com.wangtiansoft.KingDarts.results.core.EquInfoResult;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import io.netty.channel.socket.SocketChannel;

@Service("gameClientService")
public class GameClientServiceImpl extends BaseService implements GameClientService{

	private static final Logger log = LoggerFactory.getLogger(GameClientServiceImpl.class);

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private GameOrderService gameOrderService;
	@Autowired
	private EquInfoService equInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private ClubInfoService clubInfoService;
	@Autowired
	private ChallengeService challengeService;
	@Autowired
	private BossEntityService bossEntityService;

	@SuppressWarnings("rawtypes")
	@Override
	public NettyMessage gameOrder(final Map params,final SocketChannel channel){
		final String id = channel.id().asShortText();
		NettyMessage result = this.buildAuthResponse(id,new IEResponseHandler() {
			@Override
			public Object execute(String equno) throws Exception {
				
				/*if(!"K2160100108".equals(equno)&&!"K2160100588".equals(equno)){
					throw new AppRuntimeException(Constants.kCode_NoEqu, "设备升级中，敬请期待");
				}*/
				
				if(params.get("game_code")==null){
					throw new AppRuntimeException(Constants.kCode_Null,"game_code不能为空");
				}
				if(params.get("game_mode")==null){
					throw new AppRuntimeException(Constants.kCode_Null,"game_mode不能为空");
				}
				//查询设备是否授权
				EquInfoResult equInfo = equInfoService.getEquInfoResultByNo(equno);
				if(equInfo==null||equInfo.getEquAuth()==null){
					throw new AppRuntimeException(Constants.kCode_Null,"设备不存在或未授权");
				}
				String gameCode = params.get("game_code").toString();
				
				//判断如果是大富豪游戏，而且boss未被kill掉
				if(GameUtil.isRich(gameCode)){
					//获取boss数据
		        	Map mapBoass=bossEntityService.queryBossEntityInfo();
		        	if(!mapBoass.containsKey("bossEntity")){
		        		throw new AppRuntimeException(Constants.kCode_Boss_killed,"boss已被杀");
		        	}
				}
				
				EquAuthResult ear = equInfo.getEquAuth();

				//查询设备信息，查询设备所在俱乐部的定价
				BigDecimal price = clubInfoService.getPriceByEquNo(equno);
				if(price == null){
					price = new BigDecimal(Constants.price_def);
				}
				Integer game_mode = Integer.parseInt(params.get("game_mode").toString());
				BigDecimal cost = GameUtil.countCost(game_mode, price);

				//生成游戏订单
				GameOrder gameOrder = new GameOrder();
				gameOrder.setMerchant(ear.getMerchant());
				gameOrder.setAuth_no(ear.getAuth_no());
				gameOrder.setEquno(equno);
				gameOrder.setOrder_time(new Date());
				gameOrder.setOrder_no(RandomUtil.getRandom(8));
				gameOrder.setGame_code(gameCode);
				gameOrder.setGame_type(GameUtil.getGameType(gameOrder.getGame_code()));
				gameOrder.setGame_mode(game_mode);
				gameOrder.setCost(cost);
				gameOrder.setStatus(Constants.gorder_status_create);
				gameOrder.setPay_status(Constants.gorder_paystatus_nopay);
				gameOrder.setOrder_type(Constants.gorder_type_single);
				gameOrderService.save(gameOrder);//创建订单


				Map<String,Object> map = new HashMap<>();
				//判断是否是包机
				if(StringUtils.isNotEmpty(equInfo.getBooked_user())){
					//如果是包机类型，自动支付
					gameOrder.setOrder_type(Constants.gorder_type_booked);

					UserResult u = userService.getUserByUuid(equInfo.getBooked_user());

					//扣费
					try {
						userService.consume(u.getUuid(), gameOrder.getId(), cost, Constants.gorder_type_booked
								,gameOrder.getGame_code(),game_mode, null);
					} catch (AppRuntimeException e) {
						//余额不足取消包机
						EquInfo equInfoBook = new EquInfo();
						equInfoBook.setEquno(equno);
						equInfoBook.setBooked_user("");
						equInfoService.updateByNoSelective(equInfoBook);
						
						throw new AppRuntimeException(e.getMsg());
					}
					
					//等本次返回后，再通知客户端【用于自动推送】
					User user = userService.findById(u.getId());
					map.put("points", user.getPoints());
					map.put("balance", user.getBalance().add(user.getGive_balance()).toString());
					map.put("game_code", gameOrder.getGame_code());
				}else{
					//返回支付二维码url,包含订单编号
					StringBuffer url = new StringBuffer().append(PropKit.get("domain"))
							.append("/wx/oauth?trade_no=").append(gameOrder.getOrder_no());
					map.put("url", url.toString());
				}

				map.put("order_no", gameOrder.getOrder_no());
				map.put("order_type", gameOrder.getOrder_type().toString());
				return map;
			}
		});
		return result;
	}

	@Override
	public NettyMessage gameOrderStart(final Map params,final SocketChannel channel){
		final String id = channel.id().asShortText();
		NettyMessage result = this.buildAuthResponse(id,new IEResponseHandler() {
			@Override
			public Object execute(String equno) throws Exception {
				if(params.get("order_no")==null){
					throw new AppRuntimeException(Constants.kCode_Null,"order_no不能为空");
				}
				GameOrder gameOrder = new GameOrder();
				gameOrder.setOrder_no(params.get("order_no").toString());
				gameOrder = gameOrderService.getGameOrderByNo(gameOrder);
				if(gameOrder==null){
					throw new AppRuntimeException(Constants.kCode_NoOrder,"订单不存在");
				}
				if(!gameOrder.getStatus().toString().equals(Constants.gorder_status_create+"")){
					throw new AppRuntimeException(Constants.kCode_OrderStatusFail,"订单状态错误");
				}
				if(!gameOrder.getPay_status().toString().equals(Constants.gorder_paystatus_haspay+"")){
					throw new AppRuntimeException(Constants.kCode_OrderPayStatusFail,"订单支付 错误");
				}

				//如果游戏是网络对账，自动匹配
				if(GameUtil.isNetGame(gameOrder.getGame_code())){
					//判断用户当前是否有约战,且游戏编码与约战游戏编码相同
					ChallengeResult challenge = challengeService.getUserChallenge(gameOrder.getUser_id(), new Date());
					//测试用
					/*Challenge cha = challengeService.findById(90L);
					ChallengeResult challenge = new ChallengeResult();
					BeanUtil.copyProperties(cha, challenge);*/
					if(challenge != null&&gameOrder.getGame_code().equals(challenge.getGame_type_id())){
						//自动匹配约战
						challengeMatching(equno, challenge, gameOrder);
						
					}else{
						//不是约战，则添加到待匹配列表里，由定时分配任务去分配
						redisTemplate.opsForHash().put(Constants.game_equno_wait+gameOrder.getGame_code(), equno, gameOrder.getOrder_no());
					}
					
				}else{
					GameOrder entity = new GameOrder();
					entity.setId(gameOrder.getId());
					entity.setStatus(Constants.gorder_status_ingame);
					entity.setStart_time(new Date());
					gameOrderService.updateByIdSelective(entity);
				}

				Map<String,String> map = new HashMap<>();
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 自动匹配约战
	 */
	public void challengeMatching(String equno,ChallengeResult challenge,GameOrder gameOrder){
		//约战记录与游戏的订单进行关联
		if(challenge.getSponsor_useraccount().equals(gameOrder.getUser_id())){
			//发起人订单
			Challenge entity = new Challenge();
			entity.setId(challenge.getId());
			entity.setSponsor_order_no(gameOrder.getOrder_no());
			challengeService.updateByIdSelective(entity);
		}else{
			//接受人订单
			Challenge entity = new Challenge();
			entity.setId(challenge.getId());
			entity.setReceiver_order_no(gameOrder.getOrder_no());
			challengeService.updateByIdSelective(entity);
		}
		
		//查询目标用户是否已经在线等待
		String targetuuid = gameOrder.getUser_id().equals(challenge.getSponsor_useraccount())?challenge.getReceiver_useraccount():challenge.getSponsor_useraccount();
		Object obj = redisTemplate.opsForHash().get(Constants.game_user_wait, targetuuid);
		if(obj!=null){
			ChallengeResult targetChallenge  = (ChallengeResult)obj;
			
			//如果完成匹配
			redisTemplate.opsForHash().put(Constants.equno_net, equno,targetChallenge.getEquno() );
			redisTemplate.opsForHash().put(Constants.equno_net, targetChallenge.getEquno(), equno);
			
			//约战匹配完成，记录约战订单号，用于成绩回写约战
			redisTemplate.opsForHash().put(Constants.challenge_orderno, gameOrder.getOrder_no(),targetChallenge.getOrder_no() );
			redisTemplate.opsForHash().put(Constants.challenge_orderno, targetChallenge.getOrder_no(), gameOrder.getOrder_no());
			
			//发送匹配成功通知
			redisTemplate.opsForHash().put(Constants.challenge_equno_wait, equno+"_"+targetChallenge.getEquno(), gameOrder.getOrder_no()+"_"+targetChallenge.getOrder_no());
			//pushGameStart(equno, targetChallenge.getEquno(), gameOrder.getOrder_no(), targetChallenge.getOrder_no());

			redisTemplate.opsForHash().delete(Constants.game_user_wait, targetuuid);
			
		}else{
			//目标用户未在线，则加入等待列表
			challenge.setEquno(equno);
			challenge.setOrder_no(gameOrder.getOrder_no());
			challenge.setOut_time(DateUtil.addMinute(challenge.getStart_time(), challenge.getFloat_time()));
			redisTemplate.opsForHash().put(Constants.game_user_wait, gameOrder.getUser_id(), challenge);
		}
	}

	@Override
	public NettyMessage gameOrderResults(final Map params,final SocketChannel channel){
		final String id = channel.id().asShortText();
		NettyMessage result = this.buildAuthResponse(id,new IEResponseHandler() {
			@Override
			public Object execute(String equno) throws Exception {

				/*log.warn("=====成绩上报======== "+params);
				if(params.get("order_no")==null||params.get("results")==null){
					throw new AppRuntimeException("order_no，results不能为空");
				}
				gameResPlayerService.saveGameResult(params.get("order_no").toString(),(List)params.get("results"));*/

				Map<String,String> map = new HashMap<>();
				//				map.put("code", com.wangtiansoft.KingDarts.config.netty.constants.Constants.code_Success);
				//				map.put("type", com.wangtiansoft.KingDarts.config.netty.constants.Constants.message_type_orderresults);
				return map;
			}
		});
		return result;
	}

	@Override
	public NettyMessage pushGameOrder(final String equno,final String clientId,final String orderNo
			,final Integer orderType,final Integer points,final String balance,final String gameCode){
		NettyMessage result = this.buildResponse(new IResponseHandler() {
			@Override
			public Object execute() throws Exception {
				String cid = clientId;
				if(StringUtils.isEmpty(cid)){
					Object obj = redisTemplate.opsForHash().get(Constants.online_channel, equno);
					if(obj==null){
						throw new AppRuntimeException("设备登录状态错误");
					}
					OnlineChannel onlineChannel = (OnlineChannel)obj;
					cid = onlineChannel.getClientId();
				}
				
				SocketChannel channel = channelService.getGatewayChannel(cid);

				Map<String,Object> data = new HashMap<>();
				data.put("order_no", orderNo);
				data.put("order_type", orderType);
				data.put("userPoints", points);
				data.put("balance", balance);
				data.put("equno", equno);
				
				//判断游戏类型是大富豪
				if(GameUtil.isRich(gameCode)){
					data.put("vipLevel", GameUtil.getVipLevel(points));
					
					//获取boss数据
		        	Map mapBoass=bossEntityService.queryBossEntityInfo();
		        	if(mapBoass.containsKey("bossEntity")){
		        		Map bossEntity = (Map)mapBoass.get("bossEntity");
		        		data.put("lifeRate", new BigDecimal(bossEntity.get("lifeRate").toString()).multiply(new BigDecimal(100)));
						data.put("remainTime", mapBoass.get("timeLeft"));
						
						GameOrder gameOrder = new GameOrder();
						gameOrder.setOrder_no(orderNo);
						gameOrder.setBoss_id(bossEntity.get("uuid").toString());
						gameOrderService.updateByNoSelective(gameOrder);
		        	}else{
		        		data.put("lifeRate",0.0);
						data.put("remainTime", 0);
		        	}
				}

				Map<String,Object> map = new HashMap<>();
				map.put("code", com.wangtiansoft.KingDarts.config.netty.constants.Constants.code_Success);
				map.put("type", com.wangtiansoft.KingDarts.config.netty.constants.Constants.message_type_orderpay);
				map.put("msg", "支付成功");
				map.put("data", data);
				channel.writeAndFlush(JSON.toJSONString(map)+"\n"); 

				Map<String,String> result = new HashMap<>();
				map.put("msg", "支付成功");
				return result;
			}
		});
		return result;
	}

	@Override
	public boolean gameStartPush(String player1,String player2,String orderNo1,String orderNo2){

		try {
			SocketChannel channel1 = getSocketChannel(player1);
			SocketChannel channel2 = getSocketChannel(player2);
			String time = new Date().getTime()+"";

			gameOrderService.netGameStart(orderNo1, orderNo2);
			String str1 = getStartPush(orderNo1,"player1",time);
			String str2 = getStartPush(orderNo2,"player2",time);
			log.info(player1+" "+str1);
			log.info(player2+" "+str2);
			channel1.writeAndFlush(str1+"\n"); 
			channel2.writeAndFlush(str2+"\n"); 

			return true;
		} catch (AppRuntimeException e) {
			log.error(e.getMsg());
		} catch (Exception e) {
			log.error("",e);
		}

		return false;
	}
	
	@Override
	public NettyMessage pushHitData(final Map params,final SocketChannel channel){
		final String id = channel.id().asShortText();
		NettyMessage result = this.buildAuthResponse(id,new IEResponseHandler() {
			@Override
			public Object execute(String equno) throws Exception {

				//查询此设备匹配的其他设备编号
				Object targetEquno = redisTemplate.opsForHash().get(Constants.equno_net,equno);
				if(targetEquno == null){
					throw new AppRuntimeException("无匹配设备");
				}
				SocketChannel targetChannel = getSocketChannel(targetEquno.toString());
				
				targetChannel.writeAndFlush(getPushHit(params)+"\n"); 

				Map<String,Object> data = new HashMap<>();
				data.put("target_equno", targetEquno);

				Map<String,Object> map = new HashMap<>();
				map.put("code", com.wangtiansoft.KingDarts.config.netty.constants.Constants.code_Success);
				map.put("type", com.wangtiansoft.KingDarts.config.netty.constants.Constants.message_type_hit);
				map.put("msg", "推送成功");
				map.put("data", data);
				channel.writeAndFlush(JSON.toJSONString(map)+"\n"); 

				Map<String,String> result = new HashMap<>();
				map.put("msg", "支付成功");
				return result;
			}
		});
		return result;
	}

	public SocketChannel getSocketChannel(String equno){
		Object obj = redisTemplate.opsForHash().get(Constants.online_channel, equno);
		if(obj==null){
			throw new AppRuntimeException("设备登录状态错误");
		}
		OnlineChannel onlineChannel = (OnlineChannel)obj;
		String cid = onlineChannel.getClientId();
		return channelService.getGatewayChannel(cid);
	}

	private String getStartPush(String orderNo,String name,String time){
		Map<String,Object> data = new HashMap<>();
		data.put("order_no", orderNo);
		data.put("playerName", name);
		data.put("time", time);

		Map<String,Object> map = new HashMap<>();
		map.put("code", com.wangtiansoft.KingDarts.config.netty.constants.Constants.code_Success);
		map.put("type", com.wangtiansoft.KingDarts.config.netty.constants.Constants.message_type_startpush);
		map.put("msg", "匹配成功");
		map.put("data", data);
		return JSON.toJSONString(map);
	}
	
	private String getPushHit(Map data){
		Map<String,Object> map = new HashMap<>();
		map.put("code", com.wangtiansoft.KingDarts.config.netty.constants.Constants.code_Success);
		map.put("type", com.wangtiansoft.KingDarts.config.netty.constants.Constants.message_type_hitpush);
		map.put("data", data);
		return JSON.toJSONString(map);
	}


//	@Override
//	红包接口
//

		@Override
		public NettyMessage pushPacketsUrl(final Map params,final SocketChannel channel){
			final String id = channel.id().asShortText();
			NettyMessage result = this.buildAuthResponse(id,new IEResponseHandler() {
				@Override
				public Object execute(String equno) throws Exception {
					
					/*if(!"K2160100108".equals(equno)&&!"K2160100588".equals(equno)){
						throw new AppRuntimeException(Constants.kCode_NoEqu, "设备升级中，敬请期待");
					}*/
					Map<String,Object> data = new HashMap<>();
					
					if(params.get("game_code")==null){
						throw new AppRuntimeException(Constants.kCode_Null,"game_code不能为空");
					}
					if(params.get("game_mode")==null){
						throw new AppRuntimeException(Constants.kCode_Null,"game_mode不能为空");
					}

					String gameCode = params.get("game_code").toString();

					Integer game_mode = Integer.parseInt(params.get("game_mode").toString());
					data.put("game_mode", game_mode);
					data.put("game_Code", gameCode);

					List<String> list=	com.wangtiansoft.KingDarts.config.netty.constants.Constants.stringListUrl;
					Random random = new Random(System.currentTimeMillis());
					int iRandom = random.nextInt(list.size()-1);
			        String packetsURL=list.get(iRandom);  

				Map<String,Object> map = new HashMap<>();
				map.put("code", com.wangtiansoft.KingDarts.config.netty.constants.Constants.code_Success);
				map.put("type", com.wangtiansoft.KingDarts.config.netty.constants.Constants.message_type_packets);
				map.put("msg", packetsURL);
				map.put("data", data);
				channel.writeAndFlush(JSON.toJSONString(map)+"\n"); 

				Map<String,String> result = new HashMap<>();
				map.put("msg", packetsURL);
				return result;
			}
		});
		return result;
	}
	
	
}
