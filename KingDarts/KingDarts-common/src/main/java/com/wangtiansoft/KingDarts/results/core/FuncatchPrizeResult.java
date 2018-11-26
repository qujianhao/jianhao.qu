package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class FuncatchPrizeResult extends BaseResult implements Serializable{

    private Integer prize_id;   //  奖品id
    private Integer position_num;  //位置编号
    private String prize_name;   //  抓娃娃的奖品名称
    
    private String icon_name; //icon文件名称
    private String icon_url; //icon文件路径
    
    private String is_physical;   //  是否为实物奖品：Y:为实物；N:为虚拟奖品
    private String worth;   //  价值，如果是虚拟产品则代表点数，如果是实物奖品则代表价格
    private String win_rate;   //  该奖品的中奖率，小于等于1
    private Integer stock;   //  库存数量，只针对实物奖品
    private Integer is_valid;   //  奖品是否生效：1:生效；0:不生效

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time; //生成时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;//修改时间
    
    
    public FuncatchPrizeResult() {
    }

    public Integer getPrize_id() {
        return this.prize_id;
    }
    public void setPrize_id(Integer prize_id) {
        this.prize_id = prize_id;
    }
    public String getPrize_name() {
        return this.prize_name;
    }
    public void setPrize_name(String prize_name) {
        this.prize_name = prize_name;
    }
    public String getIs_physical() {
        return this.is_physical;
    }
    public void setIs_physical(String is_physical) {
        this.is_physical = is_physical;
    }
    public String getWorth() {
        return this.worth;
    }
    public void setWorth(String worth) {
        this.worth = worth;
    }
    public String getWin_rate() {
        return this.win_rate;
    }
    public void setWin_rate(String win_rate) {
        this.win_rate = win_rate;
    }
    public Integer getStock() {
        return this.stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Integer getIs_valid() {
        return this.is_valid;
    }
    public void setIs_valid(Integer is_valid) {
        this.is_valid = is_valid;
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
	public Integer getPosition_num() {
		return position_num;
	}
	public void setPosition_num(Integer position_num) {
		this.position_num = position_num;
	}
	public String getIcon_name() {
		return icon_name;
	}
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
    
}
