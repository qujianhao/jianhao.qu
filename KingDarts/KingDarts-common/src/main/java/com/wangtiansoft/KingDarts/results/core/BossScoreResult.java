package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class BossScoreResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String order_no;   //  订单编号
    private String user_id;   //  用户ID
    private String boss_id;   //  击打bossid
    private Integer total_score;   //  有效分数

    public BossScoreResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrder_no() {
        return this.order_no;
    }
    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }
    public String getUser_id() {
        return this.user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getBoss_id() {
        return this.boss_id;
    }
    public void setBoss_id(String boss_id) {
        this.boss_id = boss_id;
    }
    public Integer getTotal_score() {
        return this.total_score;
    }
    public void setTotal_score(Integer total_score) {
        this.total_score = total_score;
    }
}
