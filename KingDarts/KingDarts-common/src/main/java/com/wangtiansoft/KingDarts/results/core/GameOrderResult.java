package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class GameOrderResult extends BaseResult implements Serializable{

    private Integer id;   //  主键
    private String order_no;   //  订单编号
    private Integer order_type;   //  订单类型，1单次游戏，2包机游戏，3赛事
    private String game_type;	//  游戏类型编码
    private String game_code;   //  游戏编码
    private Integer game_mode;  //游戏模式，1单人，2双人，3三人，4四人，5双人赛2V2，6三人赛3V3，7网络1V1
    private BigDecimal cost;	//本次订单金额
    private String user_id;   //  玩家编号
    private Integer merchant;	//商户类型（1俱乐部 2 代理商）
    private String auth_no;	//授权对象(agno,cno)
    private String equno;   //  设备编号
    private String cardinfo;   //  卡信息
    private String cardno;   //  卡号（印刷）
    private Integer status;   //  状态（1订单创建,2 登陆完等待开始游戏、3 游戏中、4 游戏结束、5 没有登录成功、6登陆完退出游戏、7 登出）
    private Integer pay_status;   //  支付状态，0:未支付，1:已支付，2:支付失败,3:退款中,4:已退款,5:退款失败
    private Integer pay_way;   //  支付方式，1微信支付，2支付宝支付，3第三方支付
    private String net_no;	//网络对战关联订单编号
    private String win;	//网络对战，是否赢的本局，Y/N
    private String boss_id;	//大富豪游戏bossId

    public GameOrderResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrder_no() {
        return this.order_no;
    }
    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }
    public Integer getOrder_type() {
        return this.order_type;
    }
    public void setOrder_type(Integer order_type) {
        this.order_type = order_type;
    }
    public String getGame_code() {
        return this.game_code;
    }
    public void setGame_code(String game_code) {
        this.game_code = game_code;
    }
    public Integer getGame_mode() {
		return game_mode;
	}
	public void setGame_mode(Integer game_mode) {
		this.game_mode = game_mode;
	}
	public String getUser_id() {
        return this.user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
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

	public String getEquno() {
        return this.equno;
    }
    public void setEquno(String equno) {
        this.equno = equno;
    }
    public String getCardinfo() {
        return this.cardinfo;
    }
    public void setCardinfo(String cardinfo) {
        this.cardinfo = cardinfo;
    }
    public String getCardno() {
        return this.cardno;
    }
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getPay_status() {
        return this.pay_status;
    }
    public void setPay_status(Integer pay_status) {
        this.pay_status = pay_status;
    }
    public Integer getPay_way() {
        return this.pay_way;
    }
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
