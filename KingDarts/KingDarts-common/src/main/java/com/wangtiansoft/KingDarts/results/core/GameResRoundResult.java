package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class GameResRoundResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private Integer res_player_id;   //  玩家id
    private Integer hit_num;   //  击打数量
    private Integer round_score;   //  回合得分

    public GameResRoundResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRes_player_id() {
        return this.res_player_id;
    }
    public void setRes_player_id(Integer res_player_id) {
        this.res_player_id = res_player_id;
    }
    public Integer getHit_num() {
        return this.hit_num;
    }
    public void setHit_num(Integer hit_num) {
        this.hit_num = hit_num;
    }
    public Integer getRound_score() {
        return this.round_score;
    }
    public void setRound_score(Integer round_score) {
        this.round_score = round_score;
    }
}
