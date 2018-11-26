package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "wt_account")
public class Account extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 账号名称
     */
    private String username;

    /**
     * 账号真名
     */
    private String realname;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 是否独立配置权限
     */
    private Integer is_spec;

    /**
     * 角色ID
     */
    private Integer role_id;

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
     * 修改时间
     */
    private Date update_time;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属机构ID
     */
    private Integer org_id;

    /**
     * 子模块
     */
    private String module_content;

    /**
     * 子权限
     */
    private String permission_content;
    /**
     * 账号类型，1俱乐部； 2 代理商；3管理员
     */
    private Integer acc_type;
    /**
     * 所属编码，agno，cno，user.uuid
     */
    private String belong_no;

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
     * 获取账号名称
     *
     * @return username - 账号名称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置账号名称
     *
     * @param username 账号名称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取账号真名
     *
     * @return realname - 账号真名
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 设置账号真名
     *
     * @param realname 账号真名
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取是否独立配置权限
     *
     * @return is_spec - 是否独立配置权限
     */
    public Integer getIs_spec() {
        return is_spec;
    }

    /**
     * 设置是否独立配置权限
     *
     * @param is_spec 是否独立配置权限
     */
    public void setIs_spec(Integer is_spec) {
        this.is_spec = is_spec;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRole_id() {
        return role_id;
    }

    /**
     * 设置角色ID
     *
     * @param role_id 角色ID
     */
    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
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
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 设置修改时间
     *
     * @param update_time 修改时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
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

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取所属机构ID
     *
     * @return org_id - 所属机构ID
     */
    public Integer getOrg_id() {
        return org_id;
    }

    /**
     * 设置所属机构ID
     *
     * @param org_id 所属机构ID
     */
    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }

    /**
     * 获取子模块
     *
     * @return module_content - 子模块
     */
    public String getModule_content() {
        return module_content;
    }

    /**
     * 设置子模块
     *
     * @param module_content 子模块
     */
    public void setModule_content(String module_content) {
        this.module_content = module_content;
    }

    /**
     * 获取子权限
     *
     * @return permission_content - 子权限
     */
    public String getPermission_content() {
        return permission_content;
    }

    /**
     * 设置子权限
     *
     * @param permission_content 子权限
     */
    public void setPermission_content(String permission_content) {
        this.permission_content = permission_content;
    }

	public Integer getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(Integer acc_type) {
		this.acc_type = acc_type;
	}

	public String getBelong_no() {
		return belong_no;
	}

	public void setBelong_no(String belong_no) {
		this.belong_no = belong_no;
	}
    
    
}