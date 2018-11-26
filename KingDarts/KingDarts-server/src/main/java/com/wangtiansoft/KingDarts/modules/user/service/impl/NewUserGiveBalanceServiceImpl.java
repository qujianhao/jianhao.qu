package com.wangtiansoft.KingDarts.modules.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.user.service.NewUserGiveBalanceService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.NewUserGiveBalanceMapper;
import com.wangtiansoft.KingDarts.persistence.entity.NewUserGiveBalance;

@Transactional
@Service("newUserGiveBalanceService")
public class NewUserGiveBalanceServiceImpl extends BaseService<NewUserGiveBalance, Integer> implements NewUserGiveBalanceService{

	@Autowired
    private NewUserGiveBalanceMapper newUserGiveBalanceMapper;
	
	@Override
	public BaseMapper getBaseMapper() {
		return newUserGiveBalanceMapper;
	}

	@Override
    public Page<Map> queryNewUserGiveBalanceList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL))){
        	paramMap.put(SQLUtil.SQL_OrderSQL, "create_time desc");
        }
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) newUserGiveBalanceMapper.queryNewUserGiveBalanceList(paramMap);
    }
	
	@Override
	public void savegivebalance(NewUserGiveBalance newUserGiveBalance) {
		newUserGiveBalanceMapper.insert(newUserGiveBalance);
	}
}
