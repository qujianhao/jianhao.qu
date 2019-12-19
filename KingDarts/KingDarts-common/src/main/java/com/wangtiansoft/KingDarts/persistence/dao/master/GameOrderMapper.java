package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.results.core.AwardUserInfo;

import java.util.List;
import java.util.Map;

public interface GameOrderMapper extends BaseMapper<GameOrder> {

    List<Map> queryGameOrderList(Map paramMap);
    
    List<Map> queryGameCount(Map paramMap);
    
    List<Map> queryGameTypeCount(Map paramMap);
    
    List<AwardUserInfo> getAwardUserInfo(Map paraMap);
    
    int getAwardNum(Map paraMap);

}