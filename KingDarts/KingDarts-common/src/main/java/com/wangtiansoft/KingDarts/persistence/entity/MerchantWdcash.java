package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_merchant_wdcash")
public class MerchantWdcash extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商户编号
     */
    private String merno;

    /**
     * 商户操作员账号
     */
    private String meraccount;

    /**
     * 商户类型(1俱乐部2代理商)
     */
    private Integer mer_type;

    /**
     * 商户手机号
     */
    private String me_rmobile;

    /**
     * 申请提现金额
     */
    private BigDecimal cash_nums;

    /**
     * 申请时间
     */
    private Date apply_time;

    /**
     * 备注
     */
    private String wd_memos;

    /**
     * 提现状态(2打款中、1成功、0失败)
     */
    private Integer wd_status;

    /**
     * 支付方式(1微信支付2支付宝3银行卡4微信转账)
     */
    private Integer pay_types;

    /**
     * 付款成功时间
     */
    private Date paysuces_time;

    /**
     * 账户名称
     */
    private String account_name;
    
    /**
     * 微信openid（提现账户）
     */
    private String openid;

    /**
     * 账号
     */
    private String acouunt_no;

    /**
     * 开户行
     */
    private String bank_name;

    /**
     * 支付订单号(平台生成)
     */
    private String pay_order_no;

    /**
     * 失败原因
     */
    private String fail_reseaon;

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
     * 获取商户编号
     *
     * @return merno - 商户编号
     */
    public String getMerno() {
        return merno;
    }

    /**
     * 设置商户编号
     *
     * @param merno 商户编号
     */
    public void setMerno(String merno) {
        this.merno = merno;
    }

    /**
     * 获取商户操作员账号
     *
     * @return meraccount - 商户操作员账号
     */
    public String getMeraccount() {
        return meraccount;
    }

    /**
     * 设置商户操作员账号
     *
     * @param meraccount 商户操作员账号
     */
    public void setMeraccount(String meraccount) {
        this.meraccount = meraccount;
    }

    /**
     * 获取商户类型(1俱乐部2代理商)
     *
     * @return mer_type - 商户类型(1俱乐部2代理商)
     */
    public Integer getMer_type() {
        return mer_type;
    }

    /**
     * 设置商户类型(1俱乐部2代理商)
     *
     * @param mer_type 商户类型(1俱乐部2代理商)
     */
    public void setMer_type(Integer mer_type) {
        this.mer_type = mer_type;
    }

    /**
     * 获取商户手机号
     *
     * @return me_rmobile - 商户手机号
     */
    public String getMe_rmobile() {
        return me_rmobile;
    }

    /**
     * 设置商户手机号
     *
     * @param me_rmobile 商户手机号
     */
    public void setMe_rmobile(String me_rmobile) {
        this.me_rmobile = me_rmobile;
    }

    /**
     * 获取申请提现金额
     *
     * @return cash_nums - 申请提现金额
     */
    public BigDecimal getCash_nums() {
        return cash_nums;
    }

    /**
     * 设置申请提现金额
     *
     * @param cash_nums 申请提现金额
     */
    public void setCash_nums(BigDecimal cash_nums) {
        this.cash_nums = cash_nums;
    }

    /**
     * 获取申请时间
     *
     * @return apply_time - 申请时间
     */
    public Date getApply_time() {
        return apply_time;
    }

    /**
     * 设置申请时间
     *
     * @param apply_time 申请时间
     */
    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    /**
     * 获取备注
     *
     * @return wd_memos - 备注
     */
    public String getWd_memos() {
        return wd_memos;
    }

    /**
     * 设置备注
     *
     * @param wd_memos 备注
     */
    public void setWd_memos(String wd_memos) {
        this.wd_memos = wd_memos;
    }

    /**
     * 获取提现状态(2打款中、1成功、0失败)
     *
     * @return wd_status - 提现状态(2打款中、1成功、0失败)
     */
    public Integer getWd_status() {
        return wd_status;
    }

    /**
     * 设置提现状态(2打款中、1成功、0失败)
     *
     * @param wd_status 提现状态(2打款中、1成功、0失败)
     */
    public void setWd_status(Integer wd_status) {
        this.wd_status = wd_status;
    }

    /**
     * 获取支付方式(1微信支付2支付宝3银行卡)
     *
     * @return pay_types - 支付方式(1微信支付2支付宝3银行卡)
     */
    public Integer getPay_types() {
        return pay_types;
    }

    /**
     * 设置支付方式(1微信支付2支付宝3银行卡)
     *
     * @param pay_types 支付方式(1微信支付2支付宝3银行卡)
     */
    public void setPay_types(Integer pay_types) {
        this.pay_types = pay_types;
    }

    /**
     * 获取付款成功时间
     *
     * @return paysuces_time - 付款成功时间
     */
    public Date getPaysuces_time() {
        return paysuces_time;
    }

    /**
     * 设置付款成功时间
     *
     * @param paysuces_time 付款成功时间
     */
    public void setPaysuces_time(Date paysuces_time) {
        this.paysuces_time = paysuces_time;
    }

    /**
     * 获取账户名称
     *
     * @return account_name - 账户名称
     */
    public String getAccount_name() {
        return account_name;
    }

    /**
     * 设置账户名称
     *
     * @param account_name 账户名称
     */
    public void setAccount_name(String account_name) {
        this.account_name = account_name;
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
     * @return acouunt_no - 账号
     */
    public String getAcouunt_no() {
        return acouunt_no;
    }

    /**
     * 设置账号
     *
     * @param acouunt_no 账号
     */
    public void setAcouunt_no(String acouunt_no) {
        this.acouunt_no = acouunt_no;
    }

    /**
     * 获取开户行
     *
     * @return bank_name - 开户行
     */
    public String getBank_name() {
        return bank_name;
    }

    /**
     * 设置开户行
     *
     * @param bank_name 开户行
     */
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    /**
     * 获取支付订单号(平台生成)
     *
     * @return pay_order_no - 支付订单号(平台生成)
     */
    public String getPay_order_no() {
        return pay_order_no;
    }

    /**
     * 设置支付订单号(平台生成)
     *
     * @param pay_order_no 支付订单号(平台生成)
     */
    public void setPay_order_no(String pay_order_no) {
        this.pay_order_no = pay_order_no;
    }

    /**
     * 获取失败原因
     *
     * @return fail_reseaon - 失败原因
     */
    public String getFail_reseaon() {
        return fail_reseaon;
    }

    /**
     * 设置失败原因
     *
     * @param fail_reseaon 失败原因
     */
    public void setFail_reseaon(String fail_reseaon) {
        this.fail_reseaon = fail_reseaon;
    }
}