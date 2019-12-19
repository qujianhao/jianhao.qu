package com.wangtiansoft.KingDarts.persistence.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.results.core.NineGameResult;


	public interface NineGameResultMapper extends BaseMapper<GameOrder> {
//	List<String> getScoretotal();
	
	/**
	 * 获取单局九镖分数最高的前十
	 * @return
	 */
	List<NineGameResult> getScoretotalqq();
	
	/**
	 * 获取九镖总玩家数量
	 * @return
	 */
	int getTotalNum();
	
	/**
	 * 根据月份获取个人排名
	 * @param uuid  用户ID
	 * @param createTime 年月份  ex:2019-09
	 * @return
	 */
	int getRank(@Param("uuid")String uuid,@Param("createTime")String createTime);
	
	/**
	 * 获取用户指定月份的最高分
	 * @param uuid
	 * @param createTime
	 * @return
	 */
	int getSelfHighScore(@Param("uuid")String uuid,@Param("createTime")String createTime);
	
	/**
	 * 获取给定月份的排名
	 * @param paramMap
	 * @return
	 */
	List<Map> getRankListByMonth(Map paramMap);
}
