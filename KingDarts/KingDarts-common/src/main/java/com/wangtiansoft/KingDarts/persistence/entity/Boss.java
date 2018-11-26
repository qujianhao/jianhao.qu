package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;

import java.util.Date;

import javax.persistence.*;

@Table(name = "darts_boss")
public class Boss extends BaseEntity {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * boss编号
     */
    private String bno;

    /**
     * boss名称
     */
    private String bname;

    /**
     * boss血量
     */
    private Long bvolume;

    /**
     * 是否应用 0：否 1:是
     */
    private Integer is_use;

    /**
     * 是否删除 0 否 1 是
     */
    private Integer is_delete;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取boss编号
     *
     * @return bno - boss编号
     */
    public String getBno() {
        return bno;
    }

    /**
     * 设置boss编号
     *
     * @param bno boss编号
     */
    public void setBno(String bno) {
        this.bno = bno;
    }

    /**
     * 获取boss名称
     *
     * @return bname - boss名称
     */
    public String getBname() {
        return bname;
    }

    /**
     * 设置boss名称
     *
     * @param bname boss名称
     */
    public void setBname(String bname) {
        this.bname = bname;
    }

    /**
     * 获取boss血量
     *
     * @return bvolume - boss血量
     */
    public Long getBvolume() {
        return bvolume;
    }

    /**
     * 设置boss血量
     *
     * @param bvolume boss血量
     */
    public void setBvolume(Long bvolume) {
        this.bvolume = bvolume;
    }

    /**
     * 获取是否应用 0：否 1:是
     *
     * @return is_use - 是否应用 0：否 1:是
     */
    public Integer getIs_use() {
        return is_use;
    }

    /**
     * 设置是否应用 0：否 1:是
     *
     * @param is_use 是否应用 0：否 1:是
     */
    public void setIs_use(Integer is_use) {
        this.is_use = is_use;
    }

    /**
     * 获取是否删除 0 否 1 是
     *
     * @return is_delete - 是否删除 0 否 1 是
     */
    public Integer getIs_delete() {
        return is_delete;
    }

    /**
     * 设置是否删除 0 否 1 是
     *
     * @param is_delete 是否删除 0 否 1 是
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