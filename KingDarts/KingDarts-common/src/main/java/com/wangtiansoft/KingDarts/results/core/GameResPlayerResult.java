package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class GameResPlayerResult extends BaseResult implements Serializable{

    private Integer id;   //  唯一编码
    private String order_no;   //  订单编号
    private Integer group_id;   //  分组id
    private Integer group_score;   //  分组得分
    private BigDecimal ppd;   //  玩家平均每镖分数
    private BigDecimal ppr;   //  平均每回合分数
    private BigDecimal mpr;   //  
    private Integer hit_num;   //  击打数量
    private Integer player_score;   //  分数
    private Integer player_id;   //  用户编号
    private String player_name;   //  

    public GameResPlayerResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrder_no() {
        return this.order_no;
    }
    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }
    public Integer getGroup_id() {
        return this.group_id;
    }
    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }
    public Integer getGroup_score() {
        return this.group_score;
    }
    public void setGroup_score(Integer group_score) {
        this.group_score = group_score;
    }
    public BigDecimal getPpd() {
		return ppd;
	}

	public void setPpd(BigDecimal ppd) {
		this.ppd = ppd;
	}

	public BigDecimal getPpr() {
		return ppr;
	}

	public void setPpr(BigDecimal ppr) {
		this.ppr = ppr;
	}

	public BigDecimal getMpr() {
		return mpr;
	}

	public void setMpr(BigDecimal mpr) {
		this.mpr = mpr;
	}

	public Integer getHit_num() {
        return this.hit_num;
    }
    public void setHit_num(Integer hit_num) {
        this.hit_num = hit_num;
    }
    public Integer getPlayer_score() {
        return this.player_score;
    }
    public void setPlayer_score(Integer player_score) {
        this.player_score = player_score;
    }
    public Integer getPlayer_id() {
        return this.player_id;
    }
    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }
    public String getPlayer_name() {
        return this.player_name;
    }
    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }
}
