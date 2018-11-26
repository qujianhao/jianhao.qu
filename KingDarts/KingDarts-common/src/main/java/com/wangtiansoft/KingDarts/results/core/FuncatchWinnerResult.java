package com.wangtiansoft.KingDarts.results.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wt-templete-helper on unknow.
 */
public class FuncatchWinnerResult extends BaseResult implements Serializable{

    private Integer id;   //  id
    private Integer user_id;   //  中奖用户id
    private String useraccount;   //  中奖用户账号
    private String username;   //  中奖用户姓名
    private String headimg;   //  中奖用户头像
    private Integer prize_id;   //  所中奖品id
    private String prize_name;   //  奖品名称
    private Integer is_deliver;   //  如果奖品是实物：1:已发货; 0:未发货。如果是虚拟奖品，则总是为1
    private String is_physical;   //  是否为实物奖品：Y:为实物；N:为虚拟奖品
    private String receive_name;//实物领取用户姓名
    private String receive_phone;//实物领取手机号
    private String receive_address;//实物领取地址
    private Integer is_top;//是否置顶显示
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time; //生成时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;//修改时间
    
    public FuncatchWinnerResult() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUser_id() {
        return this.user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public String getUseraccount() {
        return this.useraccount;
    }
    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getHeadimg() {
        return this.headimg;
    }
    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }
    public Integer getPrize_id() {
        return this.prize_id;
    }
    public void setPrize_id(Integer prize_id) {
        this.prize_id = prize_id;
    }
    public String getPrize_name() {
        return this.prize_name;
    }
    public void setPrize_name(String prize_name) {
        this.prize_name = prize_name;
    }
    public Integer getIs_deliver() {
        return this.is_deliver;
    }
    public void setIs_deliver(Integer is_deliver) {
        this.is_deliver = is_deliver;
    }
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getReceive_name() {
		return receive_name;
	}
	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}
	public String getReceive_phone() {
		return receive_phone;
	}
	public void setReceive_phone(String receive_phone) {
		this.receive_phone = receive_phone;
	}
	public String getReceive_address() {
		return receive_address;
	}
	public void setReceive_address(String receive_address) {
		this.receive_address = receive_address;
	}
	public Integer getIs_top() {
		return is_top;
	}
	public void setIs_top(Integer is_top) {
		this.is_top = is_top;
	}

	public String getIs_physical() {
		return is_physical;
	}

	public void setIs_physical(String is_physical) {
		this.is_physical = is_physical;
	}
	
}
