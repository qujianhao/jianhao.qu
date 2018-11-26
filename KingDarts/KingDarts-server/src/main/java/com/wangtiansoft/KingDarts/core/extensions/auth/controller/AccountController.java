package com.wangtiansoft.KingDarts.core.extensions.auth.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.extensions.account.service.AccountService;
import com.wangtiansoft.KingDarts.core.extensions.role.service.RoleService;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.persistence.entity.Account;
import com.wangtiansoft.KingDarts.results.core.AccountResult;
import com.wangtiansoft.KingDarts.results.core.RoleResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/account")
public class AccountController extends BaseController {

    @Resource
    private AccountService accountService;
    @Resource
    private RoleService roleService;

    //  列表
    @PreAuthorize("hasPermission('','_ACCOUNT:VIEW')")
    @RequestMapping("/account_list")
    public String account_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/auth/account_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_ACCOUNT:VIEW')")
    @PostMapping("/account_search")
    public
    @ResponseBody
    JQGirdPageResult account_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = accountService.queryAccountPageList(paramMap, pageBean);
        return makePageResult(page, AccountResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_ACCOUNT:VIEW')")
    @GetMapping("/account_view")
    public String account_view() {
    String id = getParaValue("id");
        Account entity = accountService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/auth/account_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_ACCOUNT:EDIT')")
    @GetMapping("/account_edit")
    public String account_edit(String id) {
        if(StringUtils.isNotEmpty(id)){
        	Account entity = accountService.findById(Integer.valueOf(id));
            request.setAttribute("accountEntity", entity);
        }else{
        	Account entity = new Account();
        	entity.setIs_publish(1);
        	request.setAttribute("accountEntity", entity);
        }
        Map<String, Object> params = new HashMap<String, Object>();
		params.put("isEnabled", 1);
		List<RoleResult> roles = roleService.queryRolePageList(params);
		request.setAttribute("roleEntityList", roles);

		if(StringUtils.isNotEmpty(id)){
			return "/a/auth/account_edit";
		}else{
			return "/a/auth/account_add";
		}

    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_ACCOUNT:EDIT')")
    @PostMapping("/account_edit")
    public
    @ResponseBody
    ApiResult account_edit(AccountResult account) {
    	accountService.saveOrUpdateAccount(account);
//        AccountResult result = makeResult(entity, AccountResult.class);
        return ApiResult.success(account);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_ACCOUNT:ADD')")
    @GetMapping("/account_add")
    public String account_add() {
        return "/a/account/account_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_ACCOUNT:ADD')")
    @PostMapping("/account_add")
    public
    @ResponseBody
    ApiResult account_add(AccountResult account) {
        accountService.saveOrUpdateAccount(account);
//        AccountResult result = makeResult(entity, AccountResult.class);
        return ApiResult.success(account);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_ACCOUNT:EDIT')")
    @PostMapping("/account_state")
    public
    @ResponseBody
    ApiResult account_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        Account entity = accountService.findById(Integer.valueOf(id));
        entity.setIs_publish(Integer.valueOf(is_publish));
        accountService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_ACCOUNT:DELETE')")
    @PostMapping("/account_delete")
    public
    @ResponseBody
    ApiResult account_delete() {
        String id = getParaValue("id");
        Account entity = accountService.findById(Integer.valueOf(id));
        entity.setIs_delete(Constants.True);
        accountService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



