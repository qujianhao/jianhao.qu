package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import javax.persistence.*;

@Table(name = "darts_user_game_points")
public class UserGamePoints extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 游戏类型
     */
    private String game_type;
    /**
     * 游戏编码
     */
    private String game_code;

    /**
     * 积分
     */
    private Integer points;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置用户id
     *
     * @param user_id 用户id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取游戏类型
     *
     * @return game_type - 游戏类型
     */
    public String getGame_type() {
        return game_type;
    }

    /**
     * 设置游戏类型
     *
     * @param game_type 游戏类型
     */
    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    /**
     * 获取积分
     *
     * @return points - 积分
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * 设置积分
     *
     * @param points 积分
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

	public String getGame_code() {
		return game_code;
	}

	public void setGame_code(String game_code) {
		this.game_code = game_code;
	}
    
}