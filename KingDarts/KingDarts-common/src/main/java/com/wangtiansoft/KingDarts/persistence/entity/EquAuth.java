package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_equ_auth")
public class EquAuth extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 设备编号
     */
    private String equno;

    /**
     * 授权人（account.id）
     */
    private Integer auth_acc;

    /**
     * 授权人类别（1公司;2代理商）
     */
    private Integer auth_type;

    /**
     * 商户类型（1俱乐部 2 代理商）
     */
    private Integer merchant;

    /**
     * 授权对象(agno,cno)
     */
    private String auth_no;

    /**
     * 授权对象名称
     */
    private String auth_name;

    /**
     * 授权时间
     */
    private Date auth_time;

    /**
     * 俱乐部场所编号
     */
    private String placeno;

    /**
     * 结算类型 (1：设备 )
     */
    private Integer acc_type;

    /**
     * 创建日期
     */
    private Date add_time;
    
    /**
     * 合作方式，1出售，2租赁
     */
    private Integer cooperation;
    
    /**
     * 租金
     */
    private BigDecimal rent;
    
    /**
     * 分成比例
     */
    private BigDecimal profit_ratio;
    

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
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

    public Integer getAuth_acc() {
		return auth_acc;
	}

	public void setAuth_acc(Integer auth_acc) {
		this.auth_acc = auth_acc;
	}

	/**
     * 获取授权人类别（1公司;2代理商）
     *
     * @return auth_type - 授权人类别（1公司;2代理商）
     */
    public Integer getAuth_type() {
        return auth_type;
    }

    /**
     * 设置授权人类别（1公司;2代理商）
     *
     * @param auth_type 授权人类别（1公司;2代理商）
     */
    public void setAuth_type(Integer auth_type) {
        this.auth_type = auth_type;
    }

    /**
     * 获取商户类型（1俱乐部 2 代理商）
     *
     * @return merchant - 商户类型（1俱乐部 2 代理商）
     */
    public Integer getMerchant() {
        return merchant;
    }

    /**
     * 设置商户类型（1俱乐部 2 代理商）
     *
     * @param merchant 商户类型（1俱乐部 2 代理商）
     */
    public void setMerchant(Integer merchant) {
        this.merchant = merchant;
    }

    /**
     * 获取授权对象(agno,cno)
     *
     * @return auth_no - 授权对象(agno,cno)
     */
    public String getAuth_no() {
        return auth_no;
    }

    /**
     * 设置授权对象(agno,cno)
     *
     * @param auth_no 授权对象(agno,cno)
     */
    public void setAuth_no(String auth_no) {
        this.auth_no = auth_no;
    }

    /**
     * 获取授权对象名称
     *
     * @return auth_name - 授权对象名称
     */
    public String getAuth_name() {
        return auth_name;
    }

    /**
     * 设置授权对象名称
     *
     * @param auth_name 授权对象名称
     */
    public void setAuth_name(String auth_name) {
        this.auth_name = auth_name;
    }

    /**
     * 获取授权时间
     *
     * @return auth_time - 授权时间
     */
    public Date getAuth_time() {
        return auth_time;
    }

    /**
     * 设置授权时间
     *
     * @param auth_time 授权时间
     */
    public void setAuth_time(Date auth_time) {
        this.auth_time = auth_time;
    }

    /**
     * 获取俱乐部场所编号
     *
     * @return placeno - 俱乐部场所编号
     */
    public String getPlaceno() {
        return placeno;
    }

    /**
     * 设置俱乐部场所编号
     *
     * @param placeno 俱乐部场所编号
     */
    public void setPlaceno(String placeno) {
        this.placeno = placeno;
    }

    /**
     * 获取结算类型 (1：设备 )
     *
     * @return acc_type - 结算类型 (1：设备 )
     */
    public Integer getAcc_type() {
        return acc_type;
    }

    /**
     * 设置结算类型 (1：设备 )
     *
     * @param acc_type 结算类型 (1：设备 )
     */
    public void setAcc_type(Integer acc_type) {
        this.acc_type = acc_type;
    }

    /**
     * 获取创建日期
     *
     * @return add_time - 创建日期
     */
    public Date getAdd_time() {
        return add_time;
    }

    /**
     * 设置创建日期
     *
     * @param add_time 创建日期
     */
    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

	public Integer getCooperation() {
		return cooperation;
	}

	public void setCooperation(Integer cooperation) {
		this.cooperation = cooperation;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public BigDecimal getProfit_ratio() {
		return profit_ratio;
	}

	public void setProfit_ratio(BigDecimal profit_ratio) {
		this.profit_ratio = profit_ratio;
	}
    
}