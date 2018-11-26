package com.wangtiansoft.KingDarts.modules.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.common.utils.SmsUtil;
import com.wangtiansoft.KingDarts.config.utils.Digests;
import com.wangtiansoft.KingDarts.config.utils.Encodes;
import com.wangtiansoft.KingDarts.config.utils.MD5Util;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.agent.service.AgentInfoService;
import com.wangtiansoft.KingDarts.modules.api.token.Token;
import com.wangtiansoft.KingDarts.modules.api.token.TokenManager;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.merchantAccount.service.MerchantAccountService;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.UserResult;

@Controller
@RequestMapping("/api/wx")
public class WexinLoginController extends BaseController  {

	private final static Logger logger = LoggerFactory.getLogger(WexinLoginController.class);
	
	@Resource
	private MerchantAccountService merchantAccountService;
	
	@Resource
	private ClubInfoService clubInfoService;
	
	@Resource
	private AgentInfoService agentInfoService;
	
	@Resource
	private TokenManager tokenManager;
	
	@Autowired
    private RedisTemplate redisTemplate;

	private Long EXPIRES_SECONDS = new Long(300);//验证码超时时间
	private String verifyCodeCache = "verifyCodeCache";
	private static String AUTHORIZATION = "x-access-token";
	
	/**
	 * 俱乐部登录
	 * @param loginName
	 * @param password
	 * @param response
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public @ResponseBody ApiResult loginManager(final String openid,final String loginName,final String password,HttpServletResponse response) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				if(StringUtils.isEmpty(loginName)) {
					throw new AppRuntimeException("账号不能为空！");
				}
				if(StringUtils.isEmpty(password)) {
					throw new AppRuntimeException("密码不能为空！");
				}
				if(StringUtils.isEmpty(openid)) {
					throw new AppRuntimeException("系统错误，请联系管理员！");
				}
				// 校验用户名密码
				MerchantAccount merchantAccount = merchantAccountService.getUserByLoginName(loginName);
				//	user.getMerchant() //1：金捷报 2:俱乐部3：代理商
				if (merchantAccount == null) {
					throw new AppRuntimeException("账号或密码错误.");
				}
				if(merchantAccount.getMerno()=="11000059") {
					throw new AppRuntimeException("账号或密码错误.");
				}
				
				MD5Util md5Util = new MD5Util();
				if(merchantAccount.getCreatetime()==null){
					String pwd = merchantAccountService.getOldSysUserPwd(merchantAccount.getMerno());
					if(StringUtils.isNotEmpty(pwd)){
						if(validatePassword(password, pwd)){
							//旧密码校验通过
							MerchantAccount entity = new MerchantAccount();
							entity.setId(merchantAccount.getId());
							entity.setPassword(md5Util.MD5Encode(password, "utf-8"));
							entity.setCreatetime(new Date());
							merchantAccountService.updateByIdSelective(entity);
							
							merchantAccount = merchantAccountService.getUserByLoginName(loginName);//重新更新查询
						}
					}
				}
				
				if(!md5Util.MD5Encode(password, "utf-8").equals(merchantAccount.getPassword())){
					throw new AppRuntimeException("账号或密码错误.");
				}
				Integer count = merchantAccountService.updateOpenidByMerno(openid, merchantAccount.getMerno());
				if(count>1) {
					throw new AppRuntimeException("系统错误，请联系管理员！");
				}
				Token model = tokenManager.createToken(merchantAccount.getMerno());
				Map<String,Object> map = new HashMap<>();
				map.put("token", model.getUserId()+"_"+model.getToken());
				map.put("type", merchantAccount.getMertype());
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	private boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, 1024);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 用户手机校验
	 * @param loginName
	 * @param password
	 * @param response
	 */
	@RequestMapping(value="/verifycode", method = RequestMethod.POST)
	public @ResponseBody ApiResult verifyCode(final String mobile,HttpServletResponse response) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				if(StringUtils.isEmpty(mobile)) {
					throw new AppRuntimeException("手机号不能为空");
				}
				
				Random rand=new Random();
				int code=100000+rand.nextInt(900000);
				redisTemplate.boundValueOps(verifyCodeCache+mobile).set(code+"", EXPIRES_SECONDS, TimeUnit.SECONDS);
				
				SendSmsResponse response = SmsUtil.sendCodeSms(mobile,code+"");
				if(!"OK".equals(response.getCode())){
					throw new AppRuntimeException("短信发送失败");
				}
				Map<String,Object> map = new HashMap<>();
				map.put("msg", "发送成功");
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 修改密码
	 * @param phone
	 * @param code
	 * @param password
	 * @param response
	 */
	@RequestMapping(value="/changepwd", method = RequestMethod.POST)
	public @ResponseBody ApiResult changepwd(final String mobile,final String code,final String password,HttpServletResponse response) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				if(StringUtils.isEmpty(mobile)) {
					throw new AppRuntimeException("手机号不能为空！");
				}
				if(StringUtils.isEmpty(code)) {
					throw new AppRuntimeException("验证码不能为空！");
				}
				if(StringUtils.isEmpty(password)) {
					throw new AppRuntimeException("密码不能为空！");
				}
				Map<String,Object> mapResult = new HashMap<>();
				Object obj=redisTemplate.boundValueOps(verifyCodeCache+mobile).get();
				if(obj!=null&&StringUtils.equals(obj.toString(), code)){
					MerchantAccount merchantAccount = merchantAccountService.getUserByMobile(mobile);
					if(merchantAccount!=null) {
						MD5Util md5Util = new MD5Util();
						merchantAccountService.updatePasswordById(md5Util.MD5Encode(password, "utf-8"), mobile, merchantAccount.getId());
						if(merchantAccount.getMertype()==1) {
							clubInfoService.updatePasswordByCno(md5Util.MD5Encode(password, "utf-8"), merchantAccount.getMerno());
						}else {
							agentInfoService.updatePasswordByAgno(md5Util.MD5Encode(password, "utf-8"), merchantAccount.getMerno());
						}
					}else {
						throw new AppRuntimeException("手机号输入有误！");
					}
					return mapResult;
				}else {
					throw new AppRuntimeException("验证码错误！");
				}
			}
		});
		return result;
	}
	
	/**
	 * 登出
	 * @param response
	 */
	@RequestMapping(value="logout")
	public @ResponseBody ApiResult logout(HttpServletResponse response) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				String authorization = request.getHeader(AUTHORIZATION);
				Token model = tokenManager.getToken(authorization);
				tokenManager.deleteToken(model.getUserId());
				return null;
			}
		});
		return result;
	}
	
}
