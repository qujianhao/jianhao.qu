package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by weiLsh on 17/4/6.
 */
public class OfficialDetailBean implements Serializable {

    private String officalType;
    private String officalContent;

    public OfficialDetailBean() {
    }

    public String getOfficalType() {
        return officalType;
    }

    public void setOfficalType(String officalType) {
        this.officalType = officalType;
    }

    public String getOfficalContent() {
        return officalContent;
    }

    public void setOfficalContent(String officalContent) {
        this.officalContent = officalContent;
    }
}
