package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_userprotocol")
public class Userprotocol extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * 主题
     */
    private String title;

    /**
     * 静态页面地址
     */
    private String htmlurl;

    /**
     * 更新人账号
     */
    private String maccount;

    /**
     * 更新姓名
     */
    private String mnames;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * 创建时间
     */
    private Date create_time;
    
    /**
     * 是否删除
     */
    private Integer is_delete;
    
    /**
     * 是否发布
     */
    private Integer is_publish;
    
    /**
     * 协议内容
     */
    private String prcontent;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * 获取主题
     *
     * @return title - 主题
     */
    public String getTitle() {
		return title;
	}

    /**
     * 设置主题
     *
     * @param title 主题
     */
	public void setTitle(String title) {
		this.title = title;
	}

    /**
     * 获取静态页面地址
     *
     * @return htmlurl - 静态页面地址
     */
    public String getHtmlurl() {
        return htmlurl;
    }

    /**
     * 设置静态页面地址
     *
     * @param htmlurl 静态页面地址
     */
    public void setHtmlurl(String htmlurl) {
        this.htmlurl = htmlurl;
    }

    /**
     * 获取更新人账号
     *
     * @return maccount - 更新人账号
     */
    public String getMaccount() {
        return maccount;
    }

    /**
     * 设置更新人账号
     *
     * @param maccount 更新人账号
     */
    public void setMaccount(String maccount) {
        this.maccount = maccount;
    }

    /**
     * 获取更新姓名
     *
     * @return mnames - 更新姓名
     */
    public String getMnames() {
        return mnames;
    }

    /**
     * 设置更新姓名
     *
     * @param mnames 更新姓名
     */
    public void setMnames(String mnames) {
        this.mnames = mnames;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdate_time() {
		return update_time;
	}
    
    /**
     * 设置更新时间
     *
     * @param update_time 更新时间
     */
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	/**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
	public Date getCreate_time() {
		return create_time;
	}

	/**
     * 设置创建时间
     *
     * @param create_time 创建时间
     */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

    /**
     * 获取协议内容
     *
     * @return prcontent - 协议内容
     */
    public String getPrcontent() {
        return prcontent;
    }

    /**
     * 设置协议内容
     *
     * @param prcontent 协议内容
     */
    public void setPrcontent(String prcontent) {
        this.prcontent = prcontent;
    }
    
    /**
     * 获取是否删除 
     * @return  is_delete-是否删除：0，未删除 1，已删除 
     */
	public Integer getIs_delete() {
		return is_delete;
	}
    
    /**
     * 设置是否删除 
     *
     * @param is_delete-是否删除：0，未删除 1，已删除 
     */
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}

	/**
     * 获取是否发布
     * @return  is_publish-是否发布：0，未发布 1，已发布 
     */
	public Integer getIs_publish() {
		return is_publish;
	}

	/**
     * 设置是否发布
     *
     * @param is_publish-是否发布：0，未发布 1，已发布 
     */
	public void setIs_publish(Integer is_publish) {
		this.is_publish = is_publish;
	}
}