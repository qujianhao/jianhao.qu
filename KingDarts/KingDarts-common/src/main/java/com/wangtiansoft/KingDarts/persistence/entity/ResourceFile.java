package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import javax.persistence.*;

@Table(name = "wt_resource_file")
public class ResourceFile extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文件标示
     */
    private String uuid;

    /**
     * 文件名
     */
    private String url;

    /**
     * 文件MD5
     */
    private String md5;

    /**
     * 文件大小
     */
    private Long lenght;

    /**
     * 文件名称
     */
    private String name;

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
     * 获取文件标示
     *
     * @return uuid - 文件标示
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置文件标示
     *
     * @param uuid 文件标示
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取文件名
     *
     * @return url - 文件名
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置文件名
     *
     * @param url 文件名
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取文件MD5
     *
     * @return md5 - 文件MD5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * 设置文件MD5
     *
     * @param md5 文件MD5
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * 获取文件大小
     *
     * @return lenght - 文件大小
     */
    public Long getLenght() {
        return lenght;
    }

    /**
     * 设置文件大小
     *
     * @param lenght 文件大小
     */
    public void setLenght(Long lenght) {
        this.lenght = lenght;
    }

    /**
     * 获取文件名称
     *
     * @return name - 文件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文件名称
     *
     * @param name 文件名称
     */
    public void setName(String name) {
        this.name = name;
    }
}