package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ClubInfoMapper extends BaseMapper<ClubInfo> {

    List<Map> queryClubInfoList(Map paramMap);
    
    List<Map> queryAccountClubInfoList(Map paramMap);
    
    //查询代理商名下俱乐部
    List<Map> queryAgentClubInfoList(Map paramMap);
    
    //后台管理员修改游戏定价
    @Update("update darts_club_info set manage_game_price = #{manage_game_price},"
    		+ "price_start_time = #{price_start_time},price_end_time = #{price_end_time} where id = #{id}")
    Integer updateManagePrice(@Param("id")Integer id,@Param("manage_game_price")BigDecimal manage_game_price,
    		@Param("price_start_time")Date price_start_time,@Param("price_end_time")Date price_end_time);

    
    //根据设备查询俱乐部定价
    Map getPriceByEquNo(String equno);
    
    /**
     * 充值提成
     * @param paramMap
     * @return
     */
    int rechargeCommission(Map paramMap);
    
    ClubInfo queryClubByCno(String clubCno);
    
    //根据俱乐部查询可提现金额
    @Select("select resum from darts_club_info where cno=#{cno}")
    BigDecimal getResumByCno(String cno);
    
    //根据代理商编号获取俱乐部列表并查找授权的设备的数量
    List<Map> getClubListByAgno(Map paramMap); 
    
    //根据俱乐部编号修改密码
    @Update("update darts_club_info set c_password=#{c_password} where cno=#{cno}")
    Integer updatePasswordByCno(@Param("c_password")String c_password,@Param("cno")String cno);
    
    //根据俱乐部编号修改可提现金额
    Integer updateResumByCno(Map paramMap);
    
  //俱乐部消费统计
    List<Map> queryClubOrder(Map paramMap);
    
}