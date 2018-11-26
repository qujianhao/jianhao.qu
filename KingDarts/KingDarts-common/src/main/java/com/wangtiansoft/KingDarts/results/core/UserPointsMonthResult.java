package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class UserPointsMonthResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String user_id;   //  用户id
    private String game_type;   //  游戏类型
    private String game_code;   //  游戏编码
    private Integer points;   //  本月积分

    public UserPointsMonthResult() {
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
    public String getGame_type() {
        return this.game_type;
    }
    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }
    public String getGame_code() {
        return this.game_code;
    }
    public void setGame_code(String game_code) {
        this.game_code = game_code;
    }
    public Integer getPoints() {
        return this.points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }
}
