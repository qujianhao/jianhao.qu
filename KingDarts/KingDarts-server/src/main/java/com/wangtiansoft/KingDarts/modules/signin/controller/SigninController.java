package com.wangtiansoft.KingDarts.modules.signin.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.modules.signin.service.SigninService;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.Signin;
import com.wangtiansoft.KingDarts.results.core.SigninResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/signin")
public class SigninController extends BaseController {

    @Resource
    private SigninService signinService;

    //  列表
    @PreAuthorize("hasPermission('','_SIGNIN:VIEW')")
    @RequestMapping("/signin_list")
    public String signin_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/signin/signin_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_SIGNIN:VIEW')")
    @PostMapping("/signin_search")
    public
    @ResponseBody
    JQGirdPageResult signin_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = signinService.querySigninPageList(paramMap, pageBean);
        return makePageResult(page, SigninResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_SIGNIN:VIEW')")
    @GetMapping("/signin_view")
    public String signin_view() {
    String id = getParaValue("id");
        Signin entity = signinService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/signin/signin_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_SIGNIN:EDIT')")
    @GetMapping("/signin_edit")
    public String signin_edit() {
        String id = getParaValue("id");
        Signin entity = signinService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/signin/signin_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_SIGNIN:EDIT')")
    @PostMapping("/signin_edit")
    public
    @ResponseBody
    ApiResult signin_edit(@ModelAttribute("entity") Signin entity) {
        signinService.updateByIdSelective(entity);
        SigninResult result = makeResult(entity, SigninResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_SIGNIN:ADD')")
    @GetMapping("/signin_add")
    public String signin_add() {
        return "/a/signin/signin_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_SIGNIN:ADD')")
    @PostMapping("/signin_add")
    public
    @ResponseBody
    ApiResult signin_add(@ModelAttribute("entity") Signin entity) {
        signinService.save(entity);
        SigninResult result = makeResult(entity, SigninResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_SIGNIN:EDIT')")
    @PostMapping("/signin_state")
    public
    @ResponseBody
    ApiResult signin_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        Signin entity = signinService.findById(Integer.valueOf(id));
        signinService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_SIGNIN:DELETE')")
    @PostMapping("/signin_delete")
    public
    @ResponseBody
    ApiResult signin_delete() {
        String id = getParaValue("id");
        Signin entity = signinService.findById(Integer.valueOf(id));
        signinService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



