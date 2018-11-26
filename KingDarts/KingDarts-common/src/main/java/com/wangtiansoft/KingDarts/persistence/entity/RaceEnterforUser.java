package com.wangtiansoft.KingDarts.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "darts_race_enterfor_user")
public class RaceEnterforUser {

	/**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * 报名用户ID
     */
    private String user_id;
    
    /**
     * 赛事编号
     */
    private String raceno;
    
    /**
     * 报名时间
     */
    private Date enterfor_time;
    
    /**
     * 名次
     */
    private Integer ranking;
    
    /**
     * 积分奖励
     */
    private Integer reward_points;
    
    private Date add_time;
    
    private Date update_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRaceno() {
		return raceno;
	}

	public void setRaceno(String raceno) {
		this.raceno = raceno;
	}

	public Date getEnterfor_time() {
		return enterfor_time;
	}

	public void setEnterfor_time(Date enterfor_time) {
		this.enterfor_time = enterfor_time;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getReward_points() {
		return reward_points;
	}

	public void setReward_points(Integer reward_points) {
		this.reward_points = reward_points;
	}
    
    
}
