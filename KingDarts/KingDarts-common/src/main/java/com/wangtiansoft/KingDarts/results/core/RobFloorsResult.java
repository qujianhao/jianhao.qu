package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class RobFloorsResult extends BaseResult implements Serializable{

    private Integer id;   //  
    private String user_id;   //  用户id
    private String useraccount;   //  用户账号
    private String user_nickname;   //  用户昵称
    private String get_point_id;   //  用户已领取的点数信息id，与sys_dict表中的id对应
    private Integer expense_point;   //  领取游戏点数所需消耗的点数
    private Integer point;   //  得到的游戏点数
    private Integer status;   //  1：每日；2：每周；3：每月

    public RobFloorsResult() {
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
    public String getUser_nickname() {
        return this.user_nickname;
    }
    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }
    public String getGet_point_id() {
        return this.get_point_id;
    }
    public void setGet_point_id(String get_point_id) {
        this.get_point_id = get_point_id;
    }
    public Integer getExpense_point() {
        return this.expense_point;
    }
    public void setExpense_point(Integer expense_point) {
        this.expense_point = expense_point;
    }
    public Integer getPoint() {
        return this.point;
    }
    public void setPoint(Integer point) {
        this.point = point;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
