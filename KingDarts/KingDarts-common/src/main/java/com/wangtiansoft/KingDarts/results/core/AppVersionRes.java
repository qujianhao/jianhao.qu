package com.wangtiansoft.KingDarts.results.core;

import com.wangtiansoft.KingDarts.persistence.base.BaseResult;
import com.wangtiansoft.KingDarts.persistence.entity.AppVersion;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by apple on 2018/7/14.
 */
@Getter
@Setter
public class AppVersionRes extends BaseResult implements Serializable{
    /**
     * 资源url
     */
    private String appUrl;
    /**
     * 资源版本
     */
    private Integer appVersion;

    /**
     * 创建时间
     */
    private Date createTime;
   

    public AppVersionRes(AppVersion appVersion) {
        this.appUrl = appVersion.getApp_url();
        this.appVersion = appVersion.getApp_version();
        this.createTime = appVersion.getCreate_time();
    }


	public String getAppUrl() {
		return appUrl;
	}


	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}


	public Integer getAppVersion() {
		return appVersion;
	}


	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
}
