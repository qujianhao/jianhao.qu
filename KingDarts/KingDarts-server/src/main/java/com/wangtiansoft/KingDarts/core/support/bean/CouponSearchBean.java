package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/6.
 */
public class CouponSearchBean implements Serializable {

    private String releaseType; //发布渠道
    private String brandName;// 所属品牌
    private String merchantName;// 所属商户
    private String couponName;// 优惠券名称
    private String status;// 优惠券使用状态
    private String isPublish;//优惠券发布状态
    private String createTime_TimeBegin;
    private String createTime_TimeEnd;

    public CouponSearchBean() {
    }

    public String getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(String releaseType) {
        this.releaseType = releaseType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCreateTime_TimeBegin() {
        return createTime_TimeBegin;
    }

    public void setCreateTime_TimeBegin(String createTime_TimeBegin) {
        this.createTime_TimeBegin = createTime_TimeBegin;
    }

    public String getCreateTime_TimeEnd() {
        return createTime_TimeEnd;
    }

    public void setCreateTime_TimeEnd(String createTime_TimeEnd) {
        this.createTime_TimeEnd = createTime_TimeEnd;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}
