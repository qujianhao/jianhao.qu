package com.wangtiansoft.KingDarts.modules.signin.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Signin;

import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface SigninService extends IBaseService<Signin, Integer> {

    // 分页查询Signin
    Page<Map> querySigninPageList(Map paramMap, PageBean pageBean);
}

