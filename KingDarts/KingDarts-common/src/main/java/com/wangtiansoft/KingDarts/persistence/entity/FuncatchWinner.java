package com.wangtiansoft.KingDarts.persistence.entity;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "darts_funcatch_winner")
public class FuncatchWinner extends BaseEntity {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 中奖用户id
     */
    private Integer user_id;

    /**
     * 中奖用户账号
     */
    private String useraccount;

    /**
     * 中奖用户姓名
     */
    private String username;

    /**
     * 中奖用户头像
     */
    private String headimg;

    /**
     * 所中奖品id
     */
    private Integer prize_id;

    /**
     * 奖品名称
     */
    private String prize_name;

    /**
     * 如果奖品是实物：1:已发货; 0:未发货。如果是虚拟奖品，则总是为1
     */
    private Integer is_deliver;
    
    /**
     * 实物领取用户姓名
     */
    private String receive_name;
    
    /**
     * 实物领取手机号
     */
    private String receive_phone;
    
    /**
     * 实物领取地址
     */
    private String receive_address;

    /**
     * 1:被删除；0:未删除
     */
    private Integer is_delete;

    /**
     * 抽中奖品时间
     */
    private Date create_time;
    
    /**
     * 更新时间
     */
    private Date update_time;
    
    /**
     * 是否置顶显示（主要针对获取大奖励的信息进行特殊展示）
     */
    private Integer is_top;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取中奖用户id
     *
     * @return user_id - 中奖用户id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * 设置中奖用户id
     *
     * @param user_id 中奖用户id
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取中奖用户账号
     *
     * @return useraccount - 中奖用户账号
     */
    public String getUseraccount() {
        return useraccount;
    }

    /**
     * 设置中奖用户账号
     *
     * @param useraccount 中奖用户账号
     */
    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    /**
     * 获取中奖用户姓名
     *
     * @return username - 中奖用户姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置中奖用户姓名
     *
     * @param username 中奖用户姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取中奖用户头像
     *
     * @return headimg - 中奖用户头像
     */
    public String getHeadimg() {
        return headimg;
    }

    /**
     * 设置中奖用户头像
     *
     * @param headimg 中奖用户头像
     */
    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    /**
     * 获取所中奖品id
     *
     * @return prize_id - 所中奖品id
     */
    public Integer getPrize_id() {
        return prize_id;
    }

    /**
     * 设置所中奖品id
     *
     * @param prize_id 所中奖品id
     */
    public void setPrize_id(Integer prize_id) {
        this.prize_id = prize_id;
    }

    /**
     * 获取奖品名称
     *
     * @return prize_name - 奖品名称
     */
    public String getPrize_name() {
        return prize_name;
    }

    /**
     * 设置奖品名称
     *
     * @param prize_name 奖品名称
     */
    public void setPrize_name(String prize_name) {
        this.prize_name = prize_name;
    }

    /**
     * 获取如果奖品是实物：1:已发货; 0:未发货。如果是虚拟奖品，则总是为1
     *
     * @return is_deliver - 如果奖品是实物：1:已发货; 0:未发货。如果是虚拟奖品，则总是为1
     */
    public Integer getIs_deliver() {
        return is_deliver;
    }

    /**
     * 设置如果奖品是实物：1:已发货; 0:未发货。如果是虚拟奖品，则总是为0
     *
     * @param is_deliver 如果奖品是实物：1:已发货; 0:未发货。如果是虚拟奖品，则总是为1
     */
    public void setIs_deliver(Integer is_deliver) {
        this.is_deliver = is_deliver;
    }

    /**
     * 获取1:被删除；0:未删除
     *
     * @return is_delete - 1:被删除；0:未删除
     */
    public Integer getIs_delete() {
        return is_delete;
    }

    /**
     * 设置1:被删除；0:未删除
     *
     * @param is_delete 1:被删除；0:未删除
     */
    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    /**
     * 获取抽中奖品时间
     *
     * @return create_time - 抽中奖品时间
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 设置抽中奖品时间
     *
     * @param create_time 抽中奖品时间
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    
    /**
     * 获取发奖品时间
     *
     * @return update_time - 发奖品时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 设置发奖品时间
     *
     * @param update_time 发奖品时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    /**
     * 获取领取奖品用户名称
     *
     * @return receive_name - 领取奖品用户名称
     */
	public String getReceive_name() {
		return receive_name;
	}

	/**
     * 设置领取奖品用户名称
     *
     * @param receive_name 领取奖品用户名称
     */
	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}

	/**
     * 获取领取奖品用户手机
     *
     * @return receive_phone - 领取奖品用户手机
     */
	public String getReceive_phone() {
		return receive_phone;
	}

	/**
     * 设置领取奖品用户手机
     *
     * @param receive_phone 领取奖品用户手机
     */
	public void setReceive_phone(String receive_phone) {
		this.receive_phone = receive_phone;
	}

	/**
     * 获取领取奖品用户手机
     *
     * @return receive_address - 领取奖品用户手机
     */
	public String getReceive_address() {
		return receive_address;
	}

	/**
     * 设置领取奖品用户地址
     *
     * @param receive_address 领取奖品用户地址
     */
	public void setReceive_address(String receive_address) {
		this.receive_address = receive_address;
	}

	/**
     * 获取是否置顶显示
     *
     * @return is_top - 是否置顶显示
     */
	public Integer getIs_top() {
		return is_top;
	}

	/**
     * 设置是否置顶显示
     *
     * @return is_top - 是否置顶显示
     */
	public void setIs_top(Integer is_top) {
		this.is_top = is_top;
	}
	
	
    
}