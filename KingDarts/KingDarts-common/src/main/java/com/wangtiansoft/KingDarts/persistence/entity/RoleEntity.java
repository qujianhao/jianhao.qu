package com.wangtiansoft.KingDarts.persistence.entity;


import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "wt_role")
public class RoleEntity extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 排序
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 是否发布
     */
    @Column(name = "is_publish")
    private Byte isPublish;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Byte isDelete;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 权限列表
     */
    @Column(name = "prem_content")
    private String premContent;

    /**
     * 权限列表
     */
    @Column(name = "code_content")
    private String codeContent;

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
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取排序
     *
     * @return order_num - 排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置排序
     *
     * @param orderNum 排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取是否发布
     *
     * @return is_publish - 是否发布
     */
    public Byte getIsPublish() {
        return isPublish;
    }

    /**
     * 设置是否发布
     *
     * @param isPublish 是否发布
     */
    public void setIsPublish(Byte isPublish) {
        this.isPublish = isPublish;
    }

    /**
     * 获取是否删除
     *
     * @return is_delete - 是否删除
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
     *
     * @param isDelete 是否删除
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取权限列表
     *
     * @return prem_content - 权限列表
     */
    public String getPremContent() {
        return premContent;
    }

    /**
     * 设置权限列表
     *
     * @param premContent 权限列表
     */
    public void setPremContent(String premContent) {
        this.premContent = premContent == null ? null : premContent.trim();
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent == null ? null : codeContent.trim();
    }
}