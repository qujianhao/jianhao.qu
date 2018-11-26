package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ActivityDetailBean implements Serializable{

    private String id;                 //活动id
    private String activityImg;             //活动图片
    private String activityName;       //活动名称
    private String[] couponTemplateId; //优惠券模版id
    private String[] allQuantity;      //优惠券发放数量
    private String[] personQuantity;   //每人限领
    private String[] couponInfo;       //优惠信息
    private String[] merchantId;       //商户Id
    private String activityInfo;       //活动信息
    private String activityStatus;     //活动状态   已保存 已发布 已过期
    private String startTime;          //有效期
    private String endTime;

    public ActivityDetailBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    public String[] getCouponTemplateId() {
        return couponTemplateId;
    }

    public void setCouponTemplateId(String[] couponTemplateId) {
        this.couponTemplateId = couponTemplateId;
    }

    public String[] getAllQuantity() {
        return allQuantity;
    }

    public void setAllQuantity(String[] allQuantity) {
        this.allQuantity = allQuantity;
    }

    public String[] getPersonQuantity() {
        return personQuantity;
    }

    public void setPersonQuantity(String[] personQuantity) {
        this.personQuantity = personQuantity;
    }

    public String[] getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String[] couponInfo) {
        this.couponInfo = couponInfo;
    }

    public String[] getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String[] merchantId) {
        this.merchantId = merchantId;
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


    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
