package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wt-templete-helper on unknow.
 */
public class ClubInfoResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String cno;   //  俱乐部编号
    private String cname;   //  俱乐部名称
    private String agno;   //  所属代理商
    private String ctype;   //  俱乐部类型 1cdl 2cudl
    private String captain;   //  负责人
    private String cardid;   //  负责人身份证
    private String mobile;   //  手机号
    private String telno;   //  固话
    private String email;   //  email
    private String qq;   //  QQ
    private String resum;   //  账户余额
    private String incomes;   //  总收入（提现，提现后账户余额减少，必须>0)
    private String expends;   //  总支出（充值，充值后账户余额增加，利润=账户余额+总收入-总支出，利润可能为负）
    private Integer clubintr;   //  俱乐部积分
    private String c_password;   //  密码
    private String activate_flag;   //  激活状态0未激活，1已激活
    private Integer commnets;   //  评论数
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date add_time;   //  创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;   //  修改时间
    private Integer isvalid;   //  用户状态(1:有效;0:无效）
    private String logo;   //  海报图片
    private Integer version;   //  版本，用于乐观锁
    private String describe_message;//描述信息
    private BigDecimal manage_game_price;//管理员游戏定价
    private Date price_start_time;//管理员设置的定价起始时间
    private Date price_end_time;//管理员设置的定价结束时间
    private BigDecimal companyscale;//公司分成比例
    private BigDecimal clubscale;//俱乐部分成比例
    private Integer acac;
    
    private List<ClubPlaceResult> clubPlaceResultList;//营业场所
    
    private List<EquAuthResult> equAuthResultList;//设备授权
    
    private File logofile;//logo文件
    

    
	public File getLogofile() {
		return logofile;
	}



	public void setLogofile(File logofile) {
		this.logofile = logofile;
	}



	public List<ClubPlaceResult> getClubPlaceResultList() {
		return clubPlaceResultList;
	}



	public void setClubPlaceResultList(List<ClubPlaceResult> clubPlaceResultList) {
		this.clubPlaceResultList = clubPlaceResultList;
	}

	
	public List<EquAuthResult> getEquAuthResultList() {
		return equAuthResultList;
	}



	public void setEquAuthResultList(List<EquAuthResult> equAuthResultList) {
		this.equAuthResultList = equAuthResultList;
	}



	public ClubInfoResult() {
    }

    
    
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


	public Date getAdd_time() {
		return add_time;
	}


	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
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


	public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCno() {
        return this.cno;
    }
    public void setCno(String cno) {
        this.cno = cno;
    }
    public String getCname() {
        return this.cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public String getAgno() {
        return this.agno;
    }
    public void setAgno(String agno) {
        this.agno = agno;
    }
    public String getCtype() {
        return this.ctype;
    }
    public void setCtype(String ctype) {
        this.ctype = ctype;
    }
    public String getCaptain() {
        return this.captain;
    }
    public void setCaptain(String captain) {
        this.captain = captain;
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
    public Integer getClubintr() {
        return this.clubintr;
    }
    public void setClubintr(Integer clubintr) {
        this.clubintr = clubintr;
    }
    public String getC_password() {
        return this.c_password;
    }
    public void setC_password(String c_password) {
        this.c_password = c_password;
    }
    public String getActivate_flag() {
        return this.activate_flag;
    }
    public void setActivate_flag(String activate_flag) {
        this.activate_flag = activate_flag;
    }
    public Integer getCommnets() {
        return this.commnets;
    }
    public void setCommnets(Integer commnets) {
        this.commnets = commnets;
    }
    public Date getUpdate_time() {
        return this.update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public Integer getIsvalid() {
        return this.isvalid;
    }
    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
    public String getLogo() {
        return this.logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public Integer getVersion() {
        return this.version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
}
