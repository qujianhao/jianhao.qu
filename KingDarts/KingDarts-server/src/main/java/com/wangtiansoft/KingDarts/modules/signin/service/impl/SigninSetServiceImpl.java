package com.wangtiansoft.KingDarts.modules.signin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.signin.service.SigninSetService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.SigninSetMapper;
import com.wangtiansoft.KingDarts.persistence.entity.SigninSet;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("signinSetService")
public class SigninSetServiceImpl extends BaseService<SigninSet, Integer> implements SigninSetService{

    @Autowired
    private SigninSetMapper signinSetMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return signinSetMapper;
    }

    @Override
    public Page<Map> querySigninSetPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) signinSetMapper.querySigninSetList(paramMap);
    }
}
