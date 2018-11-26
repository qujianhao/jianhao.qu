package com.wangtiansoft.KingDarts.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 充值规则
 * @author Administrator
 *
 */
@Table(name = "darts_lft_recharge_rule")
public class LftRechargeRule {

	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 金额
     */
    private BigDecimal money;
    
    /**
     * 充值游戏点
     */
    private BigDecimal game_balance;
    
    /**
     * 赠送游戏点
     */
    private BigDecimal give_game_balance;
    
    /**
     * 排序
     */
    private Integer sort;
    
    /**
     * 有效性1：有效；0：无效
     */
    private Integer isvalid;

    private Date create_time;
    
    private Date update_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getGame_balance() {
		return game_balance;
	}

	public void setGame_balance(BigDecimal game_balance) {
		this.game_balance = game_balance;
	}

	public BigDecimal getGive_game_balance() {
		return give_game_balance;
	}

	public void setGive_game_balance(BigDecimal give_game_balance) {
		this.give_game_balance = give_game_balance;
	}
	

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
    
    
}
