package com.wangtiansoft.KingDarts.persistence.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.ClubMember;

public interface ClubMemberMapper extends BaseMapper<ClubMember> {

	List<Map> queryMemberList(Map paramMap);
	
	List<Map> queryMemberStatistics(Map paramMap);
	
	//会员总数
	Integer queryMemberCount(@Param("cno")String cno,@Param("agno")String agno);
	
}
