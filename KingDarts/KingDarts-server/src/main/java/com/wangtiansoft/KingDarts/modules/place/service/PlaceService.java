package com.wangtiansoft.KingDarts.modules.place.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Place;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface PlaceService extends IBaseService<Place, Integer> {

    // 分页查询Place
    Page<Map> queryPlacePageList(Map paramMap, PageBean pageBean);
}

