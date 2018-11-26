package com.wangtiansoft.KingDarts.persistence.dao.master;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Coupon;

public interface CouponMapper extends BaseMapper<Coupon> {

    List<Map> queryCouponList(Map paramMap);
    
    @Update("update darts_coupon set coupon_status=#{coupon_status},user_id=#{user_id},"
    		+ "apply_time=#{apply_time} where couponno=#{couponno}")
    Integer updateCouponByCouponno(Coupon coupon);
    
    @Select("select * from darts_coupon where couponno=#{couponno}")
    Coupon getCouponByCouponno(String couponno);
}
