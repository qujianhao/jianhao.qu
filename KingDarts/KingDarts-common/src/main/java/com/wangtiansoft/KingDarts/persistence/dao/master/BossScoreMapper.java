package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.BossScore;

import java.util.List;
import java.util.Map;

public interface BossScoreMapper extends BaseMapper<BossScore> {

    List<Map> queryBossScoreList(Map paramMap);
    
    List<Map> getBossScoreRankList(Map paramMap);
    
}