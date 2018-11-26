package com.wangtiansoft.KingDarts.persistence.dao.master;

import java.util.List;
import java.util.Map;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.ClubPlace;

public interface ClubPlaceMapper extends BaseMapper<ClubPlace> {

	List<Map> queryNearClubPlaceInfoList(Map paramMap);
	
	public Map queryNearClubPlaceInfo(Map<String, Object> paramMap);
	
	List<Map> queryAthleticsClubInfoList(Map paramMap);
	
	List<Map> queryStrengthClubInfoList(Map paramMap);
}
