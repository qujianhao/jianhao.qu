package com.wangtiansoft.KingDarts.modules.place.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.modules.place.service.PlaceService;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.Place;
import com.wangtiansoft.KingDarts.results.core.PlaceResult;

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
@RequestMapping("/a/place")
public class PlaceController extends BaseController {

    @Resource
    private PlaceService placeService;

    //  列表
    @PreAuthorize("hasPermission('','_PLACE:VIEW')")
    @RequestMapping("/place_list")
    public String place_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/place/place_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_PLACE:VIEW')")
    @PostMapping("/place_search")
    public
    @ResponseBody
    JQGirdPageResult place_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = placeService.queryPlacePageList(paramMap, pageBean);
        return makePageResult(page, PlaceResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_PLACE:VIEW')")
    @GetMapping("/place_view")
    public String place_view() {
    String id = getParaValue("id");
        Place entity = placeService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/place/place_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_PLACE:EDIT')")
    @GetMapping("/place_edit")
    public String place_edit() {
        String id = getParaValue("id");
        Place entity = placeService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/place/place_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_PLACE:EDIT')")
    @PostMapping("/place_edit")
    public
    @ResponseBody
    ApiResult place_edit(@ModelAttribute("entity") Place entity) {
        placeService.updateByIdSelective(entity);
        PlaceResult result = makeResult(entity, PlaceResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_PLACE:ADD')")
    @GetMapping("/place_add")
    public String place_add() {
        return "/a/place/place_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_PLACE:ADD')")
    @PostMapping("/place_add")
    public
    @ResponseBody
    ApiResult place_add(@ModelAttribute("entity") Place entity) {
        placeService.save(entity);
        PlaceResult result = makeResult(entity, PlaceResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_PLACE:EDIT')")
    @PostMapping("/place_state")
    public
    @ResponseBody
    ApiResult place_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        Place entity = placeService.findById(Integer.valueOf(id));
        placeService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_PLACE:DELETE')")
    @PostMapping("/place_delete")
    public
    @ResponseBody
    ApiResult place_delete() {
        String id = getParaValue("id");
        Place entity = placeService.findById(Integer.valueOf(id));
        placeService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



