package com.wangtiansoft.KingDarts.persistence.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.LftRechargeRule;

public interface LftRechargeRuleMapper extends BaseMapper<LftRechargeRule>   {

	@Select("select * from darts_lft_recharge_rule where isvalid=1")
	List<LftRechargeRule> getAllRechargeRule();
}
