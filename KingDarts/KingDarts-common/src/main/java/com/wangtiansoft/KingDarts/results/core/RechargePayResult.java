package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class RechargePayResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String rechargeno;   //  充值编号（订单号）
    private String money;   //  金额
    private String give_money;   //  赠送金额
    private String remark;   //  备注（打折等等）
    private String pay_way;   //  支付方式（1:微信）
    private String recharge_type;   //  充值账户类型（1个人,2俱乐部,3代理商）
    private String rechargeaccount;   //  充值账户
    private String resum;   //  账户余额
    private String user_id;   //  支付人员
    private String pay_status;   //  支付状态（0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败）
    private String isvalid;   //  (1:有效;0:无效）

    public RechargePayResult() {
    }

	public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRechargeno() {
        return this.rechargeno;
    }
    public void setRechargeno(String rechargeno) {
        this.rechargeno = rechargeno;
    }
    public String getMoney() {
        return this.money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public String getGive_money() {
        return this.give_money;
    }
    public void setGive_money(String give_money) {
        this.give_money = give_money;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getPay_way() {
        return this.pay_way;
    }
    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }
    public String getRecharge_type() {
        return this.recharge_type;
    }
    public void setRecharge_type(String recharge_type) {
        this.recharge_type = recharge_type;
    }
    public String getRechargeaccount() {
        return this.rechargeaccount;
    }
    public void setRechargeaccount(String rechargeaccount) {
        this.rechargeaccount = rechargeaccount;
    }
    public String getResum() {
        return this.resum;
    }
    public void setResum(String resum) {
        this.resum = resum;
    }
    public String getUser_id() {
        return this.user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getPay_status() {
        return this.pay_status;
    }
    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }
    public String getIsvalid() {
        return this.isvalid;
    }
    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }
}
