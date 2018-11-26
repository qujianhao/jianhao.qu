package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class CommissionResult extends BaseResult implements Serializable{

    private Integer id;   //  唯一标识
    private Integer lft_pay_id;   //  支付订单id
    private String amount;   //  充值金额
    private String user_id; //充值用户ID
    private String equno; //设备编码
    private String agno;   //  代理商编码
    private String ag_amount;   //  代理商佣金
    private String cno;   //  俱乐部编码
    private String c_amount;   //  俱乐部佣金
    private String remark;   //  描述

    public CommissionResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getLft_pay_id() {
        return this.lft_pay_id;
    }
    public void setLft_pay_id(Integer lft_pay_id) {
        this.lft_pay_id = lft_pay_id;
    }
    public String getAmount() {
        return this.amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAgno() {
        return this.agno;
    }
    public void setAgno(String agno) {
        this.agno = agno;
    }
    public String getAg_amount() {
        return this.ag_amount;
    }
    public void setAg_amount(String ag_amount) {
        this.ag_amount = ag_amount;
    }
    public String getCno() {
        return this.cno;
    }
    public void setCno(String cno) {
        this.cno = cno;
    }
    public String getC_amount() {
        return this.c_amount;
    }
    public void setC_amount(String c_amount) {
        this.c_amount = c_amount;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEquno() {
		return equno;
	}

	public void setEquno(String equno) {
		this.equno = equno;
	}
    
}
