package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class UserBalanceResult extends BaseResult implements Serializable{

    private Integer id;   //  唯一id
    private String user_id;   //  
    private BigDecimal amount;   //  总金额
    private Integer type;   //  变动类型，1充值，2消费
    private Integer order_id;   //  游戏订单ID，game_order.id
    private Integer pay_id;   //  支付订单ID，lft_pay.id
    private String equno;   //  设备编码
    private String remark;   //  说明
    private BigDecimal balance;	//金额变动
    private BigDecimal give_balance; //赠送金额变动
    private BigDecimal coupon_balance;//优惠券金额变动
    private BigDecimal coupon_balance_pre;//变动前优惠券金额

    public UserBalanceResult() {
    }

    
    public BigDecimal getCoupon_balance() {
		return coupon_balance;
	}


	public void setCoupon_balance(BigDecimal coupon_balance) {
		this.coupon_balance = coupon_balance;
	}


	public BigDecimal getCoupon_balance_pre() {
		return coupon_balance_pre;
	}


	public void setCoupon_balance_pre(BigDecimal coupon_balance_pre) {
		this.coupon_balance_pre = coupon_balance_pre;
	}


	public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUser_id() {
        return this.user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getOrder_id() {
        return this.order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public Integer getPay_id() {
        return this.pay_id;
    }
    public void setPay_id(Integer pay_id) {
        this.pay_id = pay_id;
    }
    public String getEquno() {
        return this.equno;
    }
    public void setEquno(String equno) {
        this.equno = equno;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getGive_balance() {
		return give_balance;
	}

	public void setGive_balance(BigDecimal give_balance) {
		this.give_balance = give_balance;
	}
    
}
