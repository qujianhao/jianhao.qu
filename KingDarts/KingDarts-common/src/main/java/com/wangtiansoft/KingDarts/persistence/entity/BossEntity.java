package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_boss_entity")
public class BossEntity extends BaseEntity {
    @Id
    private String uuid;

    /**
     * boss信息id
     */
    private Integer boss_id;

    /**
     * 应用时间
     */
    private String application_time;

    /**
     * 当前血量
     */
    private Long evolume;

    /**
     * 状态： 0 待击杀 1 已击杀 
     */
    private Integer kill_status;

    /**
     * 最后击杀用户uuid
     */
    private String kill_user_id;

    /**
     * 击杀时间
     */
    private Date kill_time;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * 是否删除 0：否 1:是
     */
    private Integer is_delete;

    /**
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取boss信息id
     *
     * @return boss_id - boss信息id
     */
    public Integer getBoss_id() {
        return boss_id;
    }

    /**
     * 设置boss信息id
     *
     * @param boss_id boss信息id
     */
    public void setBoss_id(Integer boss_id) {
        this.boss_id = boss_id;
    }

    /**
     * 获取应用时间
     *
     * @return application_time - 应用时间
     */
    public String getApplication_time() {
        return application_time;
    }

    /**
     * 设置应用时间
     *
     * @param application_time 应用时间
     */
    public void setApplication_time(String application_time) {
        this.application_time = application_time;
    }

    /**
     * 获取当前血量
     *
     * @return evolume - 当前血量
     */
    public Long getEvolume() {
        return evolume;
    }

    /**
     * 设置当前血量
     *
     * @param evolume 当前血量
     */
    public void setEvolume(Long evolume) {
        this.evolume = evolume;
    }

    /**
     * 获取状态： 0 待击杀 1 已击杀 
     *
     * @return kill_status - 状态： 0 待击杀 1 已击杀 
     */
    public Integer getKill_status() {
        return kill_status;
    }

    /**
     * 设置状态： 0 待击杀 1 已击杀 
     *
     * @param kill_status 状态： 0 待击杀 1 已击杀 
     */
    public void setKill_status(Integer kill_status) {
        this.kill_status = kill_status;
    }

    /**
     * 获取最后击杀用户uuid
     *
     * @return kill_user_id - 最后击杀用户uuid
     */
    public String getKill_user_id() {
        return kill_user_id;
    }

    /**
     * 设置最后击杀用户uuid
     *
     * @param kill_user_id 最后击杀用户uuid
     */
    public void setKill_user_id(String kill_user_id) {
        this.kill_user_id = kill_user_id;
    }

    /**
     * 获取击杀时间
     *
     * @return kill_time - 击杀时间
     */
    public Date getKill_time() {
        return kill_time;
    }

    /**
     * 设置击杀时间
     *
     * @param kill_time 击杀时间
     */
    public void setKill_time(Date kill_time) {
        this.kill_time = kill_time;
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 设置更新时间
     *
     * @param update_time 更新时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    /**
     * 获取是否删除 0：否 1:是
     *
     * @return is_delete - 是否删除 0：否 1:是
     */
    public Integer getIs_delete() {
        return is_delete;
    }

    /**
     * 设置是否删除 0：否 1:是
     *
     * @param is_delete 是否删除 0：否 1:是
     */
    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }
}