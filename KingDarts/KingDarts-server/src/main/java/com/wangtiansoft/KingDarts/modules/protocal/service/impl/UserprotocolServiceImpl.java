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
import com.wangtiansoft.KingDarts.modules.protocal.service.UserprotocolService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserprotocolMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Userprotocol;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("userprotocolService")
public class UserprotocolServiceImpl extends BaseService<Userprotocol, Integer> implements UserprotocolService{

    @Autowired
    private UserprotocolMapper userprotocolMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return userprotocolMapper;
    }

    @Override
    public Page<Map> queryUserprotocolPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL))){
        	paramMap.put(SQLUtil.SQL_OrderSQL, " update_time desc");
        }
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) userprotocolMapper.queryUserprotocolList(paramMap);
    }
}
