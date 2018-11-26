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
import com.wangtiansoft.KingDarts.modules.protocal.service.UserprotocolService;
import com.wangtiansoft.KingDarts.persistence.entity.Userprotocol;
import com.wangtiansoft.KingDarts.results.core.UserprotocolResult;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/protocol")
public class UserprotocolController extends BaseController {

    @Resource
    private UserprotocolService userprotocolService;

    //  列表
    @PreAuthorize("hasPermission('','KINGDARTS_USERPROTOCOL:VIEW')")
    @RequestMapping("/userprotocol_list")
    public String userprotocol_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/protocol/userprotocol_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','KINGDARTS_USERPROTOCOL:VIEW')")
    @PostMapping("/userprotocol_search")
    public
    @ResponseBody
    JQGirdPageResult userprotocol_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = userprotocolService.queryUserprotocolPageList(paramMap, pageBean);
        return makePageResult(page, UserprotocolResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','KINGDARTS_USERPROTOCOL:VIEW')")
    @GetMapping("/userprotocol_view")
    public String userprotocol_view() {
    String id = getParaValue("id");
        Userprotocol entity = userprotocolService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/protocol/userprotocol_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','KINGDARTS_USERPROTOCOL:EDIT')")
    @GetMapping("/userprotocol_edit")
    public String userprotocol_edit() {
        String id = getParaValue("id");
        Userprotocol entity = userprotocolService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/protocol/userprotocol_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','KINGDARTS_USERPROTOCOL:EDIT')")
    @PostMapping("/userprotocol_edit")
    public
    @ResponseBody
    ApiResult userprotocol_edit(@ModelAttribute("entity") Userprotocol entity) {
    	entity.setUpdate_time(new Date());
        userprotocolService.updateByIdSelective(entity);
        UserprotocolResult result = makeResult(entity, UserprotocolResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','KINGDARTS_USERPROTOCOL:ADD')")
    @GetMapping("/userprotocol_add")
    public String userprotocol_add() {
        return "/a/protocol/userprotocol_edit";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','KINGDARTS_USERPROTOCOL:ADD')")
    @PostMapping("/userprotocol_add")
    public
    @ResponseBody
    ApiResult userprotocol_add(@ModelAttribute("entity") Userprotocol entity) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	SecurityPrincipal securityPrincipal = (SecurityPrincipal) authentication.getPrincipal();
        Integer userId=securityPrincipal.getAccount().getId();
        entity.setMaccount(userId+"");
        entity.setMnames(securityPrincipal.getAccount().getRealname());
        userprotocolService.save(entity);
        UserprotocolResult result = makeResult(entity, UserprotocolResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','KINGDARTS_USERPROTOCOL:EDIT')")
    @PostMapping("/userprotocol_state")
    public
    @ResponseBody
    ApiResult userprotocol_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        Userprotocol entity = userprotocolService.findById(Integer.valueOf(id));
        entity.setIs_publish(Integer.valueOf(is_publish));
        entity.setUpdate_time(new Date());
        userprotocolService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','KINGDARTS_USERPROTOCOL:DELETE')")
    @PostMapping("/userprotocol_delete")
    public
    @ResponseBody
    ApiResult userprotocol_delete() {
        String id = getParaValue("id");
        Userprotocol entity = userprotocolService.findById(Integer.valueOf(id));
        entity.setIs_delete(Constants.True);
        userprotocolService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



