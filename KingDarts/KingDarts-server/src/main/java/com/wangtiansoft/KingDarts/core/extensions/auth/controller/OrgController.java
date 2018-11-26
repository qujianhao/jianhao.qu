package com.wangtiansoft.KingDarts.core.extensions.auth.controller;

import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.extensions.auth.bean.AuthSearchBean;
import com.wangtiansoft.KingDarts.core.extensions.auth.service.AuthService;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.persistence.entity.OrgEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/16 0016.
 */
@Controller
@RequestMapping("/a/org")
public class OrgController extends BaseController {

    @Resource
    AuthService authService;

    //  用户列表
    @PreAuthorize("hasPermission('','ORG:VIEW')")
    @RequestMapping("/org_list")
    public String org_list(@ModelAttribute AuthSearchBean searchBean) {
        request.setAttribute("searchBean", searchBean);
        return "/a/auth/org_list";
    }

    //  用户列表查询分页
    @PreAuthorize("hasPermission('','ORG:VIEW')")
    @PostMapping("/org_search")
    public
    @ResponseBody
    JQGirdPageResult org_search(final @ModelAttribute AuthSearchBean searchBean, @ModelAttribute PageBean pageBean) {
        return new JQGirdPageResult(authService.queryOrgPage(searchBean, pageBean));
    }

    //	加载权限树
    @PreAuthorize("hasPermission('','ORG:VIEW')")
    @RequestMapping(value = "/org_tree")
    public
    @ResponseBody
    ApiResult org_tree(final @ModelAttribute AuthSearchBean searchBean) {
        List<Map> resultList = authService.queryOrgTreeListByParentId(searchBean.getId());
        return ApiResult.success(resultList);
    }

    //  跳转页面 新建权限信息
    @PreAuthorize("hasPermission('','ORG:ADD')")
    @GetMapping("/org_add")
    public String org_add(final @ModelAttribute AuthSearchBean searchBean) {
        request.setAttribute("parent", searchBean.getParent());
        return "/a/auth/org_edit";
    }

    //  新建权限信息
    @PreAuthorize("hasPermission('','ORG:ADD')")
    @PostMapping("/org_add")
    public
    @ResponseBody
    ApiResult org_add_post(final @ModelAttribute AuthSearchBean searchBean) {
        ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
            @Override
            public Object execute() throws Exception {
                authService.saveOrUpdateOrg(searchBean);
                return null;
            }
        });
        return result;
    }

    //  跳转页面 编辑权限信息
    @PreAuthorize("hasPermission('','ORG:EDIT')")
    @GetMapping("/org_edit")
    public String org_edit(final @ModelAttribute AuthSearchBean searchBean) {
        OrgEntity orgEntity = authService.findOrgEntityById(searchBean.getId());
        request.setAttribute("orgEntity", orgEntity);
        return "/a/auth/org_edit";
    }

    //  编辑权限信息
    @PreAuthorize("hasPermission('','ORG:EDIT')")
    @PostMapping("/org_edit")
    public
    @ResponseBody
    ApiResult org_edit_post(final @ModelAttribute AuthSearchBean searchBean) {
        ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
            @Override
            public Object execute() throws Exception {
                authService.saveOrUpdateOrg(searchBean);
                return null;
            }
        });
        return result;
    }

}
