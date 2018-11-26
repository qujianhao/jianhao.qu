package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchWinner;

import java.util.List;
import java.util.Map;

public interface FuncatchWinnerMapper extends BaseMapper<FuncatchWinner> {

    List<Map> queryFuncatchWinnerList(Map paramMap);

}