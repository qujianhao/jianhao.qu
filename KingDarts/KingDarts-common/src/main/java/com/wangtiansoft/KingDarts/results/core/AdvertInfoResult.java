package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class AdvertInfoResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String file_url;   //  资源url
    private String file_name;   //  音频资源url
    private String file_size;   //  广告资源编号
    private String des_title;   //  描述
    private Integer is_publish;   //  是否可用1可用 0不可用
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;   //  最后一次更新时间
    private String user_id;   //  创建人编号
    private Integer is_delete;	//是否删除
    private Integer qr_left;	//二维码左边距
    private Integer qr_top;	//二维码顶部边距
    private String qrcode_url;	//二维码地址

    public AdvertInfoResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFile_url() {
        return this.file_url;
    }
    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }
    public String getFile_name() {
        return this.file_name;
    }
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
    public String getFile_size() {
        return this.file_size;
    }
    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }
    
    public String getDes_title() {
		return des_title;
	}

	public void setDes_title(String des_title) {
		this.des_title = des_title;
	}

	public Integer getIs_publish() {
        return this.is_publish;
    }
    public void setIs_publish(Integer is_publish) {
        this.is_publish = is_publish;
    }
    public Date getUpdate_time() {
        return this.update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public String getUser_id() {
        return this.user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

	public Integer getIs_delete() {
		return is_delete;
	}

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
    
}
