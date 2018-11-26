package com.wangtiansoft.KingDarts.modules.coupon.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Coupon;

public interface CouponService extends IBaseService<Coupon, Integer> {

	Page<Map> queryCouponList(Map paramMap, PageBean pageBean);
	
	//使用优惠券
	Integer useCouponByUser(String couponno,String user_id);
}
