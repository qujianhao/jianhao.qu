package com.wangtiansoft.KingDarts.modules.signin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.modules.signin.service.SigninService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.SigninMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Signin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("signinService")
public class SigninServiceImpl extends BaseService<Signin, Integer> implements SigninService{

    @Autowired
    private SigninMapper signinMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return signinMapper;
    }

    @Override
    public Page<Map> querySigninPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) signinMapper.querySigninList(paramMap);
    }
}
