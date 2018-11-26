package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class SigninSetResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private Integer signin_day;   //  本周第几天
    private String name;   //  设置名称
    private Integer point;   //  获取点数
    private Integer is_publish;   //  是否发布 1 发布 0未发布
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;   //  修改时间

    public SigninSetResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getSignin_day() {
        return this.signin_day;
    }
    public void setSignin_day(Integer signin_day) {
        this.signin_day = signin_day;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPoint() {
        return this.point;
    }
    public void setPoint(Integer point) {
        this.point = point;
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
