package com.wangtiansoft.KingDarts.modules.robFloors.controller;

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
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.robFloors.service.RobFloorsService;
import com.wangtiansoft.KingDarts.persistence.entity.RobFloors;
import com.wangtiansoft.KingDarts.results.core.RobFloorsResult;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/robFloors")
public class RobFloorsController extends BaseController {

    @Resource
    private RobFloorsService robFloorsService;

    //  列表
    @PreAuthorize("hasPermission('','_ROBFLOORS:VIEW')")
    @RequestMapping("/robFloors_list")
    public String robFloors_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/robFloors/robFloors_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_ROBFLOORS:VIEW')")
    @PostMapping("/robFloors_search")
    public
    @ResponseBody
    JQGirdPageResult robFloors_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = robFloorsService.queryRobFloorsPageList(paramMap, pageBean);
        return makePageResult(page, RobFloorsResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_ROBFLOORS:VIEW')")
    @GetMapping("/robFloors_view")
    public String robFloors_view() {
    String id = getParaValue("id");
        RobFloors entity = robFloorsService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/robFloors/robFloors_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_ROBFLOORS:EDIT')")
    @GetMapping("/robFloors_edit")
    public String robFloors_edit() {
        String id = getParaValue("id");
        RobFloors entity = robFloorsService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/robFloors/robFloors_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_ROBFLOORS:EDIT')")
    @PostMapping("/robFloors_edit")
    public
    @ResponseBody
    ApiResult robFloors_edit(@ModelAttribute("entity") RobFloors entity) {
        robFloorsService.updateByIdSelective(entity);
        RobFloorsResult result = makeResult(entity, RobFloorsResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_ROBFLOORS:ADD')")
    @GetMapping("/robFloors_add")
    public String robFloors_add() {
        return "/a/robFloors/robFloors_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_ROBFLOORS:ADD')")
    @PostMapping("/robFloors_add")
    public
    @ResponseBody
    ApiResult robFloors_add(@ModelAttribute("entity") RobFloors entity) {
        robFloorsService.save(entity);
        RobFloorsResult result = makeResult(entity, RobFloorsResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_ROBFLOORS:EDIT')")
    @PostMapping("/robFloors_state")
    public
    @ResponseBody
    ApiResult robFloors_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        RobFloors entity = robFloorsService.findById(Integer.valueOf(id));
        robFloorsService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_ROBFLOORS:DELETE')")
    @PostMapping("/robFloors_delete")
    public
    @ResponseBody
    ApiResult robFloors_delete() {
        String id = getParaValue("id");
        RobFloors entity = robFloorsService.findById(Integer.valueOf(id));
        robFloorsService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



