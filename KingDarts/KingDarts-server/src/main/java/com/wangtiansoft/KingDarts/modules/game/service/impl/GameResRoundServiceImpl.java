package com.wangtiansoft.KingDarts.modules.game.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.game.service.GameResRoundService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.GameResRoundMapper;
import com.wangtiansoft.KingDarts.persistence.entity.GameResRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("gameResRoundService")
public class GameResRoundServiceImpl extends BaseService<GameResRound, Integer> implements GameResRoundService{

    @Autowired
    private GameResRoundMapper gameResRoundMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return gameResRoundMapper;
    }

    @Override
    public Page<Map> queryGameResRoundPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) gameResRoundMapper.queryGameResRoundList(paramMap);
    }
}
