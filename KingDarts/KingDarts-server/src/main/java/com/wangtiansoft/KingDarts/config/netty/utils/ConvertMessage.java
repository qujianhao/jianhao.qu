package com.wangtiansoft.KingDarts.config.netty.utils;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.config.netty.constants.Constants;
import com.wangtiansoft.KingDarts.config.netty.model.NettyMessage;


public class ConvertMessage {

	public static NettyMessage jsonToMessage(String json) {
		try {
			JSONObject o = null;
			if(StringUtils.isEmpty(json)){
				return NettyMessage.formatFail("消息转换错误");
			}else if(Constants.message_type_ping.equals(json)){//心跳类型
				o = new JSONObject();
				o.put("type", Constants.message_type_ping);
			}
			
			try {
				if(o==null) o = JSON.parseObject(json);
			} catch (Exception e) {
				return NettyMessage.formatFail(Constants.code_MessageError,json,"消息格式错误");
			}
			if(!o.containsKey("type")){
				throw new RuntimeException("类型错误");
			}
			NettyMessage m = new NettyMessage();
			m.setCode(Constants.code_Success);
			m.setType(o.getString("type"));
			m.setRequestId(o.getString("requestId"));
//			int positon = Arrays.binarySearch(Constants.message_types, m.getType());    
//			m.setTypeCode(positon);
			if(o.get("data")!=null){
				Map<String, Object> itemMap = JSONObject.toJavaObject((JSONObject)o.get("data"), Map.class);
				m.setData(itemMap);
			}
			return m;
		} catch (Exception e) {
			return NettyMessage.formatFail("消息转换错误");
			//throw new RuntimeException(e);
		}
	}
	
}
