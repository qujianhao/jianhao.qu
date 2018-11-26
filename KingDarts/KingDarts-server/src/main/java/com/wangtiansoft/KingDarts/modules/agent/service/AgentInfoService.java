package com.wangtiansoft.KingDarts.modules.agent.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.AgentInfo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* Created by wt-templete-helper.
 */
public interface AgentInfoService extends IBaseService<AgentInfo, Integer> {

    // 分页查询AgentInfo
    Page<Map> queryAgentInfoPageList(Map paramMap, PageBean pageBean);
    
    Page<Map> queryAgentEquInfoList(Map paramMap,PageBean pageBean);

	void commission(String agno, BigDecimal incomes);
	
	//根据代理商编号查询可提现金额
    BigDecimal getResumByAgno(String agno);
    
    //根据代理商编号修改密码
    Integer updatePasswordByAgno(String ag_password,String agno);
    
    //根据代理商编号修改可提现金额
    void updateResumByAgno(BigDecimal resum,String agno);

	List<Map> queryAgentInfoList(Map paramMap);
}

