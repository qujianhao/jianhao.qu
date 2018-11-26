package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class GameResHitResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private Integer res_round_id;   //  回合id
    private Integer area;   //  倍区 ;从0到3依次为：内围，三倍，外围，二倍(对应靶盘从内到外)
    private Integer score;   //  分区（0代表0分区，21代表牛眼）

    public GameResHitResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRes_round_id() {
        return this.res_round_id;
    }
    public void setRes_round_id(Integer res_round_id) {
        this.res_round_id = res_round_id;
    }
    public Integer getArea() {
        return this.area;
    }
    public void setArea(Integer area) {
        this.area = area;
    }
    public Integer getScore() {
        return this.score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
}
