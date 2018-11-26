package com.wangtiansoft.KingDarts.core.extensions.plugins.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.extensions.plugins.service.PluginsService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.PluginsMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Plugins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("pluginsService")
public class PluginsServiceImpl extends BaseService<Plugins, Integer> implements PluginsService {

    @Autowired
    private PluginsMapper pluginsMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return pluginsMapper;
    }

    @Override
    public Page<Map> queryPluginsPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderBy(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) pluginsMapper.queryPluginsList(paramMap);
    }
}
