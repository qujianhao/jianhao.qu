package com.wangtiansoft.KingDarts.modules.funcatchPrize.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchPrize;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface FuncatchPrizeService extends IBaseService<FuncatchPrize, Integer> {

    // 分页查询FuncatchPrize
    Page<Map> queryFuncatchPrizePageList(Map paramMap, PageBean pageBean);
    
    //获取有效的所有奖品列表
    public List<FuncatchPrize> findValidFuncatchPrizeList();
    
    //随机抽取奖品
    public FuncatchPrize returnWinPrize(List<FuncatchPrize> funcatchPrizes);
    
    //返回计算好的点数
    public BigDecimal returnBalancePrize(FuncatchPrize funcatchPrize);

    //每局消耗点数
	BigDecimal getConsumeBalance();
	
	//总概率值
	Integer countWinRateFuncatchPrizeList();
	
}

