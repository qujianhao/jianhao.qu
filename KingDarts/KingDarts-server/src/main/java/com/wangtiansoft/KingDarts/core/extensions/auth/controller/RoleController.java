package com.wangtiansoft.KingDarts.core.extensions.auth.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.config.netty.constants.Constants;
import com.wangtiansoft.KingDarts.core.extensions.auth.bean.AuthSearchBean;
import com.wangtiansoft.KingDarts.core.extensions.auth.service.AuthService;
import com.wangtiansoft.KingDarts.core.extensions.common.service.CommonService;
import com.wangtiansoft.KingDarts.core.extensions.common.service.impl.CommonServiceImpl;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.Module;
import com.wangtiansoft.KingDarts.persistence.entity.PermEntity;
import com.wangtiansoft.KingDarts.persistence.entity.RoleEntity;
import com.wangtiansoft.KingDarts.results.core.RoleResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/7/16 0016.
 */
@Controller
@RequestMapping("/a/role")
public class RoleController extends BaseController {

    @Resource
    AuthService authService;
    @Resource
    CommonService commonService;
    //  ------------------------------------------  角色    ------------------------------------------

    //  角色列表
    @PreAuthorize("hasPermission('','ROLE:VIEW')")
    @RequestMapping("/role_list")
    public String role_list(@ModelAttribute AuthSearchBean searchBean) {
        request.setAttribute("searchBean", searchBean);
        return "/a/auth/role_list";
    }

    //  角色列表查询分页
    @PreAuthorize("hasPermission('','ROLE:VIEW')")
    @PostMapping("/role_search")
    public
    @ResponseBody
    JQGirdPageResult role_search(final @ModelAttribute AuthSearchBean searchBean, @ModelAttribute PageBean pageBean) {
        Page<RoleResult> entityPage = (Page<RoleResult>) commonService.commonPage(pageBean, RoleEntity.class, RoleResult.class, new CommonServiceImpl.IBaseExample() {
            @Override
            public BaseExample example() {
                BaseExample example = new BaseExample(PermEntity.class);
                example.createCriteria()
                        .andEqualTo(true, "isDelete", Constants.False)
                        .andEqualTo(StringUtils.isNotEmpty(searchBean.getIsPublish()), "isPublish", searchBean.getIsPublish())
                        .andLike(StringUtils.isNotEmpty(searchBean.getName()), "name", searchBean.getName());
                return example;
            }
        });
        return new JQGirdPageResult(entityPage);
    }

    //  编辑角色状态
    @PreAuthorize("hasPermission('','ROLE:EDIT')")
    @PostMapping("/role_edit_status")
    public
    @ResponseBody
    ApiResult role_edit_status(@ModelAttribute final AuthSearchBean searchBean) {
        ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
            @Override
            public Object execute() throws Exception {
                authService.updateRoleStatus(searchBean);
                return null;
            }
        });
        return result;
    }


    //  编辑角色
    @PreAuthorize("hasPermission('','ROLE:EDIT')")
    @GetMapping("/role_edit")
    public String role_edit(@ModelAttribute AuthSearchBean searchBean) {
        String roleId = searchBean.getRoleId();
        RoleEntity roleEntity = authService.findRoleById(roleId);

        LinkedHashMap<String, List<Module>> permList = authService.queryPermTreeListAll();
        String[] rolePermArray = new String[]{};
        String[] roleCodeArray = new String[]{};
        if (roleEntity != null){
            String[] tempRolePermArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(roleEntity.getPremContent(), ",");
            if (tempRolePermArray != null)
                rolePermArray = tempRolePermArray;
            String[] tempRoleCodeArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(roleEntity.getCodeContent(), ",");
            if (tempRoleCodeArray != null)
                roleCodeArray = tempRoleCodeArray;
        }
        request.setAttribute("rolePermArray", rolePermArray);
        request.setAttribute("roleCodeArray", roleCodeArray);
        request.setAttribute("roleEntity", roleEntity);
        request.setAttribute("permList", permList);
        request.setAttribute("searchBean", searchBean);
        return "/a/auth/role_edit";
    }

    //  编辑角色
    @PreAuthorize("hasPermission('','ROLE:EDIT')")
    @PostMapping("/role_edit")
    public
    @ResponseBody
    ApiResult role_edit_post(@ModelAttribute final AuthSearchBean searchBean) {
        ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
            @Override
            public Object execute() throws Exception {
                authService.saveOrUpdateRole(searchBean);
                return null;
            }
        });
        return result;
    }




}
