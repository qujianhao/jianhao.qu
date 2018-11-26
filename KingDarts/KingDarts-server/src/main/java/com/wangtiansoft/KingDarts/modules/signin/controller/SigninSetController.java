package com.wangtiansoft.KingDarts.modules.signin.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
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
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.signin.service.SigninSetService;
import com.wangtiansoft.KingDarts.persistence.entity.SigninSet;
import com.wangtiansoft.KingDarts.results.core.SigninSetResult;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/signinSet")
public class SigninSetController extends BaseController {

    @Resource
    private SigninSetService signinSetService;

    //  列表
    @PreAuthorize("hasPermission('','_SIGNINSET:VIEW')")
    @RequestMapping("/signinSet_list")
    public String signinSet_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/signin/signinSet_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_SIGNINSET:VIEW')")
    @PostMapping("/signinSet_search")
    public
    @ResponseBody
    JQGirdPageResult signinSet_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = signinSetService.querySigninSetPageList(paramMap, pageBean);
        return makePageResult(page, SigninSetResult.class);
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_SIGNINSET:EDIT')")
    @GetMapping("/signinSet_edit")
    public String signinSet_edit() {
        String id = getParaValue("id");
        SigninSet entity = signinSetService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/signin/signinSet_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_SIGNINSET:EDIT')")
    @PostMapping("/signinSet_edit")
    public
    @ResponseBody
    ApiResult signinSet_edit(@ModelAttribute("entity") SigninSet entity) {
        signinSetService.updateByIdSelective(entity);
        SigninSetResult result = makeResult(entity, SigninSetResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_SIGNINSET:ADD')")
    @GetMapping("/signinSet_add")
    public String signinSet_add() {
        return "/a/signin/signinSet_edit";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_SIGNINSET:ADD')")
    @PostMapping("/signinSet_add")
    public
    @ResponseBody
    ApiResult signinSet_add(@ModelAttribute("entity") SigninSet entity) {
        signinSetService.save(entity);
        SigninSetResult result = makeResult(entity, SigninSetResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_SIGNINSET:EDIT')")
    @PostMapping("/signinSet_state")
    public
    @ResponseBody
    ApiResult signinSet_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        SigninSet entity = signinSetService.findById(Integer.valueOf(id));
        entity.setIs_publish(Integer.valueOf(is_publish));
        signinSetService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_SIGNINSET:DELETE')")
    @PostMapping("/signinSet_delete")
    public
    @ResponseBody
    ApiResult signinSet_delete() {
        String id = getParaValue("id");
        SigninSet entity = signinSetService.findById(Integer.valueOf(id));
        entity.setIs_delete(Constants.True);
        signinSetService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



