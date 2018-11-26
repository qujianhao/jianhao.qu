package com.wangtiansoft.KingDarts.results.core;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

/**
 * Created by wt-templete-helper on unknow.
 */
public class BossResult extends BaseResult implements Serializable{

    private Integer id;   //  主键id
    private String bno;   //  boss编号
    private String bname;   //  boss名称
    private Integer bvolume;   //  boss血量
    private Integer is_use;   //  是否应用 0：否 1:是
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;//创建时间

    public BossResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getBno() {
        return this.bno;
    }
    public void setBno(String bno) {
        this.bno = bno;
    }
    public String getBname() {
        return this.bname;
    }
    public void setBname(String bname) {
        this.bname = bname;
    }
    public Integer getBvolume() {
        return this.bvolume;
    }
    public void setBvolume(Integer bvolume) {
        this.bvolume = bvolume;
    }
    public Integer getIs_use() {
        return this.is_use;
    }
    public void setIs_use(Integer is_use) {
        this.is_use = is_use;
    }
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
    
}
