package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class EquInfoResult extends BaseResult implements Serializable{

    private String id;   //  主键
    private String equno;   //  设备编号
    private String equname;   //  设备名称
    private String models;   //  型号
    private Integer equ_status;   //  设备状态(0未授权、1授权、2投产审核、3投产、4临时维修、维修后使用（删除）、5报废、6回收)
    private Integer isowed;   //  欠费停机状态，1:正常；0：欠费
    private String supp_id;   //  供应商编号
    private Integer oper_modal;   //  运营模式（1出售2租赁3合作分润）
    private String dispo_amount;   //  一次性取得设备费用
    private String prop_own;   //  产权归属
    private String use_own;   //  使用权归属
    private String equ_version;   //  使用版本
    private String cur_version;   //  当前app版本
    private Integer isallow;   //  是否允许设备连线
    private Integer isline;   //  是否在线
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date last_online;   //  最后登录或退出时间
    private String prod_batch;   //  生产批次
    private String sys_reg_no;   //  系统登记号
    private Integer line_days;   //  在线天数
    private String cur_addr_id;   //  设备当前地址
    private String produ_addr;   //  设备生产地址
    private Integer isvalid;   //  是否有效（1有效0无效）
    private Integer version;   //  版本，用于乐观锁
    private String booked_user;	//包机用户
    private Integer isactivation;//设备是否激活（0未激活，1已激活）
    private BigDecimal game_price;//游戏定价
    private Integer isentity;//设备是否是实体（0否，1是）
    
    private EquAuthResult equAuth;

    public EquInfoResult() {
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEquno() {
        return this.equno;
    }
    public void setEquno(String equno) {
        this.equno = equno;
    }
    public String getEquname() {
        return this.equname;
    }
    public void setEquname(String equname) {
        this.equname = equname;
    }
    public String getModels() {
        return this.models;
    }
    public void setModels(String models) {
        this.models = models;
    }
    public Integer getEqu_status() {
        return this.equ_status;
    }
    public void setEqu_status(Integer equ_status) {
        this.equ_status = equ_status;
    }
    public Integer getIsowed() {
        return this.isowed;
    }
    public void setIsowed(Integer isowed) {
        this.isowed = isowed;
    }
    public String getSupp_id() {
        return this.supp_id;
    }
    public void setSupp_id(String supp_id) {
        this.supp_id = supp_id;
    }
    public Integer getOper_modal() {
        return this.oper_modal;
    }
    public void setOper_modal(Integer oper_modal) {
        this.oper_modal = oper_modal;
    }
    public String getDispo_amount() {
        return this.dispo_amount;
    }
    public void setDispo_amount(String dispo_amount) {
        this.dispo_amount = dispo_amount;
    }
    public String getProp_own() {
        return this.prop_own;
    }
    public void setProp_own(String prop_own) {
        this.prop_own = prop_own;
    }
    public String getUse_own() {
        return this.use_own;
    }
    public void setUse_own(String use_own) {
        this.use_own = use_own;
    }
    public String getEqu_version() {
        return this.equ_version;
    }
    public void setEqu_version(String equ_version) {
        this.equ_version = equ_version;
    }
    public String getCur_version() {
        return this.cur_version;
    }
    public void setCur_version(String cur_version) {
        this.cur_version = cur_version;
    }
    public Integer getIsallow() {
        return this.isallow;
    }
    public void setIsallow(Integer isallow) {
        this.isallow = isallow;
    }
    public Integer getIsline() {
        return this.isline;
    }
    public void setIsline(Integer isline) {
        this.isline = isline;
    }
    public Date getLast_online() {
        return this.last_online;
    }
    public void setLast_online(Date last_online) {
        this.last_online = last_online;
    }
    public String getProd_batch() {
        return this.prod_batch;
    }
    public void setProd_batch(String prod_batch) {
        this.prod_batch = prod_batch;
    }
    public String getSys_reg_no() {
        return this.sys_reg_no;
    }
    public void setSys_reg_no(String sys_reg_no) {
        this.sys_reg_no = sys_reg_no;
    }
    public Integer getLine_days() {
        return this.line_days;
    }
    public void setLine_days(Integer line_days) {
        this.line_days = line_days;
    }
    public String getCur_addr_id() {
        return this.cur_addr_id;
    }
    public void setCur_addr_id(String cur_addr_id) {
        this.cur_addr_id = cur_addr_id;
    }
    public String getProdu_addr() {
        return this.produ_addr;
    }
    public void setProdu_addr(String produ_addr) {
        this.produ_addr = produ_addr;
    }
    public Integer getIsvalid() {
        return this.isvalid;
    }
    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
    public Integer getVersion() {
        return this.version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
	public String getBooked_user() {
		return booked_user;
	}
	public void setBooked_user(String booked_user) {
		this.booked_user = booked_user;
	}

	public EquAuthResult getEquAuth() {
		return equAuth;
	}

	public void setEquAuth(EquAuthResult equAuth) {
		this.equAuth = equAuth;
	}

	public Integer getIsactivation() {
		return isactivation;
	}

	public void setIsactivation(Integer isactivation) {
		this.isactivation = isactivation;
	}

	public BigDecimal getGame_price() {
		return game_price;
	}

	public void setGame_price(BigDecimal game_price) {
		this.game_price = game_price;
	}

	public Integer getIsentity() {
		return isentity;
	}

	public void setIsentity(Integer isentity) {
		this.isentity = isentity;
	}
    
}
