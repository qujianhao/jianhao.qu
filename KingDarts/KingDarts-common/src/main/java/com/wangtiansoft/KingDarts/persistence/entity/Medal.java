package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_medal")
public class Medal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 勋章类型：其类型在数据字典中配置 
     */
    private Integer medal_style;

    /**
     * 勋章类型名称
     */
    private String medal_style_name;

    /**
     * 勋章名称
     */
    private String medal_name;

    /**
     * 对勋章的描述
     */
    private String medal_desc;

    /**
     * 奖励点数
     */
    private Integer prize_point;

    /**
     * 完成描述中规定任务的次数（累计完成/对战次数）
     */
    private Integer complete_times;

    /**
     * 完成描述中规定任务的次数（游戏胜利/获奖次数）
     */
    private Integer win_times;

    /**
     * 完成描述中规定任务的次数（命中/结镖次数）
     */
    private Integer hit_times;

    /**
     * 每个勋章对应的规则函数
     */
    private String rule_function;

    /**
     * 1:有效; 0:无效
     */
    private Integer is_valid;

    /**
     * 1:被删除; 0:未被删除
     */
    private Integer is_delete;

    /**
     * 排序，数值越大越靠前
     */
    private Integer order_no;

    /**
     * 勋章图片地址
     */
    private String medal_url;

    /**
     * 生成时间
     */
    private Date create_time;
    
    /**
     * 修改时间
     */
    private Date update_time;
    
    /**
     * sql查询语句
     */
    private String sql_str;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取勋章类型：其类型在数据字典中配置
     *
     * @return medal_style - 勋章类型：其类型在数据字典中配置
     */
    public Integer getMedal_style() {
        return medal_style;
    }

    /**
     * 设置勋章类型：其类型在数据字典中配置
     *
     * @param medal_style 勋章类型：其类型在数据字典中配置
     */
    public void setMedal_style(Integer medal_style) {
        this.medal_style = medal_style;
    }

    /**
     * 获取勋章类型名称
     *
     * @return medal_style_name - 勋章类型名称
     */
    public String getMedal_style_name() {
        return medal_style_name;
    }

    /**
     * 设置勋章类型名称
     *
     * @param medal_style_name 勋章类型名称
     */
    public void setMedal_style_name(String medal_style_name) {
        this.medal_style_name = medal_style_name;
    }

    /**
     * 获取勋章名称
     *
     * @return medal_name - 勋章名称
     */
    public String getMedal_name() {
        return medal_name;
    }

    /**
     * 设置勋章名称
     *
     * @param medal_name 勋章名称
     */
    public void setMedal_name(String medal_name) {
        this.medal_name = medal_name;
    }

    /**
     * 获取对勋章的描述
     *
     * @return medal_desc - 对勋章的描述
     */
    public String getMedal_desc() {
        return medal_desc;
    }

    /**
     * 设置对勋章的描述
     *
     * @param medal_desc 对勋章的描述
     */
    public void setMedal_desc(String medal_desc) {
        this.medal_desc = medal_desc;
    }

    /**
     * 获取奖励点数
     *
     * @return prize_point - 奖励点数
     */
    public Integer getPrize_point() {
        return prize_point;
    }

    /**
     * 设置奖励点数
     *
     * @param prize_point 奖励点数
     */
    public void setPrize_point(Integer prize_point) {
        this.prize_point = prize_point;
    }

    /**
     * 获取完成描述中规定任务的次数（累计完成/对战次数）
     *
     * @return complete_times - 完成描述中规定任务的次数（累计完成/对战次数）
     */
    public Integer getComplete_times() {
        return complete_times;
    }

    /**
     * 设置完成描述中规定任务的次数（累计完成/对战次数）
     *
     * @param complete_times 完成描述中规定任务的次数（累计完成/对战次数）
     */
    public void setComplete_times(Integer complete_times) {
        this.complete_times = complete_times;
    }

    /**
     * 获取完成描述中规定任务的次数（游戏胜利/获奖次数）
     *
     * @return win_times - 完成描述中规定任务的次数（游戏胜利/获奖次数）
     */
    public Integer getWin_times() {
        return win_times;
    }

    /**
     * 设置完成描述中规定任务的次数（游戏胜利/获奖次数）
     *
     * @param win_times 完成描述中规定任务的次数（游戏胜利/获奖次数）
     */
    public void setWin_times(Integer win_times) {
        this.win_times = win_times;
    }

    /**
     * 获取完成描述中规定任务的次数（命中/结镖次数）
     *
     * @return hit_times - 完成描述中规定任务的次数（命中/结镖次数）
     */
    public Integer getHit_times() {
        return hit_times;
    }

    /**
     * 设置完成描述中规定任务的次数（命中/结镖次数）
     *
     * @param hit_times 完成描述中规定任务的次数（命中/结镖次数）
     */
    public void setHit_times(Integer hit_times) {
        this.hit_times = hit_times;
    }

    /**
     * 获取每个勋章对应的规则函数
     *
     * @return rule_function - 每个勋章对应的规则函数
     */
    public String getRule_function() {
        return rule_function;
    }

    /**
     * 设置每个勋章对应的规则函数
     *
     * @param rule_function 每个勋章对应的规则函数
     */
    public void setRule_function(String rule_function) {
        this.rule_function = rule_function;
    }

    /**
     * 获取Y:有效; N:无效
     *
     * @return is_valid - Y:有效; N:无效
     */
    public Integer getIs_valid() {
        return is_valid;
    }

    /**
     * 设置Y:有效; N:无效
     *
     * @param is_valid Y:有效; N:无效
     */
    public void setIs_valid(Integer is_valid) {
        this.is_valid = is_valid;
    }

    /**
     * 获取Y:被删除; N:未被删除
     *
     * @return is_delete - Y:被删除; N:未被删除
     */
    public Integer getIs_delete() {
        return is_delete;
    }

    /**
     * 设置Y:被删除; N:未被删除
     *
     * @param is_delete Y:被删除; N:未被删除
     */
    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    /**
     * 获取排序，数值越大越靠前
     *
     * @return order_no - 排序，数值越大越靠前
     */
    public Integer getOrder_no() {
        return order_no;
    }

    /**
     * 设置排序，数值越大越靠前
     *
     * @param order_no 排序，数值越大越靠前
     */
    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }

    /**
     * 获取勋章图片地址
     *
     * @return medal_url - 勋章图片地址
     */
    public String getMedal_url() {
        return medal_url;
    }

    /**
     * 设置勋章图片地址
     *
     * @param medal_url 勋章图片地址
     */
    public void setMedal_url(String medal_url) {
        this.medal_url = medal_url;
    }

    /**
     * 获取生成时间
     *
     * @return create_time - 生成时间
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 设置生成时间
     *
     * @param create_time 生成时间
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    
    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 设置修改时间
     *
     * @param update_time 修改时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

	/**
     * 获取sql_str语句
     *
     * @return sql_str - sql语句
     */
	public String getSql_str() {
		return sql_str;
	}

	/**
     * 设置sql_str语句
     *
     * @param sql_str sql语句
     */
	public void setSql_str(String sql_str) {
		this.sql_str = sql_str;
	}
    
}