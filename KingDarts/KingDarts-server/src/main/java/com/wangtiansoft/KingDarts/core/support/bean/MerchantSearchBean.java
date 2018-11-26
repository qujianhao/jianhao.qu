package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by weiLsh on 17/3/21.
 */
public class MerchantSearchBean implements Serializable {

    private String merchantName;
    private String brandId;
    private String merchantTelephone;
    private String merchantId;
    private String createTime_TimeBegin;
    private String createTime_TimeEnd;

    public MerchantSearchBean() {
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
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

    public String getMerchantTelephone() {
        return merchantTelephone;
    }

    public void setMerchantTelephone(String merchantTelephone) {
        this.merchantTelephone = merchantTelephone;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
