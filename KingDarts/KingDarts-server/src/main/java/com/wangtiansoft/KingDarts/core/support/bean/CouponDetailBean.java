package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/7.
 */
public class CouponDetailBean implements Serializable {

    private String id;// id
    private String templateId;// 优惠券模板id
    private String[] couponMerchantId;    //优惠券商户id
    private String[] couponMerchantCount; //每位商户发放数量
    private String allQuantity;// 总发放数量
    private String personQuantity;// 每人限领
    private String couponInfo;// 优惠券信息
    private String startTime;
    private String endTime;
    private String couponStatus;

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    public CouponDetailBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String[] getCouponMerchantId() {
        return couponMerchantId;
    }

    public void setCouponMerchantId(String[] couponMerchantId) {
        this.couponMerchantId = couponMerchantId;
    }

    public String[] getCouponMerchantCount() {
        return couponMerchantCount;
    }

    public void setCouponMerchantCount(String[] couponMerchantCount) {
        this.couponMerchantCount = couponMerchantCount;
    }

    public String getAllQuantity() {
        return allQuantity;
    }

    public void setAllQuantity(String allQuantity) {
        this.allQuantity = allQuantity;
    }

    public String getPersonQuantity() {
        return personQuantity;
    }

    public void setPersonQuantity(String personQuantity) {
        this.personQuantity = personQuantity;
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
