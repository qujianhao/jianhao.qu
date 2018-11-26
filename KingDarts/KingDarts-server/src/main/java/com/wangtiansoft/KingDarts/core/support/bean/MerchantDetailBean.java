package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by weiLsh on 17/3/21.
 */
public class MerchantDetailBean implements Serializable {

    private String id;
    private String merchantName;
    private String brandId;
    private String merchantAddress;
    private String merchantTelephone;
    private String[] merchantImg;
    private String picOne;
    private String picTwo;
    private String picThree;
    private String picFour;
    private String picFive;
    private String sortNum;

    public MerchantDetailBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getMerchantTelephone() {
        return merchantTelephone;
    }

    public void setMerchantTelephone(String merchantTelephone) {
        this.merchantTelephone = merchantTelephone;
    }

    public String getPicOne() {
        return picOne;
    }

    public void setPicOne(String picOne) {
        this.picOne = picOne;
    }

    public String getPicTwo() {
        return picTwo;
    }

    public void setPicTwo(String picTwo) {
        this.picTwo = picTwo;
    }

    public String getPicThree() {
        return picThree;
    }

    public void setPicThree(String picThree) {
        this.picThree = picThree;
    }

    public String getPicFour() {
        return picFour;
    }

    public void setPicFour(String picFour) {
        this.picFour = picFour;
    }

    public String getPicFive() {
        return picFive;
    }

    public void setPicFive(String picFive) {
        this.picFive = picFive;
    }

    public String getSortNum() {
        return sortNum;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }

    public String[] getMerchantImg() {
        return merchantImg;
    }

    public void setMerchantImg(String[] merchantImg) {
        this.merchantImg = merchantImg;
    }
}
