package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class MerchantAccountResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String merno;   //  商户编号,对应club,agent里的编号
    private String password;//密码
    private String mertype;   //  商户类型(1俱乐部2代理商)
    private String accountnames;   //  账户名称
    private String openid;//微信openid
    private String acouuntno;   //  账号
    private String bankname;   //  开户行
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;   //  创建时间

    public MerchantAccountResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMerno() {
        return this.merno;
    }
    public void setMerno(String merno) {
        this.merno = merno;
    }
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMertype() {
        return this.mertype;
    }
    public void setMertype(String mertype) {
        this.mertype = mertype;
    }
    public String getAccountnames() {
        return this.accountnames;
    }
    public void setAccountnames(String accountnames) {
        this.accountnames = accountnames;
    }
    
    public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAcouuntno() {
        return this.acouuntno;
    }
    public void setAcouuntno(String acouuntno) {
        this.acouuntno = acouuntno;
    }
    public String getBankname() {
        return this.bankname;
    }
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
    public Date getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
