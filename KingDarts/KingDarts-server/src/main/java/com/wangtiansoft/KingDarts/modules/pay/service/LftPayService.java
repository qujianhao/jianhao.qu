package com.wangtiansoft.KingDarts.modules.pay.service;

import java.util.Date;
import java.util.List;

import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.LftPay;

public interface LftPayService extends IBaseService<LftPay, Integer>  {

	void updateLftPayOutTradeNoAndTradeNo(LftPay lftPay);
	
	List<LftPay> getLftPayByPayStatus(Integer pay_status);


	boolean synPayStatus(String out_trade_no, String trade_no, String merchant_no, Date time_start);

	void testPay(String out_trade_no);

	boolean transfers(String openid, String ip, String userName, String amount);

	boolean synWechatPayStatus(String out_trade_no, String trade_no, String merchant_no, Date time_start);

}
