package com.wangtiansoft.KingDarts.modules.equip.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.core.utils.AuthUtil;
import com.wangtiansoft.KingDarts.modules.equip.service.EquAuthService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.EquAuthMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.EquInfoMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Account;
import com.wangtiansoft.KingDarts.persistence.entity.EquAuth;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;
import com.wangtiansoft.KingDarts.results.core.EquAuthResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("equAuthService")
public class EquAuthServiceImpl extends BaseService<EquAuth, Integer> implements EquAuthService{

    @Autowired
    private EquAuthMapper equAuthMapper;
    @Autowired
    private EquInfoService equInfoService;

    @Override
    public BaseMapper getBaseMapper() {
        return equAuthMapper;
    }

    @Override
    public Page<Map> queryEquAuthPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) equAuthMapper.queryEquAuthList(paramMap);
    }
    
    @Override
    public Page<Map> getEquNoAuthListByAgno(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) equAuthMapper.getEquNoAuthListByAgno(paramMap);
    }
    
    @Override
    public Page<Map> getEquYesAuthListByAgno(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) equAuthMapper.getEquYesAuthListByAgno(paramMap);
    }
    
    @Override
    public EquAuth getEquAuthByNo(String equno){
    	EquAuth record = new EquAuth();
    	record.setEquno(equno);
    	return equAuthMapper.selectOne(record);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(EquAuthResult equAuthResult){
    	EquAuth equAuth = BeanUtil.cast(equAuthResult, EquAuth.class);
    	
    	if(equAuth.getId()==null){
    		//设置默认值
        	equAuth.setAdd_time(new Date());
        	equAuth.setAcc_type(1);
        	equAuth.setAuth_time(new Date());
        	if(equAuthMapper.insert(equAuth)!=1){
        		throw new AppRuntimeException("新增失败");
        	}
        	
        	EquInfo equInfo = new EquInfo();
        	equInfo.setEquno(equAuth.getEquno());
        	equInfo.setEqu_status(Constants.equ_status_auth);
        	equInfoService.updateByNoSelective(equInfo);
    	}else{
    		if(equAuthMapper.updateByPrimaryKeySelective(equAuth)!=1){
    			throw new AppRuntimeException("更新失败");
    		}
    		if(equAuthResult.getEqu_status()!=null){
    			EquInfo equInfo = new EquInfo();
            	equInfo.setEquno(equAuth.getEquno());
            	equInfo.setEqu_status(equAuthResult.getEqu_status());
            	equInfoService.updateByNoSelective(equInfo);
    		}
    	}
    	
    }
    
    @Override
    public Integer updateAgnoAuthByEquno(String equno,String agno,String auth_name) {
    	return equAuthMapper.updateAgnoAuthByEquno(equno, agno,auth_name);
    }
    
    @Override
    public Integer updateCnoAuthByEquno(String equno,String cno,String auth_name) {
    	return equAuthMapper.updateCnoAuthByEquno(equno, cno,auth_name);
    }
    
    @Override
    public Integer getYesActivationByAuthNo(String auth_no) {
    	return equAuthMapper.getYesActivationByAuthNo(auth_no);
    }
    
    @Override
    public Integer getNoActivationByAuthNo(String auth_no) {
    	return equAuthMapper.getNoActivationByAuthNo(auth_no);
    }
    
    @Override
    public Integer getEquNoAuthCountByAgno(String auth_no) {
    	return equAuthMapper.getEquNoAuthCountByAgno(auth_no);
    }
    
    @Override
    public Integer getEquYesAuthCountByAgno(String agno) {
    	return equAuthMapper.getEquYesAuthCountByAgno(agno);
    }
}
