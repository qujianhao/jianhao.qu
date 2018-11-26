package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "wt_plugins")
public class Plugins extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 插件标示
     */
    private String plugin_key;

    /**
     * 插件名称
     */
    private String title;

    /**
     * 插件描述
     */
    private String summary;

    /**
     * appId
     */
    private String app_id;

    /**
     * 插件key
     */
    private String app_key;

    /**
     * 插件secret
     */
    private String app_secret;

    /**
     * 排序字段-倒序
     */
    private Integer order_num;

    /**
     * 是否删除
     */
    private Integer is_delete;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 是否发布
     */
    private Integer is_publish;

    /**
     * 插件详细属性
     */
    private String attrs;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取插件标示
     *
     * @return plugin_key - 插件标示
     */
    public String getPlugin_key() {
        return plugin_key;
    }

    /**
     * 设置插件标示
     *
     * @param plugin_key 插件标示
     */
    public void setPlugin_key(String plugin_key) {
        this.plugin_key = plugin_key;
    }

    /**
     * 获取插件名称
     *
     * @return title - 插件名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置插件名称
     *
     * @param title 插件名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取插件描述
     *
     * @return summary - 插件描述
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置插件描述
     *
     * @param summary 插件描述
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取appId
     *
     * @return app_id - appId
     */
    public String getApp_id() {
        return app_id;
    }

    /**
     * 设置appId
     *
     * @param app_id appId
     */
    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    /**
     * 获取插件key
     *
     * @return app_key - 插件key
     */
    public String getApp_key() {
        return app_key;
    }

    /**
     * 设置插件key
     *
     * @param app_key 插件key
     */
    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    /**
     * 获取插件secret
     *
     * @return app_secret - 插件secret
     */
    public String getApp_secret() {
        return app_secret;
    }

    /**
     * 设置插件secret
     *
     * @param app_secret 插件secret
     */
    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    /**
     * 获取排序字段-倒序
     *
     * @return order_num - 排序字段-倒序
     */
    public Integer getOrder_num() {
        return order_num;
    }

    /**
     * 设置排序字段-倒序
     *
     * @param order_num 排序字段-倒序
     */
    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
    }

    /**
     * 获取是否删除
     *
     * @return is_delete - 是否删除
     */
    public Integer getIs_delete() {
        return is_delete;
    }

    /**
     * 设置是否删除
     *
     * @param is_delete 是否删除
     */
    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
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
     * 获取是否发布
     *
     * @return is_publish - 是否发布
     */
    public Integer getIs_publish() {
        return is_publish;
    }

    /**
     * 设置是否发布
     *
     * @param is_publish 是否发布
     */
    public void setIs_publish(Integer is_publish) {
        this.is_publish = is_publish;
    }

    /**
     * 获取插件详细属性
     *
     * @return attrs - 插件详细属性
     */
    public String getAttrs() {
        return attrs;
    }

    /**
     * 设置插件详细属性
     *
     * @param attrs 插件详细属性
     */
    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }
}