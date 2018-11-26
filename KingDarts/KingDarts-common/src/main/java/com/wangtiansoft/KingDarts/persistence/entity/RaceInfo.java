package com.wangtiansoft.KingDarts.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "darts_race_info")
public class RaceInfo {

	/**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * 赛事编号
     */
    private String raceno;
    
    /**
     * 赛事名称
     */
    private String racename;
    
    /**
     * 举办单位（俱乐部编号）
     */
    private String cno;
    
    /**
     * 举办单位（俱乐部名称）
     */
    private String cname;
    
    /**
     * 最低人数
     */
    private Integer minimum_num;
    
    /**
     * 比赛开始时间
     */
    private Date racestart;
    
    /**
     * 比赛结束时间
     */
    private Date raceend;
    
    /**
     * 报名开始时间
     */
    private Date regstart;
    
    /**
     * 报名结束时间
     */
    private Date regend;
    
    /**
     * 比赛地点
     */
    private String raceplace;
    
    /**
     * 赛事海报URL
     */
    private String raceurl;
    
    /**
     * 赛事信息
     */
    private String consdes;
    
    /**
     * 赛事状态（0.待报名,1.报名中，2进行中，3已结束，4已取消，5已解散）
     */
    private Integer dstatus;
    
    /**
     * 是否有效（1：有效，0：无效）
     */
    private Integer isvalid;
    
    /**
     * 创建时间
     */
    private Date add_time;
    
    /**
     * 修改时间
     */
    private Date update_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRaceno() {
		return raceno;
	}

	public void setRaceno(String raceno) {
		this.raceno = raceno;
	}

	public String getRacename() {
		return racename;
	}

	public void setRacename(String racename) {
		this.racename = racename;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getMinimum_num() {
		return minimum_num;
	}

	public void setMinimum_num(Integer minimum_num) {
		this.minimum_num = minimum_num;
	}

	public Date getRacestart() {
		return racestart;
	}

	public void setRacestart(Date racestart) {
		this.racestart = racestart;
	}

	public Date getRaceend() {
		return raceend;
	}

	public void setRaceend(Date raceend) {
		this.raceend = raceend;
	}

	public Date getRegstart() {
		return regstart;
	}

	public void setRegstart(Date regstart) {
		this.regstart = regstart;
	}

	public Date getRegend() {
		return regend;
	}

	public void setRegend(Date regend) {
		this.regend = regend;
	}

	public String getRaceplace() {
		return raceplace;
	}

	public void setRaceplace(String raceplace) {
		this.raceplace = raceplace;
	}

	public String getRaceurl() {
		return raceurl;
	}

	public void setRaceurl(String raceurl) {
		this.raceurl = raceurl;
	}

	public String getConsdes() {
		return consdes;
	}

	public void setConsdes(String consdes) {
		this.consdes = consdes;
	}

	public Integer getDstatus() {
		return dstatus;
	}

	public void setDstatus(Integer dstatus) {
		this.dstatus = dstatus;
	}

	public Integer getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
    
    
}
