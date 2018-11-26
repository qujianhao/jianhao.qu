package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Place;

import java.util.List;
import java.util.Map;

public interface PlaceMapper extends BaseMapper<Place> {

    List<Map> queryPlaceList(Map paramMap);

}