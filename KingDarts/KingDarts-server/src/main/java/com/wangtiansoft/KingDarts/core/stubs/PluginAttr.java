package com.wangtiansoft.KingDarts.core.stubs;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/21 0021.
 */
public class PluginAttr implements Serializable {

    private String name;    //  属性名称
    private String key;    //  属性键
    private String value;    //  属性值
    private String summary;    //  描述

    public PluginAttr() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public PluginAttr(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public PluginAttr(String name, String key, String value) {
        this.name = name;
        this.key = key;
        this.value = value;
    }


    public PluginAttr(String name, String key, String value, String summary) {
        this.name = name;
        this.key = key;
        this.value = value;
        this.summary = summary;
    }
}
