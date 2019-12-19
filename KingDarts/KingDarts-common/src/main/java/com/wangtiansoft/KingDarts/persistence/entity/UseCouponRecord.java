package com.wangtiansoft.KingDarts.persistence.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;

@Table(name = "darts_usecoupon_record")
public class UseCouponRecord extends BaseEntity {
	
	/**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * 优惠券ID
     */
    private String couponId;
    
    /**
     * 使用者ID
     */
    private String uuid;
    
    /**
     * 使用时间
     */
    private String useTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	@Override
	public String toString() {
		return "UseCouponRecord [id=" + id + ", couponId=" + couponId + ", uuid=" + uuid + ", useTime=" + useTime + "]";
	}
	
}
