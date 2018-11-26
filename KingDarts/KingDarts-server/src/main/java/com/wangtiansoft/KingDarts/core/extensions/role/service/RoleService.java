package com.wangtiansoft.KingDarts.core.extensions.role.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Role;
import com.wangtiansoft.KingDarts.results.core.RoleResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface RoleService extends IBaseService<Role, Integer> {

    // 分页查询Role
    Page<Map> queryRolePageList(Map paramMap, PageBean pageBean);

	List<RoleResult> queryRolePageList(Map paramMap);
}

