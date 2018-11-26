package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.BossLuck;

import java.util.List;
import java.util.Map;

public interface BossLuckMapper extends BaseMapper<BossLuck> {

    List<Map> queryBossLuckList(Map paramMap);

}