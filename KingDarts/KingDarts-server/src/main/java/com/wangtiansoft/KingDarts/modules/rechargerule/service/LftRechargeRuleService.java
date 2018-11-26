package com.wangtiansoft.KingDarts.modules.rechargerule.service;

import java.util.List;

import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.LftRechargeRule;

public interface LftRechargeRuleService extends IBaseService<LftRechargeRule, Integer>  {

	public List<LftRechargeRule> getAllRechargeRule();
}
