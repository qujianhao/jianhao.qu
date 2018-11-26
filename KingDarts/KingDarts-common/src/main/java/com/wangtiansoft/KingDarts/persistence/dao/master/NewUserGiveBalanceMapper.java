package com.wangtiansoft.KingDarts.persistence.dao.master;

import java.util.List;
import java.util.Map;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.NewUserGiveBalance;

public interface NewUserGiveBalanceMapper extends BaseMapper<NewUserGiveBalance> {

	List<Map> queryNewUserGiveBalanceList(Map paramMap);
}
