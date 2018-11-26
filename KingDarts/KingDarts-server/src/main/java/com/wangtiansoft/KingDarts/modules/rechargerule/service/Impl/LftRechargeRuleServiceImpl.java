package com.wangtiansoft.KingDarts.modules.rechargerule.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.rechargerule.service.LftRechargeRuleService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.LftRechargeRuleMapper;
import com.wangtiansoft.KingDarts.persistence.entity.LftRechargeRule;
@Transactional
@Service("lftRechargeRuleService")
public class LftRechargeRuleServiceImpl extends BaseService<LftRechargeRule, Integer> implements LftRechargeRuleService {

	@Autowired
    private LftRechargeRuleMapper lftRechargeRuleMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return lftRechargeRuleMapper;
    }
    
    @Override
    public List<LftRechargeRule> getAllRechargeRule(){
    	return lftRechargeRuleMapper.getAllRechargeRule();
    }
}
