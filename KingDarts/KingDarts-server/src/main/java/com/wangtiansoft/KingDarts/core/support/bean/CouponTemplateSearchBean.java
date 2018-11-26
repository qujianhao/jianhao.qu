package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;
import java.text.ParseException;

/**
 * Created by Administrator on 2017/4/6.
 */
public class CouponTemplateSearchBean implements Serializable {
    private String templateName;// 优惠券模板名称
    private String createTimeBegin;// 开始时间
    //private Date createTime_TimeBegin;
    private String createTimeEnd;// 结束时间
    //private Date createTime_TimeEnd;
    private String templateType;// 优惠券类型
    private String createTime_TimeBegin;
    private String createTime_TimeEnd;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName){
        this.templateName = templateName;
    }

    public String getCreateTimeBegin() {
        return createTimeBegin;

    }

    public void setCreateTimeBegin(String createTimeBegin) throws ParseException {
        this.createTimeBegin = createTimeBegin;
        //this.createTime_TimeBegin = DateUtils.parseDate(createTimeBegin, "yyyy-MM-dd");
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) throws ParseException {
        this.createTimeEnd = createTimeEnd;
        //this.createTime_TimeEnd = DateUtils.parseDate(createTimeBegin, "yyyy-MM-dd");
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
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

    /*public Date getCreateTime_TimeEnd() {
        return createTime_TimeEnd;
    }*/

    /*public Date getCreateTime_TimeBegin() {
        return createTime_TimeBegin;
    }*/
}
