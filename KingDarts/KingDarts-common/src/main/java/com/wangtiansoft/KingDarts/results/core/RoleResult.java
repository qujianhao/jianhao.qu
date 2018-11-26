package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.util.Date;

/**
 * Created by weitong on 17/3/24.
 */
public class RoleResult extends BaseResult {

    private Integer id;
    private String name;
    private Integer orderNum;
    private Byte isPublish;
    private Byte isDelete;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private String premContent;
    private String codeContent;

    public RoleResult() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Byte getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Byte isPublish) {
        this.isPublish = isPublish;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPremContent() {
        return premContent;
    }

    public void setPremContent(String premContent) {
        this.premContent = premContent;
    }
}
