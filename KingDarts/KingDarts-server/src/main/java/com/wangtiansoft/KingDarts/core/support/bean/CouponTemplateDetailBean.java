package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/7.
 */
public class CouponTemplateDetailBean implements Serializable {

    private String id;
    private String uuid;// UUID
    private String templateName;// 优惠券模板名称
    private Integer templateType;// 优惠券模板类型
    private String templateImg;// 优惠券模板图片

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getTemplateImg() {
        return templateImg;
    }

    public void setTemplateImg(String templateImg) {
        this.templateImg = templateImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
