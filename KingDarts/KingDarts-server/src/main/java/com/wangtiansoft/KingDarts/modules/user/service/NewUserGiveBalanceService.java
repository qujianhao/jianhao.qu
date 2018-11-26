package com.wangtiansoft.KingDarts.modules.user.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.NewUserGiveBalance;

public interface NewUserGiveBalanceService extends IBaseService<NewUserGiveBalance, Integer> {

	Page<Map> queryNewUserGiveBalanceList(Map paramMap, PageBean pageBean);
	
	void savegivebalance(NewUserGiveBalance newUserGiveBalance);
}
