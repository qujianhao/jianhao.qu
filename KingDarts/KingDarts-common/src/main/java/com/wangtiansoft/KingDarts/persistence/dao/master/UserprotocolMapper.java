package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Userprotocol;

import java.util.List;
import java.util.Map;

public interface UserprotocolMapper extends BaseMapper<Userprotocol> {

    List<Map> queryUserprotocolList(Map paramMap);

}