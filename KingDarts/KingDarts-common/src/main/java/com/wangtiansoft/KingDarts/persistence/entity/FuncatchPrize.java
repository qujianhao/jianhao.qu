package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_funcatch_prize")
public class FuncatchPrize extends BaseEntity {
    /**
     * 奖品id
     */
    @Id
    private Integer prize_id;
    
    /**
     * 位置编号
     */
    private Integer position_num;

    /**
     * 抓娃娃的奖品名称
     */
    private String prize_name;
    
    /**
     * icon文件名称
     */
    private String icon_name;
    
    /**
     * icon文件路径
     */
    private String icon_url;

    /**
     * 是否为实物奖品：Y:为实物；N:为虚拟奖品 B:抽奖按钮
     */
    private String is_physical;
    
    /**
     * 价值，如果是虚拟产品则代表点数，如果是实物奖品则代表价格 
     * 可以为负数 负数则代表每局消耗游戏点数
     */
    private BigDecimal worth;

    /**
     * 该奖品的中奖率，小于等于1
     */
    private BigDecimal win_rate;

    /**
     * 库存数量，只针对实物奖品
     */
    private Integer stock;

    /**
     * 奖品是否生效：1:生效；0:不生效
     */
    private Integer is_valid;

    /**
     * 1:奖品被删除；0:未被删除
     */
    private Integer is_delete;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;
    
    
    /**
     * 获取奖品id
     *
     * @return prize_id - 奖品id
     */
    public Integer getPrize_id() {
        return prize_id;
    }

    /**
     * 设置奖品id
     *
     * @param prize_id 奖品id
     */
    public void setPrize_id(Integer prize_id) {
        this.prize_id = prize_id;
    }
    
    /**
     * 获取位置
     *
     * @return position_num - 位置
     */
    public Integer getPosition_num() {
		return position_num;
	}

	/**
     * 设置位置
     *
     * @param position_num 位置
     */
    public void setPosition_num(Integer position_num) {
		this.position_num = position_num;
	}

    /**
     * 获取抓娃娃的奖品名称
     *
     * @return prize_name - 抓娃娃的奖品名称
     */
    public String getPrize_name() {
        return prize_name;
    }

    /**
     * 设置抓娃娃的奖品名称
     *
     * @param prize_name 抓娃娃的奖品名称
     */
    public void setPrize_name(String prize_name) {
        this.prize_name = prize_name;
    }
    
    /**
     * 获取icon文件名称
     *
     * @return icon_name - icon文件名称
     */
    public String getIcon_name() {
		return icon_name;
	}

    /**
     * 设置icon文件名称
     *
     * @param icon_name - icon文件名称
     */
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}

	/**
     * 获取icon文件路径
     *
     * @return icon_url - icon文件路径
     */
	public String getIcon_url() {
		return icon_url;
	}

	/**
     * 设置抓娃娃的奖品名称
     *
     * @param icon_url 抓娃娃的奖品名称
     */
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	/**
     * 获取是否为实物奖品：Y:为实物；N:为虚拟奖品
     *
     * @return is_physical - 是否为实物奖品：Y:为实物；N:为虚拟奖品
     */
    public String getIs_physical() {
        return is_physical;
    }

    /**
     * 设置是否为实物奖品：Y:为实物；N:为虚拟奖品
     *
     * @param is_physical 是否为实物奖品：Y:为实物；N:为虚拟奖品
     */
    public void setIs_physical(String is_physical) {
        this.is_physical = is_physical;
    }

    /**
     * 获取价值，如果是虚拟产品则代表点数，如果是实物奖品则代表价格
     *
     * @return worth - 价值，如果是虚拟产品则代表点数，如果是实物奖品则代表价格
     */
    public BigDecimal getWorth() {
        return worth;
    }

    /**
     * 设置价值，如果是虚拟产品则代表点数，如果是实物奖品则代表价格
     *
     * @param worth 价值，如果是虚拟产品则代表点数，如果是实物奖品则代表价格
     */
    public void setWorth(BigDecimal worth) {
        this.worth = worth;
    }

    /**
     * 获取该奖品的中奖率，小于等于1
     *
     * @return win_rate - 该奖品的中奖率，小于等于1
     */
    public BigDecimal getWin_rate() {
        return win_rate;
    }

    /**
     * 设置该奖品的中奖率，小于等于1
     *
     * @param win_rate 该奖品的中奖率，小于等于1
     */
    public void setWin_rate(BigDecimal win_rate) {
        this.win_rate = win_rate;
    }

    /**
     * 获取库存数量，只针对实物奖品
     *
     * @return stock - 库存数量，只针对实物奖品
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置库存数量，只针对实物奖品
     *
     * @param stock 库存数量，只针对实物奖品
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取奖品是否生效：1:生效；0:不生效
     *
     * @return is_valid - 奖品是否生效：1:生效；0:不生效
     */
    public Integer getIs_valid() {
        return is_valid;
    }

    /**
     * 设置奖品是否生效：1:生效；0:不生效
     *
     * @param is_valid 奖品是否生效：1:生效；0:不生效
     */
    public void setIs_valid(Integer is_valid) {
        this.is_valid = is_valid;
    }

    /**
     * 获取1:奖品被删除；0:未被删除
     *
     * @return is_delete - 1:奖品被删除；0:未被删除
     */
    public Integer getIs_delete() {
        return is_delete;
    }

    /**
     * 设置1:奖品被删除；0:未被删除
     *
     * @param is_delete 1:奖品被删除；0:未被删除
     */
    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 设置创建时间
     *
     * @param create_time 创建时间
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    
    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 设置更新时间
     *
     * @param update_time 更新时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    
   
    
    

}