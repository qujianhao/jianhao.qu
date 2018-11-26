package com.wangtiansoft.KingDarts.modules.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wangtiansoft.KingDarts.common.utils.DateUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.utils.GameUtil;
import com.wangtiansoft.KingDarts.modules.boss.service.BossEntityService;
import com.wangtiansoft.KingDarts.modules.boss.service.BossService;
import com.wangtiansoft.KingDarts.modules.challenge.service.ChallengeService;
import com.wangtiansoft.KingDarts.modules.client.service.GameClientService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubMonthRankService;
import com.wangtiansoft.KingDarts.modules.pay.service.LftPayService;
import com.wangtiansoft.KingDarts.modules.race.service.RaceEnterforUserService;
import com.wangtiansoft.KingDarts.modules.race.service.RaceInfoService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.entity.Boss;
import com.wangtiansoft.KingDarts.persistence.entity.BossEntity;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.persistence.entity.LftPay;
import com.wangtiansoft.KingDarts.persistence.entity.RaceInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Component
public class TimerTask {

	private final static Logger logger = LoggerFactory.getLogger(TimerTask.class);

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private LftPayService lftPayService;
	@Autowired
	private GameClientService gameClientService;
	@Autowired
	private ChallengeService challengeService;
	@Autowired
	private ClubMonthRankService clubMonthRankService;
	@Autowired
	private UserService userService;
	@Autowired
	private BossService bossService;
	@Autowired
	private BossEntityService bossEntityService;
	@Autowired
	private RaceInfoService raceInfoService;
	@Autowired
	private RaceEnterforUserService raceEnterforUserService;
	
	@Value("${challenge.timeout}") 
	private boolean challengeTimeout; 

//	private static int maxCount = 100;//最大同步次数，大于这个同步次数则将数据库中支付中订单写入缓存
//	private int synCount = 0;//同步次数
	
	

	/**
	 * 同步支付中订单状态
	 * 单线程执行
	 */
	@Scheduled(cron = "0/5 * * * * *")
	private void payStatus() {  
		//从缓存中获取支付中订单
		Map<String, String> map = redisTemplate.opsForHash().entries(Constants.pay_order_onpay);
		if(map != null){
			for (Map.Entry<String,String> entry : map.entrySet()) {  
				LftPay lftPay = JSON.parseObject(entry.getValue(), new TypeReference<LftPay>(){});
				if(lftPayService.synPayStatus(lftPay.getOut_trade_no(), lftPay.getTrade_no(), lftPay.getMerchant_no(),lftPay.getTime_start())){
					redisTemplate.opsForHash().delete(Constants.pay_order_onpay, entry.getKey());
				}
			}
		}
		/*if(map != null){
			for (Map.Entry<String,String> entry : map.entrySet()) {  
				LftPay lftPay = JSON.parseObject(entry.getValue(), new TypeReference<LftPay>(){});
				//判断js是否已支付
				Object payDate = redisTemplate.opsForHash().get(Constants.pay_order_jspay, entry.getValue());
				if(payDate!=null){
					//超过1分钟，自动关闭订单
					if(lftPayService.synPayStatus(lftPay.getOut_trade_no(), lftPay.getTrade_no(), lftPay.getMerchant_no()
							,com.wangtiansoft.KingDarts.common.utils.date.DateUtil.StringToDate(payDate.toString()))){
						redisTemplate.opsForHash().delete(Constants.pay_order_onpay, entry.getKey());
						redisTemplate.opsForHash().delete(Constants.pay_order_jspay, entry.getKey());
					}
					
				}else{
					//一直未支付，超过10分钟关闭
				}
				
			}
		}*/
		Map<String, String> mapapp = redisTemplate.opsForHash().entries(Constants.pay_order_onpay_app);
		if(mapapp != null){
			for (Map.Entry<String,String> entry : mapapp.entrySet()) {  
				LftPay lftPay = JSON.parseObject(entry.getValue(), new TypeReference<LftPay>(){});
				if(lftPayService.synWechatPayStatus(lftPay.getOut_trade_no(), lftPay.getTrade_no(), lftPay.getMerchant_no(),lftPay.getTime_start())){
					redisTemplate.opsForHash().delete(Constants.pay_order_onpay_app, entry.getKey());
				}
			}
		}
		/*if(synCount>=maxCount){
			List<LftPay> lftPayList = lftPayService.getLftPayByPayStatus(1);//查询数据库中支付中的订单
			for(LftPay lftPay : lftPayList) {
				redisTemplate.opsForHash().put(Constants.pay_order_onpay, lftPay.getTrade_no(), JSON.toJSONString(lftPay));
			}
			synCount = 0;
		}
		synCount ++;*/
	}
	
	/**
	 * 网络订单匹配
	 * 单线程执行
	 */
	@SuppressWarnings("unchecked")
	@Scheduled(cron = "0/5 * * * * *")
	private void netGameMatching() {  
		//从缓存中获取待分配列表
		String[] netCodes = GameUtil.netCodes;
		for(int i = 0;i<netCodes.length;i++){
			String key = Constants.game_equno_wait+netCodes[i];
			Map<String, String> map = redisTemplate.opsForHash().entries(key);
			if(map==null||map.isEmpty()){
				continue;
			}
			String player1 = "";//设备编码1
			String player2 = "";//设备编码2
			String orderNo1 = "";
			String orderNo2 = "";
			for (Map.Entry<String,String> entry : map.entrySet()) { 
//				System.out.println(entry.getKey());
				//判断设备是否在线
				Object obj = redisTemplate.opsForHash().get(Constants.online_channel, entry.getKey());
				if(obj==null){
					redisTemplate.opsForHash().delete(key, entry.getKey());
					redisTemplate.opsForHash().delete(Constants.equno_net, entry.getKey());
					continue;
				}
				
				if("".equals(player1)){
					player1 = entry.getKey();
					orderNo1 = entry.getValue();
				}else if("".equals(player2)){
					player2 = entry.getKey();
					orderNo2 = entry.getValue();
				}

				if(!"".equals(player1)&&!"".equals(player2)){
					//如果完成匹配
					redisTemplate.opsForHash().put(Constants.equno_net, player1, player2);
					redisTemplate.opsForHash().put(Constants.equno_net, player2, player1);
					//发送匹配成功通知
					gameClientService.gameStartPush(player1, player2, orderNo1, orderNo2);

					redisTemplate.opsForHash().delete(key, player1, player2);
					//匹配其他设备
					player1 = "";
					player2 = "";
					orderNo1 = "";
					orderNo2 = "";
				}
			}
		}
		
		Map<String, String> map = redisTemplate.opsForHash().entries(Constants.challenge_equno_wait);
		for (Map.Entry<String,String> entry : map.entrySet()) { 
//			System.out.println(entry.getKey()+" "+ entry.getValue());
			String[] equno = entry.getKey().split("_");
			String[] orderno = entry.getValue().split("_");
			
			//判断设备是否在线
			Object obj1 = redisTemplate.opsForHash().get(Constants.online_channel, equno[0]);
			Object obj2 = redisTemplate.opsForHash().get(Constants.online_channel, equno[1]);
			if(obj1==null||obj2==null){
				redisTemplate.opsForHash().delete(Constants.challenge_equno_wait, entry.getKey());
				continue;
			}
			
			gameClientService.gameStartPush(equno[0], equno[1], orderno[0], orderno[1]);
			
			redisTemplate.opsForHash().delete(Constants.challenge_equno_wait, entry.getKey());
		}
	}
	
	/**
	 * 修改约战状态
	 */
	@Scheduled(cron = "0 0/5 * * * *")
	private void changeChallengeStatus() {  
		//从缓存中获取支付中订单
		if(challengeTimeout){
			challengeService.changeChallengeStatus();
		}
	}
	
	/**
	 * 添加历史月积分 每个月1号 6点 跑定时任务 添加 俱乐部竞技值，实力值和用户积分数据
	 */
	@Scheduled(cron = "0 0 6 1 * ?")
	private void rankMothSet() {  
		//上一个月的
		String dateStr=DateUtil.formatDateBy("YYYY-MM",DateUtil.dateToMonth(DateUtil.addDate(new Date(), -1)));
		Map paramMap=new HashMap<>();
		paramMap.put("rank_time",dateStr);
		clubMonthRankService.insertClubMonthRankList(paramMap);
		userService.insertUserPointsMonth(paramMap);
	    
	}
	
	/**
	 * 每一天 0点0时0分1秒添加boss实体
	 */
	@Scheduled(cron = "1 0 0 * * ?")
	private void bossInit() {  
		//查找可用的boss设置
		Boss entity = bossService.getUseBoss();
		if(entity!=null) {
			//新增实体
			BossEntity bossEntityNew=new BossEntity();
			bossEntityNew.setBoss_id(entity.getId());
			bossEntityNew.setEvolume(entity.getBvolume());
			bossEntityNew.setKill_status(0);
			String dateApp=DateUtil.mongoDateToSave(new Date());
			bossEntityNew.setApplication_time(dateApp);
			bossEntityService.save(bossEntityNew);
		}
	}
	
	/**
	 * 每一天 0点0时0分1秒检查比赛状态
	 */
	@Scheduled(cron = "1 0 0 * * ?")
	private void racetime() {
		List<RaceInfo> raceInfoList = raceInfoService.getAllRaceInfoByStatus();
		if(raceInfoList!=null) {
			for(RaceInfo raceinfo:raceInfoList) {
				//报名人数
				Integer countnum = raceEnterforUserService.getCountUserByRaceno(raceinfo.getRaceno());
				
				//将时间转换成Int类型再做比较
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				String nowD = dateFormat.format(new Date());
				String regstart = dateFormat.format(raceinfo.getRegstart());
				String regend = dateFormat.format(raceinfo.getRegend());
				String racestart = dateFormat.format(raceinfo.getRacestart());
				String raceend = dateFormat.format(raceinfo.getRaceend());
				int nowD1 = Integer.parseInt(nowD);//当前时间
				int regstart1 = Integer.parseInt(regstart);//报名开始时间
				int regend1 = Integer.parseInt(regend);//报名结束时间
				int racestart1 = Integer.parseInt(racestart);//比赛开始时间
				int raceend1 = Integer.parseInt(raceend);//比赛结束时间
				
				if(regstart1<=nowD1&&regend1>=nowD1) {
					//报名中
					raceinfo.setDstatus(1);
				}
				if(racestart1<=nowD1&&raceend1>=nowD1&&countnum>=raceinfo.getMinimum_num()) {
					//时间及报名人数达到即进行中
					raceinfo.setDstatus(2);
				}
				if(racestart1<=nowD1&&raceend1>=nowD1&&countnum<raceinfo.getMinimum_num()) {
					//时间达到但报名人数未达到，即解散
					raceinfo.setDstatus(5);
				}
				if(nowD1>raceend1) {
					//当前时间大于比赛结束时间，即已结束
					raceinfo.setDstatus(5);
				}
				raceInfoService.updateByIdSelective(raceinfo);
			}
		}
	}

	
}
