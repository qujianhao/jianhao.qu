package com.wangtiansoft.KingDarts.modules.race.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.race.service.RaceInfoService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.RaceInfoMapper;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.persistence.entity.RaceInfo;
import com.wangtiansoft.KingDarts.results.core.RaceInfoResult;

@Transactional
@Service("raceInfoService")
public class RaceInfoServiceImpl extends BaseService<RaceInfo, Integer> implements RaceInfoService {

	@Autowired
    private RaceInfoMapper raceInfoMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return raceInfoMapper;
    }
    
    @Override
    public Page<Map> queryRaceInfoList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) raceInfoMapper.queryRaceInfoList(paramMap);
    }
    
    @Override
    public Page<Map> queryMyRaceInfoList(Map paramMap, PageBean pageBean){
    	paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) raceInfoMapper.queryMyRaceInfoList(paramMap);
    }
    
    @Override
    public RaceInfo saveRaceInfo(RaceInfoResult raceInfoResult) {
    	RaceInfo raceInfo = new RaceInfo();
    	BeanUtils.copyProperties(raceInfoResult,raceInfo);
    	
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        raceInfo.setRaceno("C"+newDate);
        raceInfo.setAdd_time(new Date());
        raceInfo.setUpdate_time(new Date());
        raceInfo.setIsvalid(1);
        raceInfoMapper.insert(raceInfo);
    	return raceInfo;
    }
    
    @Override
    public List<RaceInfo> getAllRaceInfoByStatus(){
    	return raceInfoMapper.getAllRaceInfoByStatus();
    }
    
    @Override 
    public Integer getCountByCno(String cno) {
    	return raceInfoMapper.getCountByCno(cno);
    }
    
    @Override
    public RaceInfoResult getRaceByRaceNo(String raceno) {
    	return raceInfoMapper.getRaceByRaceNo(raceno);
    }
    
    @Override
    public Integer getCountByMonth(String cno,String add_time) {
    	return raceInfoMapper.getCountByMonth(cno, add_time);
    }
    
    @Override
    public Integer getCountByCnoAndPersonNum(String cno,Integer minimum_num) {
    	return raceInfoMapper.getCountByCnoAndPersonNum(cno, minimum_num);
    }
}
