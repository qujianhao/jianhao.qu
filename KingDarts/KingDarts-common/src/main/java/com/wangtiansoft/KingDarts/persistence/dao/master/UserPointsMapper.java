package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.UserPoints;

import java.util.List;
import java.util.Map;

public interface UserPointsMapper extends BaseMapper<UserPoints> {

    List<Map> queryUserPointsList(Map paramMap);
    
    List<Map> getUserRankByPoints(Map paramMap);

    List<UserPoints> selectByclickUserId(Map map1);
    
    List<Map> getYunChuanRank(Map paraMap);
}