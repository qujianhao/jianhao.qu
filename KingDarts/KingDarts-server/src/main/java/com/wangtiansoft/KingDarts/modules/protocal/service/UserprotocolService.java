package com.wangtiansoft.KingDarts.modules.protocal.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Userprotocol;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface UserprotocolService extends IBaseService<Userprotocol, Integer> {

    // 分页查询Userprotocol
    Page<Map> queryUserprotocolPageList(Map paramMap, PageBean pageBean);
}

