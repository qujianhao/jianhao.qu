package com.wangtiansoft.KingDarts.persistence.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.LftPay;

public interface LftPayMapper extends BaseMapper<LftPay>  {

	void updateLftPayOutTradeNoAndTradeNo(LftPay lftPay);
	
	@Select("select * from darts_lft_pay where pay_status=#{pay_status}")
	List<LftPay> getLftPayByPayStatus(Integer pay_status);
}
