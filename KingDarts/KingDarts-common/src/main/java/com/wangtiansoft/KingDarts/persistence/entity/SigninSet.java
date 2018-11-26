package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_signin_set")
public class SigninSet extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 本周第几天
     */
    private Short signin_day;

    /**
     * 设置名称
     */
    private String name;

    /**
     * 获取点数
     */
    private Integer point;

    /**
     * 是否删除：0未删除1已删除
     */
    private Integer is_delete;

    /**
     * 是否发布 1 发布 0未发布
     */
    private Integer is_publish;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取本周第几天
     *
     * @return signin_day - 本周第几天
     */
    public Short getSignin_day() {
        return signin_day;
    }

    /**
     * 设置本周第几天
     *
     * @param signin_day 本周第几天
     */
    public void setSignin_day(Short signin_day) {
        this.signin_day = signin_day;
    }

    /**
     * 获取设置名称
     *
     * @return name - 设置名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置设置名称
     *
     * @param name 设置名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取获取点数
     *
     * @return point - 获取点数
     */
    public Integer getPoint() {
        return point;
    }

    /**
     * 设置获取点数
     *
     * @param point 获取点数
     */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * 获取是否删除：0未删除1已删除
     *
     * @return is_delete - 是否删除：0未删除1已删除
     */
    public Integer getIs_delete() {
        return is_delete;
    }

    /**
     * 设置是否删除：0未删除1已删除
     *
     * @param is_delete 是否删除：0未删除1已删除
     */
    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    /**
     * 获取是否发布 1 发布 0未发布
     *
     * @return is_publish - 是否发布 1 发布 0未发布
     */
    public Integer getIs_publish() {
        return is_publish;
    }

    /**
     * 设置是否发布 1 发布 0未发布
     *
     * @param is_publish 是否发布 1 发布 0未发布
     */
    public void setIs_publish(Integer is_publish) {
        this.is_publish = is_publish;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 设置创建时间
     *
     * @param create_time 创建时间
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 设置修改时间
     *
     * @param update_time 修改时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}