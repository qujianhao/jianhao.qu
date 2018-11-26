package com.wangtiansoft.KingDarts.modules.boss.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.CollectionUtil;
import com.wangtiansoft.KingDarts.common.utils.DateUtil;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.boss.service.BossEntityService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.BossEntityMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.BossScoreMapper;
import com.wangtiansoft.KingDarts.persistence.entity.BossEntity;

/**
 * Created by wt-templete-helper.
 */
@Transactional
@Service("bossEntityService")
public class BossEntityServiceImpl extends BaseService<BossEntity, String> implements BossEntityService{

	@Autowired
	private BossEntityMapper bossEntityMapper;
	
	@Autowired
	private BossScoreMapper bossScoreMapper;

	@Override
	public BaseMapper getBaseMapper() {
		return bossEntityMapper;
	}

	@Override
	public Page<Map> queryBossEntityPageList(Map paramMap, PageBean pageBean) {
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
		if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL))){
			paramMap.put(SQLUtil.SQL_OrderSQL, " create_time  desc");
		}
		PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		return (Page<Map>) bossEntityMapper.queryBossEntityList(paramMap);
	}

	@Override
	public Map queryBossEntityInfo(Map paramMap) {
		return bossEntityMapper.queryBossEntityInfo(paramMap);
	}

	@Override
	public Map queryBossEntityInfo() {
		Map<String,Object> map = new HashMap<>();
		String date=DateUtil.mongoDateToSave(new Date());
		Map<String,Object> paramMap=new HashMap<>();
		paramMap.put("application_time", date);
		Map mapBoass=bossEntityMapper.queryBossEntityInfo(paramMap);
		if(mapBoass!=null) {
			if(mapBoass.get("kill_status").equals(1)) {
				map.put("msg","boss已击杀！");
			}else {
				map.put("bossEntity", mapBoass);
				//剩余击杀时间
				map.put("timeLeft", DateUtil.getTodayTimeLeft());
			}
		}else {
			map.put("msg","无boss信息！");
		}
		return map;
	}
	
	@Override
	public void lossLife(String bossId,Integer loss,String uuid){
		BossEntity bossEntity= bossEntityMapper.selectByPrimaryKey(bossId);
		if(bossEntity==null) {
			throw new AppRuntimeException("boss不存在！");
		}
		if(loss>0) {
			if(bossEntity.getEvolume()!=0) {
			   long ev=bossEntity.getEvolume()-loss;
				if(ev<=0) {
					bossEntity.setKill_status(1);
					bossEntity.setKill_time(new Date());
					bossEntity.setKill_user_id(uuid);
					bossEntity.setEvolume(bossEntity.getEvolume());
//					bossEntity.setEvolume((long) 0);
					//map.put("msg","boss击杀" );
				}else {
					bossEntity.setEvolume(new Long(loss));
					//map.put("msg","boss正击杀" );
				}
			}else {
				//map.put("msg","boss已击杀" );
			}
			bossEntity.setUpdate_time(new Date());
//			bossEntityService.updateByIdSelective(bossEntity);
			
			if(bossEntityMapper.lossLife(CollectionUtil.objectToMap(bossEntity)) != 1){
				throw new AppRuntimeException("boss掉血失败！");
			}
		}
	}

	@Override
	public Page<Map> queryBossScoreRankPageList(Map paramMap, PageBean pageBean) {
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
		PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		return (Page<Map>) bossScoreMapper.getBossScoreRankList(paramMap);
	}
}
