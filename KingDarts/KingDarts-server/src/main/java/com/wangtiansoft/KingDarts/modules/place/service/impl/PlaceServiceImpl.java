package com.wangtiansoft.KingDarts.modules.place.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.modules.place.service.PlaceService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.PlaceMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("placeService")
public class PlaceServiceImpl extends BaseService<Place, Integer> implements PlaceService{

    @Autowired
    private PlaceMapper placeMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return placeMapper;
    }

    @Override
    public Page<Map> queryPlacePageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) placeMapper.queryPlaceList(paramMap);
    }
}
