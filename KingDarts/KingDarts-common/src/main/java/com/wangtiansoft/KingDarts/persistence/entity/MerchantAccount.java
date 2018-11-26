package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_merchant_account")
public class MerchantAccount extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商户编号,对应club,agent里的编号
     */
    private String merno;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 商户类型(1俱乐部2代理商)
     */
    private Integer mertype;

    /**
     * 账户名称
     */
    private String accountnames;
    
    /**
     * 微信openid
     */
    private String openid;

    /**
     * 账号
     */
    private String acouuntno;

    /**
     * 开户行
     */
    private String bankname;

    /**
     * 创建时间
     */
    private Date createtime;
    
    /**
     * 是否删除
     */
    private Integer isdelete;
    
    

    public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
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
     * 获取商户编号,对应club,agent里的编号
     *
     * @return merno - 商户编号,对应club,agent里的编号
     */
    public String getMerno() {
        return merno;
    }

    /**
     * 设置商户编号,对应club,agent里的编号
     *
     * @param merno 商户编号,对应club,agent里的编号
     */
    public void setMerno(String merno) {
        this.merno = merno;
    }
    
    

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
     * 获取商户类型(1俱乐部2代理商)
     *
     * @return mertype - 商户类型(1俱乐部2代理商)
     */
    public Integer getMertype() {
        return mertype;
    }

    /**
     * 设置商户类型(1俱乐部2代理商)
     *
     * @param mertype 商户类型(1俱乐部2代理商)
     */
    public void setMertype(Integer mertype) {
        this.mertype = mertype;
    }

    /**
     * 获取账户名称
     *
     * @return accountnames - 账户名称
     */
    public String getAccountnames() {
        return accountnames;
    }

    /**
     * 设置账户名称
     *
     * @param accountnames 账户名称
     */
    public void setAccountnames(String accountnames) {
        this.accountnames = accountnames;
    }
    

    public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
     * 获取账号
     *
     * @return acouuntno - 账号
     */
    public String getAcouuntno() {
        return acouuntno;
    }

    /**
     * 设置账号
     *
     * @param acouuntno 账号
     */
    public void setAcouuntno(String acouuntno) {
        this.acouuntno = acouuntno;
    }

    /**
     * 获取开户行
     *
     * @return bankname - 开户行
     */
    public String getBankname() {
        return bankname;
    }

    /**
     * 设置开户行
     *
     * @param bankname 开户行
     */
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}