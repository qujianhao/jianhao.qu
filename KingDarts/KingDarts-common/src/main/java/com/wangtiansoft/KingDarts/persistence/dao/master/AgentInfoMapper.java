package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.AgentInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AgentInfoMapper extends BaseMapper<AgentInfo> {

    List<Map> queryAgentInfoList(Map paramMap);
    
    //查询代理商下所有飞镖机
    List<Map> queryAgentEquInfoList(Map paramMap);
    
    //充值分成
    int rechargeCommission(Map paramMap);

    //根据代理商编号查询可提现金额
    @Select("select resum from darts_agent_info where agno=#{agno}")
    BigDecimal getResumByAgno(String agno);
    
    //根据代理商编号修改密码
    @Update("update darts_agent_info set ag_password=#{ag_password} where agno=#{agno}")
    Integer updatePasswordByAgno(@Param("ag_password")String ag_password,@Param("agno")String agno);
    
    //根据代理商编号修改可提现金额
    Integer updateResumByAgno(Map paramMap);
}