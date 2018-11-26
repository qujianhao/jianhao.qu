package com.wangtiansoft.KingDarts.modules.game.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Game;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface GameService extends IBaseService<Game, Integer> {

    // 分页查询Game
    Page<Map> queryGamePageList(Map paramMap, PageBean pageBean);
}

