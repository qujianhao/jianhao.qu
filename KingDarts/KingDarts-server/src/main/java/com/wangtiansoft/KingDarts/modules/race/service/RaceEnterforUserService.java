package com.wangtiansoft.KingDarts.modules.race.service;

import java.util.List;

import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.RaceEnterforUser;
import com.wangtiansoft.KingDarts.results.core.RaceEnterforUserResult;

public interface RaceEnterforUserService extends IBaseService<RaceEnterforUser, Integer>  {

	RaceEnterforUserResult getEnterforByReceno(String raceno);
	
	Integer getCountUserByRaceno(String raceno);
	
	Integer saveRanking(List<RaceEnterforUserResult> raceEnterforUserList);
	
	List<RaceEnterforUserResult> getRaceUserByRaceno(String raceno);
	
	//比赛报名
	String addEnterfor(String raceno,String user_id);
}
