package com.wangtiansoft.KingDarts.core.extensions.role.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.extensions.role.service.RoleService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.RoleEntityMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Role;
import com.wangtiansoft.KingDarts.results.core.RoleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("roleService")
public class RoleServiceImpl extends BaseService<Role, Integer> implements RoleService {

    @Autowired
    private RoleEntityMapper roleEntityMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return roleEntityMapper;
    }

    @Override
    public Page<Map> queryRolePageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) roleEntityMapper.queryRoleList(paramMap);
    }
    
    @Override
	public List<RoleResult> queryRolePageList(Map paramMap) {
		List<RoleResult> roles = roleEntityMapper.queryRoleResultList(paramMap);
		return roles;
	}
}
