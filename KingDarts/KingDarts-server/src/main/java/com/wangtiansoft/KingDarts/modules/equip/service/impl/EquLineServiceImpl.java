package com.wangtiansoft.KingDarts.modules.equip.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquLineService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.EquLineMapper;
import com.wangtiansoft.KingDarts.persistence.entity.EquLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("equLineService")
public class EquLineServiceImpl extends BaseService<EquLine, Integer> implements EquLineService{

    @Autowired
    private EquLineMapper equLineMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return equLineMapper;
    }

    @Override
    public Page<Map> queryEquLinePageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) equLineMapper.queryEquLineList(paramMap);
    }
    
    @Override
    public List<Map> queryEquLineList(Map paramMap){
    	return equLineMapper.queryEquLineList(paramMap);
    }
    
    @Override
    public void updateEquLine(EquLine equLine){
    	equLineMapper.updateByPrimaryKeySelective(equLine);
    }
}
