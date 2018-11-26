package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import javax.persistence.*;

@Table(name = "darts_club_moth_rank")
public class ClubMonthRank extends BaseEntity {
    /**
     * 主键信息id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 俱乐部编号
     */
    private String cno;

    /**
     * 竞技榜局数
     */
    private Integer athletics_num;

    /**
     * 实力榜实力值
     */
    private Integer strength_value;

    /**
     * 排名月份
     */
    private String rank_time;

    /**
     * 获取主键信息id
     *
     * @return id - 主键信息id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键信息id
     *
     * @param id 主键信息id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取俱乐部编号
     *
     * @return cno - 俱乐部编号
     */
    public String getCno() {
        return cno;
    }

    /**
     * 设置俱乐部编号
     *
     * @param cno 俱乐部编号
     */
    public void setCno(String cno) {
        this.cno = cno;
    }

    /**
     * 获取竞技榜局数
     *
     * @return athletics_num - 竞技榜局数
     */
    public Integer getAthletics_num() {
        return athletics_num;
    }

    /**
     * 设置竞技榜局数
     *
     * @param athletics_num 竞技榜局数
     */
    public void setAthletics_num(Integer athletics_num) {
        this.athletics_num = athletics_num;
    }

    /**
     * 获取实力榜实力值
     *
     * @return strength_value - 实力榜实力值
     */
    public Integer getStrength_value() {
        return strength_value;
    }

    /**
     * 设置实力榜实力值
     *
     * @param strength_value 实力榜实力值
     */
    public void setStrength_value(Integer strength_value) {
        this.strength_value = strength_value;
    }

    /**
     * 获取排名月份
     *
     * @return rank_time - 排名月份
     */
    public String getRank_time() {
        return rank_time;
    }

    /**
     * 设置排名月份
     *
     * @param rank_time 排名月份
     */
    public void setRank_time(String rank_time) {
        this.rank_time = rank_time;
    }
}