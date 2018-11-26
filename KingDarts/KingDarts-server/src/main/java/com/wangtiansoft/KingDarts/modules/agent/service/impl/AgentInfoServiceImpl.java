package com.wangtiansoft.KingDarts.modules.agent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.agent.service.AgentInfoService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.AgentInfoMapper;
import com.wangtiansoft.KingDarts.persistence.entity.AgentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("agentInfoService")
public class AgentInfoServiceImpl extends BaseService<AgentInfo, Integer> implements AgentInfoService{

    @Autowired
    private AgentInfoMapper agentInfoMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return agentInfoMapper;
    }

    @Override
    public Page<Map> queryAgentInfoPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) agentInfoMapper.queryAgentInfoList(paramMap);
    }
    @Override
    public List<Map> queryAgentInfoList(Map paramMap) {
    	return agentInfoMapper.queryAgentInfoList(paramMap);
    }
    
    @Override
    public Page<Map> queryAgentEquInfoList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) agentInfoMapper.queryAgentEquInfoList(paramMap);
    }
    
    @Override
    public void commission(String agno,BigDecimal incomes){
    	Map<String,Object> paramMap = new HashMap<>();
    	paramMap.put("agno", agno);
    	paramMap.put("incomes", incomes);
    	paramMap.put("resum", incomes);
    	if(agentInfoMapper.rechargeCommission(paramMap)!=1){
    		throw new AppRuntimeException("更新代理商金额失败");
    	}
    }
    
    @Override
    public BigDecimal getResumByAgno(String agno) {
    	return agentInfoMapper.getResumByAgno(agno);
    }
    
    @Override
    public Integer updatePasswordByAgno(String ag_password,String agno) {
    	return agentInfoMapper.updatePasswordByAgno(ag_password, agno);
    }
    
    @Override
    public void updateResumByAgno(BigDecimal resum,String agno) {
    	Map<String,Object> paramMap = new HashMap<>();
    	paramMap.put("agno", agno);
    	paramMap.put("resum", resum);
    	if(agentInfoMapper.updateResumByAgno(paramMap)!=1) {
    		throw new AppRuntimeException("更新代理商金额失败");
    	}
    }
}
