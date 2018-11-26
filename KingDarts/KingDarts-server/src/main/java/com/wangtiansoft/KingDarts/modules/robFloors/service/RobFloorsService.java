package com.wangtiansoft.KingDarts.modules.robFloors.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.RobFloors;

/**
* Created by wt-templete-helper.
 */
public interface RobFloorsService extends IBaseService<RobFloors, Integer> {

    // 分页查询RobFloors
    Page<Map> queryRobFloorsPageList(Map paramMap, PageBean pageBean);
    
    List<Map> queryRobFloorsList(Map paramMap);
}

