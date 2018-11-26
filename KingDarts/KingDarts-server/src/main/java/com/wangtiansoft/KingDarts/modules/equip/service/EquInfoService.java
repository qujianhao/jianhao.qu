package com.wangtiansoft.KingDarts.modules.equip.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;
import com.wangtiansoft.KingDarts.results.core.EquInfoResult;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* Created by wt-templete-helper.
 */
public interface EquInfoService extends IBaseService<EquInfo, String> {

    // 分页查询EquInfo
    Page<Map> queryEquInfoPageList(Map paramMap, PageBean pageBean);

	EquInfo getEquInfoByNo(String equno);

	void equLogin(String id, String equno, String ip, String serverIp);
	
	void equLogout(String equno, int offType);

	Page<Map> queryEquOnliePageList(Map paramMap, PageBean pageBean);

	void updateByNoSelective(EquInfo equInfo);

	EquInfoResult getEquInfoResultByNo(String equno);
	
	//获取设备游戏定价
	BigDecimal getGamePpriceByEquNo(String equno);
	
	//商户端修改游戏定价
	Integer updateManagePrice(String equno,BigDecimal game_price);

	/**
	 * 查询包机设备列表
	 * @param userId
	 * @return
	 */
	List<Map> queryBookedList(String userId);

	/**
	 * 取消包机
	 * @param userId
	 * @param equno
	 */
	void unBooked(String userId, String equno);

	/**
	 * 终端充值，消费统计
	 * @param paramMap
	 * @param pageBean
	 * @return
	 */
	Page<Map> queryEquStatistics(Map paramMap, PageBean pageBean);

	Integer offLine();

}

