package com.wangtiansoft.KingDarts.modules.funcatchWinner.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.modules.funcatchWinner.service.FuncatchWinnerService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.FuncatchWinnerMapper;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchPrize;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchWinner;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("funcatchWinnerService")
public class FuncatchWinnerServiceImpl extends BaseService<FuncatchWinner, Long> implements FuncatchWinnerService{

    @Autowired
    private FuncatchWinnerMapper funcatchWinnerMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return funcatchWinnerMapper;
    }

    @Override
    public Page<Map> queryFuncatchWinnerPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL))){
        	paramMap.put(SQLUtil.SQL_OrderSQL,"create_time desc" );
        }
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) funcatchWinnerMapper.queryFuncatchWinnerList(paramMap);
    }

	@Override
	public FuncatchWinner insertFuncatchWinner(UserResult user, FuncatchPrize funcatchPrize) {
		FuncatchWinner funcatchWinner=new FuncatchWinner();
		funcatchWinner.setHeadimg(user.getHeadimgurl());
		funcatchWinner.setUser_id(user.getId());
		funcatchWinner.setUseraccount(user.getUuid());
		funcatchWinner.setUsername(user.getNickname());
		funcatchWinner.setPrize_id(funcatchPrize.getPrize_id());
		funcatchWinner.setPrize_name(funcatchPrize.getPrize_name());
		if(funcatchPrize.getIs_physical().equals("N")) {
			funcatchWinner.setIs_deliver(1);
		}else {
			funcatchWinner.setIs_deliver(0);
		}
		BeanUtil.entityInit(funcatchWinner);
		save(funcatchWinner);
		return funcatchWinner;
	}
}
