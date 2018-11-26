package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.UserOpenid;
import com.wangtiansoft.KingDarts.results.core.UserOpenidResult;

import java.util.List;
import java.util.Map;

public interface UserOpenidMapper extends BaseMapper<UserOpenid> {

    List<Map> queryUserOpenidList(Map paramMap);
    
    List<UserOpenidResult> selectUserOpenidList(Map paramMap);

}