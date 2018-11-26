package com.wangtiansoft.KingDarts.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by apple on 2018/7/3.
 * App资源表
 */
@Table(name = "darts_resource")
@Getter
@Setter
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 资源url
     */
    private String resource_url;
    /**
     * 资源name
     */
    private String resource_name;
	/**
	 * 资源name
	 */
	private String file_name;
    /**
     * 资源版本
     */
    private Integer resource_version;
    /**
     * 是否是压缩包
     */
    private Integer type;
    /**
     * 是否发布
     */
    private Integer is_publish;

    /**
     * 是否删除
     */
    private Integer is_delete;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 创建时间
     */
    private Date create_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResource_url() {
		return resource_url;
	}

	public void setResource_url(String resource_url) {
		this.resource_url = resource_url;
	}

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	public Integer getResource_version() {
		return resource_version;
	}

	public void setResource_version(Integer resource_version) {
		this.resource_version = resource_version;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
}
