package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.GameResPlayer;

import java.util.List;
import java.util.Map;

public interface GameResPlayerMapper extends BaseMapper<GameResPlayer> {

    List<Map> queryGameResPlayerList(Map paramMap);

}