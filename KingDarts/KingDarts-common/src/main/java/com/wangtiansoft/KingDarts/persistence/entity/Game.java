package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_game")
public class Game extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 游戏代码
     */
    private String game_code;

    /**
     * 游戏名称
     */
    private String game_name;

    /**
     * 游戏类型
     */
    private Integer game_type;

    /**
     * 游戏模式外键
     */
    private String game_mode_code;

    /**
     * 扣除币数
     */
    private BigDecimal dedcount;

    /**
     * 创建时间
     */
    private Date create_date;

    /**
     * 创建人
     */
    private String create_by;

    /**
     * 修改时间
     */
    private Date update_date;

    /**
     * 修改人
     */
    private String update_by;

    /**
     * 状态(1禁用 2启用)
     */
    private Integer status;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取游戏代码
     *
     * @return game_code - 游戏代码
     */
    public String getGame_code() {
        return game_code;
    }

    /**
     * 设置游戏代码
     *
     * @param game_code 游戏代码
     */
    public void setGame_code(String game_code) {
        this.game_code = game_code;
    }

    /**
     * 获取游戏名称
     *
     * @return game_name - 游戏名称
     */
    public String getGame_name() {
        return game_name;
    }

    /**
     * 设置游戏名称
     *
     * @param game_name 游戏名称
     */
    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    /**
     * 获取游戏类型
     *
     * @return game_type - 游戏类型
     */
    public Integer getGame_type() {
        return game_type;
    }

    /**
     * 设置游戏类型
     *
     * @param game_type 游戏类型
     */
    public void setGame_type(Integer game_type) {
        this.game_type = game_type;
    }

    /**
     * 获取游戏模式外键
     *
     * @return game_mode_code - 游戏模式外键
     */
    public String getGame_mode_code() {
        return game_mode_code;
    }

    /**
     * 设置游戏模式外键
     *
     * @param game_mode_code 游戏模式外键
     */
    public void setGame_mode_code(String game_mode_code) {
        this.game_mode_code = game_mode_code;
    }

    /**
     * 获取扣除币数
     *
     * @return dedcount - 扣除币数
     */
    public BigDecimal getDedcount() {
        return dedcount;
    }

    /**
     * 设置扣除币数
     *
     * @param dedcount 扣除币数
     */
    public void setDedcount(BigDecimal dedcount) {
        this.dedcount = dedcount;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * 设置创建时间
     *
     * @param create_date 创建时间
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreate_by() {
        return create_by;
    }

    /**
     * 设置创建人
     *
     * @param create_by 创建人
     */
    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    /**
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdate_date() {
        return update_date;
    }

    /**
     * 设置修改时间
     *
     * @param update_date 修改时间
     */
    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    /**
     * 获取修改人
     *
     * @return update_by - 修改人
     */
    public String getUpdate_by() {
        return update_by;
    }

    /**
     * 设置修改人
     *
     * @param update_by 修改人
     */
    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    /**
     * 获取状态(1禁用 2启用)
     *
     * @return status - 状态(1禁用 2启用)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态(1禁用 2启用)
     *
     * @param status 状态(1禁用 2启用)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}