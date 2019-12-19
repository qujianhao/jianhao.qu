package com.wangtiansoft.KingDarts.modules.user.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.DateUtil;
import com.wangtiansoft.KingDarts.config.lftpay.api.util.StringUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.club.service.ClubMemberService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;

@Controller
@RequestMapping("/a/userPoints")
public class UserPointsController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private ClubMemberService clubMemberService;
    
    //  列表
    @PreAuthorize("hasPermission('','_POINTS:VIEW')")
    @RequestMapping("/rank_list")
    public String rank_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/user/user_points_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_POINTS:VIEW')")
    @PostMapping("/rank_search")
    public
    @ResponseBody
    JQGirdPageResult rank_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
    	String rank_time=paramMap.get("rank_time").toString();
    	Page<Map> mapRank=null;
    	if(StringUtil.isNotEmpty(rank_time)) {
			if(DateUtil.checkTimeInMonth(rank_time)) {
				mapRank=userService.queryUserRankByPointsPageList(paramMap,pageBean);
			}else {
				mapRank=userService.queryUserRankByMonthPointsPageList(paramMap,pageBean);
			}
		}else {
			mapRank=userService.queryUserRankByPointsPageList(paramMap,pageBean);
		}
        return new JQGirdPageResult(mapRank);
    }
    
    
}
