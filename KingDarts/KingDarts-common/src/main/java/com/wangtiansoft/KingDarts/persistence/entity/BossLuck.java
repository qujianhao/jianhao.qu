package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import javax.persistence.*;

@Table(name = "darts_boss_luck")
public class BossLuck extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 得分ID
     */
    private Long score_id;

    /**
     * 分数
     */
    private Integer score;

    private Integer times;
    
    private String luck;

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
     * 获取得分ID
     *
     * @return score_id - 得分ID
     */
    public Long getScore_id() {
        return score_id;
    }

    /**
     * 设置得分ID
     *
     * @param score_id 得分ID
     */
    public void setScore_id(Long score_id) {
        this.score_id = score_id;
    }

    /**
     * 获取分数
     *
     * @return score - 分数
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置分数
     *
     * @param score 分数
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @return times
     */
    public Integer getTimes() {
        return times;
    }

    /**
     * @param times
     */
    public void setTimes(Integer times) {
        this.times = times;
    }

	public String getLuck() {
		return luck;
	}

	public void setLuck(String luck) {
		this.luck = luck;
	}
    
}