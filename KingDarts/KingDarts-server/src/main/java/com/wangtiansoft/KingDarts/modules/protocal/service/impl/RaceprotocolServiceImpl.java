package com.wangtiansoft.KingDarts.modules.protocal.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.protocal.service.RaceprotocolService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.RaceprotocolMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Raceprotocol;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("raceprotocolService")
public class RaceprotocolServiceImpl extends BaseService<Raceprotocol, Integer> implements RaceprotocolService{

    @Autowired
    private RaceprotocolMapper raceprotocolMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return raceprotocolMapper;
    }

    @Override
    public Page<Map> queryRaceprotocolPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL))){
        	paramMap.put(SQLUtil.SQL_OrderSQL, " update_time desc");
        }
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) raceprotocolMapper.queryRaceprotocolList(paramMap);
    }
}
