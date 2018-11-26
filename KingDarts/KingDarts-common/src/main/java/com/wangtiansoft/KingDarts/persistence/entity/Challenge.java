package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_challenge")
public class Challenge extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 发起者id
     */
    private Integer sponsor_id;

    /**
     * 发起者账号
     */
    private String sponsor_useraccount;

    /**
     * 发起者昵称
     */
    private String sponsor_nickname;

    /**
     * 发起者头像
     */
    private String sponsor_headimg;

    /**
     * 应战者id
     */
    private Integer receiver_id;

    /**
     * 应战者账号
     */
    private String receiver_useraccount;

    /**
     * 应战者昵称
     */
    private String receiver_nickname;

    /**
     * 应战者头像
     */
    private String receiver_headimg;

    /**
     * 类型id
     */
    private String game_type_id;

    /**
     * 游戏类型值
     */
    private String game_type_value;

    /**
     * 游戏名
     */
    private String game_name;

    /**
     * 发起者游戏订单
     */
    private String sponsor_order_no;

    /**
     * 接收者游戏订单编号
     */
    private String receiver_order_no;

    /**
     * 发起者是否获胜，Y/N
     */
    private String sponsor_win;

    /**
     * 开始时间
     */
    private Date start_time;

    /**
     * 浮动时间：分钟
     */
    private Integer float_time;

    /**
     * 1：未设置状态；2：拒绝；3：应战
     */
    private Integer receive_status;

    /**
     * 游戏状态：1：待开始；2：已过期
     */
    private Integer challenge_status;

    /**
     * 发起人是否爽约：(Y/N)
     */
    private String sponsor_miss;

    /**
     * 迎战这是否爽约：(Y/N)
     */
    private String receiver_miss;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取发起者id
     *
     * @return sponsor_id - 发起者id
     */
    public Integer getSponsor_id() {
        return sponsor_id;
    }

    /**
     * 设置发起者id
     *
     * @param sponsor_id 发起者id
     */
    public void setSponsor_id(Integer sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    /**
     * 获取发起者账号
     *
     * @return sponsor_useraccount - 发起者账号
     */
    public String getSponsor_useraccount() {
        return sponsor_useraccount;
    }

    /**
     * 设置发起者账号
     *
     * @param sponsor_useraccount 发起者账号
     */
    public void setSponsor_useraccount(String sponsor_useraccount) {
        this.sponsor_useraccount = sponsor_useraccount;
    }

    /**
     * 获取发起者昵称
     *
     * @return sponsor_nickname - 发起者昵称
     */
    public String getSponsor_nickname() {
        return sponsor_nickname;
    }

    /**
     * 设置发起者昵称
     *
     * @param sponsor_nickname 发起者昵称
     */
    public void setSponsor_nickname(String sponsor_nickname) {
        this.sponsor_nickname = sponsor_nickname;
    }

    /**
     * 获取发起者头像
     *
     * @return sponsor_headimg - 发起者头像
     */
    public String getSponsor_headimg() {
        return sponsor_headimg;
    }

    /**
     * 设置发起者头像
     *
     * @param sponsor_headimg 发起者头像
     */
    public void setSponsor_headimg(String sponsor_headimg) {
        this.sponsor_headimg = sponsor_headimg;
    }

    /**
     * 获取应战者id
     *
     * @return receiver_id - 应战者id
     */
    public Integer getReceiver_id() {
        return receiver_id;
    }

    /**
     * 设置应战者id
     *
     * @param receiver_id 应战者id
     */
    public void setReceiver_id(Integer receiver_id) {
        this.receiver_id = receiver_id;
    }

    /**
     * 获取应战者账号
     *
     * @return receiver_useraccount - 应战者账号
     */
    public String getReceiver_useraccount() {
        return receiver_useraccount;
    }

    /**
     * 设置应战者账号
     *
     * @param receiver_useraccount 应战者账号
     */
    public void setReceiver_useraccount(String receiver_useraccount) {
        this.receiver_useraccount = receiver_useraccount;
    }

    /**
     * 获取应战者昵称
     *
     * @return receiver_nickname - 应战者昵称
     */
    public String getReceiver_nickname() {
        return receiver_nickname;
    }

    /**
     * 设置应战者昵称
     *
     * @param receiver_nickname 应战者昵称
     */
    public void setReceiver_nickname(String receiver_nickname) {
        this.receiver_nickname = receiver_nickname;
    }

    /**
     * 获取应战者头像
     *
     * @return receiver_headimg - 应战者头像
     */
    public String getReceiver_headimg() {
        return receiver_headimg;
    }

    /**
     * 设置应战者头像
     *
     * @param receiver_headimg 应战者头像
     */
    public void setReceiver_headimg(String receiver_headimg) {
        this.receiver_headimg = receiver_headimg;
    }

    /**
     * 获取类型id
     *
     * @return game_type_id - 类型id
     */
    public String getGame_type_id() {
        return game_type_id;
    }

    /**
     * 设置类型id
     *
     * @param game_type_id 类型id
     */
    public void setGame_type_id(String game_type_id) {
        this.game_type_id = game_type_id;
    }

    /**
     * 获取游戏类型值
     *
     * @return game_type_value - 游戏类型值
     */
    public String getGame_type_value() {
        return game_type_value;
    }

    /**
     * 设置游戏类型值
     *
     * @param game_type_value 游戏类型值
     */
    public void setGame_type_value(String game_type_value) {
        this.game_type_value = game_type_value;
    }

    /**
     * 获取游戏名
     *
     * @return game_name - 游戏名
     */
    public String getGame_name() {
        return game_name;
    }

    /**
     * 设置游戏名
     *
     * @param game_name 游戏名
     */
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
     * 获取浮动时间：分钟
     *
     * @return float_time - 浮动时间：分钟
     */
    public Integer getFloat_time() {
        return float_time;
    }

    /**
     * 设置浮动时间：分钟
     *
     * @param float_time 浮动时间：分钟
     */
    public void setFloat_time(Integer float_time) {
        this.float_time = float_time;
    }

    /**
     * 获取1：未设置状态；2：拒绝；3：应战
     *
     * @return receive_status - 1：未设置状态；2：拒绝；3：应战
     */
    public Integer getReceive_status() {
        return receive_status;
    }

    /**
     * 设置1：未设置状态；2：拒绝；3：应战
     *
     * @param receive_status 1：未设置状态；2：拒绝；3：应战
     */
    public void setReceive_status(Integer receive_status) {
        this.receive_status = receive_status;
    }

    /**
     * 获取游戏状态：1：待开始；2：已过期
     *
     * @return challenge_status - 游戏状态：1：待开始；2：已过期
     */
    public Integer getChallenge_status() {
        return challenge_status;
    }

    /**
     * 设置游戏状态：1：待开始；2：已过期
     *
     * @param challenge_status 游戏状态：1：待开始；2：已过期
     */
    public void setChallenge_status(Integer challenge_status) {
        this.challenge_status = challenge_status;
    }

    /**
     * 获取发起人是否爽约：(Y/N)
     *
     * @return sponsor_miss - 发起人是否爽约：(Y/N)
     */
    public String getSponsor_miss() {
        return sponsor_miss;
    }

    /**
     * 设置发起人是否爽约：(Y/N)
     *
     * @param sponsor_miss 发起人是否爽约：(Y/N)
     */
    public void setSponsor_miss(String sponsor_miss) {
        this.sponsor_miss = sponsor_miss;
    }

    /**
     * 获取迎战这是否爽约：(Y/N)
     *
     * @return receiver_miss - 迎战这是否爽约：(Y/N)
     */
    public String getReceiver_miss() {
        return receiver_miss;
    }

    /**
     * 设置迎战这是否爽约：(Y/N)
     *
     * @param receiver_miss 迎战这是否爽约：(Y/N)
     */
    public void setReceiver_miss(String receiver_miss) {
        this.receiver_miss = receiver_miss;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 设置创建时间
     *
     * @param create_time 创建时间
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}