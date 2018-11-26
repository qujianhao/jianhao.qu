package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Account;

import java.util.List;
import java.util.Map;

public interface AccountMapper extends BaseMapper<Account> {

    List<Map> queryAccountList(Map paramMap);

}