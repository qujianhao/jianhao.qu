package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "wt_organization")
public class Organization extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机构名称
     */
    private String organization_name;

    /**
     * 父机构名称
     */
    private Integer organization_parent_id;

    /**
     * 父机构路径
     */
    private String organization_path;

    /**
     * 机构描述
     */
    private String organization_summary;

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
     * 创建时间
     */
    private Date create_time;

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
     * @return organization_name - 机构名称
     */
    public String getOrganization_name() {
        return organization_name;
    }

    /**
     * 设置机构名称
     *
     * @param organization_name 机构名称
     */
    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    /**
     * 获取父机构名称
     *
     * @return organization_parent_id - 父机构名称
     */
    public Integer getOrganization_parent_id() {
        return organization_parent_id;
    }

    /**
     * 设置父机构名称
     *
     * @param organization_parent_id 父机构名称
     */
    public void setOrganization_parent_id(Integer organization_parent_id) {
        this.organization_parent_id = organization_parent_id;
    }

    /**
     * 获取父机构路径
     *
     * @return organization_path - 父机构路径
     */
    public String getOrganization_path() {
        return organization_path;
    }

    /**
     * 设置父机构路径
     *
     * @param organization_path 父机构路径
     */
    public void setOrganization_path(String organization_path) {
        this.organization_path = organization_path;
    }

    /**
     * 获取机构描述
     *
     * @return organization_summary - 机构描述
     */
    public String getOrganization_summary() {
        return organization_summary;
    }

    /**
     * 设置机构描述
     *
     * @param organization_summary 机构描述
     */
    public void setOrganization_summary(String organization_summary) {
        this.organization_summary = organization_summary;
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
}