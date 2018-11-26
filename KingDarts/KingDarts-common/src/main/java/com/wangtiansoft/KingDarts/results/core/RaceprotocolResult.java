package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class RaceprotocolResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String title; //主题
    private String prcontent;   //  协议内容
    private String htmlurl;   //  静态页面地址
    private String maccount;   //  更新人账号
    private String mnames;   //  更新姓名
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;   //  更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;   //  创建时间
    
    private Integer is_delete;//是否删除
    
    private Integer is_publish;//是否发布

    public RaceprotocolResult() {
    }
    
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrcontent() {
        return this.prcontent;
    }
    public void setPrcontent(String prcontent) {
        this.prcontent = prcontent;
    }
    public String getHtmlurl() {
        return this.htmlurl;
    }
    public void setHtmlurl(String htmlurl) {
        this.htmlurl = htmlurl;
    }
    public String getMaccount() {
        return this.maccount;
    }
    public void setMaccount(String maccount) {
        this.maccount = maccount;
    }
    public String getMnames() {
        return this.mnames;
    }
    public void setMnames(String mnames) {
        this.mnames = mnames;
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
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public Integer getIs_publish() {
		return is_publish;
	}
	public void setIs_publish(Integer is_publish) {
		this.is_publish = is_publish;
	}
    
}
