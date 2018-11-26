package com.wangtiansoft.KingDarts.modules.protocal.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.config.security.SecurityPrincipal;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.protocal.service.RaceprotocolService;
import com.wangtiansoft.KingDarts.persistence.entity.Raceprotocol;
import com.wangtiansoft.KingDarts.results.core.RaceprotocolResult;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/protocol")
public class RaceprotocolController extends BaseController {

    @Resource
    private RaceprotocolService raceprotocolService;

    //  列表
    @PreAuthorize("hasPermission('','KINGDARTS_RACEPROTOCOL:VIEW')")
    @RequestMapping("/raceprotocol_list")
    public String raceprotocol_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/protocol/raceprotocol_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','KINGDARTS_RACEPROTOCOL:VIEW')")
    @PostMapping("/raceprotocol_search")
    public
    @ResponseBody
    JQGirdPageResult raceprotocol_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = raceprotocolService.queryRaceprotocolPageList(paramMap, pageBean);
        return makePageResult(page, RaceprotocolResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','KINGDARTS_RACEPROTOCOL:VIEW')")
    @GetMapping("/raceprotocol_view")
    public String raceprotocol_view() {
    String id = getParaValue("id");
        Raceprotocol entity = raceprotocolService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/protocol/raceprotocol_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','KINGDARTS_RACEPROTOCOL:EDIT')")
    @GetMapping("/raceprotocol_edit")
    public String raceprotocol_edit() {
        String id = getParaValue("id");
        Raceprotocol entity = raceprotocolService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/protocol/raceprotocol_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','KINGDARTS_RACEPROTOCOL:EDIT')")
    @PostMapping("/raceprotocol_edit")
    public
    @ResponseBody
    ApiResult raceprotocol_edit(@ModelAttribute("entity") Raceprotocol entity) {
    	entity.setUpdate_time(new Date());
        raceprotocolService.updateByIdSelective(entity);
        RaceprotocolResult result = makeResult(entity, RaceprotocolResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','KINGDARTS_RACEPROTOCOL:ADD')")
    @GetMapping("/raceprotocol_add")
    public String raceprotocol_add() {
        return "/a/protocol/raceprotocol_edit";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','KINGDARTS_RACEPROTOCOL:ADD')")
    @PostMapping("/raceprotocol_add")
    public
    @ResponseBody
    ApiResult raceprotocol_add(@ModelAttribute("entity") Raceprotocol entity) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	SecurityPrincipal securityPrincipal = (SecurityPrincipal) authentication.getPrincipal();
        Integer userId=securityPrincipal.getAccount().getId();
        entity.setMaccount(userId+"");
        entity.setMnames(securityPrincipal.getAccount().getRealname());
    	raceprotocolService.save(entity);
        RaceprotocolResult result = makeResult(entity, RaceprotocolResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','KINGDARTS_RACEPROTOCOL:EDIT')")
    @PostMapping("/raceprotocol_state")
    public
    @ResponseBody
    ApiResult raceprotocol_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        Raceprotocol entity = raceprotocolService.findById(Integer.valueOf(id));
        entity.setUpdate_time(new Date());
        entity.setIs_publish(Integer.valueOf(is_publish));
        raceprotocolService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','KINGDARTS_RACEPROTOCOL:DELETE')")
    @PostMapping("/raceprotocol_delete")
    public
    @ResponseBody
    ApiResult raceprotocol_delete() {
        String id = getParaValue("id");
        Raceprotocol entity = raceprotocolService.findById(Integer.valueOf(id));
        entity.setIs_delete(Constants.True);
        raceprotocolService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



