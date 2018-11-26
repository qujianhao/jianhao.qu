package com.wangtiansoft.KingDarts.persistence.dao.record;


import java.util.List;
import java.util.Map;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Signin;

public interface SigninReMapper extends BaseMapper<Signin>{

    List<Map> querySigninList(Map paramMap);

}