package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.RobFloors;

import java.util.List;
import java.util.Map;

public interface RobFloorsMapper extends BaseMapper<RobFloors> {

    List<Map> queryRobFloorsList(Map paramMap);

}