package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.ClubMonthRank;

import java.util.List;
import java.util.Map;

public interface ClubMonthRankMapper extends BaseMapper<ClubMonthRank> {

    List<Map> queryClubMonthRankList(Map paramMap);
    
    List<Map> queryAthleticsClubMonthRankInfoList(Map paramMap);
    
    List<Map> queryStrengthClubMonthRankInfoList(Map paramMap);
    
    void insertClubMonthRank(Map paramMap);
}