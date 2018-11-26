package com.wangtiansoft.KingDarts.modules.funcatchWinner.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchPrize;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchWinner;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface FuncatchWinnerService extends IBaseService<FuncatchWinner, Long> {

    // 分页查询FuncatchWinner
    Page<Map> queryFuncatchWinnerPageList(Map paramMap, PageBean pageBean);
    
    //保存中奖记录
    FuncatchWinner insertFuncatchWinner(UserResult user,FuncatchPrize funcatchPrize);
}

