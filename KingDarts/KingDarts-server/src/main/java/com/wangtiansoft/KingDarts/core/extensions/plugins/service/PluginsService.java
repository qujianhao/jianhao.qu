package com.wangtiansoft.KingDarts.core.extensions.plugins.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Plugins;

import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface PluginsService extends IBaseService<Plugins, Integer> {

    // 分页查询Plugins
    Page<Map> queryPluginsPageList(Map paramMap, PageBean pageBean);
}

