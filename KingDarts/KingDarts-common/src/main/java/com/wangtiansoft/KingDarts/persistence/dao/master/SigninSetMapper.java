package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.SigninSet;

import java.util.List;
import java.util.Map;

public interface SigninSetMapper extends BaseMapper<SigninSet> {

    List<Map> querySigninSetList(Map paramMap);

}