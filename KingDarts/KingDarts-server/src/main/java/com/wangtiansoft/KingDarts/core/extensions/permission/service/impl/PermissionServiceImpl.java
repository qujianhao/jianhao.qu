package com.wangtiansoft.KingDarts.core.extensions.permission.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.extensions.permission.service.PermissionService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.PermEntityMapper;
import com.wangtiansoft.KingDarts.persistence.entity.PermEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("permissionService")
public class PermissionServiceImpl extends BaseService<PermEntity, Integer> implements PermissionService {

    @Autowired
    private PermEntityMapper permEntityMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return permEntityMapper;
    }

}
