package com.wangtiansoft.KingDarts.modules.api.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.common.utils.DateUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.robFloors.service.RobFloorsService;
import com.wangtiansoft.KingDarts.modules.user.service.UserBalanceService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.entity.RobFloors;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.UserResult;

@Controller
@RequestMapping("/api/floor")
public class RobFloorsAPIController  extends BaseController{

	@Resource
	private RobFloorsService robFloorsService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private UserBalanceService userBalanceService;
	
	/**
	 * 用户日 月 年 消费点数信息 获得奖励信息
	 * @return
	 */
	@RequestMapping(value="robFLoorInfo", method = RequestMethod.GET)
	public @ResponseBody ApiResult  robFLoorInfo() {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					//用户日 月 年 消费点数信息
					Map<String,Object> paramMap=new HashMap<>();
					paramMap.put("user_id", uuid);
					paramMap.put("type", 2);
					paramMap.put("log_time_start", DateUtil.mongoDateToSave(new Date()));
					Float dayBalance=userBalanceService.getUserBalanceCount(paramMap);
					if(dayBalance==null) {
						dayBalance=0f;
					}
					map.put("dayCount", Math.abs(dayBalance));
					paramMap.put("log_time_start", DateUtil.mongoDateToSave(DateUtil.dateToWeek(new Date())));
					Float weekBalance=userBalanceService.getUserBalanceCount(paramMap);
					if(weekBalance==null) {
						weekBalance=0f;
					}
					map.put("weekCount", Math.abs(weekBalance));
					paramMap.put("log_time_start", DateUtil.mongoDateToSave(DateUtil.dateToMonth(new Date())));
					Float monthBalance=userBalanceService.getUserBalanceCount(paramMap);
					if(monthBalance==null) {
						monthBalance=0f;
					}
					map.put("monthCount", Math.abs(monthBalance));
					//获取抢楼活动奖励信息
					Map<String,Object> paramRobMap=new HashMap<>();
					paramRobMap.put("user_id", uuid);
					paramRobMap.put("status", 1);
					paramRobMap.put("create_time_start", DateUtil.mongoDateToSave(new Date()));
					List<Map> dayPrizeMap=robFloorsService.queryRobFloorsList(paramRobMap);
					map.put("dayPrizeMap", dayPrizeMap);
					paramRobMap.put("status", 2);
					paramRobMap.put("create_time_start", DateUtil.mongoDateToSave(DateUtil.dateToWeek(new Date())));
					List<Map> weekPrizeMap=robFloorsService.queryRobFloorsList(paramRobMap);
					map.put("weekPrizeMap", weekPrizeMap);
					paramRobMap.put("status", 3);
					paramRobMap.put("create_time_start", DateUtil.mongoDateToSave(DateUtil.dateToMonth(new Date())));
					List<Map> monthPrizeMap=robFloorsService.queryRobFloorsList(paramRobMap);
					map.put("monthPrizeMap", monthPrizeMap);
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 获得奖励
	 * @return
	 */
	@RequestMapping(value="robFLoorReceive", method = RequestMethod.GET)
	public @ResponseBody ApiResult  robFLoorReceive(final Integer expense_point,final Integer  point,final  Integer status) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					UserResult user=userService.getUserByUuid(uuid);
					RobFloors robFloors=new RobFloors();
					robFloors.setExpense_point(expense_point);
					robFloors.setPoint(point);
					robFloors.setStatus(status);
					robFloors.setUser_id(uuid);
					robFloors.setUseraccount(user.getUsername());
					robFloors.setUser_nickname(user.getNickname());
					robFloorsService.save(robFloors);
					//更新个人点数和添加点数记录
					userService.balanceChange(uuid,new BigDecimal(point), "抢楼活动领取奖励");
					
					return map;
				}
			});
		return result;
	}
	
	
}
