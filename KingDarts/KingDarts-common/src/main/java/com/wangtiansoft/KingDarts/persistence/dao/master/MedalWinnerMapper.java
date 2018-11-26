package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.MedalWinner;

import java.util.List;
import java.util.Map;

public interface MedalWinnerMapper extends BaseMapper<MedalWinner> {

    List<Map> queryMedalWinnerList(Map paramMap);

}