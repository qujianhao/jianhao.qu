package com.wangtiansoft.KingDarts.modules.user.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.UserBalance;

public interface UserBalanceService extends IBaseService<UserBalance, Integer> {

	//查询俱乐部用户消费详情
	Page<Map> queryUserConsumptionDetails(Map paramMap, PageBean pageBean);
	
	Float  getUserBalanceCount(Map paramMap);
}
