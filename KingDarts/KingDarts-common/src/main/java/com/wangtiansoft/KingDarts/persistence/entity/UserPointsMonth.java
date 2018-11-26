package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import javax.persistence.*;

@Table(name = "darts_user_points_month")
public class UserPointsMonth extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
     * 统计排名时间
     */
    private String rank_time;

    /**
     * 本月积分
     */
    private Long points;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
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
     * 获取游戏编码
     *
     * @return game_code - 游戏编码
     */
    public String getGame_code() {
        return game_code;
    }

    /**
     * 设置游戏编码
     *
     * @param game_code 游戏编码
     */
    public void setGame_code(String game_code) {
        this.game_code = game_code;
    }

    /**
     * 获取统计排名时间
     *
     * @return rank_time - 统计排名时间
     */
    public String getRank_time() {
        return rank_time;
    }

    /**
     * 设置统计排名时间
     *
     * @param rank_time 统计排名时间
     */
    public void setRank_time(String rank_time) {
        this.rank_time = rank_time;
    }

    /**
     * 获取本月积分
     *
     * @return points - 本月积分
     */
    public Long getPoints() {
        return points;
    }

    /**
     * 设置本月积分
     *
     * @param points 本月积分
     */
    public void setPoints(Long points) {
        this.points = points;
    }
}