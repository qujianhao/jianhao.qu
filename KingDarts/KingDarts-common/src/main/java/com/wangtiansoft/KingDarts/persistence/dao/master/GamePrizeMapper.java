package com.wangtiansoft.KingDarts.persistence.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Boss;
import com.wangtiansoft.KingDarts.persistence.entity.GamePrize;

public interface GamePrizeMapper extends BaseMapper<Boss> {
	
	/**
	 * 获取有效的奖项
	 * @return
	 */
    List<GamePrize> listValidPrize();
    
    GamePrize findById(@Param("id")int id);
}