package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by wt-templete-helper on unknow.
 */
public class ChallengeResult extends BaseResult implements Serializable{

    private Long id;   //  
    private Integer sponsor_id;   //  发起者id
    private String sponsor_useraccount;   //  发起者账号
    private String sponsor_nickname;   //  发起者昵称
    private String sponsor_headimg;   //  发起者头像
    private Integer receiver_id;   //  应战者id
    private String receiver_useraccount;   //  应战者账号
    private String receiver_nickname;   //  应战者昵称
    private String receiver_headimg;   //  应战者头像
    private String game_type_id;   //  类型id
    private String game_type_value;   //  游戏类型值
    private String game_name;   //  游戏名
    private String sponsor_order_no;	//发起者游戏订单
    private String receiver_order_no;	//接收者游戏订单编号
    private String sponsor_win;	//发起者是否获胜，Y/N
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date start_time;   //  开始时间
    private Integer float_time; //浮动时间
    private Integer receive_status;   //  1：未设置状态；2：拒绝；3：应战
    private Integer challenge_status;   //  游戏状态：1：待开始；2：已过期
    private String sponsor_miss;   //  发起人是否爽约：(Y/N)
    private String receiver_miss;   //  迎战这是否爽约：(Y/N)
    
    private Date out_time;   //  超时时间
    private String equno; //用户登录设备
    private String order_no; //用户登录设备的订单号

    public ChallengeResult() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getSponsor_id() {
        return this.sponsor_id;
    }
    public void setSponsor_id(Integer sponsor_id) {
        this.sponsor_id = sponsor_id;
    }
    public String getSponsor_useraccount() {
        return this.sponsor_useraccount;
    }
    public void setSponsor_useraccount(String sponsor_useraccount) {
        this.sponsor_useraccount = sponsor_useraccount;
    }
    public String getSponsor_nickname() {
        return this.sponsor_nickname;
    }
    public void setSponsor_nickname(String sponsor_nickname) {
        this.sponsor_nickname = sponsor_nickname;
    }
    public String getSponsor_headimg() {
        return this.sponsor_headimg;
    }
    public void setSponsor_headimg(String sponsor_headimg) {
        this.sponsor_headimg = sponsor_headimg;
    }
    public Integer getReceiver_id() {
        return this.receiver_id;
    }
    public void setReceiver_id(Integer receiver_id) {
        this.receiver_id = receiver_id;
    }
    public String getReceiver_useraccount() {
        return this.receiver_useraccount;
    }
    public void setReceiver_useraccount(String receiver_useraccount) {
        this.receiver_useraccount = receiver_useraccount;
    }
    public String getReceiver_nickname() {
        return this.receiver_nickname;
    }
    public void setReceiver_nickname(String receiver_nickname) {
        this.receiver_nickname = receiver_nickname;
    }
    public String getReceiver_headimg() {
        return this.receiver_headimg;
    }
    public void setReceiver_headimg(String receiver_headimg) {
        this.receiver_headimg = receiver_headimg;
    }
    public String getGame_type_id() {
        return this.game_type_id;
    }
    public void setGame_type_id(String game_type_id) {
        this.game_type_id = game_type_id;
    }
    public String getGame_type_value() {
        return this.game_type_value;
    }
    public void setGame_type_value(String game_type_value) {
        this.game_type_value = game_type_value;
    }
    public String getGame_name() {
        return this.game_name;
    }
    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }
    
    public String getSponsor_order_no() {
		return sponsor_order_no;
	}

	public void setSponsor_order_no(String sponsor_order_no) {
		this.sponsor_order_no = sponsor_order_no;
	}

	public String getReceiver_order_no() {
		return receiver_order_no;
	}

	public void setReceiver_order_no(String receiver_order_no) {
		this.receiver_order_no = receiver_order_no;
	}

	public String getSponsor_win() {
		return sponsor_win;
	}

	public void setSponsor_win(String sponsor_win) {
		this.sponsor_win = sponsor_win;
	}

	public Integer getReceive_status() {
        return this.receive_status;
    }
    public void setReceive_status(Integer receive_status) {
        this.receive_status = receive_status;
    }
    public Integer getChallenge_status() {
        return this.challenge_status;
    }
    public void setChallenge_status(Integer challenge_status) {
        this.challenge_status = challenge_status;
    }
    public String getSponsor_miss() {
        return this.sponsor_miss;
    }
    public void setSponsor_miss(String sponsor_miss) {
        this.sponsor_miss = sponsor_miss;
    }
    public String getReceiver_miss() {
        return this.receiver_miss;
    }
    public void setReceiver_miss(String receiver_miss) {
        this.receiver_miss = receiver_miss;
    }

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Integer getFloat_time() {
		return float_time;
	}

	public void setFloat_time(Integer float_time) {
		this.float_time = float_time;
	}

	public Date getOut_time() {
		return out_time;
	}

	public void setOut_time(Date out_time) {
		this.out_time = out_time;
	}

	public String getEquno() {
		return equno;
	}

	public void setEquno(String equno) {
		this.equno = equno;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
    
}
