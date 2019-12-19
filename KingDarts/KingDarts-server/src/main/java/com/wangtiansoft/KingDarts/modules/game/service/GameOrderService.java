package com.wangtiansoft.KingDarts.modules.game.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.results.core.AwardUserInfo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface GameOrderService extends IBaseService<GameOrder, Long> {

    // 分页查询GameOrder
    Page<Map> queryGameOrderPageList(Map paramMap, PageBean pageBean);

	/**
	 * 查询游戏订单通过订单编号
	 * @param gameOrder
	 * @return
	 */
	GameOrder getGameOrderByNo(GameOrder gameOrder);

	/**
	 * 通过订单编号更新订单
	 * @param gameOrder
	 */
	void updateByNoSelective(GameOrder gameOrder);

	/**
	 * 取消订单
	 * @param orderId
	 * @param userId
	 * @param cost
	 */
	void cancel(Long orderId, String userId, BigDecimal cost);

	/**
	 * 网络游戏开始状态更新
	 * @param orderNo1
	 * @param orderNo2
	 */
	void netGameStart(String orderNo1, String orderNo2);

	/**
	 * 游戏局数统计
	 * @param paramMap
	 * @param pageBean
	 * @return
	 */
	Page<Map> queryGameCountList(Map paramMap, PageBean pageBean);

	/**
	 * 游戏局数类型统计
	 * @param paramMap
	 * @param pageBean
	 * @return
	 */
	Page<Map> queryGameTypeCountList(Map paramMap, PageBean pageBean);

	/**
	 * 获取九镖获奖人信息
	 * @param paraMap
	 * @return
	 */
	JSONObject getAwardUserInfo(Map paraMap);
}

