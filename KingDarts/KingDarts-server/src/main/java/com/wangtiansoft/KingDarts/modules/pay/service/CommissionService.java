package com.wangtiansoft.KingDarts.modules.pay.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Commission;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* Created by wt-templete-helper.
 */
public interface CommissionService extends IBaseService<Commission, Integer> {

    // 分页查询Commission
    Page<Map> queryClubCommissionList(Map paramMap, PageBean pageBean);

	void saveCommission(String userId, Long lftPayId, BigDecimal amount, String equno,String remark);
	
	//根据俱乐部编号查询可提现金额及充值金额总数
	Map getClubPutForwardAndRechargeAmount(String cno);
	
	//根据代理商编号查询可提现金额及充值金额总数
	Map getAgentPutForwardAndRechargeAmount(String agno);
	
	//复购率
    Float getSencondProbability(String agno,String cno);
    
     // 分页查询用户充值记录
    Page<Map> queryUserCommissionPageList(Map paramMap, PageBean pageBean);
    
    //根据俱乐部查询充值金额总数
    BigDecimal getClubTotalAmountByCno(String cno);
    
    //根据代理商查询充值金额总数
    BigDecimal getAgentTotalAmountByAgno(String agno);
    
    //根据俱乐部查询分成总数
    BigDecimal getClubPartitionTotalAmountByCno(String cno);
    
    //根据代理商查询分成总数
    BigDecimal getAgentPartitionTotalAmountByAgno(String agno);
    
    Page<Map> queryClubCommissionDetailPageList(Map paramMap, PageBean pageBean);

	List<Map> queryClubCommissionDetailList(List<String> timeList, Map paramMap, int type);
	
	// 获取当日充值总金额
	int getDayRechargeCount(String agno,String dayTime);
	
	// 获取当月充值总金额
	int getMonthRechargeCount(String agno,String monthTime);
}

