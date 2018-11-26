package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "wt_role")
public class Role extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer order_num;

    /**
     * 是否发布
     */
    private Integer is_publish;

    /**
     * 是否删除
     */
    private Integer is_delete;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 子权限列表
     */
    private String code_content;

    /**
     * 权限列表
     */
    private String prem_content;

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
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取排序
     *
     * @return order_num - 排序
     */
    public Integer getOrder_num() {
        return order_num;
    }

    /**
     * 设置排序
     *
     * @param order_num 排序
     */
    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
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
     * 获取子权限列表
     *
     * @return code_content - 子权限列表
     */
    public String getCode_content() {
        return code_content;
    }

    /**
     * 设置子权限列表
     *
     * @param code_content 子权限列表
     */
    public void setCode_content(String code_content) {
        this.code_content = code_content;
    }

    /**
     * 获取权限列表
     *
     * @return prem_content - 权限列表
     */
    public String getPrem_content() {
        return prem_content;
    }

    /**
     * 设置权限列表
     *
     * @param prem_content 权限列表
     */
    public void setPrem_content(String prem_content) {
        this.prem_content = prem_content;
    }
}