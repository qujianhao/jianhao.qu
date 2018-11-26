package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/6.
 */
public class CouponDetailSearchBean implements Serializable {

    private String couponId;         // 优惠券id
    private String couponMerchantId; // 优惠券商户id
    private String isVerification;   // 核销状态

    public CouponDetailSearchBean() {
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponMerchantId() {
        return couponMerchantId;
    }

    public void setCouponMerchantId(String couponMerchantId) {
        this.couponMerchantId = couponMerchantId;
    }

    public String getIsVerification() {
        return isVerification;
    }

    public void setIsVerification(String isVerification) {
        this.isVerification = isVerification;
    }

}
