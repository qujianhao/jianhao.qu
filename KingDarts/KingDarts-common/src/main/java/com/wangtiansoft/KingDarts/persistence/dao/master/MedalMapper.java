package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Medal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MedalMapper extends BaseMapper<Medal> {

    List<Map> queryMedalList(Map paramMap);
    
    Map selectMedalTerm(Map paramMap);

}