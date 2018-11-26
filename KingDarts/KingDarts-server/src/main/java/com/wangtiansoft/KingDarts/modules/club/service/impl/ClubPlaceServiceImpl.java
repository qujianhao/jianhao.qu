package com.wangtiansoft.KingDarts.modules.club.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubPlaceService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.ClubPlaceMapper;
import com.wangtiansoft.KingDarts.persistence.entity.ClubPlace;

@Transactional
@Service("clubPlaceService")
public class ClubPlaceServiceImpl extends BaseService<ClubPlace, Integer> implements ClubPlaceService {
	
	@Autowired
	private ClubPlaceMapper clubPlaceMapper;

	@Override
	public BaseMapper getBaseMapper() {
		return clubPlaceMapper;
	}

	@Override
	public Page<Map> queryNearClubPlaceInfoPageList(Map<String,Object> paramMap,PageBean pageBean){
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		return (Page<Map>) clubPlaceMapper.queryNearClubPlaceInfoList(paramMap);
	}

	@Override
	public Map queryNearClubPlaceInfo(Map<String, Object> paramMap) {
		return clubPlaceMapper.queryNearClubPlaceInfo(paramMap);
	}
	
	@Override
	public Page<Map> queryAthleticsClubInfoPageList(Map paramMap,PageBean pageBean){
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		return (Page<Map>)clubPlaceMapper.queryAthleticsClubInfoList(paramMap);
	}
	
	@Override
	public Page<Map> queryStrengthClubInfoPageList(Map paramMap,PageBean pageBean){
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		return (Page<Map>)clubPlaceMapper.queryStrengthClubInfoList(paramMap);
	}
	
}
