package com.wangtiansoft.KingDarts.modules.medal.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Medal;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface MedalService extends IBaseService<Medal, Integer> {

    // 分页查询Medal
    Page<Map> queryMedalPageList(Map paramMap, PageBean pageBean);
    
    public Map selectMedalTerm(Map paramMap);
}

