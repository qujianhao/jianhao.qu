package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_equ_info")
public class EquInfo extends BaseEntity {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 设备编号
     */
    private String equno;

    /**
     * 设备名称
     */
    private String equname;

    /**
     * 型号
     */
    private String models;

    /**
     * 设备状态(0未授权、1授权、2投产审核、3投产、4临时维修、维修后使用（删除）、5报废、6回收)
     */
    private Integer equ_status;

    /**
     * 欠费停机状态，1:正常；0：欠费
     */
    private Integer isowed;

    /**
     * 供应商编号
     */
    private String supp_id;

    /**
     * 运营模式（1出售2租赁3合作分润）
     */
    private Integer oper_modal;

    /**
     * 一次性取得设备费用
     */
    private BigDecimal dispo_amount;

    /**
     * 状态变更时间
     */
    private Date modi_time;

    /**
     * 产权归属
     */
    private String prop_own;

    /**
     * 使用权归属
     */
    private String use_own;

    /**
     * 入库时间
     */
    private Date input_time;

    /**
     * 投产日期
     */
    private Date use_time;

    /**
     * 使用到期时间
     */
    private Date due_time;

    /**
     * 使用版本
     */
    private String equ_version;

    /**
     * 当前app版本
     */
    private String cur_version;

    /**
     * 是否允许设备连线
     */
    private Integer isallow;

    /**
     * 是否在线
     */
    private Integer isline;
    
    /**
     * 最后登录或退出时间
     */
    private Date last_online;

    /**
     * 生产批次
     */
    private String prod_batch;

    /**
     * 系统登记号
     */
    private String sys_reg_no;

    /**
     * 在线天数
     */
    private Integer line_days;

    /**
     * 总在线时长
     */
    private Integer online_times;

    /**
     * 设备当前地址
     */
    private String cur_addr_id;

    /**
     * 设备生产地址
     */
    private String produ_addr;

    /**
     * 是否有效（1有效0无效）
     */
    private Integer isvalid;

    /**
     * 创建时间
     */
    private Date add_time;

    /**
     * 版本，用于乐观锁
     */
    private Integer version;
    
    /**
     * 包机用户
     */
    private String booked_user;
    
    /**
     * 设备是否激活(0未激活，1已激活)
     */
    private Integer isactivation;
    
    /**
     * 设备游戏定价
     */
    private BigDecimal game_price;
    
    /**
     * 是否是实体，1是，2否
     */
    private Integer isentity;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取设备编号
     *
     * @return equno - 设备编号
     */
    public String getEquno() {
        return equno;
    }

    /**
     * 设置设备编号
     *
     * @param equno 设备编号
     */
    public void setEquno(String equno) {
        this.equno = equno;
    }

    /**
     * 获取设备名称
     *
     * @return equname - 设备名称
     */
    public String getEquname() {
        return equname;
    }

    /**
     * 设置设备名称
     *
     * @param equname 设备名称
     */
    public void setEquname(String equname) {
        this.equname = equname;
    }

    /**
     * 获取型号
     *
     * @return models - 型号
     */
    public String getModels() {
        return models;
    }

    /**
     * 设置型号
     *
     * @param models 型号
     */
    public void setModels(String models) {
        this.models = models;
    }

    /**
     * 获取设备状态(0未授权、1授权、2投产审核、3投产、4临时维修、维修后使用（删除）、5报废、6回收)
     *
     * @return equ_status - 设备状态(0未授权、1授权、2投产审核、3投产、4临时维修、维修后使用（删除）、5报废、6回收)
     */
    public Integer getEqu_status() {
        return equ_status;
    }

    /**
     * 设置设备状态(0未授权、1授权、2投产审核、3投产、4临时维修、维修后使用（删除）、5报废、6回收)
     *
     * @param equ_status 设备状态(0未授权、1授权、2投产审核、3投产、4临时维修、维修后使用（删除）、5报废、6回收)
     */
    public void setEqu_status(Integer equ_status) {
        this.equ_status = equ_status;
    }

    /**
     * 获取欠费停机状态，1:正常；0：欠费
     *
     * @return isowed - 欠费停机状态，1:正常；0：欠费
     */
    public Integer getIsowed() {
        return isowed;
    }

    /**
     * 设置欠费停机状态，1:正常；0：欠费
     *
     * @param isowed 欠费停机状态，1:正常；0：欠费
     */
    public void setIsowed(Integer isowed) {
        this.isowed = isowed;
    }

    /**
     * 获取供应商编号
     *
     * @return supp_id - 供应商编号
     */
    public String getSupp_id() {
        return supp_id;
    }

    /**
     * 设置供应商编号
     *
     * @param supp_id 供应商编号
     */
    public void setSupp_id(String supp_id) {
        this.supp_id = supp_id;
    }

    /**
     * 获取运营模式（1出售2租赁3合作分润）
     *
     * @return oper_modal - 运营模式（1出售2租赁3合作分润）
     */
    public Integer getOper_modal() {
        return oper_modal;
    }

    /**
     * 设置运营模式（1出售2租赁3合作分润）
     *
     * @param oper_modal 运营模式（1出售2租赁3合作分润）
     */
    public void setOper_modal(Integer oper_modal) {
        this.oper_modal = oper_modal;
    }

    /**
     * 获取一次性取得设备费用
     *
     * @return dispo_amount - 一次性取得设备费用
     */
    public BigDecimal getDispo_amount() {
        return dispo_amount;
    }

    /**
     * 设置一次性取得设备费用
     *
     * @param dispo_amount 一次性取得设备费用
     */
    public void setDispo_amount(BigDecimal dispo_amount) {
        this.dispo_amount = dispo_amount;
    }

    /**
     * 获取状态变更时间
     *
     * @return modi_time - 状态变更时间
     */
    public Date getModi_time() {
        return modi_time;
    }

    /**
     * 设置状态变更时间
     *
     * @param modi_time 状态变更时间
     */
    public void setModi_time(Date modi_time) {
        this.modi_time = modi_time;
    }

    /**
     * 获取产权归属
     *
     * @return prop_own - 产权归属
     */
    public String getProp_own() {
        return prop_own;
    }

    /**
     * 设置产权归属
     *
     * @param prop_own 产权归属
     */
    public void setProp_own(String prop_own) {
        this.prop_own = prop_own;
    }

    /**
     * 获取使用权归属
     *
     * @return use_own - 使用权归属
     */
    public String getUse_own() {
        return use_own;
    }

    /**
     * 设置使用权归属
     *
     * @param use_own 使用权归属
     */
    public void setUse_own(String use_own) {
        this.use_own = use_own;
    }

    /**
     * 获取入库时间
     *
     * @return input_time - 入库时间
     */
    public Date getInput_time() {
        return input_time;
    }

    /**
     * 设置入库时间
     *
     * @param input_time 入库时间
     */
    public void setInput_time(Date input_time) {
        this.input_time = input_time;
    }

    /**
     * 获取投产日期
     *
     * @return use_time - 投产日期
     */
    public Date getUse_time() {
        return use_time;
    }

    /**
     * 设置投产日期
     *
     * @param use_time 投产日期
     */
    public void setUse_time(Date use_time) {
        this.use_time = use_time;
    }

    /**
     * 获取使用到期时间
     *
     * @return due_time - 使用到期时间
     */
    public Date getDue_time() {
        return due_time;
    }

    /**
     * 设置使用到期时间
     *
     * @param due_time 使用到期时间
     */
    public void setDue_time(Date due_time) {
        this.due_time = due_time;
    }

    /**
     * 获取使用版本
     *
     * @return equ_version - 使用版本
     */
    public String getEqu_version() {
        return equ_version;
    }

    /**
     * 设置使用版本
     *
     * @param equ_version 使用版本
     */
    public void setEqu_version(String equ_version) {
        this.equ_version = equ_version;
    }

    /**
     * 获取当前app版本
     *
     * @return cur_version - 当前app版本
     */
    public String getCur_version() {
        return cur_version;
    }

    /**
     * 设置当前app版本
     *
     * @param cur_version 当前app版本
     */
    public void setCur_version(String cur_version) {
        this.cur_version = cur_version;
    }

    /**
     * 获取是否允许设备连线
     *
     * @return isallow - 是否允许设备连线
     */
    public Integer getIsallow() {
        return isallow;
    }

    /**
     * 设置是否允许设备连线
     *
     * @param isallow 是否允许设备连线
     */
    public void setIsallow(Integer isallow) {
        this.isallow = isallow;
    }

    /**
     * 获取是否在线
     *
     * @return isline - 是否在线
     */
    public Integer getIsline() {
        return isline;
    }

    /**
     * 设置是否在线
     *
     * @param isline 是否在线
     */
    public void setIsline(Integer isline) {
        this.isline = isline;
    }

    /**
     * 获取生产批次
     *
     * @return prod_batch - 生产批次
     */
    public String getProd_batch() {
        return prod_batch;
    }

    /**
     * 设置生产批次
     *
     * @param prod_batch 生产批次
     */
    public void setProd_batch(String prod_batch) {
        this.prod_batch = prod_batch;
    }

    /**
     * 获取系统登记号
     *
     * @return sys_reg_no - 系统登记号
     */
    public String getSys_reg_no() {
        return sys_reg_no;
    }

    /**
     * 设置系统登记号
     *
     * @param sys_reg_no 系统登记号
     */
    public void setSys_reg_no(String sys_reg_no) {
        this.sys_reg_no = sys_reg_no;
    }

    /**
     * 获取在线天数
     *
     * @return line_days - 在线天数
     */
    public Integer getLine_days() {
        return line_days;
    }

    /**
     * 设置在线天数
     *
     * @param line_days 在线天数
     */
    public void setLine_days(Integer line_days) {
        this.line_days = line_days;
    }

    /**
     * 获取总在线时长
     *
     * @return online_times - 总在线时长
     */
    public Integer getOnline_times() {
        return online_times;
    }

    /**
     * 设置总在线时长
     *
     * @param online_times 总在线时长
     */
    public void setOnline_times(Integer online_times) {
        this.online_times = online_times;
    }

    /**
     * 获取设备当前地址
     *
     * @return cur_addr_id - 设备当前地址
     */
    public String getCur_addr_id() {
        return cur_addr_id;
    }

    /**
     * 设置设备当前地址
     *
     * @param cur_addr_id 设备当前地址
     */
    public void setCur_addr_id(String cur_addr_id) {
        this.cur_addr_id = cur_addr_id;
    }

    /**
     * 获取设备生产地址
     *
     * @return produ_addr - 设备生产地址
     */
    public String getProdu_addr() {
        return produ_addr;
    }

    /**
     * 设置设备生产地址
     *
     * @param produ_addr 设备生产地址
     */
    public void setProdu_addr(String produ_addr) {
        this.produ_addr = produ_addr;
    }

    /**
     * 获取是否有效（1有效0无效）
     *
     * @return isvalid - 是否有效（1有效0无效）
     */
    public Integer getIsvalid() {
        return isvalid;
    }

    /**
     * 设置是否有效（1有效0无效）
     *
     * @param isvalid 是否有效（1有效0无效）
     */
    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
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
     * 获取版本，用于乐观锁
     *
     * @return version - 版本，用于乐观锁
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本，用于乐观锁
     *
     * @param version 版本，用于乐观锁
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

	public Date getLast_online() {
		return last_online;
	}

	public void setLast_online(Date last_online) {
		this.last_online = last_online;
	}

	public String getBooked_user() {
		return booked_user;
	}

	public void setBooked_user(String booked_user) {
		this.booked_user = booked_user;
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