package com.wangtiansoft.KingDarts.results.core;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RaceEnterforUserResult {

	private Integer id;
    private String user_id;//报名用户ID
    private String raceno;//赛事编号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date enterfor_time;//报名时间
    private Integer reward_points;//积分奖励
    private Integer ranking;//名次
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date add_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;
    
    private String mobile;//手机号
    private String headimgurl;//头像地址
    private String username;//用户名
    private String nickname;//昵称
    
    
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getReward_points() {
		return reward_points;
	}
	public void setReward_points(Integer reward_points) {
		this.reward_points = reward_points;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
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
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    
    
}
