package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class EquAuthResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String equno;   //  设备编号
    private Integer auth_acc;   //  授权人（account.id）
    private Integer auth_type;   //  授权人类别（1公司;2代理商）
    private Integer merchant;   //  商户类型（1俱乐部 2 代理商）
    private String auth_no;   //  授权对象(agno,cno)
    private String auth_name;   //  授权对象名称
    private String placeno;   //  俱乐部场所编号
    private Integer acc_type;   //  结算类型 (1：设备 )
    private Integer cooperation;   //  合作方式，1出售，2租赁
    private String rent;   //  租金
    private String profit_ratio;   //  分成比例
    
    private Integer equ_status;//设备状态

    public EquAuthResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getEquno() {
        return this.equno;
    }
    public void setEquno(String equno) {
        this.equno = equno;
    }
    public Integer getAuth_acc() {
        return this.auth_acc;
    }
    public void setAuth_acc(Integer auth_acc) {
        this.auth_acc = auth_acc;
    }
    public Integer getAuth_type() {
        return this.auth_type;
    }
    public void setAuth_type(Integer auth_type) {
        this.auth_type = auth_type;
    }
    public Integer getMerchant() {
        return this.merchant;
    }
    public void setMerchant(Integer merchant) {
        this.merchant = merchant;
    }
    public String getAuth_no() {
        return this.auth_no;
    }
    public void setAuth_no(String auth_no) {
        this.auth_no = auth_no;
    }
    public String getAuth_name() {
        return this.auth_name;
    }
    public void setAuth_name(String auth_name) {
        this.auth_name = auth_name;
    }
    public String getPlaceno() {
        return this.placeno;
    }
    public void setPlaceno(String placeno) {
        this.placeno = placeno;
    }
    public Integer getAcc_type() {
        return this.acc_type;
    }
    public void setAcc_type(Integer acc_type) {
        this.acc_type = acc_type;
    }
    public Integer getCooperation() {
        return this.cooperation;
    }
    public void setCooperation(Integer cooperation) {
        this.cooperation = cooperation;
    }
    public String getRent() {
        return this.rent;
    }
    public void setRent(String rent) {
        this.rent = rent;
    }
    public String getProfit_ratio() {
        return this.profit_ratio;
    }
    public void setProfit_ratio(String profit_ratio) {
        this.profit_ratio = profit_ratio;
    }

	public Integer getEqu_status() {
		return equ_status;
	}

	public void setEqu_status(Integer equ_status) {
		this.equ_status = equ_status;
	}
    
}
