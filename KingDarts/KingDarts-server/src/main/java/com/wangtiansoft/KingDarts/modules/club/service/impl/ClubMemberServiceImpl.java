package com.wangtiansoft.KingDarts.modules.club.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubMemberService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.ClubMemberMapper;
import com.wangtiansoft.KingDarts.persistence.entity.ClubMember;

@Transactional
@Service("clubMemberService")
public class ClubMemberServiceImpl extends BaseService<ClubMember, Integer> implements ClubMemberService {

	@Autowired
	private ClubMemberMapper clubMemberMapper;

	@Override
	public BaseMapper getBaseMapper() {
		return clubMemberMapper;
	}
	
	@Override
    public Page<Map> queryMemberList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) clubMemberMapper.queryMemberList(paramMap);
    }
	
	@Override
	public Integer queryMemberCount(String cno,String agno) {
		return clubMemberMapper.queryMemberCount(cno, agno);
	}
	
	@Override
    public Page<Map> queryMemberStatistics(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) clubMemberMapper.queryMemberStatistics(paramMap);
    }
	
	@Override
	public void makeMember(String cno,String userId){
		ClubMember clubMember = new ClubMember();
    	clubMember.setCno(cno);
    	clubMember.setUser_id(userId);
    	ClubMember member = clubMemberMapper.selectOne(clubMember);
    	if(member == null){
    		clubMember.setCreate_time(new Date());
    		clubMember.setUpdate_time(new Date());
    		clubMember.setIs_delete(0);//未删除
    		clubMemberMapper.insert(clubMember);
    	}
	}
}
