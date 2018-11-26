package com.wangtiansoft.KingDarts.modules.game.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.GameResPlayer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface GameResPlayerService extends IBaseService<GameResPlayer, Integer> {

    // 分页查询GameResPlayer
    Page<Map> queryGameResPlayerPageList(Map paramMap, PageBean pageBean);

	void saveGameResult(String orderNo, List<Map> list, String netNo, String win);

	void saveGameResult(String orderNo, List<Map> list, String netNo, String win, Map attach);

}

