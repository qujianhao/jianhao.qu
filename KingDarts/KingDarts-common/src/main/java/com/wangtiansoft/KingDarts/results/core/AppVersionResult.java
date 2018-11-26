package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.entity.AppVersion;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by apple on 2018/7/14.
 */
@Getter
@Setter
public class AppVersionResult {
    
    private Integer id;
    /**
     * 资源url
     */
    private String app_url;
    /**
     * 资源版本
     */
    private Integer app_version;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;
    /**
     * 是否发布
     */
    private Integer is_publish;
    /**
     * 资源文件名称
     */
    private String file_name;
    /**
     * 是否删除
     */
    private Integer is_delete;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApp_url() {
		return app_url;
	}
	public void setApp_url(String app_url) {
		this.app_url = app_url;
	}
	public Integer getApp_version() {
		return app_version;
	}
	public void setApp_version(Integer app_version) {
		this.app_version = app_version;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getIs_publish() {
		return is_publish;
	}
	public void setIs_publish(Integer is_publish) {
		this.is_publish = is_publish;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
}
