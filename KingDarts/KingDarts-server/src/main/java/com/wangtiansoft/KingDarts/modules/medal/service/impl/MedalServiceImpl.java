package com.wangtiansoft.KingDarts.modules.medal.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.modules.medal.service.MedalService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.MedalMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Medal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Map.Entry;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("medalService")
public class MedalServiceImpl extends BaseService<Medal, Integer> implements MedalService{

    @Autowired
    private MedalMapper medalMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return medalMapper;
    }

    @Override
    public Page<Map> queryMedalPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) medalMapper.queryMedalList(paramMap);
    }

	@Override
	public Map selectMedalTerm(Map paramMap) {
		return medalMapper.selectMedalTerm(paramMap);
	}
}
