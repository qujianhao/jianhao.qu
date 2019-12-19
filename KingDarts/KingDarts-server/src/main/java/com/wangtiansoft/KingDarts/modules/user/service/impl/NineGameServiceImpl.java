package com.wangtiansoft.KingDarts.modules.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.modules.user.service.NineGameService;
import com.wangtiansoft.KingDarts.persistence.dao.master.NineGameResultMapper;
@Transactional
@Service("nineGameService")
public class NineGameServiceImpl implements NineGameService {
	
	@Autowired
	private NineGameResultMapper nineGameResultMapper;
	
	
	@Override
	public Page<Map> getRankListByMonth(Map paramMap, PageBean pageBean) {
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
		PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		return (Page<Map>) nineGameResultMapper.getRankListByMonth(paramMap);
	}


	@Override
	public int getRank(String uuid, String month) {
		return nineGameResultMapper.getRank(uuid, month);
	}

}
