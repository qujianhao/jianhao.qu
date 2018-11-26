package com.wangtiansoft.KingDarts.persistence.dao.master;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.RaceInfo;
import com.wangtiansoft.KingDarts.results.core.RaceInfoResult;

public interface RaceInfoMapper extends BaseMapper<RaceInfo> {

	List<Map> queryRaceInfoList(Map paramMap);
	
	List<Map> queryMyRaceInfoList(Map paramMap);
	
	@Select("select * from darts_race_info where dstatus not in(3,4,5) and isvalid=1")
    List<RaceInfo> getAllRaceInfoByStatus();
	
	@Select("select count(*) from darts_race_info where cno=#{cno} and isvalid=1")
	Integer getCountByCno(String cno);
	
	@Select("SELECT dri.* FROM darts_race_info as dri WHERE 1=1 AND dri.isvalid = 1 AND dri.raceno=#{raceno} ORDER BY dri.id DESC")
	RaceInfoResult getRaceByRaceNo(String raceno);
	
	@Select("select count(*) from darts_race_info where cno=#{cno} and dstatus in (0,1,2,3) and DATE_FORMAT(add_time,'%Y-%m') = #{add_time} and isvalid=1")
	Integer getCountByMonth(@Param("cno")String cno,@Param("add_time")String add_time);
	
	@Select("select count(*) from darts_race_info where cno=#{cno} and dstatus in (2,3) and minimum_num=#{minimum_num} and isvalid=1")
	Integer getCountByCnoAndPersonNum(@Param("cno")String cno,@Param("minimum_num")Integer minimum_num);
}
