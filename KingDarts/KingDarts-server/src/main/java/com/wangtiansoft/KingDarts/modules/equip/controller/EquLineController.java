package com.wangtiansoft.KingDarts.modules.equip.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.equip.service.EquLineService;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.EquLine;
import com.wangtiansoft.KingDarts.results.core.EquLineResult;

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
@RequestMapping("/a/equLine")
public class EquLineController extends BaseController {

    @Resource
    private EquLineService equLineService;

    //  列表
    @PreAuthorize("hasPermission('','_EQULINE:VIEW')")
    @RequestMapping("/equLine_list")
    public String equLine_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/equLine/equLine_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_EQULINE:VIEW')")
    @PostMapping("/equLine_search")
    public
    @ResponseBody
    JQGirdPageResult equLine_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = equLineService.queryEquLinePageList(paramMap, pageBean);
        return makePageResult(page, EquLineResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_EQULINE:VIEW')")
    @GetMapping("/equLine_view")
    public String equLine_view() {
    String id = getParaValue("id");
        EquLine entity = equLineService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/equLine/equLine_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_EQULINE:EDIT')")
    @GetMapping("/equLine_edit")
    public String equLine_edit() {
        String id = getParaValue("id");
        EquLine entity = equLineService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/equLine/equLine_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_EQULINE:EDIT')")
    @PostMapping("/equLine_edit")
    public
    @ResponseBody
    ApiResult equLine_edit(@ModelAttribute("entity") EquLine entity) {
        equLineService.updateByIdSelective(entity);
        EquLineResult result = makeResult(entity, EquLineResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_EQULINE:ADD')")
    @GetMapping("/equLine_add")
    public String equLine_add() {
        return "/a/equLine/equLine_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_EQULINE:ADD')")
    @PostMapping("/equLine_add")
    public
    @ResponseBody
    ApiResult equLine_add(@ModelAttribute("entity") EquLine entity) {
        equLineService.save(entity);
        EquLineResult result = makeResult(entity, EquLineResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_EQULINE:EDIT')")
    @PostMapping("/equLine_state")
    public
    @ResponseBody
    ApiResult equLine_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        EquLine entity = equLineService.findById(Integer.valueOf(id));
        equLineService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_EQULINE:DELETE')")
    @PostMapping("/equLine_delete")
    public
    @ResponseBody
    ApiResult equLine_delete() {
        String id = getParaValue("id");
        EquLine entity = equLineService.findById(Integer.valueOf(id));
        equLineService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



