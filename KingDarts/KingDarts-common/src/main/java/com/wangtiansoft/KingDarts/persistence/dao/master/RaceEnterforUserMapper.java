package com.wangtiansoft.KingDarts.persistence.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.RaceEnterforUser;
import com.wangtiansoft.KingDarts.persistence.entity.RaceInfo;
import com.wangtiansoft.KingDarts.results.core.RaceEnterforUserResult;

public interface RaceEnterforUserMapper extends BaseMapper<RaceEnterforUser> {

	RaceEnterforUserResult getEnterforByReceno(String raceno);
	
	@Select("select count(*) from darts_race_enterfor_user where raceno=#{raceno}")
    Integer getCountUserByRaceno(String raceno);
	
	@Select("select dreu.*,"
			+ "wu.username,wu.nickname,wu.headimgurl,wu.mobile "
			+ "from darts_race_enterfor_user as dreu "
			+ "left join wt_user as wu on wu.uuid=dreu.user_id "
			+ "where dreu.raceno=#{raceno} order by dreu.ranking asc,dreu.id asc")
	List<RaceEnterforUserResult> getRaceUserByRaceno(String raceno);
}
