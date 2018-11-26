package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import javax.persistence.*;

@Table(name = "wt_user_openid")
public class UserOpenid extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户uid
     */
    private String user_id;

    /**
     * 用户openid，服务号，小程序不同
     */
    private String openid;
    
    private String appid;

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
     * 获取用户uid
     *
     * @return user_id - 用户uid
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置用户uid
     *
     * @param user_id 用户uid
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取用户openid，服务号，小程序不同
     *
     * @return openid - 用户openid，服务号，小程序不同
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置用户openid，服务号，小程序不同
     *
     * @param openid 用户openid，服务号，小程序不同
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
    
}