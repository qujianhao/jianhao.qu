package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class EquLineResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String equno;   //  设备编号
    private String ip_address;   //  客户端ip
    private String server_ip_address;   //  服务端IP
    private Integer isline;   //  是否在线，1，在线，0，离线
    private Integer off_type;   //  离线方式（1：正常；2：超时；3：重新连线；4：强制离线）

    public EquLineResult() {
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
    public String getIp_address() {
        return this.ip_address;
    }
    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
    public String getServer_ip_address() {
        return this.server_ip_address;
    }
    public void setServer_ip_address(String server_ip_address) {
        this.server_ip_address = server_ip_address;
    }
    public Integer getIsline() {
        return this.isline;
    }
    public void setIsline(Integer isline) {
        this.isline = isline;
    }
    public Integer getOff_type() {
        return this.off_type;
    }
    public void setOff_type(Integer off_type) {
        this.off_type = off_type;
    }
}
