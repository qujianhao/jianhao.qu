package com.wangtiansoft.KingDarts.modules.medalWinner.controller;

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
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.medalWinner.service.MedalWinnerService;
import com.wangtiansoft.KingDarts.results.core.MedalWinnerResult;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/medalWinner")
public class MedalWinnerController extends BaseController {

    @Resource
    private MedalWinnerService medalWinnerService;

    //  列表
    @PreAuthorize("hasPermission('','_MEDALWINNER:VIEW')")
    @RequestMapping("/medalWinner_list")
    public String medalWinner_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/medal/medalWinner_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_MEDALWINNER:VIEW')")
    @PostMapping("/medalWinner_search")
    public
    @ResponseBody
    JQGirdPageResult medalWinner_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = medalWinnerService.queryMedalWinnerPageList(paramMap, pageBean);
        return makePageResult(page, MedalWinnerResult.class);
    }
    
}