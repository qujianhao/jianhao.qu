package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_place")
public class Place extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 场所编号
     */
    private String placeno;

    /**
     * 场所名称
     */
    private String place_name;

    /**
     * 描述信息
     */
    private String consdes;

    /**
     * 俱乐部编号
     */
    private String cno;

    /**
     * 海报图片
     */
    private String logo;

    /**
     * 简介
     */
    private String synopsis;

    /**
     * 详情URL
     */
    private String detailurl;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String areas;

    /**
     * 地址
     */
    private String address;

    /**
     * 经纬度
     */
    private String lnglat;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 维度
     */
    private BigDecimal latitude;

    /**
     * 创建时间
     */
    private Date add_time;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 是否有效
     */
    private Boolean isvalid;

    /**
     * 负责人
     */
    private String pcaptain;

    /**
     * 身份证
     */
    private String pcardid;

    /**
     * 手机号
     */
    private String pmobile;

    /**
     * 固话
     */
    private String ptelno;

    /**
     * email
     */
    private String pemail;

    /**
     * QQ
     */
    private String pqq;

    /**
     * 营业时间
     */
    private String businesshour;

    /**
     * 评论数
     */
    private Integer commnets;

    /**
     * 详情内容
     */
    private String details;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取场所编号
     *
     * @return placeno - 场所编号
     */
    public String getPlaceno() {
        return placeno;
    }

    /**
     * 设置场所编号
     *
     * @param placeno 场所编号
     */
    public void setPlaceno(String placeno) {
        this.placeno = placeno;
    }

    /**
     * 获取场所名称
     *
     * @return place_name - 场所名称
     */
    public String getPlace_name() {
        return place_name;
    }

    /**
     * 设置场所名称
     *
     * @param place_name 场所名称
     */
    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    /**
     * 获取描述信息
     *
     * @return consdes - 描述信息
     */
    public String getConsdes() {
        return consdes;
    }

    /**
     * 设置描述信息
     *
     * @param consdes 描述信息
     */
    public void setConsdes(String consdes) {
        this.consdes = consdes;
    }

    /**
     * 获取俱乐部编号
     *
     * @return cno - 俱乐部编号
     */
    public String getCno() {
        return cno;
    }

    /**
     * 设置俱乐部编号
     *
     * @param cno 俱乐部编号
     */
    public void setCno(String cno) {
        this.cno = cno;
    }

    /**
     * 获取海报图片
     *
     * @return logo - 海报图片
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 设置海报图片
     *
     * @param logo 海报图片
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 获取简介
     *
     * @return synopsis - 简介
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * 设置简介
     *
     * @param synopsis 简介
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * 获取详情URL
     *
     * @return detailurl - 详情URL
     */
    public String getDetailurl() {
        return detailurl;
    }

    /**
     * 设置详情URL
     *
     * @param detailurl 详情URL
     */
    public void setDetailurl(String detailurl) {
        this.detailurl = detailurl;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return areas - 区
     */
    public String getAreas() {
        return areas;
    }

    /**
     * 设置区
     *
     * @param areas 区
     */
    public void setAreas(String areas) {
        this.areas = areas;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取经纬度
     *
     * @return lnglat - 经纬度
     */
    public String getLnglat() {
        return lnglat;
    }

    /**
     * 设置经纬度
     *
     * @param lnglat 经纬度
     */
    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取维度
     *
     * @return latitude - 维度
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * 设置维度
     *
     * @param latitude 维度
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取创建时间
     *
     * @return add_time - 创建时间
     */
    public Date getAdd_time() {
        return add_time;
    }

    /**
     * 设置创建时间
     *
     * @param add_time 创建时间
     */
    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
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
     * 获取是否有效
     *
     * @return isvalid - 是否有效
     */
    public Boolean getIsvalid() {
        return isvalid;
    }

    /**
     * 设置是否有效
     *
     * @param isvalid 是否有效
     */
    public void setIsvalid(Boolean isvalid) {
        this.isvalid = isvalid;
    }

    /**
     * 获取负责人
     *
     * @return pcaptain - 负责人
     */
    public String getPcaptain() {
        return pcaptain;
    }

    /**
     * 设置负责人
     *
     * @param pcaptain 负责人
     */
    public void setPcaptain(String pcaptain) {
        this.pcaptain = pcaptain;
    }

    /**
     * 获取身份证
     *
     * @return pcardid - 身份证
     */
    public String getPcardid() {
        return pcardid;
    }

    /**
     * 设置身份证
     *
     * @param pcardid 身份证
     */
    public void setPcardid(String pcardid) {
        this.pcardid = pcardid;
    }

    /**
     * 获取手机号
     *
     * @return pmobile - 手机号
     */
    public String getPmobile() {
        return pmobile;
    }

    /**
     * 设置手机号
     *
     * @param pmobile 手机号
     */
    public void setPmobile(String pmobile) {
        this.pmobile = pmobile;
    }

    /**
     * 获取固话
     *
     * @return ptelno - 固话
     */
    public String getPtelno() {
        return ptelno;
    }

    /**
     * 设置固话
     *
     * @param ptelno 固话
     */
    public void setPtelno(String ptelno) {
        this.ptelno = ptelno;
    }

    /**
     * 获取email
     *
     * @return pemail - email
     */
    public String getPemail() {
        return pemail;
    }

    /**
     * 设置email
     *
     * @param pemail email
     */
    public void setPemail(String pemail) {
        this.pemail = pemail;
    }

    /**
     * 获取QQ
     *
     * @return pqq - QQ
     */
    public String getPqq() {
        return pqq;
    }

    /**
     * 设置QQ
     *
     * @param pqq QQ
     */
    public void setPqq(String pqq) {
        this.pqq = pqq;
    }

    /**
     * 获取营业时间
     *
     * @return businesshour - 营业时间
     */
    public String getBusinesshour() {
        return businesshour;
    }

    /**
     * 设置营业时间
     *
     * @param businesshour 营业时间
     */
    public void setBusinesshour(String businesshour) {
        this.businesshour = businesshour;
    }

    /**
     * 获取评论数
     *
     * @return commnets - 评论数
     */
    public Integer getCommnets() {
        return commnets;
    }

    /**
     * 设置评论数
     *
     * @param commnets 评论数
     */
    public void setCommnets(Integer commnets) {
        this.commnets = commnets;
    }

    /**
     * 获取详情内容
     *
     * @return details - 详情内容
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置详情内容
     *
     * @param details 详情内容
     */
    public void setDetails(String details) {
        this.details = details;
    }
}