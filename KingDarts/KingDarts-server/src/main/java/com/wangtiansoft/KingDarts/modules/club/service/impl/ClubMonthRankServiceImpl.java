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
import com.wangtiansoft.KingDarts.modules.club.service.ClubMonthRankService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.ClubMonthRankMapper;
import com.wangtiansoft.KingDarts.persistence.entity.ClubMonthRank;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("clubMothRankService")
public class ClubMonthRankServiceImpl extends BaseService<ClubMonthRank, Integer> implements ClubMonthRankService{

    @Autowired
    private ClubMonthRankMapper clubMonthRankMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return clubMonthRankMapper;
    }

    @Override
    public Page<Map> queryClubMonthRankPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) clubMonthRankMapper.queryClubMonthRankList(paramMap);
    }
    
    @Override
    public Page<Map> queryAthleticsClubMonthRankInfoPageList(Map paramMap, PageBean pageBean){
    	paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
    	return (Page<Map>) clubMonthRankMapper.queryAthleticsClubMonthRankInfoList(paramMap);
    }
    
    @Override
    public Page<Map> queryStrengthClubMonthRankInfoPageList(Map paramMap, PageBean pageBean){
    	paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
    	return (Page<Map>) clubMonthRankMapper.queryStrengthClubMonthRankInfoList(paramMap);
    }

	@Override
	public void insertClubMonthRankList(Map paramMap) {
		clubMonthRankMapper.insertClubMonthRank(paramMap);		
	}
    
}
