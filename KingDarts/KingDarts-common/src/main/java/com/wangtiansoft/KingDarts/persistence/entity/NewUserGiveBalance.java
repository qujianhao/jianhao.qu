package com.wangtiansoft.KingDarts.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;

@Table(name = "darts_newuser_give_balance")
public class NewUserGiveBalance extends BaseEntity {
	/**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * 赠送游戏点
     */
    private BigDecimal give_game_balance;
    
    /**
     * 起始时间
     */
    private Date start_time;
    
    /**
     * 结束时间
     */
    private Date end_time;
    
    /**
     * 是否应用（0否，1是）
     */
    private Integer is_publish;
    
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

	public BigDecimal getGive_game_balance() {
		return give_game_balance;
	}

	public void setGive_game_balance(BigDecimal give_game_balance) {
		this.give_game_balance = give_game_balance;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Integer getIs_publish() {
		return is_publish;
	}

	public void setIs_publish(Integer is_publish) {
		this.is_publish = is_publish;
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
