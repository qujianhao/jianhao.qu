package com.wangtiansoft.KingDarts.persistence.dao.master;


import java.util.List;
import java.util.Map;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Signin;

public interface SigninMapper extends BaseMapper<Signin>{

    List<Map> querySigninList(Map paramMap);

}