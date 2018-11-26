package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;

import java.math.BigDecimal;

import javax.persistence.*;

@Table(name = "darts_game_res_player")
public class GameResPlayer extends BaseEntity {
    /**
     * 唯一编码
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单编号
     */
    private String order_no;

    /**
     * 分组id
     */
    private Integer group_id;

    /**
     * 分组得分
     */
    private Integer group_score;

    /**
     * 玩家平均每镖分数
     */
    private BigDecimal ppd;

    /**
     * 平均每回合分数
     */
    private BigDecimal ppr;

    private BigDecimal mpr;

    /**
     * 击打数量
     */
    private Integer hit_num;

    /**
     * 分数
     */
    private Integer player_score;

    /**
     * 用户编号
     */
    private Integer player_id;

    private String player_name;

    /**
     * 获取唯一编码
     *
     * @return id - 唯一编码
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置唯一编码
     *
     * @param id 唯一编码
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
     * 获取分组id
     *
     * @return group_id - 分组id
     */
    public Integer getGroup_id() {
        return group_id;
    }

    /**
     * 设置分组id
     *
     * @param group_id 分组id
     */
    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    /**
     * 获取分组得分
     *
     * @return group_score - 分组得分
     */
    public Integer getGroup_score() {
        return group_score;
    }

    /**
     * 设置分组得分
     *
     * @param group_score 分组得分
     */
    public void setGroup_score(Integer group_score) {
        this.group_score = group_score;
    }

    /**
     * 获取玩家平均每镖分数
     *
     * @return PPD - 玩家平均每镖分数
     */
    public BigDecimal getPpd() {
        return ppd;
    }

    /**
     * 设置玩家平均每镖分数
     *
     * @param PPD 玩家平均每镖分数
     */
    public void setPpd(BigDecimal ppd) {
        this.ppd = ppd;
    }

    /**
     * 获取平均每回合分数
     *
     * @return PPR - 平均每回合分数
     */
    public BigDecimal getPpr() {
        return ppr;
    }

    /**
     * 设置平均每回合分数
     *
     * @param PPR 平均每回合分数
     */
    public void setPpr(BigDecimal ppr) {
        this.ppr = ppr;
    }

    /**
     * @return MPR
     */
    public BigDecimal getMpr() {
        return mpr;
    }

    /**
     * @param MPR
     */
    public void setMpr(BigDecimal mpr) {
        this.mpr = mpr;
    }

    /**
     * 获取击打数量
     *
     * @return hit_num - 击打数量
     */
    public Integer getHit_num() {
        return hit_num;
    }

    /**
     * 设置击打数量
     *
     * @param hit_num 击打数量
     */
    public void setHit_num(Integer hit_num) {
        this.hit_num = hit_num;
    }

    /**
     * 获取分数
     *
     * @return player_score - 分数
     */
    public Integer getPlayer_score() {
        return player_score;
    }

    /**
     * 设置分数
     *
     * @param player_score 分数
     */
    public void setPlayer_score(Integer player_score) {
        this.player_score = player_score;
    }

    /**
     * 获取用户编号
     *
     * @return player_id - 用户编号
     */
    public Integer getPlayer_id() {
        return player_id;
    }

    /**
     * 设置用户编号
     *
     * @param player_id 用户编号
     */
    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

    /**
     * @return player_name
     */
    public String getPlayer_name() {
        return player_name;
    }

    /**
     * @param player_name
     */
    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }
}