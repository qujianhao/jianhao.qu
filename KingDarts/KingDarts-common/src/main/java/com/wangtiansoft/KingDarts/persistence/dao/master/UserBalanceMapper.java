package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.UserBalance;

import java.util.List;
import java.util.Map;

public interface UserBalanceMapper extends BaseMapper<UserBalance> {

    List<Map> queryUserBalanceList(Map paramMap);
    
    List<Map> queryUserConsumptionDetails(Map paramMap);
    
    Float getUserBalanceCount(Map paramMap);

}