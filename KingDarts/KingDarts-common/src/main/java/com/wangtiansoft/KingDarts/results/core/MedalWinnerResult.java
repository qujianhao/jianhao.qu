package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class MedalWinnerResult extends BaseResult implements Serializable{

    private Integer id;   //  
    private String user_id;   //  获得勋章的用户id
    private String useraccount;   //  获得勋章的用户账号
    private Integer medal_id;   //  勋章id
    private String medal_name;   //  勋章名称
    private String medal_url;   //  勋章图标的url
    private Integer prize_point;   //  奖励的游戏点数
    private Integer is_receive;   //  是否领取奖励
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;//获得勋章时间

    public MedalWinnerResult() {
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
    public Integer getMedal_id() {
        return this.medal_id;
    }
    public void setMedal_id(Integer medal_id) {
        this.medal_id = medal_id;
    }
    public String getMedal_name() {
        return this.medal_name;
    }
    public void setMedal_name(String medal_name) {
        this.medal_name = medal_name;
    }
    public String getMedal_url() {
        return this.medal_url;
    }
    public void setMedal_url(String medal_url) {
        this.medal_url = medal_url;
    }
    public Integer getPrize_point() {
        return this.prize_point;
    }
    public void setPrize_point(Integer prize_point) {
        this.prize_point = prize_point;
    }
	public Integer getIs_receive() {
		return is_receive;
	}
	public void setIs_receive(Integer is_receive) {
		this.is_receive = is_receive;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
    
}
