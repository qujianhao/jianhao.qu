package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class PluginsResult extends BaseResult implements Serializable{

    private Integer id;   //  主键ID
    private String plugin_key;   //  插件标示
    private String title;   //  插件名称
    private String summary;   //  插件描述
    private String app_id;   //  appId
    private String app_key;   //  插件key
    private String app_secret;   //  插件secret
    private String attrs;   //  插件详细属性
    private Integer order_num;   //  排序字段-倒序
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;   //  修改时间
    private Integer is_publish;   //  是否发布

    public PluginsResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPlugin_key() {
        return this.plugin_key;
    }
    public void setPlugin_key(String plugin_key) {
        this.plugin_key = plugin_key;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSummary() {
        return this.summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getApp_id() {
        return this.app_id;
    }
    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }
    public String getApp_key() {
        return this.app_key;
    }
    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }
    public String getApp_secret() {
        return this.app_secret;
    }
    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }
    public String getAttrs() {
        return this.attrs;
    }
    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }
    public Integer getOrder_num() {
        return this.order_num;
    }
    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
    }
    public Date getUpdate_time() {
        return this.update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public Integer getIs_publish() {
        return this.is_publish;
    }
    public void setIs_publish(Integer is_publish) {
        this.is_publish = is_publish;
    }
}
