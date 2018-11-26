package com.wangtiansoft.KingDarts.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;

@Table(name = "darts_coupon")
public class Coupon extends BaseEntity {
	/**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * 优惠券编码
     */
    private String couponno;
    
    /**
     * 状态（0未使用，1已使用）
     */
    private Integer coupon_status;
    
    /**
     * 游戏点
     */
    private BigDecimal game_balance;
    
    /**
     * 使用用户id
     */
    private String user_id;
    
    /**
     * 使用时间
     */
    private Date apply_time;
    
    /**
     * 是否有效(1:有效;0:无效）
     */
    private Integer isvalid;
    
    private Date create_time;
    
    private Date update_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCouponno() {
		return couponno;
	}

	public void setCouponno(String couponno) {
		this.couponno = couponno;
	}

	public Integer getCoupon_status() {
		return coupon_status;
	}

	public void setCoupon_status(Integer coupon_status) {
		this.coupon_status = coupon_status;
	}

	public BigDecimal getGame_balance() {
		return game_balance;
	}

	public void setGame_balance(BigDecimal game_balance) {
		this.game_balance = game_balance;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
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
