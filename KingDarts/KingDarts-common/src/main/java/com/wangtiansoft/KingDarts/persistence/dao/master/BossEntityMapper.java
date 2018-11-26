package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.BossEntity;

import java.util.List;
import java.util.Map;

public interface BossEntityMapper extends BaseMapper<BossEntity> {

    List<Map> queryBossEntityList(Map paramMap);
    
    Map queryBossEntityInfo(Map paramMap);
    
    int lossLife(Map paramMap);

}