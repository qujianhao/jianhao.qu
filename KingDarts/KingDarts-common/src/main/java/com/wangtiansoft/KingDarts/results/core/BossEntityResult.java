package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class BossEntityResult extends BaseResult implements Serializable{

    private String uuid;   //  
    private Integer boss_id;   //  boss信息id
    private Integer evolume;   //  当前血量
    private Integer kill_status;   //  状态： 0 待击杀 1 已击杀 
    private String kill_user_id;   //  最后击杀用户uuid
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;   //  更新时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date kill_time;  
    
    private String bno;   //  boss编号
    private String bname;   //  boss名称
    private Integer bvolume;   //  boss血量
    
    public BossEntityResult() {
    }

    public String getUuid() {
        return this.uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public Integer getBoss_id() {
        return this.boss_id;
    }
    public void setBoss_id(Integer boss_id) {
        this.boss_id = boss_id;
    }
    public Integer getEvolume() {
        return this.evolume;
    }
    public void setEvolume(Integer evolume) {
        this.evolume = evolume;
    }
    public Integer getKill_status() {
        return this.kill_status;
    }
    public void setKill_status(Integer kill_status) {
        this.kill_status = kill_status;
    }
    public String getKill_user_id() {
        return this.kill_user_id;
    }
    public void setKill_user_id(String kill_user_id) {
        this.kill_user_id = kill_user_id;
    }
    public Date getUpdate_time() {
        return this.update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
	public Date getKill_time() {
		return kill_time;
	}
	public void setKill_time(Date kill_time) {
		this.kill_time = kill_time;
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
}
