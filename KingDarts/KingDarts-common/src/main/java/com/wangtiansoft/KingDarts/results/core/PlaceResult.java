package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class PlaceResult extends BaseResult implements Serializable{

    private String id;   //  主键
    private String placeno;   //  场所编号
    private String place_name;   //  场所名称
    private String consdes;   //  描述信息
    private String cno;   //  俱乐部编号
    private String logo;   //  海报图片
    private String synopsis;   //  简介
    private String details;   //  详情内容
    private String detailurl;   //  详情URL
    private String country;   //  国家
    private String province;   //  省
    private String city;   //  市
    private String areas;   //  区
    private String address;   //  地址
    private String lnglat;   //  经纬度
    private String longitude;   //  经度
    private String latitude;   //  维度
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;   //  修改时间
    private String isvalid;   //  是否有效
    private String pcaptain;   //  负责人
    private String pcardid;   //  身份证
    private String pmobile;   //  手机号
    private String ptelno;   //  固话
    private String pemail;   //  email
    private String pqq;   //  QQ
    private String businesshour;   //  营业时间
    private Integer commnets;   //  评论数

    public PlaceResult() {
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPlaceno() {
        return this.placeno;
    }
    public void setPlaceno(String placeno) {
        this.placeno = placeno;
    }
    public String getPlace_name() {
        return this.place_name;
    }
    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }
    public String getConsdes() {
        return this.consdes;
    }
    public void setConsdes(String consdes) {
        this.consdes = consdes;
    }
    public String getCno() {
        return this.cno;
    }
    public void setCno(String cno) {
        this.cno = cno;
    }
    public String getLogo() {
        return this.logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getSynopsis() {
        return this.synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public String getDetails() {
        return this.details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public String getDetailurl() {
        return this.detailurl;
    }
    public void setDetailurl(String detailurl) {
        this.detailurl = detailurl;
    }
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getProvince() {
        return this.province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAreas() {
        return this.areas;
    }
    public void setAreas(String areas) {
        this.areas = areas;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getLnglat() {
        return this.lnglat;
    }
    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
    }
    public String getLongitude() {
        return this.longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLatitude() {
        return this.latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public Date getUpdate_time() {
        return this.update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public String getIsvalid() {
        return this.isvalid;
    }
    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }
    public String getPcaptain() {
        return this.pcaptain;
    }
    public void setPcaptain(String pcaptain) {
        this.pcaptain = pcaptain;
    }
    public String getPcardid() {
        return this.pcardid;
    }
    public void setPcardid(String pcardid) {
        this.pcardid = pcardid;
    }
    public String getPmobile() {
        return this.pmobile;
    }
    public void setPmobile(String pmobile) {
        this.pmobile = pmobile;
    }
    public String getPtelno() {
        return this.ptelno;
    }
    public void setPtelno(String ptelno) {
        this.ptelno = ptelno;
    }
    public String getPemail() {
        return this.pemail;
    }
    public void setPemail(String pemail) {
        this.pemail = pemail;
    }
    public String getPqq() {
        return this.pqq;
    }
    public void setPqq(String pqq) {
        this.pqq = pqq;
    }
    public String getBusinesshour() {
        return this.businesshour;
    }
    public void setBusinesshour(String businesshour) {
        this.businesshour = businesshour;
    }
    public Integer getCommnets() {
        return this.commnets;
    }
    public void setCommnets(Integer commnets) {
        this.commnets = commnets;
    }
}
