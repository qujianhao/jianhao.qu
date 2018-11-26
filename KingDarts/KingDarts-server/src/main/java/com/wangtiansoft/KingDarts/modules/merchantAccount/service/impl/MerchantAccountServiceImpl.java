package com.wangtiansoft.KingDarts.modules.merchantAccount.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.modules.merchantAccount.service.MerchantAccountService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.MerchantAccountMapper;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("merchantAccountService")
public class MerchantAccountServiceImpl extends BaseService<MerchantAccount, Integer> implements MerchantAccountService{

    @Autowired
    private MerchantAccountMapper merchantAccountMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return merchantAccountMapper;
    }

    @Override
    public Page<Map> queryMerchantAccountPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) merchantAccountMapper.queryMerchantAccountList(paramMap);
    }
    
    @Override
    public MerchantAccount getUserByLoginName(String loginName) {
    	return merchantAccountMapper.getUserByLoginName(loginName);
    }
    
    @Override
    public MerchantAccount getUserByMobile(String mobile) {
    	return merchantAccountMapper.getUserByMobile(mobile);
    }
    
    @Override
    public Integer updatePasswordById(String password,String mobile,Integer id) {
    	return merchantAccountMapper.updatePasswordById(password, mobile, id);
    }
    
    @Override
    public Integer updatePasswordByMerno(String password,String mobile,
    		String accountnames,Integer mertype,String merno) {
    	return merchantAccountMapper.updatePasswordByMerno(password, mobile, accountnames, mertype, merno);
    }
    
    @Override
    public Integer updateAccountNameByMerno(String mobile,String accountnames,
    		Integer mertype,String merno) {
    	return merchantAccountMapper.updateAccountNameByMerno(mobile, accountnames, mertype, merno);
    }
    
    @Override
    public Integer updateOpenidByMerno(String openid,String merno) {
    	return merchantAccountMapper.updateOpenidByMerno(openid, merno);
    }
    
    @Override
    public String getOldSysUserPwd(String loginName) {
    	return merchantAccountMapper.getOldSysUserPwd(loginName);
    }
}
