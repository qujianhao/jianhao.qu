package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class GameResultsResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String order_no;   //  订单编号
    private String game_code;   //  游戏编码
    private String user_id;   //  玩家编号
    private String winorlose;   //  输赢（W/L）
    private String cno;   //  俱乐部编号
    private String equno;   //  设备编号
    private String cardinfo;   //  卡信息
    private String cardno;   //  卡号（印刷）
    private String score;   //  得分
    private String ppd;   //  当前得分÷有效镖数
    private String mpr;   //  mpr
    private String intr;   //  积分
    private String other;   //  其他信息

    public GameResultsResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrder_no() {
        return this.order_no;
    }
    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }
    public String getGame_code() {
        return this.game_code;
    }
    public void setGame_code(String game_code) {
        this.game_code = game_code;
    }
    public String getUser_id() {
        return this.user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getWinorlose() {
        return this.winorlose;
    }
    public void setWinorlose(String winorlose) {
        this.winorlose = winorlose;
    }
    public String getCno() {
        return this.cno;
    }
    public void setCno(String cno) {
        this.cno = cno;
    }
    public String getEquno() {
        return this.equno;
    }
    public void setEquno(String equno) {
        this.equno = equno;
    }
    public String getCardinfo() {
        return this.cardinfo;
    }
    public void setCardinfo(String cardinfo) {
        this.cardinfo = cardinfo;
    }
    public String getCardno() {
        return this.cardno;
    }
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
    public String getScore() {
        return this.score;
    }
    public void setScore(String score) {
        this.score = score;
    }
    public String getPpd() {
        return this.ppd;
    }
    public void setPpd(String ppd) {
        this.ppd = ppd;
    }
    public String getMpr() {
        return this.mpr;
    }
    public void setMpr(String mpr) {
        this.mpr = mpr;
    }
    public String getIntr() {
        return this.intr;
    }
    public void setIntr(String intr) {
        this.intr = intr;
    }
    public String getOther() {
        return this.other;
    }
    public void setOther(String other) {
        this.other = other;
    }
}
