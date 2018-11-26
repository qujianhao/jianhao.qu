package com.wangtiansoft.KingDarts.modules.protocal.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Raceprotocol;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface RaceprotocolService extends IBaseService<Raceprotocol, Integer> {

    // 分页查询Raceprotocol
    Page<Map> queryRaceprotocolPageList(Map paramMap, PageBean pageBean);
}

