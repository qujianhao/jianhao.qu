package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class GameResult extends BaseResult implements Serializable{

    private String id;   //  主键
    private String game_code;   //  游戏代码
    private String game_name;   //  游戏名称
    private Integer game_type;   //  游戏类型
    private String game_mode_code;   //  游戏模式外键
    private String dedcount;   //  扣除币数
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_date;   //  创建时间
    private String create_by;   //  创建人
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_date;   //  修改时间
    private String update_by;   //  修改人
    private Integer status;   //  状态(1禁用 2启用)

    public GameResult() {
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getGame_code() {
        return this.game_code;
    }
    public void setGame_code(String game_code) {
        this.game_code = game_code;
    }
    public String getGame_name() {
        return this.game_name;
    }
    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }
    public Integer getGame_type() {
        return this.game_type;
    }
    public void setGame_type(Integer game_type) {
        this.game_type = game_type;
    }
    public String getGame_mode_code() {
        return this.game_mode_code;
    }
    public void setGame_mode_code(String game_mode_code) {
        this.game_mode_code = game_mode_code;
    }
    public String getDedcount() {
        return this.dedcount;
    }
    public void setDedcount(String dedcount) {
        this.dedcount = dedcount;
    }
    public Date getCreate_date() {
        return this.create_date;
    }
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
    public String getCreate_by() {
        return this.create_by;
    }
    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }
    public Date getUpdate_date() {
        return this.update_date;
    }
    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
    public String getUpdate_by() {
        return this.update_by;
    }
    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
