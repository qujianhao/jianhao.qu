package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class AccountResult extends BaseResult implements Serializable{
    private Integer id;   //  主键ID
    private String username;   //  账号名称
    private String realname;   //  账号真名
    private String password;   //  密码
    private String mobile;   //  手机号码
    private Integer is_spec;   //  是否独立配置权限
    private String code_content;   //  子权限列表
    private String prem_content;   //  权限列表
    private Integer role_id;   //  角色ID
    private Integer order_num;   //  排序
    private Integer is_publish;   //  是否发布
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;   //  创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;   //  修改时间
    private String remark;   //  备注
    private Integer org_id;   //  所属机构ID

    public AccountResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRealname() {
        return this.realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Integer getIs_spec() {
        return this.is_spec;
    }
    public void setIs_spec(Integer is_spec) {
        this.is_spec = is_spec;
    }
    public String getCode_content() {
        return this.code_content;
    }
    public void setCode_content(String code_content) {
        this.code_content = code_content;
    }
    public String getPrem_content() {
        return this.prem_content;
    }
    public void setPrem_content(String prem_content) {
        this.prem_content = prem_content;
    }
    public Integer getRole_id() {
        return this.role_id;
    }
    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
    public Integer getOrder_num() {
        return this.order_num;
    }
    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
    }
    public Integer getIs_publish() {
        return this.is_publish;
    }
    public void setIs_publish(Integer is_publish) {
        this.is_publish = is_publish;
    }
    public Date getUpdate_time() {
        return this.update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getOrg_id() {
        return this.org_id;
    }
    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
    
}
