package com.wangtiansoft.KingDarts.modules.user.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.user.service.UserOpenidService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserOpenidMapper;
import com.wangtiansoft.KingDarts.persistence.entity.UserOpenid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("userOpenidService")
public class UserOpenidServiceImpl extends BaseService<UserOpenid, Integer> implements UserOpenidService{

    @Autowired
    private UserOpenidMapper userOpenidMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return userOpenidMapper;
    }

    @Override
    public Page<Map> queryUserOpenidPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) userOpenidMapper.queryUserOpenidList(paramMap);
    }
    
    
}
