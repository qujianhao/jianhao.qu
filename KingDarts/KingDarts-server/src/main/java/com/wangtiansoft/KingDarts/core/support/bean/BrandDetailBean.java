package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by weiLsh on 17/3/21.
 */
public class BrandDetailBean implements Serializable {

    private String id;
    private String brandName;
    private String brandInfo;
    private String brandLogo;
    private String sortNum;

    public BrandDetailBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandInfo() {
        return brandInfo;
    }

    public void setBrandInfo(String brandInfo) {
        this.brandInfo = brandInfo;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getSortNum() {
        return sortNum;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }
}
