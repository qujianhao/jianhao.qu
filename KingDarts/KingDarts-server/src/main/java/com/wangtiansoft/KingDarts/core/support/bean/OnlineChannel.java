package com.wangtiansoft.KingDarts.core.support.bean;

import java.io.Serializable;
import java.util.Date;

public class OnlineChannel implements Serializable{

	private static final long serialVersionUID = -1462346050948861198L;
	private String equno;//设备编码
	private String clientId;//客户端ID
	private String ipAddress;//客户端IP
	private String serverIpAddress;//服务端IP
	private Date lineTime;//登录时间
	private Date heartTime;//心跳时间
	private String token;//http接口token
	public String getEquno() {
		return equno;
	}
	public void setEquno(String equno) {
		this.equno = equno;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getServerIpAddress() {
		return serverIpAddress;
	}
	public void setServerIpAddress(String serverIpAddress) {
		this.serverIpAddress = serverIpAddress;
	}
	public Date getLineTime() {
		return lineTime;
	}
	public void setLineTime(Date lineTime) {
		this.lineTime = lineTime;
	}
	public Date getHeartTime() {
		return heartTime;
	}
	public void setHeartTime(Date heartTime) {
		this.heartTime = heartTime;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
