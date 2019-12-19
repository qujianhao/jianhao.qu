package com.wangtiansoft.KingDarts.persistence.dao.master;

import org.apache.ibatis.annotations.Param;

import com.wangtiansoft.KingDarts.persistence.entity.UseCouponRecord;

public interface UseCouponRecordMapper{

	int insertSelective(UseCouponRecord record);
	
	UseCouponRecord getByUuidCouponId(@Param("uuid")String uuid,@Param("couponId")String couponId);
}