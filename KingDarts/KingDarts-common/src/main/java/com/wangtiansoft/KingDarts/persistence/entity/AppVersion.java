package com.wangtiansoft.KingDarts.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;

import java.util.Date;

/**
 * Created by apple on 2018/7/3.
 * App版本
 */
@Getter
@Setter
@Table(name = "darts_app_version")
public class AppVersion extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

    
}
