package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "wt_user")
public class User extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 业务UUID
     */
    private String uuid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别 0/默认,1/男,2/女
     */
    private Integer gender;
    
    /**
     * 身份证号码
     */
    private String id_no;
    
    /**
     * 县 区
     */
    private String areas;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 国家
     */
    private String country;
    
    /**
     * 详细地址
     */
    private String address;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * 是否发布 0/未发布,1/发布
     */
    private Integer is_publish;

    /**
     * 是否删除 0/未删除,1/删除
     */
    private Integer is_delete;

    /**
     * 总金额
     */
    private BigDecimal amount;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 赠送金额（余额）
     */
    private BigDecimal give_balance;
    
    /**
     * 使用优惠券后的金额（余额）
     */
    private BigDecimal coupon_balance;

    /**
     * 冻结金额
     */
    private BigDecimal frozen;

    /**
     * 用户积分
     */
    private Integer points;

    /**
     * 微信联合ID
     */
    private String unionid;

    /**
     * 头像地址
     */
    private String headimgurl;

    /**
     * 版本，用于乐观锁
     */
    private Integer version;

    //视频上一次时间

    private Date video_time;

    public Date getVideo_time() {
        return video_time;
    }

    public void setVideo_time(Date video_time) {
        this.video_time = video_time;
    }

    public BigDecimal getCoupon_balance() {
		return coupon_balance;
	}

	public void setCoupon_balance(BigDecimal coupon_balance) {
		this.coupon_balance = coupon_balance;
	}

	/**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取业务UUID
     *
     * @return uuid - 业务UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置业务UUID
     *
     * @param uuid 业务UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取性别 0/默认,1/男,2/女
     *
     * @return gender - 性别 0/默认,1/男,2/女
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置性别 0/默认,1/男,2/女
     *
     * @param gender 性别 0/默认,1/男,2/女
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取出生日期
     *
     * @return birthday - 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取电子邮箱
     *
     * @return email - 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮箱
     *
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 设置更新时间
     *
     * @param update_time 更新时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    /**
     * 获取是否发布 0/未发布,1/发布
     *
     * @return is_publish - 是否发布 0/未发布,1/发布
     */
    public Integer getIs_publish() {
        return is_publish;
    }

    /**
     * 设置是否发布 0/未发布,1/发布
     *
     * @param is_publish 是否发布 0/未发布,1/发布
     */
    public void setIs_publish(Integer is_publish) {
        this.is_publish = is_publish;
    }

    /**
     * 获取是否删除 0/未删除,1/删除
     *
     * @return is_delete - 是否删除 0/未删除,1/删除
     */
    public Integer getIs_delete() {
        return is_delete;
    }

    /**
     * 设置是否删除 0/未删除,1/删除
     *
     * @param is_delete 是否删除 0/未删除,1/删除
     */
    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    /**
     * 获取总金额
     *
     * @return amount - 总金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置总金额
     *
     * @param amount 总金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取余额
     *
     * @return balance - 余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置余额
     *
     * @param balance 余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 获取赠送金额（余额）
     *
     * @return give_balance - 赠送金额（余额）
     */
    public BigDecimal getGive_balance() {
        return give_balance;
    }

    /**
     * 设置赠送金额（余额）
     *
     * @param give_balance 赠送金额（余额）
     */
    public void setGive_balance(BigDecimal give_balance) {
        this.give_balance = give_balance;
    }

    /**
     * 获取冻结金额
     *
     * @return frozen - 冻结金额
     */
    public BigDecimal getFrozen() {
        return frozen;
    }

    /**
     * 设置冻结金额
     *
     * @param frozen 冻结金额
     */
    public void setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
    }

    /**
     * 获取用户积分
     *
     * @return points - 用户积分
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * 设置用户积分
     *
     * @param points 用户积分
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * 获取微信联合ID
     *
     * @return unionid - 微信联合ID
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 设置微信联合ID
     *
     * @param unionid 微信联合ID
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /**
     * 获取头像地址
     *
     * @return headimgurl - 头像地址
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 设置头像地址
     *
     * @param headimgurl 头像地址
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * 获取版本，用于乐观锁
     *
     * @return version - 版本，用于乐观锁
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本，用于乐观锁
     *
     * @param version 版本，用于乐观锁
     */
    public void setVersion(Integer version) {
        this.version = version;
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