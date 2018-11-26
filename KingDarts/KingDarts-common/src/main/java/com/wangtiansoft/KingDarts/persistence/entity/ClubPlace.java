package com.wangtiansoft.KingDarts.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 营业场所信息
 * @author Administrator
 *
 */
@Table(name = "darts_club_place")
public class ClubPlace {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String placeno;//场所编号
    
    private String place_name;//场所名称
    
    private String consdes;//描述信息
    
    private String cno;//俱乐部编号
    
    private String logo;//海报图片
    
    private String synopsis;//简介
    
    private String details;//详情内容
    
    private String detailurl;//详情URL
    
    private String country;//国家
    
    private String province;//省
    
    private String city;//市
    
    private String areas;//区
    
    private String address;//地址
    
    private String lnglat;//经纬度
    
    private BigDecimal longitude;//经度
    
    private BigDecimal latitude;//纬度
    
    private Date add_time;//创建时间
    
    private Date update_time;//修改时间
    
    private Integer isvalid;//是否有效(1:有效;0:无效）
    
    private String pcaptain;//负责人
    
    private String pcardid;//身份证
    
    private String pmobile;//手机号
    
    private String ptelno;//固话
    
    private String pemail;//email
    
    private String pqq;//QQ
    
    private String businesshour;//营业时间
    
    private Integer commnets;//评论数

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaceno() {
		return placeno;
	}

	public void setPlaceno(String placeno) {
		this.placeno = placeno;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	public String getConsdes() {
		return consdes;
	}

	public void setConsdes(String consdes) {
		this.consdes = consdes;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetailurl() {
		return detailurl;
	}

	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLnglat() {
		return lnglat;
	}

	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
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

	public Integer getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
	}

	public String getPcaptain() {
		return pcaptain;
	}

	public void setPcaptain(String pcaptain) {
		this.pcaptain = pcaptain;
	}

	public String getPcardid() {
		return pcardid;
	}

	public void setPcardid(String pcardid) {
		this.pcardid = pcardid;
	}

	public String getPmobile() {
		return pmobile;
	}

	public void setPmobile(String pmobile) {
		this.pmobile = pmobile;
	}

	public String getPtelno() {
		return ptelno;
	}

	public void setPtelno(String ptelno) {
		this.ptelno = ptelno;
	}

	public String getPemail() {
		return pemail;
	}

	public void setPemail(String pemail) {
		this.pemail = pemail;
	}

	public String getPqq() {
		return pqq;
	}

	public void setPqq(String pqq) {
		this.pqq = pqq;
	}

	public String getBusinesshour() {
		return businesshour;
	}

	public void setBusinesshour(String businesshour) {
		this.businesshour = businesshour;
	}

	public Integer getCommnets() {
		return commnets;
	}

	public void setCommnets(Integer commnets) {
		this.commnets = commnets;
	}
    
    
}
