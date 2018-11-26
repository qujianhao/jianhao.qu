package com.wangtiansoft.KingDarts.modules.race.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.RaceInfo;
import com.wangtiansoft.KingDarts.results.core.RaceInfoResult;

public interface RaceInfoService extends IBaseService<RaceInfo, Integer>  {
	
	Page<Map> queryRaceInfoList(Map paramMap, PageBean pageBean);
	
	Page<Map> queryMyRaceInfoList(Map paramMap, PageBean pageBean);

	RaceInfo saveRaceInfo(RaceInfoResult raceInfoResult);
	
	List<RaceInfo> getAllRaceInfoByStatus();
	
	Integer getCountByCno(String cno);
	
	RaceInfoResult getRaceByRaceNo(String raceno);
	
	//查询该俱乐部当月举办赛事的场数
	Integer getCountByMonth(String cno,String add_time);
	
	//根据俱乐部编号及报名人数查询
	Integer getCountByCnoAndPersonNum(String cno,Integer minimum_num);
}
