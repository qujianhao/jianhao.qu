package com.wangtiansoft.KingDarts.modules.medalWinner.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.MedalWinner;

/**
* Created by wt-templete-helper.
 */
public interface MedalWinnerService extends IBaseService<MedalWinner, Integer> {

    // 分页查询MedalWinner
    Page<Map> queryMedalWinnerPageList(Map paramMap, PageBean pageBean);
    
    List<Map> queryMedalWinnerList(Map paramMap);
}

