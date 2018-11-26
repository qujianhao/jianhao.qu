package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class BossLuckResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private Integer score_id;   //  得分ID
    private Integer score;   //  分数
    private Integer times;   //  
    private String luck;   //  

    public BossLuckResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getScore_id() {
        return this.score_id;
    }
    public void setScore_id(Integer score_id) {
        this.score_id = score_id;
    }
    public Integer getScore() {
        return this.score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
    public Integer getTimes() {
        return this.times;
    }
    public void setTimes(Integer times) {
        this.times = times;
    }

	public String getLuck() {
		return luck;
	}

	public void setLuck(String luck) {
		this.luck = luck;
	}
    
}
