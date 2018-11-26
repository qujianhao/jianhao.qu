package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Boss;

import java.util.List;
import java.util.Map;

public interface BossMapper extends BaseMapper<Boss> {

    List<Map> queryBossList(Map paramMap);
    
    void updateBossIsNotUse();
}