package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_advert_info")
public class AdvertInfo extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 资源url
     */
    private String file_url;

    /**
     * 音频资源url
     */
    private String file_name;

    /**
     * 广告资源编号
     */
    private String file_size;

    /**
     * 描述
     */
    private String des_title;

    /**
     * 是否可用1可用 0不可用
     */
    private Integer is_publish;

    /**
     * 创建时间
     */
    private Date add_time;

    /**
     * 最后一次更新时间
     */
    private Date update_time;

    /**
     * 创建人编号
     */
    private String user_id;

    /**
     * 是否启用 1启用 0不启用
     */
    private Integer is_delete;
    
    /**
     * 二维码左边距
     */
    private Integer qr_left;
    /**
     * 二维码顶部边距
     */
    private Integer qr_top;
    
    /**
     * 二维码地址
     */
    private String qrcode_url;
    
    
    
    /**
     * 二维码地址
     */
    private String belong_club;

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
     * 获取资源url
     *
     * @return file_url - 资源url
     */
    public String getFile_url() {
        return file_url;
    }

    /**
     * 设置资源url
     *
     * @param file_url 资源url
     */
    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    /**
     * 获取音频资源url
     *
     * @return file_name - 音频资源url
     */
    public String getFile_name() {
        return file_name;
    }

    /**
     * 设置音频资源url
     *
     * @param file_name 音频资源url
     */
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    /**
     * 获取广告资源编号
     *
     * @return file_size - 广告资源编号
     */
    public String getFile_size() {
        return file_size;
    }

    /**
     * 设置广告资源编号
     *
     * @param file_size 广告资源编号
     */
    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public String getDes_title() {
		return des_title;
	}

	public void setDes_title(String des_title) {
		this.des_title = des_title;
	}

	/**
     * 获取是否可用1可用 0不可用
     *
     * @return is_publish - 是否可用1可用 0不可用
     */
    public Integer getIs_publish() {
        return is_publish;
    }

    /**
     * 设置是否可用1可用 0不可用
     *
     * @param is_publish 是否可用1可用 0不可用
     */
    public void setIs_publish(Integer is_publish) {
        this.is_publish = is_publish;
    }

    /**
     * 获取创建时间
     *
     * @return add_time - 创建时间
     */
    public Date getAdd_time() {
        return add_time;
    }

    /**
     * 设置创建时间
     *
     * @param add_time 创建时间
     */
    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    /**
     * 获取最后一次更新时间
     *
     * @return update_time - 最后一次更新时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 设置最后一次更新时间
     *
     * @param update_time 最后一次更新时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    /**
     * 获取创建人编号
     *
     * @return user_id - 创建人编号
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置创建人编号
     *
     * @param user_id 创建人编号
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取是否启用 1启用 0不启用
     *
     * @return is_delete - 是否启用 1启用 0不启用
     */
    public Integer getIs_delete() {
        return is_delete;
    }

    /**
     * 设置是否启用 1启用 0不启用
     *
     * @param is_delete 是否启用 1启用 0不启用
     */
    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

	public Integer getQr_left() {
		return qr_left;
	}

	public void setQr_left(Integer qr_left) {
		this.qr_left = qr_left;
	}

	public Integer getQr_top() {
		return qr_top;
	}

	public void setQr_top(Integer qr_top) {
		this.qr_top = qr_top;
	}

	public String getQrcode_url() {
		return qrcode_url;
	}

	public void setQrcode_url(String qrcode_url) {
		this.qrcode_url = qrcode_url;
	}

	public String getBelong_club() {
		return belong_club;
	}

	public void setBelong_club(String belong_club) {
		this.belong_club = belong_club;
	}
    
	
}