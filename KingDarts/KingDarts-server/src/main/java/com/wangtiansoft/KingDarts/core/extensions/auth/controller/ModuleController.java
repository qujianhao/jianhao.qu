package com.wangtiansoft.KingDarts.core.extensions.auth.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.core.extensions.module.service.ModuleService;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.dao.master.ModuleMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Module;
import com.wangtiansoft.KingDarts.results.core.ModuleResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/module")
public class ModuleController extends BaseController {

    @Resource
    private ModuleService moduleService;

    @Autowired
    private ModuleMapper moduleMapper;

    //  列表
    @PreAuthorize("hasPermission('','_MODULE:VIEW')")
    @RequestMapping("/module_list")
    public String module_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/auth/module_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_MODULE:VIEW')")
    @PostMapping("/module_search")
    public
    @ResponseBody
    JQGirdPageResult module_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = moduleService.queryModulePageList(paramMap, pageBean);
        return makePageResult(page, ModuleResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_MODULE:VIEW')")
    @GetMapping("/module_view")
    public String module_view() {
    String id = getParaValue("id");
        Module entity = moduleService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/module/module_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_MODULE:EDIT')")
    @GetMapping("/module_edit")
    public String module_edit() {
        String id = getParaValue("id");
        Module entity = moduleService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/auth/module_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_MODULE:EDIT')")
    @PostMapping("/module_edit")
    public
    @ResponseBody
    ApiResult module_edit(@ModelAttribute("entity") Module entity) {
        moduleService.updateByIdSelective(entity);
        ModuleResult result = makeResult(entity, ModuleResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_MODULE:ADD')")
    @GetMapping("/module_add")
    public String module_add() {
        request.setAttribute("parent", getParaValue("parent"));
        return "/a/auth/module_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_MODULE:ADD')")
    @PostMapping("/module_add")
    public
    @ResponseBody
    ApiResult module_add(@ModelAttribute("entity") Module entity) {
        moduleService.save(entity);
        ModuleResult result = makeResult(entity, ModuleResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_MODULE:EDIT')")
    @PostMapping("/module_state")
    public
    @ResponseBody
    ApiResult module_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        Module entity = moduleService.findById(Integer.valueOf(id));
        entity.setIs_publish(Integer.valueOf(is_publish));
        moduleService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_MODULE:DELETE')")
    @PostMapping("/module_delete")
    public
    @ResponseBody
    ApiResult module_delete() {
        String id = getParaValue("id");
        List<Map> mapList = moduleMapper.queryModuleMapListByParent(id);
        if (CollectionUtils.isNotEmpty(mapList)){
            return ApiResult.fail("不能删除拥有子节点的节点");
        }
        Module entity = moduleService.findById(Integer.valueOf(id));
        entity.setIs_delete(Constants.True);
        moduleService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //	加载权限树
    @PreAuthorize("hasPermission('','_MODULE:VIEW')")
    @RequestMapping(value = "/module_tree")
    public
    @ResponseBody
    ApiResult module_tree() {
        String parentId = getParaValue("id");
        List<Map> resultList = moduleService.queryModuleMapList(parentId);
        return ApiResult.success(resultList);
    }

    //  选择图标
    @PreAuthorize("hasPermission('','_MODULE:EDIT')")
    @GetMapping("/module_icon_list")
    public String module_icon_list() {
        return "/a/auth/module_icon_list";
    }

}



