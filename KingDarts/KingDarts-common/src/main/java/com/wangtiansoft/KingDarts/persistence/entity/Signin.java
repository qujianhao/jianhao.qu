package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_signin")
public class Signin extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 签到用户id
     */
    private String user_id;

    /**
     * 签到用户账号
     */
    private String useraccount;

    /**
     * 签到日期
     */
    private Date signin_date;

    /**
     * 签到日期是本周第几天
     */
    private Integer signin_day;

    /**
     * 签到获得的积分
     */
    private Integer signin_point;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取签到用户id
     *
     * @return user_id - 签到用户id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置签到用户id
     *
     * @param user_id 签到用户id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取签到用户账号
     *
     * @return useraccount - 签到用户账号
     */
    public String getUseraccount() {
        return useraccount;
    }

    /**
     * 设置签到用户账号
     *
     * @param useraccount 签到用户账号
     */
    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    /**
     * 获取签到日期
     *
     * @return signin_date - 签到日期
     */
    public Date getSignin_date() {
        return signin_date;
    }

    /**
     * 设置签到日期
     *
     * @param signin_date 签到日期
     */
    public void setSignin_date(Date signin_date) {
        this.signin_date = signin_date;
    }

    /**
     * 获取签到日期是本周第几天
     *
     * @return signin_day - 签到日期是本周第几天
     */
    public Integer getSignin_day() {
        return signin_day;
    }

    /**
     * 设置签到日期是本周第几天
     *
     * @param signin_day 签到日期是本周第几天
     */
    public void setSignin_day(Integer signin_day) {
        this.signin_day = signin_day;
    }

    /**
     * 获取签到获得的积分
     *
     * @return signin_point - 签到获得的积分
     */
    public Integer getSignin_point() {
        return signin_point;
    }

    /**
     * 设置签到获得的积分
     *
     * @param signin_point 签到获得的积分
     */
    public void setSignin_point(Integer signin_point) {
        this.signin_point = signin_point;
    }
}