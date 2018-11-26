package com.wangtiansoft.KingDarts.modules.equip.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.EquAuth;
import com.wangtiansoft.KingDarts.results.core.EquAuthResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* Created by wt-templete-helper.
 */
public interface EquAuthService extends IBaseService<EquAuth, Integer> {

    // 分页查询EquAuth
    Page<Map> queryEquAuthPageList(Map paramMap, PageBean pageBean);

	void saveOrUpdate(EquAuthResult equAuthResult);

	EquAuth getEquAuthByNo(String equno);
	
	//查询俱乐部已激活设备
    Integer getYesActivationByAuthNo(String auth_no);
    
    //查询俱乐部未激活设备
    Integer getNoActivationByAuthNo(String auth_no);
	
	//查询代理商下未授权设备列表
	Page<Map> getEquNoAuthListByAgno(Map paramMap,PageBean pageBean);
	
	//查询代理商下已授权设备列表
	Page<Map> getEquYesAuthListByAgno(Map paramMap,PageBean pageBean);
	
	//商户端代理商取消设备授权
    Integer updateAgnoAuthByEquno(String equno,String agno,String auth_name);
    
    //商户端代理商设备授权俱乐部
    Integer updateCnoAuthByEquno(String equno,String cno,String auth_name);
    
    //查询代理商下未授权设备数量
    Integer getEquNoAuthCountByAgno(String auth_no);
    
    //查询代理商下已授权设备数量
    Integer getEquYesAuthCountByAgno(String agno);
}

