package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Raceprotocol;

import java.util.List;
import java.util.Map;

public interface RaceprotocolMapper extends BaseMapper<Raceprotocol> {

    List<Map> queryRaceprotocolList(Map paramMap);

}