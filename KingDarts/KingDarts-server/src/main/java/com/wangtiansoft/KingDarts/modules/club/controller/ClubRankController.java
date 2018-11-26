package com.wangtiansoft.KingDarts.modules.club.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.DateUtil;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.config.lftpay.api.util.StringUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.club.service.ClubMonthRankService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubPlaceService;

@Controller
@RequestMapping("/a/clubrank")
public class ClubRankController extends BaseController  {
	
	@Resource
	private ClubPlaceService clubPlaceService;
	
	@Resource
	private ClubMonthRankService clubMonthRankService;
	
    //  列表
    @PreAuthorize("hasPermission('','_CLUBRANK:VIEW')")
    @RequestMapping("/rank_list")
    public String rank_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/club/club_rank_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_CLUBRANK:VIEW')")
    @PostMapping("/rank_search")
    public
    @ResponseBody
    JQGirdPageResult rank_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
    	Map<String,Object> map = new HashMap<>();
		//排名列表
    	String rank_time=paramMap.get("rank_time").toString();
    	String type=paramMap.get("type").toString();
    	Page<Map> mapRank=null;
    	if(StringUtils.equals(type, "1") ) {
			if(StringUtil.isNotEmpty(rank_time)) {
				if(DateUtil.checkTimeInMonth(rank_time)) {
					//俱乐部当月竞技榜排名
					mapRank=clubPlaceService.queryAthleticsClubInfoPageList(paramMap,pageBean);
				}else {
					//俱乐部历史月份竞技榜排名
					mapRank=clubMonthRankService.queryAthleticsClubMonthRankInfoPageList(paramMap,pageBean);
				}
			}else {
				//俱乐部当月竞技榜排名
				mapRank=clubPlaceService.queryAthleticsClubInfoPageList(paramMap,pageBean);
			}
    	}
    	if(StringUtils.equals(type, "2")) {
			if(StringUtil.isNotEmpty(rank_time)) {
				if(DateUtil.checkTimeInMonth(rank_time)) {
					//俱乐部当月竞技榜排名
					mapRank=clubPlaceService.queryStrengthClubInfoPageList(paramMap,pageBean);
				}else {
					//俱乐部历史月份竞技榜排名
					mapRank=clubMonthRankService.queryStrengthClubMonthRankInfoPageList(paramMap,pageBean);
				}
			}else {
				//俱乐部当月竞技榜排名
				mapRank=clubPlaceService.queryStrengthClubInfoPageList(paramMap,pageBean);
			}
    	}
        return new JQGirdPageResult(mapRank);
    }
}
