package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.UserGamePoints;

import java.util.List;
import java.util.Map;

public interface UserGamePointsMapper extends BaseMapper<UserGamePoints> {

    List<Map> queryUserGamePointsList(Map paramMap);

    int changePoints(UserGamePoints userGamePoints);
}