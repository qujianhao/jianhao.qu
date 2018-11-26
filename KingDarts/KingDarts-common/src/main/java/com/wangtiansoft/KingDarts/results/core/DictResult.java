package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class DictResult extends BaseResult implements Serializable{

    private Integer id;   //  主键ID
    private String module_key;   //  所属模块
    private String group_key;   //  所属组
    private String dict_key;   //  字典key
    private String dict_value;   //  字典值

    public DictResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getModule_key() {
        return this.module_key;
    }
    public void setModule_key(String module_key) {
        this.module_key = module_key;
    }
    public String getGroup_key() {
        return this.group_key;
    }
    public void setGroup_key(String group_key) {
        this.group_key = group_key;
    }
    public String getDict_key() {
        return this.dict_key;
    }
    public void setDict_key(String dict_key) {
        this.dict_key = dict_key;
    }
    public String getDict_value() {
        return this.dict_value;
    }
    public void setDict_value(String dict_value) {
        this.dict_value = dict_value;
    }
}
