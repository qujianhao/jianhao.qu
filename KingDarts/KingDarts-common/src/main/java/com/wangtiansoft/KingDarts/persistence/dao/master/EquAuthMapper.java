package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.EquAuth;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface EquAuthMapper extends BaseMapper<EquAuth> {

    List<Map> queryEquAuthList(Map paramMap);
    
    //查询俱乐部已激活设备
    Integer getYesActivationByAuthNo(String auth_no);
    
    //查询俱乐部未激活设备
    Integer getNoActivationByAuthNo(String auth_no);

    List<Map> queryEquCommission(Map paramMap);
    
    List<Map> getEquNoAuthListByAgno(Map paramMap);
    
    List<Map> getEquYesAuthListByAgno(Map paramMap);
    
    //商户端代理商取消设备授权
    @Update("update darts_equ_auth set merchant = 2,auth_no=#{agno},auth_name=#{auth_name} where equno = #{equno}")
    Integer updateAgnoAuthByEquno(@Param("equno")String equno,@Param("agno")String agno,@Param("auth_name")String auth_name);
    
    //商户端代理商设备授权俱乐部
    @Update("update darts_equ_auth set merchant = 1,auth_no=#{cno},auth_name=#{auth_name} where equno = #{equno}")
    Integer updateCnoAuthByEquno(@Param("equno")String equno,@Param("cno")String cno,@Param("auth_name")String auth_name);
    
    //查询代理商下未授权设备数量
    Integer getEquNoAuthCountByAgno(String auth_no);
    
    //查询代理商下已授权设备数量
    Integer getEquYesAuthCountByAgno(String agno);
}