package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Game;

import java.util.List;
import java.util.Map;

public interface GameMapper extends BaseMapper<Game> {

    List<Map> queryGameList(Map paramMap);

}