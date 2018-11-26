package com.wangtiansoft.KingDarts.modules.appVersion.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.AppVersion;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface AppVersionService extends IBaseService<AppVersion, Integer> {

    // 分页查询AppVersion
    Page<Map> queryAppVersionPageList(Map paramMap, PageBean pageBean);

	Integer getMaxVersion();
}

