package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_boss_score")
public class BossScore extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单编号
     */
    private String order_no;

    /**
     * 用户ID
     */
    private String user_id;

    /**
     * 击打bossid
     */
    private String boss_id;

    /**
     * 有效分数
     */
    private Integer total_score;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrder_no() {
        return order_no;
    }

    /**
     * 设置订单编号
     *
     * @param order_no 订单编号
     */
    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置用户ID
     *
     * @param user_id 用户ID
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取击打bossid
     *
     * @return boss_id - 击打bossid
     */
    public String getBoss_id() {
        return boss_id;
    }

    /**
     * 设置击打bossid
     *
     * @param boss_id 击打bossid
     */
    public void setBoss_id(String boss_id) {
        this.boss_id = boss_id;
    }

    /**
     * 获取有效分数
     *
     * @return total_score - 有效分数
     */
    public Integer getTotal_score() {
        return total_score;
    }

    /**
     * 设置有效分数
     *
     * @param total_score 有效分数
     */
    public void setTotal_score(Integer total_score) {
        this.total_score = total_score;
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
}