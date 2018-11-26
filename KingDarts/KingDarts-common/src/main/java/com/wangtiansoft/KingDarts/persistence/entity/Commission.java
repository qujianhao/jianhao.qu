package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_commission")
public class Commission extends BaseEntity {
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 支付订单id
     */
    private Long lft_pay_id;

    /**
     * 充值金额
     */
    private BigDecimal amount;

    /**
     * 代理商编码
     */
    private String user_id;
    
    /**
     * 设备编码
     */
    private String equno;
    
    /**
     * 代理商编码
     */
    private String agno;

    /**
     * 代理商佣金
     */
    private BigDecimal ag_amount;

    /**
     * 俱乐部编码
     */
    private String cno;

    /**
     * 俱乐部佣金
     */
    private BigDecimal c_amount;

    /**
     * 支付时间
     */
    private Date pay_time;

    /**
     * 描述
     */
    private String remark;

    /**
     * 获取唯一标识
     *
     * @return id - 唯一标识
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置唯一标识
     *
     * @param id 唯一标识
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取支付订单id
     *
     * @return lft_pay_id - 支付订单id
     */
    public Long getLft_pay_id() {
        return lft_pay_id;
    }

    /**
     * 设置支付订单id
     *
     * @param lft_pay_id 支付订单id
     */
    public void setLft_pay_id(Long lft_pay_id) {
        this.lft_pay_id = lft_pay_id;
    }

    /**
     * 获取充值金额
     *
     * @return amount - 充值金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置充值金额
     *
     * @param amount 充值金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取代理商编码
     *
     * @return agno - 代理商编码
     */
    public String getAgno() {
        return agno;
    }

    /**
     * 设置代理商编码
     *
     * @param agno 代理商编码
     */
    public void setAgno(String agno) {
        this.agno = agno;
    }

    /**
     * 获取代理商佣金
     *
     * @return ag_amount - 代理商佣金
     */
    public BigDecimal getAg_amount() {
        return ag_amount;
    }

    /**
     * 设置代理商佣金
     *
     * @param ag_amount 代理商佣金
     */
    public void setAg_amount(BigDecimal ag_amount) {
        this.ag_amount = ag_amount;
    }

    /**
     * 获取俱乐部编码
     *
     * @return cno - 俱乐部编码
     */
    public String getCno() {
        return cno;
    }

    /**
     * 设置俱乐部编码
     *
     * @param cno 俱乐部编码
     */
    public void setCno(String cno) {
        this.cno = cno;
    }

    /**
     * 获取俱乐部佣金
     *
     * @return c_amount - 俱乐部佣金
     */
    public BigDecimal getC_amount() {
        return c_amount;
    }

    /**
     * 设置俱乐部佣金
     *
     * @param c_amount 俱乐部佣金
     */
    public void setC_amount(BigDecimal c_amount) {
        this.c_amount = c_amount;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPay_time() {
        return pay_time;
    }

    /**
     * 设置支付时间
     *
     * @param pay_time 支付时间
     */
    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    /**
     * 获取描述
     *
     * @return remark - 描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置描述
     *
     * @param remark 描述
     */
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