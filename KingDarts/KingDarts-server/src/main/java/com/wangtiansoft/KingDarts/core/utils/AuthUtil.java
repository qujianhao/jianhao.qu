package com.wangtiansoft.KingDarts.core.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.entity.Account;
import com.wangtiansoft.KingDarts.results.core.UserResult;


public class AuthUtil {

//	private static String ROLE_NAME = "RoleName";
	
	/*public static boolean isAdmin(HttpServletRequest request){
		String roleName = request.getSession().getAttribute(ROLE_NAME)!=null?(String)request.getSession().getAttribute(ROLE_NAME):null;
		if(admin.equals(roleName)){
			return true;
		}
		return false;
	}
	*/
	
	/**
	 * 获得后台登录人员账号
	 * @param request
	 * @return
	 */
	public static Account getAccount(HttpServletRequest request){
		Object obj =request.getSession().getAttribute(Constants.session_account);
		if(obj==null){
			throw new AppRuntimeException("用户未登录");
		}
		return (Account)obj;
	}
	
	/**
	 * 获取登录用户openid
	 * @param request
	 * @return
	 */
	public static String getOpenId(HttpServletRequest request){
		Object obj =request.getSession().getAttribute(Constants.session_openid);
		if(obj==null){
			throw new AppRuntimeException("用户未登录");
		}
		return (String)obj;
	}
	
	/**
	 * 设置登录会员账号
	 * @param request
	 * @param userResult
	 */
	public static void setLoginMember(HttpServletRequest request,UserResult userResult){
		request.getSession().setAttribute(Constants.session_member, userResult);
	}
	
	/**
	 * 获取登录会员账号
	 * @param request
	 * @return
	 */
	public static UserResult getLoginMember(HttpServletRequest request){
		Object obj =request.getSession().getAttribute(Constants.session_member);
		if(obj==null){
			throw new AppRuntimeException("用户未登录");
		}
		return (UserResult)obj;
	}
}
