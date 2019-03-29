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
	
	
	//查询俱乐部插广告
	Page<Map> queryAdvertInfoPageListByclubId(Map paramMap, PageBean pageBean);

	//查询设备差俱乐部
	String queryEquclubByEquno(String equno);

	//查询广告信息不通过俱乐部
	Page<Map> queryAdvertInfoPageListNoclub(Map<String, Object> paramMap, PageBean pageBean);
	
    // 分页查询EquInfo
    Page<Map> queryEquInfoPageList(Map paramMap, PageBean pageBean);
    // 获取设备信息通过编号
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

	Page<Map> queryAdvertInfotianjie(Map<String, Object> paramMap, PageBean pageBean);

	
	
	
	
//	//查询代理商用过设备
	String queryEquAgentByEquno(String belongClubCno);

	
	//查询代理商用过设备
	Page<Map> queryAdvertInfoPageListByagentId(Map<String, Object> paramMap, PageBean pageBean);

	String queryEquclubCnoByEquno(String equno);

}

