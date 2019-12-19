package com.wangtiansoft.KingDarts.modules.user.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;

public interface NineGameService {
	
	/**
	 * 根据条件获取分页历史月度排名
	 */
	Page<Map> getRankListByMonth(Map paramMap, PageBean pageBean);
	
	/**
	 * 获取用户月份排名
	 * @param uuid
	 * @param month
	 * @return
	 */
	int getRank(String uuid,String month);
}	
