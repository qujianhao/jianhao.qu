package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_equ_line")
public class EquLine extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 设备编号
     */
    private String equno;

    private String ip_address;

    /**
     * 服务端IP
     */
    private String server_ip_address;
    
    /**
     * 是否在线，1，在线，0，离线
     */
    private Integer isline;

    /**
     * 连线时间
     */
    private Date line_time;

    /**
     * 离线时间
     */
    private Date off_time;

    /**
     * 在线时长（秒）
     */
    private Integer online_times;

    /**
     * 离线方式（1：正常；2：超时；3：重新连线；4：强制离线）
     */
    private Integer off_type;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取设备编号
     *
     * @return equno - 设备编号
     */
    public String getEquno() {
        return equno;
    }

    /**
     * 设置设备编号
     *
     * @param equno 设备编号
     */
    public void setEquno(String equno) {
        this.equno = equno;
    }

    /**
     * @return ip_address
     */
    public String getIp_address() {
        return ip_address;
    }

    /**
     * @param ip_address
     */
    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    /**
     * 获取服务端IP
     *
     * @return server_ip_address - 服务端IP
     */
    public String getServer_ip_address() {
        return server_ip_address;
    }

    /**
     * 设置服务端IP
     *
     * @param server_ip_address 服务端IP
     */
    public void setServer_ip_address(String server_ip_address) {
        this.server_ip_address = server_ip_address;
    }

    /**
     * 获取连线时间
     *
     * @return line_time - 连线时间
     */
    public Date getLine_time() {
        return line_time;
    }

    /**
     * 设置连线时间
     *
     * @param line_time 连线时间
     */
    public void setLine_time(Date line_time) {
        this.line_time = line_time;
    }

    /**
     * 获取离线时间
     *
     * @return off_time - 离线时间
     */
    public Date getOff_time() {
        return off_time;
    }

    /**
     * 设置离线时间
     *
     * @param off_time 离线时间
     */
    public void setOff_time(Date off_time) {
        this.off_time = off_time;
    }

    /**
     * 获取在线时长（秒）
     *
     * @return online_times - 在线时长（秒）
     */
    public Integer getOnline_times() {
        return online_times;
    }

    /**
     * 设置在线时长（秒）
     *
     * @param online_times 在线时长（秒）
     */
    public void setOnline_times(Integer online_times) {
        this.online_times = online_times;
    }

    /**
     * 获取离线方式（1：正常；2：超时；3：重新连线；4：强制离线）
     *
     * @return off_type - 离线方式（1：正常；2：超时；3：重新连线；4：强制离线）
     */
    public Integer getOff_type() {
        return off_type;
    }

    /**
     * 设置离线方式（1：正常；2：超时；3：重新连线；4：强制离线）
     *
     * @param off_type 离线方式（1：正常；2：超时；3：重新连线；4：强制离线）
     */
    public void setOff_type(Integer off_type) {
        this.off_type = off_type;
    }

	public Integer getIsline() {
		return isline;
	}

	public void setIsline(Integer isline) {
		this.isline = isline;
	}
    
}