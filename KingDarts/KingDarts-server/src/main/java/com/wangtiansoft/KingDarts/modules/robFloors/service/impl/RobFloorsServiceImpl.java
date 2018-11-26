package com.wangtiansoft.KingDarts.modules.robFloors.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.modules.robFloors.service.RobFloorsService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.RobFloorsMapper;
import com.wangtiansoft.KingDarts.persistence.entity.RobFloors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("robFloorsService")
public class RobFloorsServiceImpl extends BaseService<RobFloors, Integer> implements RobFloorsService{

    @Autowired
    private RobFloorsMapper robFloorsMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return robFloorsMapper;
    }

    @Override
    public Page<Map> queryRobFloorsPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) robFloorsMapper.queryRobFloorsList(paramMap);
    }

	@Override
	public List<Map> queryRobFloorsList(Map paramMap) {
		return robFloorsMapper.queryRobFloorsList(paramMap);
	}
}
