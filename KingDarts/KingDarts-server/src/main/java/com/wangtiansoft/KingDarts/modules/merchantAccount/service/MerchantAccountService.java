package com.wangtiansoft.KingDarts.modules.merchantAccount.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* Created by wt-templete-helper.
 */
public interface MerchantAccountService extends IBaseService<MerchantAccount, Integer> {

    // 分页查询MerchantAccount
    Page<Map> queryMerchantAccountPageList(Map paramMap, PageBean pageBean);
    
    //根据用户编号查找用户
    MerchantAccount getUserByLoginName(String loginName);
    
    //通过手机号查找用户
    MerchantAccount getUserByMobile(String mobile);
    
    //根据id修改密码
    Integer updatePasswordById(String password,String mobile,Integer id);
    
    //根据商户编号修改密码
    Integer updatePasswordByMerno(String password,String mobile,
    		String accountnames,Integer mertype,String merno);
    
    //根据商户编号修改用户名
    Integer updateAccountNameByMerno(String mobile,String accountnames,
    		Integer mertype,String merno);
    
    //根据商户编号修改微信openid
    Integer updateOpenidByMerno(String openid,String merno);

	String getOldSysUserPwd(String loginName);
}

