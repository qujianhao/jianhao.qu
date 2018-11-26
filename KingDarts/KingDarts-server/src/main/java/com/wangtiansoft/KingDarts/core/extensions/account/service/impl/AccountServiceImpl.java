package com.wangtiansoft.KingDarts.core.extensions.account.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.PasswordUtil;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.extensions.account.service.AccountService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.AccountMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Account;
import com.wangtiansoft.KingDarts.results.core.AccountResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("accountService")
public class AccountServiceImpl extends BaseService<Account, Integer> implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return accountMapper;
    }

    @Override
    public Page<Map> queryAccountPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL))){
        	paramMap.put(SQLUtil.SQL_OrderSQL, "create_time desc");
        }
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) accountMapper.queryAccountList(paramMap);
    }
    
    @Override
	public void saveOrUpdateAccount(AccountResult account) {
		Integer id = account.getId();
		if(id == null) {
			//判断教师该登录名是否已被注册过
			Account tAccount = new Account();
			tAccount.setUsername(account.getUsername());
			Account accountOld = accountMapper.selectOne(tAccount);
			if(accountOld != null) {
				throw new AppRuntimeException("该账号已被注册。");
			}
			
			Account accountEntity = new Account();
			BeanUtils.copyProperties(account, accountEntity);
			if(accountEntity.getIs_publish()==null)
				accountEntity.setIs_publish(1);
			accountEntity.setIs_delete(Constants.False);
			accountEntity.setOrder_num(2);
			accountEntity.setCreate_time(new Date());
			accountEntity.setUpdate_time(new Date());
			accountEntity.setPassword(PasswordUtil.createHash(account.getPassword()));
//			accountEntity.setIsEnabled(account.getis == null? Constants.True: account.getIsEnabled());
			int count = accountMapper.insert(accountEntity);
			if(count < 1) {
				throw new AppRuntimeException("新建账户失败！");
			}
		}else {
			Account accountOld = accountMapper.selectByPrimaryKey(id);
			if(accountOld == null) {
				throw new AppRuntimeException("该账户不存在！");
			}
			
			Account accountEntity = new Account();
			BeanUtils.copyProperties(account, accountEntity);
			
			accountEntity.setUpdate_time(new Date());
//			accountEntity.setIsEnabled(account.getIsEnabled() == null? Constants.True: account.getIsEnabled());
			if(StringUtils.isNotEmpty(account.getPassword())) {
				accountEntity.setPassword(PasswordUtil.createHash(account.getPassword()));
			}
			int count = accountMapper.updateByPrimaryKeySelective(accountEntity);
			if(count < 1) {
				throw new AppRuntimeException("更新账户失败！");
			}
		}
	}
}
