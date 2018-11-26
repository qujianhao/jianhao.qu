package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.EquLine;

import java.util.List;
import java.util.Map;

public interface EquLineMapper extends BaseMapper<EquLine> {

    List<Map> queryEquLineList(Map paramMap);

}