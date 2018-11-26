package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import javax.persistence.*;

@Table(name = "darts_game_res_hit")
public class GameResHit extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 回合id
     */
    private Long res_round_id;

    /**
     * 倍区 ;从0到3依次为：内围，三倍，外围，二倍(对应靶盘从内到外)
     */
    private Integer area;

    /**
     * 分区（0代表0分区，21代表牛眼）
     */
    private Integer score;

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
     * 获取回合id
     *
     * @return res_round_id - 回合id
     */
    public Long getRes_round_id() {
        return res_round_id;
    }

    /**
     * 设置回合id
     *
     * @param res_round_id 回合id
     */
    public void setRes_round_id(Long res_round_id) {
        this.res_round_id = res_round_id;
    }

    /**
     * 获取倍区 ;从0到3依次为：内围，三倍，外围，二倍(对应靶盘从内到外)
     *
     * @return area - 倍区 ;从0到3依次为：内围，三倍，外围，二倍(对应靶盘从内到外)
     */
    public Integer getArea() {
        return area;
    }

    /**
     * 设置倍区 ;从0到3依次为：内围，三倍，外围，二倍(对应靶盘从内到外)
     *
     * @param area 倍区 ;从0到3依次为：内围，三倍，外围，二倍(对应靶盘从内到外)
     */
    public void setArea(Integer area) {
        this.area = area;
    }

    /**
     * 获取分区（0代表0分区，21代表牛眼）
     *
     * @return score - 分区（0代表0分区，21代表牛眼）
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置分区（0代表0分区，21代表牛眼）
     *
     * @param score 分区（0代表0分区，21代表牛眼）
     */
    public void setScore(Integer score) {
        this.score = score;
    }
}