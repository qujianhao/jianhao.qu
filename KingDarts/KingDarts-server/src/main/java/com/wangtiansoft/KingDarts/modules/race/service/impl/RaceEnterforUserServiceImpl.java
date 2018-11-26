package com.wangtiansoft.KingDarts.modules.race.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.core.utils.GameUtil;
import com.wangtiansoft.KingDarts.modules.race.service.RaceEnterforUserService;
import com.wangtiansoft.KingDarts.modules.race.service.RaceInfoService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.RaceEnterforUserMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserPointsMapper;
import com.wangtiansoft.KingDarts.persistence.entity.RaceEnterforUser;
import com.wangtiansoft.KingDarts.persistence.entity.RaceInfo;
import com.wangtiansoft.KingDarts.persistence.entity.UserPoints;
import com.wangtiansoft.KingDarts.results.core.RaceEnterforUserResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Transactional
@Service("raceEnterforUserService")
public class RaceEnterforUserServiceImpl extends BaseService<RaceEnterforUser, Integer> implements RaceEnterforUserService {

	@Autowired
    private RaceEnterforUserMapper raceEnterforUserMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserPointsMapper userPointsMapper;
	
	@Resource
	private RaceInfoService raceInfoService;

    @Override
    public BaseMapper getBaseMapper() {
        return raceEnterforUserMapper;
    }
    
    @Override
    public RaceEnterforUserResult getEnterforByReceno(String raceno) {
    	return raceEnterforUserMapper.getEnterforByReceno(raceno);
    }
    
    @Override
    public Integer getCountUserByRaceno(String raceno) {
    	return raceEnterforUserMapper.getCountUserByRaceno(raceno);
    }
    
    @Override
    public Integer saveRanking(List<RaceEnterforUserResult> raceEnterforUserLists) {
    	Integer count=0;
    	String raceno =  raceEnterforUserLists.get(0).getRaceno();
    	//根据赛事编号查询赛事详情
    	Example example1=new Example(RaceInfo.class);
        Criteria cr1= example1.createCriteria();
        cr1.andEqualTo("raceno", raceno);
        RaceInfo raceInfo = raceInfoService.findOneByExample(example1);
    	
    	for(RaceEnterforUserResult raceEnterforUserResult:raceEnterforUserLists) {
    		RaceEnterforUser raceEnterforUser = new RaceEnterforUser();
        	BeanUtils.copyProperties(raceEnterforUserResult,raceEnterforUser);
        	//最低8人比赛
        	if(raceInfo.getMinimum_num()==8) {
        		//第一名奖励积分200
            	if(raceEnterforUser.getRanking()==1) {
            		raceEnterforUser.setReward_points(200);
            	}
            	//第二名奖励150
            	else if(raceEnterforUser.getRanking()==2) {
            		raceEnterforUser.setReward_points(150);
            	}
            	//第三四名奖励150
            	else if(raceEnterforUser.getRanking()==3||raceEnterforUser.getRanking()==4) {
            		raceEnterforUser.setReward_points(100);
            	}
            	//第5-8名奖励20
            	else if(raceEnterforUser.getRanking()>=5&&raceEnterforUser.getRanking()<=8) {
            		raceEnterforUser.setReward_points(20);
            	}else {
            		raceEnterforUser.setReward_points(0);
            	}
        	}
        	//最低16人比赛
        	if(raceInfo.getMinimum_num()==16) {
        		//第一名奖励积分400
            	if(raceEnterforUser.getRanking()==1) {
            		raceEnterforUser.setReward_points(400);
            	}
            	//第二名奖励300
            	else if(raceEnterforUser.getRanking()==2) {
            		raceEnterforUser.setReward_points(300);
            	}
            	//第三四名奖励200
            	else if(raceEnterforUser.getRanking()==3||raceEnterforUser.getRanking()==4) {
            		raceEnterforUser.setReward_points(200);
            	}
            	//第5-8名奖励100
            	else if(raceEnterforUser.getRanking()>=5&&raceEnterforUser.getRanking()<=8) {
            		raceEnterforUser.setReward_points(100);
            	}
            	//第9-16名奖励20
            	else if(raceEnterforUser.getRanking()>=9&&raceEnterforUser.getRanking()<=16) {
            		raceEnterforUser.setReward_points(20);
            	}else {
            		raceEnterforUser.setReward_points(0);
            	}
        	}
        	//最低32人比赛
        	if(raceInfo.getMinimum_num()==32) {
        		//第一名奖励积分800
            	if(raceEnterforUser.getRanking()==1) {
            		raceEnterforUser.setReward_points(800);
            	}
            	//第二名奖励600
            	else if(raceEnterforUser.getRanking()==2) {
            		raceEnterforUser.setReward_points(600);
            	}
            	//第三四名奖励400
            	else if(raceEnterforUser.getRanking()==3||raceEnterforUser.getRanking()==4) {
            		raceEnterforUser.setReward_points(400);
            	}
            	//第5-8名奖励200
            	else if(raceEnterforUser.getRanking()>=5&&raceEnterforUser.getRanking()<=8) {
            		raceEnterforUser.setReward_points(200);
            	}
            	//第9-16名奖励100
            	else if(raceEnterforUser.getRanking()>=9&&raceEnterforUser.getRanking()<=16) {
            		raceEnterforUser.setReward_points(100);
            	}
            	//第17-32名奖励20
            	else if(raceEnterforUser.getRanking()>=17&&raceEnterforUser.getRanking()<=32) {
            		raceEnterforUser.setReward_points(20);
            	}else {
            		raceEnterforUser.setReward_points(0);
            	}
        	}
        	//当积分奖励不等于0时添加用户总积分
        	if(raceEnterforUser.getReward_points()!=0) {
        		Map<String,Object> map = new HashMap<>();
            	map.put("uuid", raceEnterforUser.getUser_id());
            	map.put("balance", 0);
        		map.put("points", raceEnterforUser.getReward_points());
        		if(userMapper.consumeRecharge(map) != 1){
            		throw new AppRuntimeException("系统错误，请联系管理员！");
            	}
        		
        		UserPoints userPoints = new UserPoints();
            	userPoints.setLog_time(new Date());
            	userPoints.setPoints(raceEnterforUser.getReward_points());
            	userPoints.setUser_id(raceEnterforUser.getUser_id());
            	userPoints.setPoints_type(1);
            	userPoints.setRemark(raceno+"比赛奖励");
            	userPointsMapper.insert(userPoints);
        	}
    		raceEnterforUserMapper.updateByPrimaryKeySelective(raceEnterforUser);
    		count++;
    	}
    	return count;
    }
    
    @Override
    public List<RaceEnterforUserResult> getRaceUserByRaceno(String raceno){
    	return raceEnterforUserMapper.getRaceUserByRaceno(raceno);
    }
    
    @Override
    public String addEnterfor(String raceno,String user_id) {
    	RaceEnterforUser raceEnterforUser = new RaceEnterforUser();
    	raceEnterforUser.setRaceno(raceno);
    	raceEnterforUser.setUser_id(user_id);
    	raceEnterforUser.setEnterfor_time(new Date());
    	raceEnterforUser.setAdd_time(new Date());
    	raceEnterforUser.setUpdate_time(new Date());
    	raceEnterforUserMapper.insert(raceEnterforUser);
    	return null;
    }
}
