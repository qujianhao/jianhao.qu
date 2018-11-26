package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.GameResHit;

import java.util.List;
import java.util.Map;

public interface GameResHitMapper extends BaseMapper<GameResHit> {

    List<Map> queryGameResHitList(Map paramMap);

}