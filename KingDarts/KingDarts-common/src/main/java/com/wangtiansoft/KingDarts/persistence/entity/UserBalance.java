package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_user_balance")
public class UserBalance extends BaseEntity {
    /**
     * 唯一id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user_id;

    /**
     * 总金额
     */
    private BigDecimal amount;

    /**
     * 变动类型，1充值，2消费
     */
    private Integer type;

    /**
     * 变动时间
     */
    private Date log_time;

    /**
     * 游戏订单ID，game_order.id
     */
    private Long order_id;

    /**
     * 支付订单ID，lft_pay.id
     */
    private Long pay_id;

    /**
     * 设备编码
     */
    private String equno;

    /**
     * 说明
     */
    private String remark;
    
    /**
     * 余额变动
     */
    private BigDecimal balance;
    /**
     * 赠送金额变动
     */
    private BigDecimal give_balance;
    /**
     * 余额变动
     */
    private BigDecimal balance_pre;
    /**
     * 赠送金额变动
     */
    private BigDecimal give_balance_pre;
    /**
     * 优惠券金额变动
     */
    private BigDecimal coupon_balance;
    /**
     * 变动前优惠券金额变动
     */
    private BigDecimal coupon_balance_pre;
    
    
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

	/**
     * 获取唯一id
     *
     * @return id - 唯一id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置唯一id
     *
     * @param id 唯一id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取金额
     *
     * @return amount - 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置金额
     *
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取变动类型，1充值，2消费
     *
     * @return type - 变动类型，1充值，2消费
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置变动类型，1充值，2消费
     *
     * @param type 变动类型，1充值，2消费
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取变动时间
     *
     * @return log_time - 变动时间
     */
    public Date getLog_time() {
        return log_time;
    }

    /**
     * 设置变动时间
     *
     * @param log_time 变动时间
     */
    public void setLog_time(Date log_time) {
        this.log_time = log_time;
    }

    /**
     * 获取游戏订单ID，game_order.id
     *
     * @return order_id - 游戏订单ID，game_order.id
     */
    public Long getOrder_id() {
        return order_id;
    }

    /**
     * 设置游戏订单ID，game_order.id
     *
     * @param order_id 游戏订单ID，game_order.id
     */
    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    /**
     * 获取支付订单ID，lft_pay.id
     *
     * @return pay_id - 支付订单ID，lft_pay.id
     */
    public Long getPay_id() {
        return pay_id;
    }

    /**
     * 设置支付订单ID，lft_pay.id
     *
     * @param pay_id 支付订单ID，lft_pay.id
     */
    public void setPay_id(Long pay_id) {
        this.pay_id = pay_id;
    }

    /**
     * 获取设备编码
     *
     * @return equno - 设备编码
     */
    public String getEquno() {
        return equno;
    }

    /**
     * 设置设备编码
     *
     * @param equno 设备编码
     */
    public void setEquno(String equno) {
        this.equno = equno;
    }

    /**
     * 获取说明
     *
     * @return remark - 说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置说明
     *
     * @param remark 说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
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

	public BigDecimal getBalance_pre() {
		return balance_pre;
	}

	public void setBalance_pre(BigDecimal balance_pre) {
		this.balance_pre = balance_pre;
	}

	public BigDecimal getGive_balance_pre() {
		return give_balance_pre;
	}

	public void setGive_balance_pre(BigDecimal give_balance_pre) {
		this.give_balance_pre = give_balance_pre;
	}
    
}