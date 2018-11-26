package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import javax.persistence.*;

@Table(name = "wt_dict")
public class Dict extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 所属模块
     */
    private String module_key;

    /**
     * 所属组
     */
    private String group_key;

    /**
     * 字典key
     */
    private String dict_key;

    /**
     * 字典值
     */
    private String dict_value;

    /**
     * 是否删除 0/未删除,1/删除
     */
    private Integer is_delete;

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
     * 获取所属模块
     *
     * @return module_key - 所属模块
     */
    public String getModule_key() {
        return module_key;
    }

    /**
     * 设置所属模块
     *
     * @param module_key 所属模块
     */
    public void setModule_key(String module_key) {
        this.module_key = module_key;
    }

    /**
     * 获取所属组
     *
     * @return group_key - 所属组
     */
    public String getGroup_key() {
        return group_key;
    }

    /**
     * 设置所属组
     *
     * @param group_key 所属组
     */
    public void setGroup_key(String group_key) {
        this.group_key = group_key;
    }

    /**
     * 获取字典key
     *
     * @return dict_key - 字典key
     */
    public String getDict_key() {
        return dict_key;
    }

    /**
     * 设置字典key
     *
     * @param dict_key 字典key
     */
    public void setDict_key(String dict_key) {
        this.dict_key = dict_key;
    }

    /**
     * 获取字典值
     *
     * @return dict_value - 字典值
     */
    public String getDict_value() {
        return dict_value;
    }

    /**
     * 设置字典值
     *
     * @param dict_value 字典值
     */
    public void setDict_value(String dict_value) {
        this.dict_value = dict_value;
    }

    /**
     * 获取是否删除 0/未删除,1/删除
     *
     * @return is_delete - 是否删除 0/未删除,1/删除
     */
    public Integer getIs_delete() {
        return is_delete;
    }

    /**
     * 设置是否删除 0/未删除,1/删除
     *
     * @param is_delete 是否删除 0/未删除,1/删除
     */
    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }
}