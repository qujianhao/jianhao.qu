package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class MedalResult extends BaseResult implements Serializable{

    private Integer id;   //  
    private Integer medal_style;   //  勋章类型：其类型在数据字典中配置
    private String medal_style_name;   //  勋章类型名称
    private String medal_name;   //  勋章名称
    private String medal_desc;   //  对勋章的描述
    private Integer prize_point;   //  奖励点数
    private String rule_function;   //  每个勋章对应的规则函数
    private Integer is_valid;   //  1:有效; 0:无效
    private Integer order_no;   //  排序，数值越大越靠前
    private String medal_url;   //  勋章图片地址
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time; //生成时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;//修改时间
    
    private Integer complete_times;//完成规定数量
    
    private String percentComplate;//完成百分比

    public MedalResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getMedal_style() {
        return this.medal_style;
    }
    public void setMedal_style(Integer medal_style) {
        this.medal_style = medal_style;
    }
    public String getMedal_style_name() {
        return this.medal_style_name;
    }
    public void setMedal_style_name(String medal_style_name) {
        this.medal_style_name = medal_style_name;
    }
    public String getMedal_name() {
        return this.medal_name;
    }
    public void setMedal_name(String medal_name) {
        this.medal_name = medal_name;
    }
    public String getMedal_desc() {
        return this.medal_desc;
    }
    public void setMedal_desc(String medal_desc) {
        this.medal_desc = medal_desc;
    }
    public Integer getPrize_point() {
        return this.prize_point;
    }
    public void setPrize_point(Integer prize_point) {
        this.prize_point = prize_point;
    }
    public String getRule_function() {
        return this.rule_function;
    }
    public void setRule_function(String rule_function) {
        this.rule_function = rule_function;
    }
    public Integer getIs_valid() {
        return this.is_valid;
    }
    public void setIs_valid(Integer is_valid) {
        this.is_valid = is_valid;
    }
    public Integer getOrder_no() {
        return this.order_no;
    }
    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }
    public String getMedal_url() {
        return this.medal_url;
    }
    public void setMedal_url(String medal_url) {
        this.medal_url = medal_url;
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
	public String getPercentComplate() {
		return percentComplate;
	}
	public void setPercentComplate(String percentComplate) {
		this.percentComplate = percentComplate;
	}
	public Integer getComplete_times() {
		return complete_times;
	}
	public void setComplete_times(Integer complete_times) {
		this.complete_times = complete_times;
	}
}
