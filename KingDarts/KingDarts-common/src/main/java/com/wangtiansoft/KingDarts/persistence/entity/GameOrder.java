package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_game_order")
public class GameOrder extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单编号
     */
    private String order_no;

    /**
     * 订单类型，1单次游戏，2包机游戏，3赛事
     */
    private Integer order_type;

    /**
     * 游戏类型编码
     */
    private String game_type;
    /**
     * 游戏编码
     */
    private String game_code;
    
    /**
     * 游戏模式，1单人，2双人，3三人，4四人，5双人赛2V2，6三人赛3V3，7网络1V1
     */
    private Integer game_mode;
    
    /**
     * 本地订单金额
     */
    private BigDecimal cost;

    /**
     * 玩家编号
     */
    private String user_id;

    /**
     * 商户类型（1俱乐部 2 代理商）
     */
    private Integer merchant;
    
    /**
     * 授权对象(agno,cno)
     */
    private String auth_no;

    /**
     * 设备编号
     */
    private String equno;

    /**
     * 卡信息
     */
    private String cardinfo;

    /**
     * 卡号（印刷）
     */
    private String cardno;

    /**
     * 创建时间
     */
    private Date order_time;

    /**
     * 开始时间
     */
    private Date start_time;

    /**
     * 结束时间
     */
    private Date end_time;

    /**
     * 状态（1订单创建,2 登陆完等待开始游戏、3 游戏中、4 游戏结束、5 没有登录成功、6登陆完退出游戏、7 登出）
     */
    private Integer status;

    /**
     * 支付状态，0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败
     */
    private Integer pay_status;

    /**
     * 支付方式，1微信支付，2支付宝支付，3第三方支付
     */
    private Integer pay_way;
    
    /**
     * 网络对战关联订单编号
     */
    private String net_no;
    /**
     * 网络对战，是否赢的本局，Y/N
     */
    private String win;
    /**
     * 大富豪游戏bossId
     */
    private String boss_id;

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
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrder_no() {
        return order_no;
    }

    /**
     * 设置订单编号
     *
     * @param order_no 订单编号
     */
    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    /**
     * 获取订单类型，1单次游戏，2包机游戏，3赛事
     *
     * @return order_type - 订单类型，1单次游戏，2包机游戏，3赛事
     */
    public Integer getOrder_type() {
        return order_type;
    }

    /**
     * 设置订单类型，1单次游戏，2包机游戏，3赛事
     *
     * @param order_type 订单类型，1单次游戏，2包机游戏，3赛事
     */
    public void setOrder_type(Integer order_type) {
        this.order_type = order_type;
    }

    /**
     * 获取游戏编码
     *
     * @return game_code - 游戏编码
     */
    public String getGame_code() {
        return game_code;
    }

    /**
     * 设置游戏编码
     *
     * @param game_code 游戏编码
     */
    public void setGame_code(String game_code) {
        this.game_code = game_code;
    }

    public Integer getGame_mode() {
		return game_mode;
	}

	public void setGame_mode(Integer game_mode) {
		this.game_mode = game_mode;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
     * 获取玩家编号
     *
     * @return user_id - 玩家编号
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置玩家编号
     *
     * @param user_id 玩家编号
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getMerchant() {
		return merchant;
	}

	public void setMerchant(Integer merchant) {
		this.merchant = merchant;
	}

	public String getAuth_no() {
		return auth_no;
	}

	public void setAuth_no(String auth_no) {
		this.auth_no = auth_no;
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
     * 获取卡信息
     *
     * @return cardinfo - 卡信息
     */
    public String getCardinfo() {
        return cardinfo;
    }

    /**
     * 设置卡信息
     *
     * @param cardinfo 卡信息
     */
    public void setCardinfo(String cardinfo) {
        this.cardinfo = cardinfo;
    }

    /**
     * 获取卡号（印刷）
     *
     * @return cardno - 卡号（印刷）
     */
    public String getCardno() {
        return cardno;
    }

    /**
     * 设置卡号（印刷）
     *
     * @param cardno 卡号（印刷）
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    /**
     * 获取创建时间
     *
     * @return order_time - 创建时间
     */
    public Date getOrder_time() {
        return order_time;
    }

    /**
     * 设置创建时间
     *
     * @param order_time 创建时间
     */
    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Date getStart_time() {
        return start_time;
    }

    /**
     * 设置开始时间
     *
     * @param start_time 开始时间
     */
    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEnd_time() {
        return end_time;
    }

    /**
     * 设置结束时间
     *
     * @param end_time 结束时间
     */
    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    /**
     * 获取状态（1订单创建,2 登陆完等待开始游戏、3 游戏中、4 游戏结束、5 没有登录成功、6登陆完退出游戏、7 登出）
     *
     * @return status - 状态（1订单创建,2 登陆完等待开始游戏、3 游戏中、4 游戏结束、5 没有登录成功、6登陆完退出游戏、7 登出）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态（1订单创建,2 登陆完等待开始游戏、3 游戏中、4 游戏结束、5 没有登录成功、6登陆完退出游戏、7 登出）
     *
     * @param status 状态（1订单创建,2 登陆完等待开始游戏、3 游戏中、4 游戏结束、5 没有登录成功、6登陆完退出游戏、7 登出）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取支付状态，0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败
     *
     * @return pay_status - 支付状态，0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败
     */
    public Integer getPay_status() {
        return pay_status;
    }

    /**
     * 设置支付状态，0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败
     *
     * @param pay_status 支付状态，0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败
     */
    public void setPay_status(Integer pay_status) {
        this.pay_status = pay_status;
    }

    /**
     * 获取支付方式，1微信支付，2支付宝支付，3第三方支付
     *
     * @return pay_way - 支付方式，1微信支付，2支付宝支付，3第三方支付
     */
    public Integer getPay_way() {
        return pay_way;
    }

    /**
     * 设置支付方式，1微信支付，2支付宝支付，3第三方支付
     *
     * @param pay_way 支付方式，1微信支付，2支付宝支付，3第三方支付
     */
    public void setPay_way(Integer pay_way) {
        this.pay_way = pay_way;
    }

	public String getNet_no() {
		return net_no;
	}

	public void setNet_no(String net_no) {
		this.net_no = net_no;
	}

	public String getWin() {
		return win;
	}

	public void setWin(String win) {
		this.win = win;
	}

	public String getBoss_id() {
		return boss_id;
	}

	public void setBoss_id(String boss_id) {
		this.boss_id = boss_id;
	}

	public String getGame_type() {
		return game_type;
	}

	public void setGame_type(String game_type) {
		this.game_type = game_type;
	}
    
}