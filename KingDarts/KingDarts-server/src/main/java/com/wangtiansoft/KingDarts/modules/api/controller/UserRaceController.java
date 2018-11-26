package com.wangtiansoft.KingDarts.modules.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.race.service.RaceEnterforUserService;
import com.wangtiansoft.KingDarts.modules.race.service.RaceInfoService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.entity.RaceEnterforUser;
import com.wangtiansoft.KingDarts.persistence.entity.RaceInfo;
import com.wangtiansoft.KingDarts.results.core.RaceEnterforUserResult;
import com.wangtiansoft.KingDarts.results.core.RaceInfoResult;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/api/userrace")
public class UserRaceController extends BaseController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private RaceInfoService raceInfoService;
	
	@Autowired
	private RaceEnterforUserService raceEnterforUserService;
	
	/**
	 * 赛事列表
	 * @param page
	 * @param rows
	 * @param dstatus 0.待报名,1.报名中，2进行中，3已结束，4已取消，5已解散  
	 * @return
	 */
	@RequestMapping(value="raceList", method = RequestMethod.GET)
	public @ResponseBody ApiResult  raceList(final String page,final String rows,final String dstatus) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> paramMap=new HashMap<>();
					if(dstatus!=null) {
						paramMap.put("dstatus", Integer.parseInt(dstatus));
					}
					PageBean pageBean=new PageBean();
					pageBean.setPage(Integer.parseInt(page));
					pageBean.setRows(Integer.parseInt(rows));
					Map<String,Object> map = new HashMap<>();
					
					Map<String,Object> Page=new HashMap<>();
					Page<Map> list=raceInfoService.queryRaceInfoList(paramMap,pageBean);
					Page.put("total", list.getTotal());
					Page.put("pageSize", list.getPageSize());
					Page.put("pageNum", list.getPageNum());
					Page.put("pages", list.getPages());
					Page.put("list", list);
					map.put("racePage", Page);
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 比赛详情
	 * @param raceno
	 * @return
	 */
	@RequestMapping("/racedetails")
	public@ResponseBody ApiResult racedetails(final String raceno) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String uuid) throws Exception {
				int rankingStatus = 0;//是否发布排名
				UserResult user=userService.getUserByUuid(uuid);
				
				Integer userstatus = 0;
				Example example=new Example(RaceEnterforUser.class);
		        Criteria cr= example.createCriteria();
		        cr.andEqualTo("user_id", user.getUuid());
		        cr.andEqualTo("raceno", raceno);
		        RaceEnterforUser raceEnterforUser = raceEnterforUserService.findOneByExample(example);
		        //判断是否报名比赛
		        if(raceEnterforUser!=null) {
		        	//已报名
		        	userstatus = 1;
		        }
				
				Map<String,Object> map=new HashMap<>();
		        RaceInfoResult raceInfoResult = raceInfoService.getRaceByRaceNo(raceno);
		        
		        if(raceInfoResult.getDstatus()==3) {
		        	//已结束
		        	List<RaceEnterforUserResult> raceEnterforUserResultList = raceEnterforUserService.getRaceUserByRaceno(raceno);
		        	if(raceEnterforUserResultList!=null) {
		        		if(raceEnterforUserResultList.get(0).getRanking()==null) {
		        			rankingStatus = 1;
		        		}
		        	}
			        //用户TOP榜
		        	map.put("raceEnterforUserList", raceEnterforUserResultList);
		        }

		        map.put("rankingStatus", rankingStatus);
		        map.put("raceInfo", raceInfoResult);
		        map.put("userstatus", userstatus);
		        map.put("username", user.getUsername());
		        map.put("mobile", user.getMobile());
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 我的比赛
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="myrace", method = RequestMethod.GET)
	public @ResponseBody ApiResult  myrace(final String page,final String rows) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> paramMap=new HashMap<>();
					//获取用户信息
					UserResult user=userService.getUserByUuid(uuid);
					
					paramMap.put("user_id", user.getUuid());
					PageBean pageBean=new PageBean();
					pageBean.setPage(Integer.parseInt(page));
					pageBean.setRows(Integer.parseInt(rows));
					Map<String,Object> map = new HashMap<>();
					
					Map<String,Object> Page=new HashMap<>();
					//获取我的比赛分页列表
					Page<Map> list=raceInfoService.queryMyRaceInfoList(paramMap,pageBean);
					Page.put("total", list.getTotal());
					Page.put("pageSize", list.getPageSize());
					Page.put("pageNum", list.getPageNum());
					Page.put("pages", list.getPages());
					Page.put("list", list);
					map.put("racePage", Page);
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 报名金额为0的比赛
	 * @param raceno
	 * @return
	 */
	@RequestMapping("/raceEnterfor")
	public@ResponseBody ApiResult raceEnterfor(final String raceno) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String uuid) throws Exception {
				UserResult user=userService.getUserByUuid(uuid);
				raceEnterforUserService.addEnterfor(raceno, user.getUuid());
				Map<String,Object> map=new HashMap<>();
				return map;
			}
		});
		return result;
	}
	
}
