package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class MerchantWdcashResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String merno;   //  商户编号
    private String meraccount;   //  商户操作员账号
    private Integer mer_type;   //  商户类型(1俱乐部2代理商)
    private String me_rmobile;   //  商户手机号
    private String cash_nums;   //  申请提现金额
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date apply_time;	// 申请时间
    private String wd_memos;   //  备注
    private Integer wd_status;   //  提现状态(2打款中、1成功、0失败)
    private Integer pay_types;   //  支付方式(1微信支付2支付宝3银行卡)
    private String account_name;   //  账户名称
    private String openid; //微信openid(提现账户)
    private String acouunt_no;   //  账号
    private String bank_name;   //  开户行
    private String pay_order_no;   //  支付订单号(平台生成)
    private String fail_reseaon;   //  失败原因

    public MerchantWdcashResult() {
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
    public String getMeraccount() {
        return this.meraccount;
    }
    public void setMeraccount(String meraccount) {
        this.meraccount = meraccount;
    }
    public Integer getMer_type() {
        return this.mer_type;
    }
    public void setMer_type(Integer mer_type) {
        this.mer_type = mer_type;
    }
    public String getMe_rmobile() {
        return this.me_rmobile;
    }
    public void setMe_rmobile(String me_rmobile) {
        this.me_rmobile = me_rmobile;
    }
    public String getCash_nums() {
        return this.cash_nums;
    }
    public void setCash_nums(String cash_nums) {
        this.cash_nums = cash_nums;
    }
    public String getWd_memos() {
        return this.wd_memos;
    }
    public void setWd_memos(String wd_memos) {
        this.wd_memos = wd_memos;
    }
    public Integer getWd_status() {
        return this.wd_status;
    }
    public void setWd_status(Integer wd_status) {
        this.wd_status = wd_status;
    }
    public Integer getPay_types() {
        return this.pay_types;
    }
    public void setPay_types(Integer pay_types) {
        this.pay_types = pay_types;
    }
    public String getAccount_name() {
        return this.account_name;
    }
    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }
    public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAcouunt_no() {
        return this.acouunt_no;
    }
    public void setAcouunt_no(String acouunt_no) {
        this.acouunt_no = acouunt_no;
    }
    public String getBank_name() {
        return this.bank_name;
    }
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
    public String getPay_order_no() {
        return this.pay_order_no;
    }
    public void setPay_order_no(String pay_order_no) {
        this.pay_order_no = pay_order_no;
    }
    public String getFail_reseaon() {
        return this.fail_reseaon;
    }
    public void setFail_reseaon(String fail_reseaon) {
        this.fail_reseaon = fail_reseaon;
    }

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
    
}
