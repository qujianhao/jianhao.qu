package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class ResourceFileResult extends BaseResult implements Serializable{

    private Integer id;   //  主键ID
    private String uuid;   //  文件标示
    private String url;   //  文件名
    private String md5;   //  文件MD5
    private Integer lenght;   //  文件大小
    private String name;   //  文件名称

    public ResourceFileResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUuid() {
        return this.uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMd5() {
        return this.md5;
    }
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    public Integer getLenght() {
        return this.lenght;
    }
    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
