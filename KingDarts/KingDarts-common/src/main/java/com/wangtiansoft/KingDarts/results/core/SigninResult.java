package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class SigninResult extends BaseResult implements Serializable{

    private Integer id;   //  
    private String user_id;   //  签到用户id
    private String useraccount;   //  签到用户账号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date signin_date;   //  签到日期
    private Integer signin_day;   //  签到日期是本周第几天
    private Integer signin_point;   //  签到获得的积分

    public SigninResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUser_id() {
        return this.user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUseraccount() {
        return this.useraccount;
    }
    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }
    public Date getSignin_date() {
        return this.signin_date;
    }
    public void setSignin_date(Date signin_date) {
        this.signin_date = signin_date;
    }
    public Integer getSignin_day() {
        return this.signin_day;
    }
    public void setSignin_day(Integer signin_day) {
        this.signin_day = signin_day;
    }
    public Integer getSignin_point() {
        return this.signin_point;
    }
    public void setSignin_point(Integer signin_point) {
        this.signin_point = signin_point;
    }
}
