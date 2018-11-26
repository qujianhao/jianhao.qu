package com.wangtiansoft.KingDarts.modules.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.common.utils.DateUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.medal.service.MedalService;
import com.wangtiansoft.KingDarts.modules.medalWinner.service.MedalWinnerService;
import com.wangtiansoft.KingDarts.modules.signin.service.SigninService;
import com.wangtiansoft.KingDarts.modules.signin.service.SigninSetService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.entity.Medal;
import com.wangtiansoft.KingDarts.persistence.entity.MedalWinner;
import com.wangtiansoft.KingDarts.persistence.entity.Signin;
import com.wangtiansoft.KingDarts.persistence.entity.SigninSet;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.MedalResult;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/api/signin")
public class UserSigninAPIController  extends BaseController{

	@Autowired
	private SigninSetService signinSetService;
	
	@Autowired
	private SigninService signinService;
	
	@Autowired
	private UserService userService;
	
	@Resource
	private MedalWinnerService medalWinnerService;
	
	@Resource
	private MedalService medalService;
	
	/**
	 * 签到首页初始化
	 * @return
	 */
	@RequestMapping(value="index", method = RequestMethod.GET)
	public @ResponseBody ApiResult  index() {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					
					//已经签到
					List<String> dates=DateUtil.getWeekDay();
					Example exampleSign=new Example(Signin.class);
					exampleSign.createCriteria().andIn("signin_date",dates).andEqualTo("user_id", uuid);
					List<Signin> signinList=signinService.findAllByExample(exampleSign);
					map.put("signinList", signinList);
					
					//签到设置
					Example exampleSignSet=new Example(SigninSet.class);
					List<SigninSet> signinSetList=signinSetService.findAllByExample(exampleSignSet);
					map.put("signinSetList", signinSetList);
					
					//勋章列表和百分比
					//更新勋章
					//获得未获得勋章的勋章规则
					Example example=new Example(MedalWinner.class);
					example.createCriteria().andEqualTo("user_id",uuid);
					List<MedalWinner> medalWinners=medalWinnerService.findAllByExample(example);
					List<Integer> ids=new ArrayList<>();
					for (MedalWinner medalWinner : medalWinners) {
						ids.add(medalWinner.getMedal_id());
					}
					Example exampleM=new Example(Medal.class);
					Criteria criteria=exampleM.createCriteria();
					criteria.andEqualTo("is_valid",1);
					criteria.andEqualTo("is_delete",0);
					if(ids.size()>0) {
						criteria.andNotIn("id", ids);
					}
					List<Medal> medals=medalService.findAllByExample(exampleM);
					List<MedalResult> medalWinnerListLeft=new ArrayList<>();
					//查询是否满足勋章规则
					if(medals!=null) {
						for (Medal medal : medals) {
							Map<String,Object> paramMap=new HashMap<>();
							paramMap.put("user_id", uuid);
							paramMap.put("sqlStr", medal.getSql_str());
							Map mapMedal=medalService.selectMedalTerm(paramMap);
							//满足勋章规则 添加勋章 
							if((Long) mapMedal.get("count")>=medal.getComplete_times()) {
								MedalWinner entity=new MedalWinner();
								entity.setIs_receive(0);
								entity.setMedal_id(medal.getId());
								entity.setMedal_name(medal.getMedal_name());
								entity.setMedal_url(medal.getMedal_url());
								entity.setUser_id(uuid);
								entity.setPrize_point(medal.getPrize_point());
								UserResult user=userService.getUserByUuid(uuid);
								entity.setUseraccount(user.getNickname());
								medalWinnerService.save(entity);
							}else {
							   //不满足选择规则的 算出百分比
								MedalResult medalResult=new MedalResult();
								BeanUtil.copyProperties(medal, medalResult);
								//设置完成百分比
								String percentComplate=(Long) mapMedal.get("count")*100/medal.getComplete_times()+"%";
								medalResult.setPercentComplate(percentComplate);
								medalWinnerListLeft.add(medalResult);
							}
						}
					}
					Map<String,Object> queryMap=new HashMap<>();
					queryMap.put("user_id", uuid);
					List<Map> medalWinnerList=medalWinnerService.queryMedalWinnerList(queryMap);
					map.put("medalWinnerList", medalWinnerList);
					map.put("medalWinnerListLeft", medalWinnerListLeft);
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 点数领取
	 * @param blance 点数
	 * @return
	 */
	@RequestMapping(value="addSigninBalance", method = RequestMethod.GET)
	public @ResponseBody ApiResult  addSigninBalance(final String signin_day,final String signin_point) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
				    Date date=	DateUtil.stringSingleToDate(DateUtil.mongoDateToSave(new Date()));
					Signin entity=new Signin();
					Example exampleSign=new Example(Signin.class);
					exampleSign.createCriteria().andEqualTo("signin_date",date).andEqualTo("user_id", uuid);
					List<Signin> signinList=signinService.findAllByExample(exampleSign);
					if(signinList!=null && signinList.size()>0) {
						 throw new AppRuntimeException("已经签到无法再签到了");
					}
					entity.setUser_id(uuid);
					entity.setSignin_day(Integer.parseInt(signin_day));
					entity.setSignin_point(Integer.parseInt(signin_point));
					entity.setSignin_date(date);
					entity.setSignin_point(Integer.parseInt(signin_point));
					signinService.save(entity);
					//点数消耗记录
					userService.balanceChange(uuid,new BigDecimal(signin_point), "签到领取奖励");
					
					return map;
				}
			});
		return result;
	}
	
}
