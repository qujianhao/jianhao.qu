package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wt-templete-helper on unknow.
 */
public class UserResult extends BaseResult implements Serializable{

    private Integer id;   //  主键ID
    private String uuid;   //  业务UUID
    private String username;   //  用户名
    private String password;   //  密码
    private String nickname;   //  昵称
    private Integer gender;   //  性别 0/默认,1/男,2/女
    private String id_no;//身份证号码
    private String areas;//县 区
    private String city;//城市
    private String province;//省份
    private String country;//国家
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;   //  出生日期
    private String mobile;   //  手机号码
    private String email;   //  电子邮箱
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;   //  更新时间
    private Integer is_publish;   //  是否发布 0/未发布,1/发布
    private BigDecimal amount;   //  总金额
    private BigDecimal balance;   //  余额
    private BigDecimal give_balance;   //  赠送金额（余额）
    private BigDecimal coupon_balance;//使用优惠券后的金额（余额）
    private BigDecimal frozen;   //  冻结金额
    private Integer points;   //  用户积分
    private String unionid;   //  微信联合ID
    private String headimgurl;   //  头像地址
    private Integer version;   //  版本，用于乐观锁
    
    private List<UserOpenidResult> openids;

    public UserResult() {
    }

    
    public BigDecimal getCoupon_balance() {
		return coupon_balance;
	}


	public void setCoupon_balance(BigDecimal coupon_balance) {
		this.coupon_balance = coupon_balance;
	}


	public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUuid() {
        return this.uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Integer getGender() {
        return this.gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getUpdate_time() {
        return this.update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public Integer getIs_publish() {
        return this.is_publish;
    }
    public void setIs_publish(Integer is_publish) {
        this.is_publish = is_publish;
    }
    
    public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getGive_balance() {
		return give_balance;
	}

	public void setGive_balance(BigDecimal give_balance) {
		this.give_balance = give_balance;
	}

	public BigDecimal getFrozen() {
		return frozen;
	}

	public void setFrozen(BigDecimal frozen) {
		this.frozen = frozen;
	}

	public Integer getPoints() {
        return this.points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }
    public String getUnionid() {
        return this.unionid;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
    public String getHeadimgurl() {
        return this.headimgurl;
    }
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
    public Integer getVersion() {
        return this.version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }

	public List<UserOpenidResult> getOpenids() {
		return openids;
	}
	public void setOpenids(List<UserOpenidResult> openids) {
		this.openids = openids;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}
    
}
