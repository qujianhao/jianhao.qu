package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import javax.persistence.*;

@Table(name = "darts_game_res_round")
public class GameResRound extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 玩家id
     */
    private Long res_player_id;

    /**
     * 击打数量
     */
    private Integer hit_num;

    /**
     * 回合得分
     */
    private Integer round_score;

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
     * 获取玩家id
     *
     * @return res_player_id - 玩家id
     */
    public Long getRes_player_id() {
        return res_player_id;
    }

    /**
     * 设置玩家id
     *
     * @param res_player_id 玩家id
     */
    public void setRes_player_id(Long res_player_id) {
        this.res_player_id = res_player_id;
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
     * 获取回合得分
     *
     * @return round_score - 回合得分
     */
    public Integer getRound_score() {
        return round_score;
    }

    /**
     * 设置回合得分
     *
     * @param round_score 回合得分
     */
    public void setRound_score(Integer round_score) {
        this.round_score = round_score;
    }
}