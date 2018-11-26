package com.wangtiansoft.KingDarts.modules.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.DateUtil;
import com.wangtiansoft.KingDarts.config.lftpay.api.util.StringUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.club.service.ClubMonthRankService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubPlaceService;

@Controller
@RequestMapping("/api/clubRank")
public class ClubRanKAPIController  extends BaseController{

	@Autowired
	private ClubPlaceService clubPlaceService;
	
	@Autowired
	private ClubMonthRankService clubMonthRankService;
	
	/**
	 * 俱乐部竞技榜
	 * @param provice 省名称
	 * @param rank_time 时间
	 * @param page 页数
	 * @param rows 每页显示条数
	 * @return
	 */
	@RequestMapping(value="athletics", method = RequestMethod.GET)
	public @ResponseBody ApiResult  athletics(final String province,
			final String rank_time,final int page,final int rows) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					//排名列表
					Map<String,Object> queryRank=new HashMap<>();
					queryRank.put("province", province);
					PageBean pageBean=new PageBean();
					pageBean.setPage(page);
					pageBean.setRows(rows);
					Page<Map> mapUser=null;
					Map<String,Object> rankPage=new HashMap<>();
					Page<Map> mapRank=null;
					if(StringUtil.isNotEmpty(rank_time)) {
						if(DateUtil.checkTimeInMonth(rank_time)) {
							//俱乐部当月竞技榜排名
							mapRank=clubPlaceService.queryAthleticsClubInfoPageList(queryRank,pageBean);
						}else {
							//俱乐部历史月份竞技榜排名
							queryRank.put("rank_time", rank_time);
							mapRank=clubMonthRankService.queryAthleticsClubMonthRankInfoPageList(queryRank,pageBean);
						}
					}else {
						//俱乐部当月竞技榜排名
						mapRank=clubPlaceService.queryAthleticsClubInfoPageList(queryRank,pageBean);
					}
					rankPage.put("total", mapRank.getTotal());
					rankPage.put("pageSize", mapRank.getPageSize());
					rankPage.put("pageNum", mapRank.getPageNum());
					rankPage.put("pages", mapRank.getPages());
					rankPage.put("rankList", mapRank);
					map.put("rankPage", rankPage);
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 俱乐部实力榜
	 * @param provice 省名称
	 * @param rank_time 时间
	 * @param page 页数
	 * @param rows 每页显示条数
	 * @return
	 */
	@RequestMapping(value="strengthList", method = RequestMethod.GET)
	public @ResponseBody ApiResult  strengthList(final String province,
			final String rank_time,final int page,final int rows) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					//排名列表
					Map<String,Object> queryRank=new HashMap<>();
					queryRank.put("province", province);
					PageBean pageBean=new PageBean();
					pageBean.setPage(page);
					pageBean.setRows(rows);
					Page<Map> mapUser=null;
					Map<String,Object> rankPage=new HashMap<>();
					Page<Map> mapRank=null;
					if(StringUtil.isNotEmpty(rank_time)) {
						if(DateUtil.checkTimeInMonth(rank_time)) {
							//俱乐部当月竞技榜排名
							mapRank=clubPlaceService.queryStrengthClubInfoPageList(queryRank,pageBean);
						}else {
							//俱乐部历史月份竞技榜排名
							queryRank.put("rank_time", rank_time);
							mapRank=clubMonthRankService.queryStrengthClubMonthRankInfoPageList(queryRank,pageBean);
						}
					}else {
						//俱乐部当月竞技榜排名
						mapRank=clubPlaceService.queryStrengthClubInfoPageList(queryRank,pageBean);
					}
					rankPage.put("total", mapRank.getTotal());
					rankPage.put("pageSize", mapRank.getPageSize());
					rankPage.put("pageNum", mapRank.getPageNum());
					rankPage.put("pages", mapRank.getPages());
					rankPage.put("rankList", mapRank);
					map.put("rankPage", rankPage);
					return map;
				}
			});
		return result;
	}
	
	
}
