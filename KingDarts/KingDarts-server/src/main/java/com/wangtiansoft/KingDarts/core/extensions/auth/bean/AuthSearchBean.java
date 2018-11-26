package com.wangtiansoft.KingDarts.core.extensions.auth.bean;

import java.io.Serializable;

/**
 * Created by weitong on 17/3/21.
 */
public class AuthSearchBean implements Serializable{

    private String id;
    private String name;
    private String code;
    private String url;
    private String parent;
    private String isPublish;
    private String isDelete;
    private Integer orderNum;
    private String username;
    private String realname;
    private String startTime;
    private String endTime;
    private String mobile;
    private String remark;
    private Integer userId;
    private String roleId;
    private String[] subperm_codes;
    private String[] perm_codes;

    private Integer isSpec;
    private String password;
    private String password_confirm;
    private String summary;

    public AuthSearchBean() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String[] getSubperm_codes() {
        return subperm_codes;
    }

    public void setSubperm_codes(String[] subperm_codes) {
        this.subperm_codes = subperm_codes;
    }

    public String[] getPerm_codes() {
        return perm_codes;
    }

    public void setPerm_codes(String[] perm_codes) {
        this.perm_codes = perm_codes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsSpec() {
        return isSpec;
    }

    public void setIsSpec(Integer isSpec) {
        this.isSpec = isSpec;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
