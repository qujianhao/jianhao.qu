package com.wangtiansoft.KingDarts.modules.advert.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.AdvertInfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface AdvertInfoService extends IBaseService<AdvertInfo, Integer> {

    // 分页查询AdvertInfo
    Page<Map> queryAdvertInfoPageList(Map paramMap, PageBean pageBean);

	Page<Map> queryAdvertInfoPageListByclubId(Map paramMap, PageBean pageBean);

	String queryEquclubByEquno(String equno);

	Page<Map> queryAdvertInfoPageListNoclub(Map<String, Object> paramMap, PageBean pageBean);
}

