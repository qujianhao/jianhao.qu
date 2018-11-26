package com.wangtiansoft.KingDarts.modules.club.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.results.core.ClubInfoResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* Created by wt-templete-helper.
 */
public interface ClubInfoService extends IBaseService<ClubInfo, Integer> {

    // 分页查询ClubInfo
    Page<Map> queryClubInfoPageList(Map paramMap, PageBean pageBean);
    
    //分页查询没有在代理商名下俱乐部
    Page<Map> queryAccountClubInfoList(Map paramMap,PageBean pageBean);
    
    //查询代理商名下俱乐部
    Page<Map> queryAgentClubInfoList(Map paramMap, PageBean pageBean);
    
    //新增保存
    ClubInfoResult saveClubInfo(ClubInfoResult clubInfoResult);
    
    //管理员修改游戏定价
    Integer updateManagePrice(Integer id,BigDecimal manage_game_price,Date price_start_time,Date price_end_time);
    
    //俱乐部详情
    Map getClubInfoView(Integer id);
    
    //根据设备查询俱乐部定价
    BigDecimal getPriceByEquNo(String equno);

	void commission(String cno, BigDecimal incomes);
	
	//根据俱乐部查询可提现金额
	BigDecimal getResumByCno(String cno);
	
	//根据代理商编号获取俱乐部列表并查找授权的设备的数量
	Page<Map> getClubListByAgno(Map paramMap, PageBean pageBean); 
	
	//代理商设备授权
	ClubInfoResult updateCnoAuthByEquno(ClubInfoResult clubInfoResult);
	
	//根据俱乐部编号修改密码
    Integer updatePasswordByCno(String c_password,String cno);
    
    //根据俱乐部编号修改可提现金额
    void updateResumByCno(BigDecimal resum,String cno);

    //俱乐部消费统计
	Page<Map> queryClubOrder(Map paramMap, PageBean pageBean);
}

