package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    List<Map> queryUserList(Map paramMap);
    
    int consumeRecharge(Map paramMap);
      
    User getByUserAccount(String userAccount);
    
    List<Map> getHisUserList(Map paramMap);
    
    int updateHisUserUnionid(Map paramMap);
    
    
    int consumeRechargeaddfive(Map paramMap);
}