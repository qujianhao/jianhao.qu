package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_agent_info")
public class AgentInfo extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 代理商
     */
    private String agno;

    /**
     * 上级代理商编号
     */
    private String pagno;

    /**
     * 代理商名称
     */
    private String agname;

    /**
     * 负责人
     */
    private String captain;

    /**
     * 身份证
     */
    private String card_id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 固话
     */
    private String telno;

    /**
     * email
     */
    private String email;

    /**
     * QQ
     */
    private String qq;

    /**
     * 地址
     */
    private String ag_addr;

    /**
     * 经度
     */
    private BigDecimal lnglat;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 账户余额
     */
    private BigDecimal resum;

    /**
     * 总收入（提现，提现后账户余额减少，必须>0)
     */
    private BigDecimal incomes;

    /**
     * 总支出（充值，充值后账户余额增加，利润=账户余额+总收入-总支出，利润可能为负）
     */
    private BigDecimal expends;

    /**
     * 密码
     */
    private String ag_password;

    /**
     * 代理级别
     */
    private Boolean ag_level;

    /**
     * 创建时间
     */
    private Date add_time;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 是否有效(1有效0无效）
     */
    private Integer isvalid;

    /**
     * logo图片地址
     */
    private String racepht;

    /**
     * 0代表普通 1代表企业 2 爱镖体育公司
     */
    private String ag_type;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String areas;

    /**
     * 公司分成比例
     */
    private BigDecimal companyscale;

    /**
     * 代理分成比例
     */
    private BigDecimal agentscale;

    /**
     * 版本，用于乐观锁
     */
    private Integer version;

    public BigDecimal getCompanyscale() {
		return companyscale;
	}

	public void setCompanyscale(BigDecimal companyscale) {
		this.companyscale = companyscale;
	}

	public BigDecimal getAgentscale() {
		return agentscale;
	}

	public void setAgentscale(BigDecimal agentscale) {
		this.agentscale = agentscale;
	}

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
     * 获取代理商
     *
     * @return agno - 代理商
     */
    public String getAgno() {
        return agno;
    }

    /**
     * 设置代理商
     *
     * @param agno 代理商
     */
    public void setAgno(String agno) {
        this.agno = agno;
    }

    /**
     * 获取上级代理商编号
     *
     * @return pagno - 上级代理商编号
     */
    public String getPagno() {
        return pagno;
    }

    /**
     * 设置上级代理商编号
     *
     * @param pagno 上级代理商编号
     */
    public void setPagno(String pagno) {
        this.pagno = pagno;
    }

    /**
     * 获取代理商名称
     *
     * @return agname - 代理商名称
     */
    public String getAgname() {
        return agname;
    }

    /**
     * 设置代理商名称
     *
     * @param agname 代理商名称
     */
    public void setAgname(String agname) {
        this.agname = agname;
    }

    /**
     * 获取负责人
     *
     * @return captain - 负责人
     */
    public String getCaptain() {
        return captain;
    }

    /**
     * 设置负责人
     *
     * @param captain 负责人
     */
    public void setCaptain(String captain) {
        this.captain = captain;
    }

    /**
     * 获取身份证
     *
     * @return card_id - 身份证
     */
    public String getCard_id() {
        return card_id;
    }

    /**
     * 设置身份证
     *
     * @param card_id 身份证
     */
    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取固话
     *
     * @return telno - 固话
     */
    public String getTelno() {
        return telno;
    }

    /**
     * 设置固话
     *
     * @param telno 固话
     */
    public void setTelno(String telno) {
        this.telno = telno;
    }

    /**
     * 获取email
     *
     * @return email - email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置email
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取QQ
     *
     * @return qq - QQ
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ
     *
     * @param qq QQ
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取地址
     *
     * @return ag_addr - 地址
     */
    public String getAg_addr() {
        return ag_addr;
    }

    /**
     * 设置地址
     *
     * @param ag_addr 地址
     */
    public void setAg_addr(String ag_addr) {
        this.ag_addr = ag_addr;
    }

    /**
     * 获取经度
     *
     * @return lnglat - 经度
     */
    public BigDecimal getLnglat() {
        return lnglat;
    }

    /**
     * 设置经度
     *
     * @param lnglat 经度
     */
    public void setLnglat(BigDecimal lnglat) {
        this.lnglat = lnglat;
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取账户余额
     *
     * @return resum - 账户余额
     */
    public BigDecimal getResum() {
        return resum;
    }

    /**
     * 设置账户余额
     *
     * @param resum 账户余额
     */
    public void setResum(BigDecimal resum) {
        this.resum = resum;
    }

    /**
     * 获取总收入（提现，提现后账户余额减少，必须>0)
     *
     * @return incomes - 总收入（提现，提现后账户余额减少，必须>0)
     */
    public BigDecimal getIncomes() {
        return incomes;
    }

    /**
     * 设置总收入（提现，提现后账户余额减少，必须>0)
     *
     * @param incomes 总收入（提现，提现后账户余额减少，必须>0)
     */
    public void setIncomes(BigDecimal incomes) {
        this.incomes = incomes;
    }

    /**
     * 获取总支出（充值，充值后账户余额增加，利润=账户余额+总收入-总支出，利润可能为负）
     *
     * @return expends - 总支出（充值，充值后账户余额增加，利润=账户余额+总收入-总支出，利润可能为负）
     */
    public BigDecimal getExpends() {
        return expends;
    }

    /**
     * 设置总支出（充值，充值后账户余额增加，利润=账户余额+总收入-总支出，利润可能为负）
     *
     * @param expends 总支出（充值，充值后账户余额增加，利润=账户余额+总收入-总支出，利润可能为负）
     */
    public void setExpends(BigDecimal expends) {
        this.expends = expends;
    }

    /**
     * 获取密码
     *
     * @return ag_password - 密码
     */
    public String getAg_password() {
        return ag_password;
    }

    /**
     * 设置密码
     *
     * @param ag_password 密码
     */
    public void setAg_password(String ag_password) {
        this.ag_password = ag_password;
    }

    /**
     * 获取代理级别
     *
     * @return ag_level - 代理级别
     */
    public Boolean getAg_level() {
        return ag_level;
    }

    /**
     * 设置代理级别
     *
     * @param ag_level 代理级别
     */
    public void setAg_level(Boolean ag_level) {
        this.ag_level = ag_level;
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
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 设置修改时间
     *
     * @param update_time 修改时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    /**
     * 获取是否有效(1有效0无效）
     *
     * @return isvalid - 是否有效(1有效0无效）
     */
    public Integer getIsvalid() {
        return isvalid;
    }

    /**
     * 设置是否有效(1有效0无效）
     *
     * @param isvalid 是否有效(1有效0无效）
     */
    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    /**
     * 获取logo图片地址
     *
     * @return racepht - logo图片地址
     */
    public String getRacepht() {
        return racepht;
    }

    /**
     * 设置logo图片地址
     *
     * @param racepht logo图片地址
     */
    public void setRacepht(String racepht) {
        this.racepht = racepht;
    }

    /**
     * 获取0代表普通 1代表企业 2 爱镖体育公司
     *
     * @return ag_type - 0代表普通 1代表企业 2 爱镖体育公司
     */
    public String getAg_type() {
        return ag_type;
    }

    /**
     * 设置0代表普通 1代表企业 2 爱镖体育公司
     *
     * @param ag_type 0代表普通 1代表企业 2 爱镖体育公司
     */
    public void setAg_type(String ag_type) {
        this.ag_type = ag_type;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return areas - 区
     */
    public String getAreas() {
        return areas;
    }

    /**
     * 设置区
     *
     * @param areas 区
     */
    public void setAreas(String areas) {
        this.areas = areas;
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
}