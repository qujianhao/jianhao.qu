package com.wangtiansoft.KingDarts.modules.appVersion.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.appVersion.service.AppVersionService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.AppVersionMapper;
import com.wangtiansoft.KingDarts.persistence.entity.AppVersion;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("appVersionService")
public class AppVersionServiceImpl extends BaseService<AppVersion, Integer> implements AppVersionService{

    @Autowired
    private AppVersionMapper appVersionMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return appVersionMapper;
    }

    @Override
    public Page<Map> queryAppVersionPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL).toString())){
        	paramMap.put(SQLUtil.SQL_OrderSQL, " app_version desc");
        }
        return (Page<Map>) appVersionMapper.queryAppVersionList(paramMap);
    }
    
    @Override
    public Integer getMaxVersion(){
    	Integer maxVersion = appVersionMapper.getMaxVersion();
    	return maxVersion == null ? 1 :maxVersion + 1;
    }
}
