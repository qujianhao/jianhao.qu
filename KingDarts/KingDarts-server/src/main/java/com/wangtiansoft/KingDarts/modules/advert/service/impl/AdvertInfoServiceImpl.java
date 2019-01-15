package com.wangtiansoft.KingDarts.modules.advert.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.advert.service.AdvertInfoService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.AdvertInfoMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.EquInfoMapper;
import com.wangtiansoft.KingDarts.persistence.entity.AdvertInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("advertInfoService")
public class AdvertInfoServiceImpl extends BaseService<AdvertInfo, Integer> implements AdvertInfoService{

    @Autowired
    private AdvertInfoMapper advertInfoMapper;

    @Autowired
    private EquInfoMapper equInfoMapper;


    
    @Override
    public BaseMapper getBaseMapper() {
        return advertInfoMapper;
    }

    @Override
    public Page<Map> queryAdvertInfoPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL))){
        	 paramMap.put(SQLUtil.SQL_OrderSQL, " update_time desc");
        }
        return (Page<Map>) advertInfoMapper.queryAdvertInfoList(paramMap);
    }

    @Override
    public Page<Map> queryAdvertInfoPageListByclubId(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL))){
        	 paramMap.put(SQLUtil.SQL_OrderSQL, " update_time desc");
        }
        return (Page<Map>) advertInfoMapper.queryAdvertInfoListByclubId(paramMap);
    }

    
	@Override
	public String queryEquclubByEquno(String equno) {

		
		 String belongClub=    equInfoMapper.queryEquclubByEquno(equno);
 
		return belongClub;
	}

	@Override
	public Page<Map> queryAdvertInfoPageListNoclub(Map<String, Object> paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL))){
        	 paramMap.put(SQLUtil.SQL_OrderSQL, " update_time desc");
        }
        return (Page<Map>) advertInfoMapper.queryAdvertInfoListNoclub(paramMap);
	}
}
