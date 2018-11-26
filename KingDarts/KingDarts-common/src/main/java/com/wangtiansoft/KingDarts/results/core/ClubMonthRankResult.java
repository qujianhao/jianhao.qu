package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class ClubMonthRankResult extends BaseResult implements Serializable{

    private Integer id;   //  主键信息id
    private String cno;   //  俱乐部编号
    private Integer athletics_num;   //  竞技榜局数
    private Integer strength_value;   //  实力榜实力值

    public ClubMonthRankResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCno() {
        return this.cno;
    }
    public void setCno(String cno) {
        this.cno = cno;
    }
    public Integer getAthletics_num() {
        return this.athletics_num;
    }
    public void setAthletics_num(Integer athletics_num) {
        this.athletics_num = athletics_num;
    }
    public Integer getStrength_value() {
        return this.strength_value;
    }
    public void setStrength_value(Integer strength_value) {
        this.strength_value = strength_value;
    }
}
