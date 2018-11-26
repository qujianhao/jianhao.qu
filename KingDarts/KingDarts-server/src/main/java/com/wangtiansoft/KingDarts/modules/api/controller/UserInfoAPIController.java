package com.wangtiansoft.KingDarts.modules.api.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.common.utils.SmsUtil;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.pay.service.CommissionService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.UserPointsResult;
import com.wangtiansoft.KingDarts.results.core.UserResult;

@Controller
@RequestMapping("/api/userinfo")
public class UserInfoAPIController  extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommissionService commissionService;
	
	@Autowired
    private RedisTemplate redisTemplate;

	private Long EXPIRES_SECONDS = new Long(300);//验证码超时时间
	
	private String verifyCodeCache = "verifyCodeCache";

	protected Page<Map> pageRecords;
	
	/**
	 * 用户信息初始化
	 * @return
	 */
	@RequestMapping(value="userInfo", method = RequestMethod.GET)
	public @ResponseBody ApiResult  userInfo() {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					map.put("userInfo", userService.getUserByUuid(uuid));
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 我的积分列表
	 * @return
	 */
	@RequestMapping(value="pointsList", method = RequestMethod.GET)
	public @ResponseBody ApiResult  pointsList(final String page,final String rows) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					Map<String,Object> paramMap = new HashMap<>();
					PageBean pageBean=new PageBean();
					paramMap.put("user_id", uuid);
					paramMap.put("orderSql", "log_time desc");
					pageBean.setPage(Integer.parseInt(page));
					pageBean.setRows(Integer.parseInt(rows));
					Page<Map> pointsPage=userService.queryUserPointsPageList(paramMap, pageBean);
					Map<String,Object> mapList=new HashMap<>();
					mapList.put("pointsList", pointsPage);
					mapList.put("total", pointsPage.getTotal());
					mapList.put("pageSize", pointsPage.getPageSize());
					mapList.put("pageNum", pointsPage.getPageNum());
					mapList.put("pages", pointsPage.getPages());
					map.put("pointsPage",mapList);
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 个人信息保存
	 * @return
	 */
	@RequestMapping(value="saveUserInfo", method = RequestMethod.GET)
	public @ResponseBody ApiResult  saveUserInfo(final String headimgurl,final String username,final String nickname,
			final String id_no,final String province,final String city,final String areas,final String address) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					UserResult result=userService.getUserByUuid(uuid);
					result.setHeadimgurl(headimgurl);
					result.setUsername(username);
					result.setNickname(nickname);
					result.setId_no(id_no);
					result.setProvince(province);
					result.setCity(city);
					result.setAreas(areas);
					result.setAddress(address);
					User entity=new User();
					BeanUtil.copyProperties(result, entity);
					userService.updateByIdSelective(entity);
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 用户手机校验
	 * @param mobile
	 * @return 
	 */
	@RequestMapping(value="verifycode", method = RequestMethod.GET)
	public @ResponseBody ApiResult verifyCode(final String mobile) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String uuid) throws Exception {
				if(StringUtils.isEmpty(mobile)) {
					throw new AppRuntimeException("手机号不能为空");
				}
				
				Random rand=new Random();
				int code=100000+rand.nextInt(900000);
				redisTemplate.boundValueOps(verifyCodeCache+mobile).set(code+"", EXPIRES_SECONDS, TimeUnit.SECONDS);
				//发送短信
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
	 * 个人信息手机信息保存 
	 * @param mobile
	 * @param code 
	 * @return
	 */
	@RequestMapping(value="saveUserMobile", method = RequestMethod.GET)
	public @ResponseBody ApiResult  saveUserMobile(final String mobile,final String code) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					if(StringUtils.isEmpty(mobile)) {
						throw new AppRuntimeException("手机号不能为空！");
					}
					if(StringUtils.isEmpty(code)) {
						throw new AppRuntimeException("验证码不能为空！");
					}
					Map<String,Object> mapResult = new HashMap<>();
					Object obj=redisTemplate.boundValueOps(verifyCodeCache+mobile).get();
					if(obj!=null && StringUtils.equals(obj.toString(), code)){
						UserResult result=userService.getUserByUuid(uuid);
						//验证短信验证码
						result.setMobile(mobile);
						User entity=new User();
						BeanUtil.copyProperties(result, entity);
						userService.updateByIdSelective(entity);
						return mapResult;
					}else {
						throw new AppRuntimeException("验证码错误！");
					}
				}
			});
		return result;
	}
	
	/**
	 * 充值记录
	 * @return
	 */
	@RequestMapping(value="userPayList", method = RequestMethod.POST)
	public @ResponseBody ApiResult  payList(final PageBean pageBean) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					Map<String,Object> paramMap = new HashMap<>();
					paramMap.put("user_id", uuid);
					paramMap.put("orderSql", "log_time desc");
					paramMap.put("type", Constants.amount_type_recharge);//充值
					Map<String,Object> mapList = new HashMap<>();
					Page<Map> balanceList=userService.queryUserBalancePageList(paramMap, pageBean);
					for(Map m:balanceList.getResult()){
						if(!m.get("remark").equals("报名比赛")) {
							m.put("amount", m.get("balance"));
						}
					}
					mapList.put("balanceList", balanceList);
					mapList.put("total", balanceList.getTotal());
					mapList.put("pageSize", balanceList.getPageSize());
					mapList.put("pageNum", balanceList.getPageNum());
					mapList.put("pages", balanceList.getPages());
					map.put("balancePage",mapList);
					
					/*Map<String,Object> mapList = new HashMap<>();
//					PageBean pageBean=new PageBean();
//					pageBean.setPage(Integer.parseInt(page));
//					pageBean.setRows(Integer.parseInt(rows));
					Page<Map> commissionList=commissionService.queryUserCommissionPageList(paramMap, pageBean);
					mapList.put("commissionList", commissionList);
					mapList.put("total", commissionList.getTotal());
					mapList.put("pageSize", commissionList.getPageSize());
					mapList.put("pageNum", commissionList.getPageNum());
					mapList.put("pages", commissionList.getPages());
					map.put("commissionPage",mapList);*/
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 消费明细
	 * @return
	 */
	@RequestMapping(value="balanceList", method = RequestMethod.GET)
	public @ResponseBody ApiResult  balanceList(final PageBean pageBean) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					Map<String,Object> paramMap = new HashMap<>();
					paramMap.put("user_id", uuid);
					paramMap.put("orderSql", "log_time desc");
					paramMap.put("type", Constants.amount_type_consume);//消费
					Map<String,Object> mapList = new HashMap<>();
					Page<Map> balanceList=userService.queryUserBalancePageList(paramMap, pageBean);
					mapList.put("balanceList", balanceList);
					mapList.put("total", balanceList.getTotal());
					mapList.put("pageSize", balanceList.getPageSize());
					mapList.put("pageNum", balanceList.getPageNum());
					mapList.put("pages", balanceList.getPages());
					map.put("balancePage",mapList);
					return map;
				}
			});
		return result;
	}
}
