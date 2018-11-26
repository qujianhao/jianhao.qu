package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_medal_winner")
public class MedalWinner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 获得勋章的用户id
     */
    private String user_id;

    /**
     * 获得勋章的用户账号
     */
    private String useraccount;

    /**
     * 勋章id
     */
    private Integer medal_id;

    /**
     * 勋章名称
     */
    private String medal_name;

    /**
     * 勋章的url
     */
    private String medal_url;

    /**
     * 奖励的游戏点数
     */
    private Integer prize_point;

    /**
     * 获得勋章时间
     */
    private Date create_time;
    
    /**
     * 是否领取了奖励 0 未领取 1 领取
     */
    private Integer is_receive;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取获得勋章的用户id
     *
     * @return user_id - 获得勋章的用户id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置获得勋章的用户id
     *
     * @param user_id 获得勋章的用户id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取获得勋章的用户账号
     *
     * @return useraccount - 获得勋章的用户账号
     */
    public String getUseraccount() {
        return useraccount;
    }

    /**
     * 设置获得勋章的用户账号
     *
     * @param useraccount 获得勋章的用户账号
     */
    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    /**
     * 获取勋章id
     *
     * @return medal_id - 勋章id
     */
    public Integer getMedal_id() {
        return medal_id;
    }

    /**
     * 设置勋章id
     *
     * @param medal_id 勋章id
     */
    public void setMedal_id(Integer medal_id) {
        this.medal_id = medal_id;
    }

    /**
     * 获取勋章名称
     *
     * @return medal_name - 勋章名称
     */
    public String getMedal_name() {
        return medal_name;
    }

    /**
     * 设置勋章名称
     *
     * @param medal_name 勋章名称
     */
    public void setMedal_name(String medal_name) {
        this.medal_name = medal_name;
    }

    /**
     * 获取勋章的url
     *
     * @return medal_url - 勋章的url
     */
    public String getMedal_url() {
        return medal_url;
    }

    /**
     * 设置勋章的url
     *
     * @param medal_url 勋章的url
     */
    public void setMedal_url(String medal_url) {
        this.medal_url = medal_url;
    }

    /**
     * 获取奖励的游戏点数
     *
     * @return prize_point - 奖励的游戏点数
     */
    public Integer getPrize_point() {
        return prize_point;
    }

    /**
     * 设置奖励的游戏点数
     *
     * @param prize_point 奖励的游戏点数
     */
    public void setPrize_point(Integer prize_point) {
        this.prize_point = prize_point;
    }

    /**
     * 获取获得勋章时间
     *
     * @return create_time - 获得勋章时间
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 设置获得勋章时间
     *
     * @param create_time 获得勋章时间
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /**
     * 获得是否领取奖励 0 没领取 1 已领取
     *
     * @return is_receive - 是否领取奖励
     */
	public Integer getIs_receive() {
		return is_receive;
	}

	/**
     * 设置是否领取奖励 0 没领取 1 已领取
     *
     * @param is_receive 是否领取奖励
     */
	public void setIs_receive(Integer is_receive) {
		this.is_receive = is_receive;
	}
    
    
}