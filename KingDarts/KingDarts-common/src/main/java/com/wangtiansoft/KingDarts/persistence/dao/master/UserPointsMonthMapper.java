package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.UserPointsMonth;

import java.util.List;
import java.util.Map;

public interface UserPointsMonthMapper extends BaseMapper<UserPointsMonth> {

    List<Map> queryUserPointsMonthList(Map paramMap);

    List<Map> getUserRankByHistoryPoints(Map paramMap);
    
    void insertUserPointsMonth(Map paramMap);
}