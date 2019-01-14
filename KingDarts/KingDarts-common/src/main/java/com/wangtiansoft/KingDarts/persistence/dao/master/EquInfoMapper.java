package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EquInfoMapper extends BaseMapper<EquInfo> {

    List<Map> queryEquInfoList(Map paramMap);
    
    //查询在线设备列表
    List<Map> queryEquOnlieList(Map paramMap);
    
    //商户端修改设备游戏定价
    @Update("update darts_equ_info set game_price = #{game_price} where equno = #{equno}")
    Integer updateManagePrice(@Param("equno")String equno,@Param("game_price")BigDecimal game_price);
    
    //获取设备游戏定价
    @Select("select game_price from darts_equ_info where equno=#{equno}")
    BigDecimal getGamePpriceByEquNo(String equno);
    
    //查询包机设备列表
    List<Map> queryEquList(Map paramMap);
    
    //用户取消包机
    @Update("update darts_equ_info set booked_user = '' where equno = #{equno} and booked_user = #{booked_user}")
    Integer updateBookedUser(@Param("equno")String equno,@Param("booked_user")String booked_user);
    
  //设备取消包机
    @Update("update darts_equ_info set booked_user = '' where equno = #{equno} ")
    Integer cancelBookedUser(@Param("equno")String equno);
    
    //终端充值，消费统计
    List<Map> queryEquStatistics(Map paramMap);
    
    @Update("update darts_equ_info set isline = 0 where isline = 1")
    Integer equOffLine();
    
    //查詢设备的俱乐部
    @Select("SELECT  dci.id FROM  darts_equ_auth dea  INNER JOIN darts_club_info dci ON dci.cno = dea.auth_no  where  dea.equno = #{equno}")
        String   queryEquclubByEquno(String equno);
}