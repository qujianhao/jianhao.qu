package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_club_info")
public class ClubInfo extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 俱乐部编号
     */
    private String cno;

    /**
     * 俱乐部名称
     */
    private String cname;

    /**
     * 所属代理商
     */
    private String agno;

    /**
     * 俱乐部类型 1cdl 2cudl
     */
    private Boolean ctype;

    /**
     * 负责人
     */
    private String captain;

    /**
     * 负责人身份证
     */
    private String cardid;

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
     * 俱乐部积分
     */
    private Integer clubintr;

    /**
     * 密码
     */
    private String c_password;

    /**
     * 激活状态0未激活，1已激活
     */
    private Boolean activate_flag;

    /**
     * 评论数
     */
    private Integer commnets;

    /**
     * 创建时间
     */
    private Date add_time;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 用户状态(1:有效;0:无效）
     */
    private Integer isvalid;

    /**
     * 海报图片
     */
    private String logo;

    /**
     * 版本，用于乐观锁
     */
    private Integer version;
    
    /**
     * 管理员游戏定价
     */
    private BigDecimal manage_game_price;
    
    /**
     * 管理员设置的定价起始时间
     */
    private Date price_start_time;
    
    /**
     * 管理员设置的定价结束时间
     */
    private Date price_end_time;

    /**
     * 描述信息
     */
    private String describe_message;
    
    /**
     * 公司分成比例
     */
    private BigDecimal companyscale;
    
    /**
     * 俱乐部分成比例
     */
    private BigDecimal clubscale;
    
    private Integer acac;
    
    
    
	public Integer getAcac() {
		return acac;
	}

	public void setAcac(Integer acac) {
		this.acac = acac;
	}

	public BigDecimal getCompanyscale() {
		return companyscale;
	}

	public void setCompanyscale(BigDecimal companyscale) {
		this.companyscale = companyscale;
	}

	public BigDecimal getClubscale() {
		return clubscale;
	}

	public void setClubscale(BigDecimal clubscale) {
		this.clubscale = clubscale;
	}

	public BigDecimal getManage_game_price() {
		return manage_game_price;
	}

	public void setManage_game_price(BigDecimal manage_game_price) {
		this.manage_game_price = manage_game_price;
	}

	public Date getPrice_start_time() {
		return price_start_time;
	}

	public void setPrice_start_time(Date price_start_time) {
		this.price_start_time = price_start_time;
	}

	public Date getPrice_end_time() {
		return price_end_time;
	}

	public void setPrice_end_time(Date price_end_time) {
		this.price_end_time = price_end_time;
	}

	public String getDescribe_message() {
		return describe_message;
	}

	public void setDescribe_message(String describe_message) {
		this.describe_message = describe_message;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
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
     * 获取俱乐部编号
     *
     * @return cno - 俱乐部编号
     */
    public String getCno() {
        return cno;
    }

    /**
     * 设置俱乐部编号
     *
     * @param cno 俱乐部编号
     */
    public void setCno(String cno) {
        this.cno = cno;
    }

    /**
     * 获取俱乐部名称
     *
     * @return cname - 俱乐部名称
     */
    public String getCname() {
        return cname;
    }

    /**
     * 设置俱乐部名称
     *
     * @param cname 俱乐部名称
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * 获取所属代理商
     *
     * @return agno - 所属代理商
     */
    public String getAgno() {
        return agno;
    }

    /**
     * 设置所属代理商
     *
     * @param agno 所属代理商
     */
    public void setAgno(String agno) {
        this.agno = agno;
    }

    /**
     * 获取俱乐部类型 1cdl 2cudl
     *
     * @return ctype - 俱乐部类型 1cdl 2cudl
     */
    public Boolean getCtype() {
        return ctype;
    }

    /**
     * 设置俱乐部类型 1cdl 2cudl
     *
     * @param ctype 俱乐部类型 1cdl 2cudl
     */
    public void setCtype(Boolean ctype) {
        this.ctype = ctype;
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
     * 获取俱乐部积分
     *
     * @return clubintr - 俱乐部积分
     */
    public Integer getClubintr() {
        return clubintr;
    }

    /**
     * 设置俱乐部积分
     *
     * @param clubintr 俱乐部积分
     */
    public void setClubintr(Integer clubintr) {
        this.clubintr = clubintr;
    }

    /**
     * 获取密码
     *
     * @return c_password - 密码
     */
    public String getC_password() {
        return c_password;
    }

    /**
     * 设置密码
     *
     * @param c_password 密码
     */
    public void setC_password(String c_password) {
        this.c_password = c_password;
    }

    /**
     * 获取激活状态0未激活，1已激活
     *
     * @return activate_flag - 激活状态0未激活，1已激活
     */
    public Boolean getActivate_flag() {
        return activate_flag;
    }

    /**
     * 设置激活状态0未激活，1已激活
     *
     * @param activate_flag 激活状态0未激活，1已激活
     */
    public void setActivate_flag(Boolean activate_flag) {
        this.activate_flag = activate_flag;
    }

    /**
     * 获取评论数
     *
     * @return commnets - 评论数
     */
    public Integer getCommnets() {
        return commnets;
    }

    /**
     * 设置评论数
     *
     * @param commnets 评论数
     */
    public void setCommnets(Integer commnets) {
        this.commnets = commnets;
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
     * 获取用户状态(1:有效;0:无效）
     *
     * @return isvalid - 用户状态(1:有效;0:无效）
     */
    public Integer getIsvalid() {
        return isvalid;
    }

    /**
     * 设置用户状态(1:有效;0:无效）
     *
     * @param isvalid 用户状态(1:有效;0:无效）
     */
    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    /**
     * 获取海报图片
     *
     * @return logo - 海报图片
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 设置海报图片
     *
     * @param logo 海报图片
     */
    public void setLogo(String logo) {
        this.logo = logo;
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