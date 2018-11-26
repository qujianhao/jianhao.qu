package com.wangtiansoft.KingDarts.results.core;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

public class CouponResult extends BaseResult implements Serializable{

	private Integer id;
    private String couponno;//优惠券编码
    private Integer coupon_status;//状态（0未使用，1已使用）
    private BigDecimal game_balance;//游戏点
    private String user_id;//使用用户id
    private Date apply_time;//使用时间
    private Integer isvalid;//是否有效(1:有效;0:无效）
    private Date create_time;
    private Date update_time;
    
    private String username;//用户名
    private String nickname;//昵称
    
    
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
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
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
    
    
}
