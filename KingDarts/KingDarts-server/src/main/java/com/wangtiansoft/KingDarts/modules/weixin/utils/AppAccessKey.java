package com.wangtiansoft.KingDarts.modules.weixin.utils;

import java.io.Serializable;
import java.util.Map;

import com.jfinal.weixin.sdk.utils.JsonUtils;

public class AppAccessKey implements Serializable{

	private static final long serialVersionUID = -4137903645229524111L;
	private String session_key;    //
	private String openid;    //
    private String unionid;    //
    private Integer errcode;        // 出错时有值
    private String errmsg;            // 出错时有值
    
    private String json;
    
    public AppAccessKey(String jsonStr){
    	 this.json = jsonStr;
    	 
    	 try{
             @SuppressWarnings("unchecked")
             Map<String, Object> temp = JsonUtils.parse(jsonStr, Map.class);
             session_key = (String) temp.get("session_key");
             openid = (String) temp.get("openid");
             unionid = (String) temp.get("unionid");
             errcode = getInt(temp, "errcode");
             errmsg = (String) temp.get("errmsg");


         } catch (Exception e){
             throw new RuntimeException(e);
         }
    }
    
    public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	private Integer getInt(Map<String, Object> temp, String key) {
        Number number = (Number) temp.get(key);
        return number == null ? null : number.intValue();
    }
}
