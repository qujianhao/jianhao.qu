package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Commission;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CommissionMapper extends BaseMapper<Commission> {

	//根据俱乐部查询充值记录
    List<Map> queryClubCommissionList(Map paramMap);

    //根据俱乐部查询充值金额总数
    @Select("select SUM(amount) as club_total_amount from darts_commission where cno=#{cno}")
    BigDecimal getClubTotalAmountByCno(String cno);
    
    //根据代理商查询充值金额总数
    @Select("select SUM(amount) as agent_total_amount from darts_commission where agno=#{agno}")
    BigDecimal getAgentTotalAmountByAgno(String agno);
    
    //根据俱乐部查询分成总数
    @Select("select SUM(c_amount) as club_total_amount from darts_commission where cno=#{cno}")
    BigDecimal getClubPartitionTotalAmountByCno(String cno);
    
    //根据代理商查询分成总数
    @Select("select SUM(ag_amount) as agent_total_amount from darts_commission where agno=#{agno}")
    BigDecimal getAgentPartitionTotalAmountByAgno(String agno);
    
    //复购率
    Float getSencondProbability(@Param("agno")String agno,@Param("cno")String cno);

    //用户端查询充值记录
    List<Map> queryUserCommissionList(Map paramMap);
    
    //查询俱乐部充值记录
    List<Map> queryClubCommissionDetailMonth(Map paramMap);
    
    //查询俱乐部充值提成记录
    List<Map> queryClubCommissionDetailDay(Map paramMap);
    
  //查询俱乐部充值实际支付记录
    List<Map> queryClubCommissionPayDetailDay(Map paramMap);
    
    //根据用户ID查询最新一条扫码充值记录（取设备编号）
    @Select("select * from darts_commission where user_id=#{user_id} order by pay_time desc limit 1")
    Commission getNewOneCommissionByUserId(String user_id);
    
    // 获取俱乐部当日充值金额
    int getDayRechargeCount(@Param("agno")String agno,@Param("dayTime")String dayTime);
    
    // 获取俱乐部当月充值金额
    int getMonthRechargeCount(@Param("agno")String agno,@Param("monthTime")String monthTime);
    
}