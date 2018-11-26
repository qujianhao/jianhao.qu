package com.wangtiansoft.KingDarts.config.netty.model;

import java.io.Serializable;

import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.config.netty.constants.Constants;


public class NettyMessage<T> implements Serializable{

	private String code;
	private String msg;
	private String type;
	private String requestId;
	private Integer typeCode;
	private T data;
	
	public NettyMessage() {
        this.code = Constants.code_Fail;
        this.msg = "";
    }

    public NettyMessage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
	public NettyMessage(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
	public NettyMessage(String code, String msg,Integer typeCode, T data) {
		this.code = code;
		this.msg = msg;
		this.typeCode = typeCode;
		this.data = data;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}
	public Object getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public static NettyMessage success(Object data, String msg){
		NettyMessage message = new NettyMessage(Constants.code_Success, msg, data);
        return message;
    }
	
	public static NettyMessage successMsg(String msg){
		NettyMessage message = new NettyMessage(Constants.code_Success, msg, null);
        return message;
    }

    public static NettyMessage success(Object data){
    	NettyMessage message = new NettyMessage(Constants.code_Success, "", data);
        return message;
    }

    public static NettyMessage fail(){
        return fail("请求失败");
    }
    
    public static NettyMessage fail(String msg){
    	NettyMessage message = new NettyMessage(Constants.code_Fail, msg,null);
    	return message;
    }

    public static NettyMessage fail(String code, String msg){
    	NettyMessage message = new NettyMessage(code, msg,null);
        return message;
    }
    
    public static NettyMessage fail(String code,Object data, String msg){
    	NettyMessage message = new NettyMessage(code, msg,data);
    	return message;
    }

    public static NettyMessage sessionError(){
    	NettyMessage message = new NettyMessage(Constants.code_SessionError, "登陆超时");
        return message;
    }
    
   public static NettyMessage formatFail(String msg){
    	NettyMessage message = new NettyMessage(Constants.code_Fail, msg ,Constants.message_fail,null);
        return message;
    }
    public static NettyMessage formatFail(String code,Object data,String msg){
    	NettyMessage message = new NettyMessage(code, msg ,Constants.message_fail,data);
    	return message;
    }

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
    
}
