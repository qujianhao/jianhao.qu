package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_user_points")
public class UserPoints extends BaseEntity {
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id，user.uuid
     */
    private String user_id;

    /**
     * 游戏订单ID
     */
    private Long order_id;
    
    /**
     * 游戏类型
     */
    private String game_type;
    
    /**
     * 游戏编码
     */
    private String game_code;

    /**
     * 积分数量
     */
    private Integer points;
    
    /**
     * 积分类型（0.游戏奖励，1.比赛奖励）
     */
    private Integer points_type;

    /**
     * 记录时间
     */
    private Date log_time;

    /**
     * 描述
     */
    private String remark;

    //点击用户id
    private String click_user_id;

    public String getClick_user_id() {
        return click_user_id;
    }

    public void setClick_user_id(String click_user_id) {
        this.click_user_id = click_user_id;
    }

    public Integer getPoints_type() {
		return points_type;
	}

	public void setPoints_type(Integer points_type) {
		this.points_type = points_type;
	}

	/**
     * 获取唯一标识
     *
     * @return id - 唯一标识
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置唯一标识
     *
     * @param id 唯一标识
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id，user.uuid
     *
     * @return user_id - 用户id，user.uuid
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置用户id，user.uuid
     *
     * @param user_id 用户id，user.uuid
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取游戏订单ID
     *
     * @return order_id - 游戏订单ID
     */
    public Long getOrder_id() {
        return order_id;
    }

    /**
     * 设置游戏订单ID
     *
     * @param order_id 游戏订单ID
     */
    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    /**
     * 获取积分数量
     *
     * @return points - 积分数量
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * 设置积分数量
     *
     * @param points 积分数量
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * 获取记录时间
     *
     * @return log_time - 记录时间
     */
    public Date getLog_time() {
        return log_time;
    }

    /**
     * 设置记录时间
     *
     * @param log_time 记录时间
     */
    public void setLog_time(Date log_time) {
        this.log_time = log_time;
    }

    /**
     * 获取描述
     *
     * @return remark - 描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置描述
     *
     * @param remark 描述
     */
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
    
    
}