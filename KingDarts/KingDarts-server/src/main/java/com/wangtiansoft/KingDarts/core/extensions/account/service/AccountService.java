package com.wangtiansoft.KingDarts.core.extensions.account.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Account;
import com.wangtiansoft.KingDarts.results.core.AccountResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface AccountService extends IBaseService<Account, Integer> {

    // 分页查询Account
    Page<Map> queryAccountPageList(Map paramMap, PageBean pageBean);

	void saveOrUpdateAccount(AccountResult account);
}

