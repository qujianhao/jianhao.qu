package com.wangtiansoft.KingDarts.modules.game.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.boss.service.BossEntityService;
import com.wangtiansoft.KingDarts.modules.game.service.GameOrderService;
import com.wangtiansoft.KingDarts.modules.game.service.GameResPlayerService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.BossLuckMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.BossScoreMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.GameResHitMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.GameResPlayerMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.GameResRoundMapper;
import com.wangtiansoft.KingDarts.persistence.entity.BossLuck;
import com.wangtiansoft.KingDarts.persistence.entity.BossScore;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.persistence.entity.GameResHit;
import com.wangtiansoft.KingDarts.persistence.entity.GameResPlayer;
import com.wangtiansoft.KingDarts.persistence.entity.GameResRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by wt-templete-helper.
 */
@Transactional
@Service("gameResPlayerService")
public class GameResPlayerServiceImpl extends BaseService<GameResPlayer, Integer> implements GameResPlayerService{

	@Autowired
	private GameResPlayerMapper gameResPlayerMapper;
	@Autowired
	private GameResRoundMapper gameResRoundMapper;
	@Autowired
	private GameResHitMapper gameResHitMapper;
	@Autowired
	private GameOrderService gameOrderService;
	@Autowired
	private BossScoreMapper bossScoreMapper;
	@Autowired
	private BossLuckMapper bossLuckMapper;
	@Autowired
	private BossEntityService bossEntityService;

	@Override
	public BaseMapper getBaseMapper() {
		return gameResPlayerMapper;
	}

	@Override
	public Page<Map> queryGameResPlayerPageList(Map paramMap, PageBean pageBean) {
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
		PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		return (Page<Map>) gameResPlayerMapper.queryGameResPlayerList(paramMap);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveGameResult(String orderNo,List<Map> list,String netNo,String win){
		this.saveGameResult(orderNo, list, netNo, win, null);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveGameResult(String orderNo,List<Map> list,String netNo,String win,Map attach){
		Map<Integer,Long> playerMap = new HashMap<>();
		
		for(Map<String,Object> params:list){
			//保存用户得分数据
			List<Map> playerList = (List)params.get("playerList");
			for(Map player:playerList){
				GameResPlayer gameResPlayer = new GameResPlayer();
				gameResPlayer.setGroup_id((Integer)params.get("groupId"));
				gameResPlayer.setGroup_score((Integer)params.get("groupScore"));
				gameResPlayer.setPlayer_id((Integer)player.get("playerId"));
				gameResPlayer.setPlayer_name(player.get("playerName").toString());
				gameResPlayer.setPlayer_score((Integer)player.get("playerScore"));
				gameResPlayer.setHit_num((Integer)player.get("hitNum"));
				gameResPlayer.setPpd(new BigDecimal(player.get("PPD").toString()));
				gameResPlayer.setPpr(new BigDecimal(player.get("PPR").toString()));
				gameResPlayer.setMpr(new BigDecimal(player.get("MPR").toString()));
				gameResPlayer.setOrder_no(orderNo);

				gameResPlayerMapper.insert(gameResPlayer);
				playerMap.put(gameResPlayer.getPlayer_id(), gameResPlayer.getId());
			}

			//保存每回合数据
			List<Map> roundList = (List)params.get("roundList");
			for(Map round:roundList){
				GameResRound gameResRound  = new GameResRound();
				gameResRound.setHit_num((Integer)round.get("hitNum"));
				gameResRound.setRound_score((Integer)round.get("roundScore"));
				gameResRound.setRes_player_id(playerMap.get((Integer)round.get("playerId")));

				gameResRoundMapper.insert(gameResRound);

				List<Map> hitData = (List)round.get("hitData");
				for(Map hit:hitData){
					GameResHit gameResHit = new GameResHit();
					gameResHit.setArea((Integer)hit.get("area"));
					gameResHit.setScore((Integer)hit.get("score"));
					gameResHit.setRes_round_id(gameResRound.getId());
					gameResHitMapper.insert(gameResHit);
				}
			}
		}

		//更新游戏订单状态
		GameOrder gameOrder = new GameOrder();
		gameOrder.setOrder_no(orderNo);
		gameOrder.setStatus(Constants.gorder_status_overgame);
		gameOrder.setEnd_time(new Date());
		gameOrder.setWin(win);
		gameOrderService.updateByNoSelective(gameOrder);
		
		//如果是网络游戏
		if(StringUtils.isNotEmpty(netNo)){
			GameOrder gameOrderNet = new GameOrder();
			gameOrderNet.setOrder_no(netNo);
			gameOrderNet.setStatus(Constants.gorder_status_overgame);
			gameOrderNet.setEnd_time(new Date());
			gameOrderNet.setWin("Y".equals(win)?"N":"Y");
			gameOrderService.updateByNoSelective(gameOrderNet);
		}
		
		//如果是大富豪游戏
		if(attach!=null&&!attach.isEmpty()){
			//查询订单信息
			GameOrder order = new GameOrder();
			order.setOrder_no(orderNo);
			order = gameOrderService.getGameOrderByNo(order);
			
			//记录成绩
			BossScore bossScore = new BossScore();
			bossScore.setBoss_id(order.getBoss_id());
			bossScore.setCreate_time(new Date());
			bossScore.setOrder_no(orderNo);
			bossScore.setTotal_score((Integer)attach.get("total_score"));
			bossScore.setUser_id(order.getUser_id());
			bossScoreMapper.insert(bossScore);
			
			BossLuck bossLuck = new BossLuck();
			bossLuck.setScore_id(bossScore.getId());
			bossLuck.setLuck(JSONObject.toJSONString(attach.get("luck_scores")));
			bossLuckMapper.insert(bossLuck);
			/*for(Map<String,Object> luck:(List<Map>)attach.get("luck_scores")){
				BossLuck bossLuck = new BossLuck();
				bossLuck.setScore((Integer)luck.get("score"));
				bossLuck.setScore_id(bossScore.getId());
				bossLuck.setTimes((Integer)luck.get("times"));
				bossLuckMapper.insert(bossLuck);
			}*/
			
			//扣除boss生命值
			bossEntityService.lossLife(order.getBoss_id(), bossScore.getTotal_score(), order.getUser_id());
			
			//TODO 发送大富豪模板消息
			
		}
	}
}
