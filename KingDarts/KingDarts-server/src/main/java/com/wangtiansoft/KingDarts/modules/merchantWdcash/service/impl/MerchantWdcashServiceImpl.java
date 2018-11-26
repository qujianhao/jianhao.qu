package com.wangtiansoft.KingDarts.modules.merchantWdcash.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.modules.agent.service.AgentInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.merchantWdcash.service.MerchantWdcashService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.MerchantWdcashMapper;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantWdcash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("merchantWdcashService")
public class MerchantWdcashServiceImpl extends BaseService<MerchantWdcash, Integer> implements MerchantWdcashService{

    @Autowired
    private MerchantWdcashMapper merchantWdcashMapper;
    @Autowired
	private ClubInfoService clubInfoService;
	@Autowired
	private AgentInfoService agentInfoService;

    @Override
    public BaseMapper getBaseMapper() {
        return merchantWdcashMapper;
    }

    @Override
    public Page<Map> queryMerchantWdcashPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL))){
        	paramMap.put(SQLUtil.SQL_OrderSQL, "apply_time desc");
        }
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) merchantWdcashMapper.queryMerchantWdcashList(paramMap);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCashStatus(MerchantWdcash merchantWdcash){
    	if(merchantWdcash.getWd_status()==null){
    		throw new AppRuntimeException("状态不能为空");
    	}
    	
    	if(merchantWdcashMapper.updateByPrimaryKeySelective(merchantWdcash) != 1){
    		throw new AppRuntimeException("更新失败");
    	}
    	
    	//返还代理商或俱乐部金额
    	if(merchantWdcash.getWd_status() - Constants.wd_status_success != 0){
    		MerchantWdcash entity = merchantWdcashMapper.selectByPrimaryKey(merchantWdcash.getId());
    		if(entity.getMer_type().equals(new Integer(1))){
    			//俱乐部
    			clubInfoService.updateResumByCno(entity.getCash_nums(), entity.getMerno());
    		}else{
    			//代理商
    			agentInfoService.updateResumByAgno(entity.getCash_nums(), entity.getMerno());
    		}
    	}
    }
}
