package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.GameResRound;

import java.util.List;
import java.util.Map;

public interface GameResRoundMapper extends BaseMapper<GameResRound> {

    List<Map> queryGameResRoundList(Map paramMap);

}