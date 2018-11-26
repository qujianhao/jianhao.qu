package com.wangtiansoft.KingDarts.modules.medalWinner.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.modules.medalWinner.service.MedalWinnerService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.MedalWinnerMapper;
import com.wangtiansoft.KingDarts.persistence.entity.MedalWinner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("medalWinnerService")
public class MedalWinnerServiceImpl extends BaseService<MedalWinner, Integer> implements MedalWinnerService{

    @Autowired
    private MedalWinnerMapper medalWinnerMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return medalWinnerMapper;
    }

    @Override
    public Page<Map> queryMedalWinnerPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) medalWinnerMapper.queryMedalWinnerList(paramMap);
    }

	@Override
	public List<Map> queryMedalWinnerList(Map paramMap) {
		return  medalWinnerMapper.queryMedalWinnerList(paramMap);
	}
}
