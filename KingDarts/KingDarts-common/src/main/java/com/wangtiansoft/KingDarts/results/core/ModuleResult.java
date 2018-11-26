package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class ModuleResult extends BaseResult implements Serializable{

    private Integer id;   //  主键ID
    private String name;   //  角色名称
    private String icon;   //  图标
    private String url;   //  访问地址
    private Integer parent;   //  父权限 0表示根权限
    private String code;   //  权限码
    private Integer order_num;   //  排序
    private Integer is_publish;   //  是否发布
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;   //  修改时间

    public ModuleResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getParent() {
        return this.parent;
    }
    public void setParent(Integer parent) {
        this.parent = parent;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Integer getOrder_num() {
        return this.order_num;
    }
    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
    }
    public Integer getIs_publish() {
        return this.is_publish;
    }
    public void setIs_publish(Integer is_publish) {
        this.is_publish = is_publish;
    }
    public Date getUpdate_time() {
        return this.update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
