package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class UserPointsResult extends BaseResult implements Serializable{

    private Integer id;   //  唯一标识
    private String user_id;   //  用户id，user.uuid
    private Integer order_id;   //  游戏订单ID
    private String game_type;
    private String game_code;
    private Integer points;   //  积分数量
    private Integer points_type;//积分类型（0.游戏奖励，1.比赛奖励）
    private String remark;   //  描述
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date log_time;//记录时间

    public UserPointsResult() {
    }
    
    public Integer getPoints_type() {
		return points_type;
	}

	public void setPoints_type(Integer points_type) {
		this.points_type = points_type;
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
    public Integer getOrder_id() {
        return this.order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public Integer getPoints() {
        return this.points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getGame_type() {
		return game_type;
	}

	public void setGame_type(String game_type) {
		this.game_type = game_type;
	}

	public String getGame_code() {
		return game_code;
	}

	public void setGame_code(String game_code) {
		this.game_code = game_code;
	}

	public Date getLog_time() {
		return log_time;
	}

	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}
    
}
