package com.wangtiansoft.KingDarts.modules.equip.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.common.utils.CollectionUtil;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquAuthService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquLineService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.EquInfoMapper;
import com.wangtiansoft.KingDarts.persistence.entity.EquAuth;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;
import com.wangtiansoft.KingDarts.persistence.entity.EquLine;
import com.wangtiansoft.KingDarts.results.core.EquAuthResult;
import com.wangtiansoft.KingDarts.results.core.EquInfoResult;

import tk.mybatis.mapper.entity.Example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("equInfoService")
public class EquInfoServiceImpl extends BaseService<EquInfo, String> implements EquInfoService{

    @Autowired
    private EquInfoMapper equInfoMapper;
    @Autowired
    private EquLineService equLineService;
    @Autowired
    private EquAuthService equAuthService;

    @Override
    public BaseMapper getBaseMapper() {
        return equInfoMapper;
    }

    @Override
    public Page<Map> queryEquInfoPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL))){
        	paramMap.put(SQLUtil.SQL_OrderSQL, "add_time desc");
        }
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) equInfoMapper.queryEquInfoList(paramMap);
    }
    
    @Override
    public void updateByNoSelective(EquInfo equInfo){
    	EquInfo record = new EquInfo();
    	record.setEquno(equInfo.getEquno());
    	record = equInfoMapper.selectOne(record);
    	
    	if(record == null){
    		throw new AppRuntimeException("设备不存在");
    	}
    	equInfo.setId(record.getId());
		if(equInfoMapper.updateByPrimaryKeySelective(equInfo)!=1){
			throw new AppRuntimeException("更新失败");
		}
    }
    
    @Override
    public EquInfo getEquInfoByNo(String equno){
    	EquInfo record = new EquInfo();
    	record.setEquno(equno);
    	return equInfoMapper.selectOne(record);
    }
    
    @Override
    public EquInfoResult getEquInfoResultByNo(String equno){
    	EquInfo record = new EquInfo();
    	record.setEquno(equno);
    	EquInfo equInfo = equInfoMapper.selectOne(record);
    	if(equInfo==null){
    		return null;
    	}
    	EquInfoResult equInfoResult = new EquInfoResult();
    	BeanUtil.copyProperties(equInfo, equInfoResult);
    	
    	EquAuthResult equAuthResult = new EquAuthResult();
    	EquAuth equAuth = equAuthService.getEquAuthByNo(equno);
    	if(equAuth!=null){
    		BeanUtil.copyProperties(equAuth, equAuthResult);
    		equInfoResult.setEquAuth(equAuthResult);
    	}
    	return equInfoResult;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void equLogin(String id,String equno,String ip,String serverIp){
    	EquLine equLine = new EquLine();
		equLine.setEquno(equno);
		equLine.setIp_address(ip);
		equLine.setLine_time(new Date());
		equLine.setOff_type(Constants.off_type_normal);
		equLine.setServer_ip_address(serverIp);
		equLine.setIsline(Constants.True);
		equLineService.save(equLine);

		EquInfo entity = new EquInfo();
		entity.setId(id);
		entity.setIsline(Constants.True);
		entity.setLast_online(new Date());
		equInfoMapper.updateByPrimaryKeySelective(entity);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void equLogout(String equno,int offType){
    	Map paramMap = new HashMap<>();
    	paramMap.put("equno", equno);
    	paramMap.put("isline", Constants.True);
    	List<Map> list = equLineService.queryEquLineList(paramMap);
    	for(Map m:list){
    		EquLine equLine = (EquLine)CollectionUtil.mapToObject(m, EquLine.class);
    		equLine.setOff_time(new Date());
    		equLine.setOff_type(offType);
    		equLine.setIsline(Constants.False);
    		Long s = (equLine.getLine_time().getTime() - equLine.getOff_time().getTime())/1000;
    		equLine.setOnline_times(s.intValue());
    		equLineService.updateEquLine(equLine);
    	}
    	
    	EquInfo record = new EquInfo();
    	record.setIsline(Constants.False);
    	record.setLast_online(new Date());
    	Example example = new Example(EquInfo.class);
    	example.createCriteria().andEqualTo("equno", equno);
    	equInfoMapper.updateByExampleSelective(record, example);
    }
    
    @Override
    public Page<Map> queryEquOnliePageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) equInfoMapper.queryEquOnlieList(paramMap);
    }
    
    @Override
    public BigDecimal getGamePpriceByEquNo(String equno) {
    	return equInfoMapper.getGamePpriceByEquNo(equno);
    }
    
    @Override
    public Integer updateManagePrice(String equno,BigDecimal game_price) {
    	return equInfoMapper.updateManagePrice(equno,game_price);
    }
    
    @Override
    public Integer offLine() {
    	return equInfoMapper.equOffLine();
    }
    
    @Override
    public List<Map> queryBookedList(String userId){
    	Map<String,Object> paramMap = new HashMap<>();
    	paramMap.put("booked_user", userId);
    	return equInfoMapper.queryEquList(paramMap);
    }
    
    @Override
    public void unBooked(String booked_user,String equno){
    	if(StringUtils.isEmpty(booked_user) ){
    		if(equInfoMapper.cancelBookedUser(equno)!=1){
    			throw new AppRuntimeException("更新失败");
    		}
    	}else{
    		if(equInfoMapper.updateBookedUser(equno,booked_user)!=1){
    			throw new AppRuntimeException("更新失败");
    		}
    	}
    	
    }
    
    @Override
    public Page<Map> queryEquStatistics(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) equInfoMapper.queryEquStatistics(paramMap);
    }
    
}
