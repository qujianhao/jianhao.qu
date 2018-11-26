package com.wangtiansoft.KingDarts.results.mapper;

import java.io.Serializable;

import com.wangtiansoft.KingDarts.persistence.base.BaseResult;
import com.wangtiansoft.KingDarts.persistence.entity.Resource;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by apple on 2018/7/3.
 */
@Getter
@Setter
public class ResourceEntity extends BaseResult implements Serializable{
    /**
     * 资源url
     */
    private String resourceUrl;
    /**
     * 资源name
     */
    private String resourceName;
    /**
     * 资源name
     */
    private String fileName;
    /**
     * 资源版本
     */
    private Integer resourceVersion;

    /**
     * 是否是压缩包
     */
    private Integer type;

    public ResourceEntity(Resource resource) {
        this.resourceUrl = resource.getResource_url();
        this.resourceName = resource.getResource_name();
        this.resourceVersion = resource.getResource_version();
        this.type = resource.getType();
        this.fileName = resource.getFile_name();
    }

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getResourceVersion() {
		return resourceVersion;
	}

	public void setResourceVersion(Integer resourceVersion) {
		this.resourceVersion = resourceVersion;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
