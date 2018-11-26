package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by weiLsh on 2017/4/18.
 */
public class UserCouponDetailBean implements Serializable {

    private String userCouponId;       // 用户优惠券id
    private String verifyCode;         // 用户优惠券核销id
    private String veritySureCode;     // 用户优惠券核销确认码

    public UserCouponDetailBean() {
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVeritySureCode() {
        return veritySureCode;
    }

    public void setVeritySureCode(String veritySureCode) {
        this.veritySureCode = veritySureCode;
    }

    public String getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(String userCouponId) {
        this.userCouponId = userCouponId;
    }

}
