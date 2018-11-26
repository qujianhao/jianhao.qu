package com.wangtiansoft.KingDarts.persistence.dao.master;


import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.RoleEntity;
import com.wangtiansoft.KingDarts.results.core.RoleResult;

import java.util.List;
import java.util.Map;

public interface RoleEntityMapper extends BaseMapper<RoleEntity> {

    List<Map> queryRoleList(Map paramMap);

    List<RoleResult> queryRoleResultList(Map paramMap);

}