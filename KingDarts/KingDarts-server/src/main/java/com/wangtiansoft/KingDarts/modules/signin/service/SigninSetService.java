package com.wangtiansoft.KingDarts.modules.signin.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.SigninSet;

/**
* Created by wt-templete-helper.
 */
public interface SigninSetService extends IBaseService<SigninSet, Integer> {

    // 分页查询SigninSet
    Page<Map> querySigninSetPageList(Map paramMap, PageBean pageBean);
}

