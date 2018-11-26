package com.wangtiansoft.KingDarts.modules.user.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.modules.club.service.ClubMemberService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.UserResult;

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
@RequestMapping("/a/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private ClubMemberService clubMemberService;

    //  列表
    @PreAuthorize("hasPermission('','_USER:VIEW')")
    @RequestMapping("/user_list")
    public String user_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/user/user_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_USER:VIEW')")
    @PostMapping("/user_search")
    public
    @ResponseBody
    JQGirdPageResult user_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = userService.queryUserPageList(paramMap, pageBean);
        return makePageResult(page, UserResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_USER:VIEW')")
    @GetMapping("/user_view")
    public String user_view() {
    String id = getParaValue("id");
        User entity = userService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/user/user_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_USER:EDIT')")
    @GetMapping("/user_edit")
    public String user_edit() {
        String id = getParaValue("id");
        User entity = userService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/user/user_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_USER:EDIT')")
    @PostMapping("/user_edit")
    public
    @ResponseBody
    ApiResult user_edit(@ModelAttribute("entity") User entity) {
        userService.updateByIdSelective(entity);
        UserResult result = makeResult(entity, UserResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_USER:ADD')")
    @GetMapping("/user_add")
    public String user_add() {
        return "/a/user/user_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_USER:ADD')")
    @PostMapping("/user_add")
    public
    @ResponseBody
    ApiResult user_add(@ModelAttribute("entity") User entity) {
        userService.save(entity);
        UserResult result = makeResult(entity, UserResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_USER:EDIT')")
    @PostMapping("/user_state")
    public
    @ResponseBody
    ApiResult user_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        User entity = userService.findById(Integer.valueOf(id));
        entity.setIs_publish(Integer.valueOf(is_publish));
        userService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_USER:DELETE')")
    @PostMapping("/user_delete")
    public
    @ResponseBody
    ApiResult user_delete() {
        String id = getParaValue("id");
        User entity = userService.findById(Integer.valueOf(id));
        entity.setIs_delete(Constants.True);
        userService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    
//  列表
    @PreAuthorize("hasPermission('','_USER:VIEW')")
    @RequestMapping("/member_list")
    public String member_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/user/member_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_USER:VIEW')")
    @PostMapping("/member_search")
    public
    @ResponseBody
    JQGirdPageResult member_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = clubMemberService.queryMemberStatistics(paramMap, pageBean);
        return new JQGirdPageResult(page);
    }
}



