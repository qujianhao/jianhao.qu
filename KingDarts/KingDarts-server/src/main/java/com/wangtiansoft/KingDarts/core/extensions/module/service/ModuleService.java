package com.wangtiansoft.KingDarts.core.extensions.module.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Module;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface ModuleService extends IBaseService<Module, Integer> {

    // 分页查询Module
    Page<Map> queryModulePageList(Map paramMap, PageBean pageBean);

    //  查询模块子列表
    List<Map> queryModuleMapList(String parentId);

    //  查询模块
    LinkedHashMap<String, List<Module>> queryModuleMap(Integer userId);
}

