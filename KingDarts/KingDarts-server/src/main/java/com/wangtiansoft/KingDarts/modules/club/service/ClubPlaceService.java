package com.wangtiansoft.KingDarts.modules.club.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.ClubPlace;

public interface ClubPlaceService extends IBaseService<ClubPlace, Integer>  {

	public Page<Map> queryNearClubPlaceInfoPageList(Map<String,Object> paramMap,PageBean pageBean);
	
	
	public Map queryNearClubPlaceInfo(Map<String,Object> paramMap);
	
	public Page<Map> queryAthleticsClubInfoPageList(Map paramMap,PageBean pageBean);
	
	public Page<Map> queryStrengthClubInfoPageList(Map paramMap,PageBean pageBean);
	
}
