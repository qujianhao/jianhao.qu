package com.wangtiansoft.KingDarts.common.bean;

import com.wangtiansoft.KingDarts.persistence.entity.PermEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weitong on 17/3/15.
 */
public class PermBean implements Serializable{

    private Integer id;
    private String name;
    private String icon;
    private String url;
    private String parent;
    private String code;
    private List<PermEntity> subList;

    public PermBean() {
        subList = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<PermEntity> getSubList() {
        return subList;
    }

    public void setSubList(List<PermEntity> subList) {
        this.subList = subList;
    }
}
