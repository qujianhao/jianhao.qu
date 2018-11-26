package com.wangtiansoft.KingDarts.modules.game.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.GameResRound;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface GameResRoundService extends IBaseService<GameResRound, Integer> {

    // 分页查询GameResRound
    Page<Map> queryGameResRoundPageList(Map paramMap, PageBean pageBean);
}

