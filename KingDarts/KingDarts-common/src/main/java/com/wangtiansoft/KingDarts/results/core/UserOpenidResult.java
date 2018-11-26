package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class UserOpenidResult extends BaseResult implements Serializable{

    private Integer id;   //  
    private String user_id;   //  用户uid
    private String openid;   //  用户openid，服务号，小程序不同
    private String appid;   //  用户appid，服务号，小程序不同

    public UserOpenidResult() {
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
    public String getOpenid() {
        return this.openid;
    }
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
