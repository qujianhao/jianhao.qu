package com.wangtiansoft.KingDarts.modules.user.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.UserOpenid;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface UserOpenidService extends IBaseService<UserOpenid, Integer> {

    // 分页查询UserOpenid
    Page<Map> queryUserOpenidPageList(Map paramMap, PageBean pageBean);
}

