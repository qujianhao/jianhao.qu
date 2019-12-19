package com.wangtiansoft.KingDarts.persistence.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;

@Table(name = "darts_game_prize")
public class GamePrize extends BaseEntity {

	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    /**
     * 奖项
     */
    private String prize;
    
    /**
     * 礼品
     */
    private String gift;
    
    /**
     * 获取奖品的条件
     */
    private String cond;
    
    /**
     * 是否有效(1:有效,0:无效)
     */
    private int isvalid;
    
    /**
     * 添加时间
     */
    private String addTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public String getCond() {
		return cond;
	}

	public void setCondition(String cond) {
		this.cond = cond;
	}

	public int getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(int isvalid) {
		this.isvalid = isvalid;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "GamePrize [id=" + id + ", prize=" + prize + ", gift=" + gift + ", condition=" + cond + ", isvalid="
				+ isvalid + ", addTime=" + addTime + "]";
	}
    
}
