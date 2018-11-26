package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class AgentInfoResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String agno;   //  代理商
    private String pagno;   //  上级代理商编号
    private String agname;   //  代理商名称
    private String captain;   //  负责人
    private String card_id;   //  身份证
    private String mobile;   //  手机号
    private String telno;   //  固话
    private String email;   //  email
    private String qq;   //  QQ
    private String ag_addr;   //  地址
    private String lnglat;   //  经度
    private String latitude;   //  纬度
    private String resum;   //  账户余额
    private String incomes;   //  总收入（提现，提现后账户余额减少，必须>0)
    private String expends;   //  总支出（充值，充值后账户余额增加，利润=账户余额+总收入-总支出，利润可能为负）
    private String ag_password;   //  密码
    private String ag_level;   //  代理级别
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date add_time; //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;   //  修改时间
    private String isvalid;   //  是否有效(1有效0无效）
    private String racepht;   //  logo图片地址
    private String ag_type;   //  0代表普通 1代表企业 2 爱镖体育公司
    private String country;   //  国家
    private String province;   //  省
    private String city;   //  市
    private String areas;   //  区
    private BigDecimal companyscale;//公司分成比例
    private BigDecimal agentscale;//代理分成比例
    private Integer version;   //  版本，用于乐观锁

    public AgentInfoResult() {
    }

    
    public Date getAdd_time() {
		return add_time;
	}


	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}


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


	public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAgno() {
        return this.agno;
    }
    public void setAgno(String agno) {
        this.agno = agno;
    }
    public String getPagno() {
        return this.pagno;
    }
    public void setPagno(String pagno) {
        this.pagno = pagno;
    }
    public String getAgname() {
        return this.agname;
    }
    public void setAgname(String agname) {
        this.agname = agname;
    }
    public String getCaptain() {
        return this.captain;
    }
    public void setCaptain(String captain) {
        this.captain = captain;
    }
    public String getCard_id() {
        return this.card_id;
    }
    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getTelno() {
        return this.telno;
    }
    public void setTelno(String telno) {
        this.telno = telno;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getQq() {
        return this.qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getAg_addr() {
        return this.ag_addr;
    }
    public void setAg_addr(String ag_addr) {
        this.ag_addr = ag_addr;
    }
    public String getLnglat() {
        return this.lnglat;
    }
    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
    }
    public String getLatitude() {
        return this.latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getResum() {
        return this.resum;
    }
    public void setResum(String resum) {
        this.resum = resum;
    }
    public String getIncomes() {
        return this.incomes;
    }
    public void setIncomes(String incomes) {
        this.incomes = incomes;
    }
    public String getExpends() {
        return this.expends;
    }
    public void setExpends(String expends) {
        this.expends = expends;
    }
    public String getAg_password() {
        return this.ag_password;
    }
    public void setAg_password(String ag_password) {
        this.ag_password = ag_password;
    }
    public String getAg_level() {
        return this.ag_level;
    }
    public void setAg_level(String ag_level) {
        this.ag_level = ag_level;
    }
    public Date getUpdate_time() {
        return this.update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public String getIsvalid() {
        return this.isvalid;
    }
    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }
    public String getRacepht() {
        return this.racepht;
    }
    public void setRacepht(String racepht) {
        this.racepht = racepht;
    }
    public String getAg_type() {
        return this.ag_type;
    }
    public void setAg_type(String ag_type) {
        this.ag_type = ag_type;
    }
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getProvince() {
        return this.province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAreas() {
        return this.areas;
    }
    public void setAreas(String areas) {
        this.areas = areas;
    }
    public Integer getVersion() {
        return this.version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
}
