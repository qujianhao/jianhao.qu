package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchPrize;

import java.util.List;
import java.util.Map;

public interface FuncatchPrizeMapper extends BaseMapper<FuncatchPrize> {

    List<Map> queryFuncatchPrizeList(Map paramMap);
    
    Integer countWinRateFuncatchPrizeList();
    

}