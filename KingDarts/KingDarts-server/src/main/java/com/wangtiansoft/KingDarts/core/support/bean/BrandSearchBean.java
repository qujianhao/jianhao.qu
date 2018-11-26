package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by weitong on 17/3/21.
 */
public class BrandSearchBean implements Serializable {

    private String brandName;
    private String createTime_TimeBegin;
    private String createTime_TimeEnd;

    public BrandSearchBean() {
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
}
