package com.wangtiansoft.KingDarts.modules.game.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.game.service.GameResHitService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.GameResHitMapper;
import com.wangtiansoft.KingDarts.persistence.entity.GameResHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("gameResHitService")
public class GameResHitServiceImpl extends BaseService<GameResHit, Integer> implements GameResHitService{

    @Autowired
    private GameResHitMapper gameResHitMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return gameResHitMapper;
    }

    @Override
    public Page<Map> queryGameResHitPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) gameResHitMapper.queryGameResHitList(paramMap);
    }
}
