package com.wangtiansoft.KingDarts.modules.user.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.user.service.UserOpenidService;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.UserOpenid;
import com.wangtiansoft.KingDarts.results.core.UserOpenidResult;

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
@RequestMapping("/a/userOpenid")
public class UserOpenidController extends BaseController {

    @Resource
    private UserOpenidService userOpenidService;

    //  列表
    @PreAuthorize("hasPermission('','_USEROPENID:VIEW')")
    @RequestMapping("/userOpenid_list")
    public String userOpenid_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/userOpenid/userOpenid_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_USEROPENID:VIEW')")
    @PostMapping("/userOpenid_search")
    public
    @ResponseBody
    JQGirdPageResult userOpenid_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = userOpenidService.queryUserOpenidPageList(paramMap, pageBean);
        return makePageResult(page, UserOpenidResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_USEROPENID:VIEW')")
    @GetMapping("/userOpenid_view")
    public String userOpenid_view() {
    String id = getParaValue("id");
        UserOpenid entity = userOpenidService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/userOpenid/userOpenid_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_USEROPENID:EDIT')")
    @GetMapping("/userOpenid_edit")
    public String userOpenid_edit() {
        String id = getParaValue("id");
        UserOpenid entity = userOpenidService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/userOpenid/userOpenid_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_USEROPENID:EDIT')")
    @PostMapping("/userOpenid_edit")
    public
    @ResponseBody
    ApiResult userOpenid_edit(@ModelAttribute("entity") UserOpenid entity) {
        userOpenidService.updateByIdSelective(entity);
        UserOpenidResult result = makeResult(entity, UserOpenidResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_USEROPENID:ADD')")
    @GetMapping("/userOpenid_add")
    public String userOpenid_add() {
        return "/a/userOpenid/userOpenid_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_USEROPENID:ADD')")
    @PostMapping("/userOpenid_add")
    public
    @ResponseBody
    ApiResult userOpenid_add(@ModelAttribute("entity") UserOpenid entity) {
        userOpenidService.save(entity);
        UserOpenidResult result = makeResult(entity, UserOpenidResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_USEROPENID:EDIT')")
    @PostMapping("/userOpenid_state")
    public
    @ResponseBody
    ApiResult userOpenid_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        UserOpenid entity = userOpenidService.findById(Integer.valueOf(id));
        userOpenidService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_USEROPENID:DELETE')")
    @PostMapping("/userOpenid_delete")
    public
    @ResponseBody
    ApiResult userOpenid_delete() {
        String id = getParaValue("id");
        UserOpenid entity = userOpenidService.findById(Integer.valueOf(id));
        userOpenidService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



