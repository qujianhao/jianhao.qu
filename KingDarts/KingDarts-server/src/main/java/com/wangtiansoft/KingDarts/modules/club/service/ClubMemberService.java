package com.wangtiansoft.KingDarts.modules.club.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.ClubMember;

public interface ClubMemberService extends IBaseService<ClubMember, Integer> {

	Page<Map> queryMemberList(Map<String,Object> paramMap,PageBean pageBean);
	
	//会员总数
	Integer queryMemberCount(String cno,String agno);

	Page<Map> queryMemberStatistics(Map paramMap, PageBean pageBean);

	void makeMember(String cno, String userId);
}
