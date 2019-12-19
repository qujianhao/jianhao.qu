package com.wangtiansoft.KingDarts.modules.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.DateUtil;
import com.wangtiansoft.KingDarts.common.utils.MapUtils;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.config.lftpay.api.util.StringUtil;
import com.wangtiansoft.KingDarts.core.extensions.token.service.impl.TokenServiceImpl;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubPlaceService;
import com.wangtiansoft.KingDarts.modules.user.service.NineGameService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.results.core.UserResult;

@Controller
@RequestMapping("/api/userindex")
public class UserIndexAPIController  extends BaseController{

	private final Logger _logger = LoggerFactory.getLogger(UserIndexAPIController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClubPlaceService clubPlaceService;
	
	@Autowired 
	private ClubInfoService clubInfoService;
	
	@Autowired
	private NineGameService nineGameService;
	
	/**
	 * 首页初始化
	 * @param longitude 经度
	 * @param latitude  纬度
	 * @return
	 */
	@RequestMapping(value="index", method = RequestMethod.GET)
	public @ResponseBody ApiResult  index(final String longitude,final String latitude) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					//用户信息和当月我的排名
					Map<String,Object> queryUser=new HashMap<>();
					UserResult user=userService.getUserByUuid(uuid);
					map.put("user", user);
					
					queryUser.put("uuid", uuid);
					PageBean pageBean=new PageBean();
					pageBean.setPage(0);
					pageBean.setRows(1);
					Page<Map> mapUser=userService.queryUserRankByPointsPageList(queryUser,pageBean);
					if(mapUser.getTotal()>0) {
					map.put("userRank", mapUser.getResult().get(0).get("rank"));
					}else {
					map.put("userRank", 0);
					}
					//附件的飞镖俱乐部
					Map<String,Object> queryMap=new HashMap<>();
					queryMap.put("longitude", longitude);
					queryMap.put("latitude", latitude);
					Map clubPlace=clubPlaceService.queryNearClubPlaceInfo(queryMap);
					
//					clubPlace.put("distance", MapUtils.GetDistance(Double.parseDouble(longitude), Double.parseDouble(latitude), 
//							Double.parseDouble(clubPlace.get("longitude").toString()), Double.parseDouble(clubPlace.get("latitude").toString())));
					map.put("clubInfo", clubPlace);
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 积分排行榜
	 * @param provice 省名称
	 * @param game_type 游戏类型
	 * @param rank_time 时间
	 * @param page 页数
	 * @param rows 每页显示条数
	 * @return
	 */
	@RequestMapping(value="rankingList", method = RequestMethod.GET)
	public @ResponseBody ApiResult  rankingList(final String province,final String game_type,
			final String rank_time,final PageBean pageBean) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					//排名列表
					Map<String,Object> queryRank=new HashMap<>();
					queryRank.put("province", StringUtils.isNotEmpty(province)?province.replace("省", "").replace("自治区", ""):province);
					queryRank.put("game_type", game_type);
//					PageBean pageBean=new PageBean();
//					pageBean.setPage(Integer.parseInt(page));
//					pageBean.setRows(Integer.parseInt(rows));
					Page<Map> mapUser=null;
					Map<String,Object> rankPage=new HashMap<>();
					Page<Map> mapRank=null;
					_logger.info("rank list param -------province={},game_type={},rank_time={}",province,game_type,rank_time);
					// 九镖游戏查询
					if("s010".equals(game_type)) {
						PageBean pageBean=new PageBean();
						pageBean.setPage(0);
						pageBean.setRows(100);
						int rank = 0;
						if (!StringUtil.isNotEmpty(rank_time)) {
							String subString = rank_time;
							String month = subString.substring(0, 7);
							queryRank.put("createTime", month);
							rank = nineGameService.getRank(uuid, month);
						}else {
							queryRank.put("createTime", rank_time);
							rank = nineGameService.getRank(uuid, rank_time);
						}
						mapRank = nineGameService.getRankListByMonth(queryRank, pageBean);
						map.put("userRank", rank);
						
					}else {
						if(StringUtil.isNotEmpty(rank_time)) {
							if(DateUtil.checkTimeInMonth(rank_time)) {
								mapRank=userService.queryUserRankByPointsPageList(queryRank,pageBean);
								//用户信息和当月排行
								queryRank.put("uuid", uuid);
								PageBean pageBean=new PageBean();
								pageBean.setPage(0);
								pageBean.setRows(1);
								mapUser=userService.queryUserRankByPointsPageList(queryRank,pageBean);
							}else {
								queryRank.put("rank_time", rank_time);
								mapRank=userService.queryUserRankByMonthPointsPageList(queryRank,pageBean);
								//用户信息和历史月份排行
								queryRank.put("uuid", uuid);
								PageBean pageBean=new PageBean();
								pageBean.setPage(0);
								pageBean.setRows(1);
								mapUser=userService.queryUserRankByMonthPointsPageList(queryRank,pageBean);
							}
						}else {
							mapRank=userService.queryUserRankByPointsPageList(queryRank,pageBean);
							//用户信息和当月排行
							queryRank.put("uuid", uuid);
							PageBean pageBean=new PageBean();
							pageBean.setPage(0);
							pageBean.setRows(1);
							mapUser=userService.queryUserRankByPointsPageList(queryRank,pageBean);
						}
						if(mapUser.getTotal()>0&&mapUser.getResult()!=null&&mapUser.getResult().size()>0) {
							map.put("userRank", mapUser.getResult().get(0));
						}else {
						    map.put("userRank", 0);
						}
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
	 * 俱乐部产所列表
	 * @param clubName 俱乐部名称
	 * @param longitude 经度
	 * @param latitude  纬度
	 * @param page 页数
	 * @param rows 每页显示条数
	 * @return
	 */
	@RequestMapping(value="clubMapList", method = RequestMethod.GET)
	public @ResponseBody ApiResult  clubList(final String cname,final String longitude,final String latitude,
			final PageBean pageBean) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					//附件的飞镖俱乐部列表
					Map<String,Object> queryMap=new HashMap<>();
					queryMap.put("longitude", longitude);
					queryMap.put("latitude", latitude);
					queryMap.put("cname", cname);
//					PageBean pageBean=new PageBean();
//					pageBean.setPage(Integer.parseInt(page));
//					pageBean.setRows(Integer.parseInt(rows));
					Map<String,Object> clubPlacePage=new HashMap<>();
					Page<Map> clubPlaceList=clubPlaceService.queryNearClubPlaceInfoPageList(queryMap,pageBean);
					/*if(clubPlaceList!=null && clubPlaceList.size()>0) {
						for (Map mapPlace : clubPlaceList) {
							if(mapPlace.get("longitude")!=null && mapPlace.get("latitude").toString()!=null ) {
								mapPlace.put("distance", MapUtils.GetDistance(Double.parseDouble(longitude), Double.parseDouble(latitude), 
										Double.parseDouble(mapPlace.get("longitude").toString()), Double.parseDouble(mapPlace.get("latitude").toString())));
							}else {
								mapPlace.put("distance", "未知"); 
							}
						}
					}*/
					clubPlacePage.put("total", clubPlaceList.getTotal());
					clubPlacePage.put("pageSize", clubPlaceList.getPageSize());
					clubPlacePage.put("pageNum", clubPlaceList.getPageNum());
					clubPlacePage.put("pages", clubPlaceList.getPages());
					clubPlacePage.put("clubPlaceList", clubPlaceList);
					map.put("clubPlacePage", clubPlacePage);
					return map;
				}
			});
		return result;
	}
	
	
	/**
	 * 俱乐部详情
	 * @param id 俱乐部产所id
	 * @return
	 */
	@RequestMapping(value="clubPlaceInfo", method = RequestMethod.GET)
	public @ResponseBody ApiResult  clubPlaceInfo(final String id) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					Map clubInfoMap=clubInfoService.getClubInfoView(Integer.parseInt(id));
					map.put("ClubInfo", clubInfoMap);
					return map;
				}
			});
		return result;
	}
}
