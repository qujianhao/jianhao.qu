package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Plugins;

import java.util.List;
import java.util.Map;

public interface PluginsMapper extends BaseMapper<Plugins> {

    List<Map> queryPluginsList(Map paramMap);

}