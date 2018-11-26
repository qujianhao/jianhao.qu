package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "wt_org")
public class OrgEntity extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    private Integer id;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 父机构名称
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 路径
     */
    private String path;

    /**
     * 机构描述
     */
    private String summary;

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
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 排序
     */
    @Column(name = "order_num")
    private Integer orderNum;

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
     * 获取机构名称
     *
     * @return name - 机构名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置机构名称
     *
     * @param name 机构名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取父机构名称
     *
     * @return parent_id - 父机构名称
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父机构名称
     *
     * @param parentId 父机构名称
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取机构描述
     *
     * @return summary - 机构描述
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置机构描述
     *
     * @param summary 机构描述
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
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
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}