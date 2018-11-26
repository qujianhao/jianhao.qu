package com.wangtiansoft.KingDarts.modules.merchantAccount.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.modules.merchantAccount.service.MerchantAccountService;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;
import com.wangtiansoft.KingDarts.results.core.MerchantAccountResult;

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
@RequestMapping("/a/merchantAccount")
public class MerchantAccountController extends BaseController {

    @Resource
    private MerchantAccountService merchantAccountService;

    //  列表
    @PreAuthorize("hasPermission('','_MERCHANTACCOUNT:VIEW')")
    @RequestMapping("/merchantAccount_list")
    public String merchantAccount_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/merchantAccount/merchantAccount_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_MERCHANTACCOUNT:VIEW')")
    @PostMapping("/merchantAccount_search")
    public
    @ResponseBody
    JQGirdPageResult merchantAccount_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = merchantAccountService.queryMerchantAccountPageList(paramMap, pageBean);
        return makePageResult(page, MerchantAccountResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_MERCHANTACCOUNT:VIEW')")
    @GetMapping("/merchantAccount_view")
    public String merchantAccount_view() {
    String id = getParaValue("id");
        MerchantAccount entity = merchantAccountService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/merchantAccount/merchantAccount_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_MERCHANTACCOUNT:EDIT')")
    @GetMapping("/merchantAccount_edit")
    public String merchantAccount_edit() {
        String id = getParaValue("id");
        MerchantAccount entity = merchantAccountService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/merchantAccount/merchantAccount_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_MERCHANTACCOUNT:EDIT')")
    @PostMapping("/merchantAccount_edit")
    public
    @ResponseBody
    ApiResult merchantAccount_edit(@ModelAttribute("entity") MerchantAccount entity) {
        merchantAccountService.updateByIdSelective(entity);
        MerchantAccountResult result = makeResult(entity, MerchantAccountResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_MERCHANTACCOUNT:ADD')")
    @GetMapping("/merchantAccount_add")
    public String merchantAccount_add() {
        return "/a/merchantAccount/merchantAccount_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_MERCHANTACCOUNT:ADD')")
    @PostMapping("/merchantAccount_add")
    public
    @ResponseBody
    ApiResult merchantAccount_add(@ModelAttribute("entity") MerchantAccount entity) {
        merchantAccountService.save(entity);
        MerchantAccountResult result = makeResult(entity, MerchantAccountResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_MERCHANTACCOUNT:EDIT')")
    @PostMapping("/merchantAccount_state")
    public
    @ResponseBody
    ApiResult merchantAccount_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        MerchantAccount entity = merchantAccountService.findById(Integer.valueOf(id));
        merchantAccountService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_MERCHANTACCOUNT:DELETE')")
    @PostMapping("/merchantAccount_delete")
    public
    @ResponseBody
    ApiResult merchantAccount_delete() {
        String id = getParaValue("id");
        MerchantAccount entity = merchantAccountService.findById(Integer.valueOf(id));
        merchantAccountService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



