package com.wangtiansoft.KingDarts.modules.equip.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.EquLine;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface EquLineService extends IBaseService<EquLine, Integer> {

    // 分页查询EquLine
    Page<Map> queryEquLinePageList(Map paramMap, PageBean pageBean);

	List<Map> queryEquLineList(Map paramMap);

	void updateEquLine(EquLine equLine);
}

