package com.wangtiansoft.KingDarts.modules.medal.controller;

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
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.medal.service.MedalService;
import com.wangtiansoft.KingDarts.persistence.dao.master.ResourceFileMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Medal;
import com.wangtiansoft.KingDarts.persistence.entity.ResourceFile;
import com.wangtiansoft.KingDarts.results.core.MedalResult;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/medal")
public class MedalController extends BaseController {

    @Resource
    private MedalService medalService;
    
    //  列表
    @PreAuthorize("hasPermission('','_MEDAL:VIEW')")
    @RequestMapping("/medal_list")
    public String medal_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/medal/medal_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_MEDAL:VIEW')")
    @PostMapping("/medal_search")
    public
    @ResponseBody
    JQGirdPageResult medal_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = medalService.queryMedalPageList(paramMap, pageBean);
        return makePageResult(page, MedalResult.class);
    }


    //  编辑页面
    @PreAuthorize("hasPermission('','_MEDAL:EDIT')")
    @GetMapping("/medal_edit")
    public String medal_edit() {
        String id = getParaValue("id");
        Medal entity = medalService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/medal/medal_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_MEDAL:EDIT')")
    @PostMapping("/medal_edit")
    public
    @ResponseBody
    ApiResult medal_edit(@ModelAttribute("entity") Medal entity) {
        medalService.updateByIdSelective(entity);
        MedalResult result = makeResult(entity, MedalResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_MEDAL:ADD')")
    @GetMapping("/medal_add")
    public String medal_add() {
        return "/a/medal/medal_edit";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_MEDAL:ADD')")
    @PostMapping("/medal_add")
    public
    @ResponseBody
    ApiResult medal_add(@ModelAttribute("entity") Medal entity) {
        medalService.save(entity);
        MedalResult result = makeResult(entity, MedalResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_MEDAL:EDIT')")
    @PostMapping("/medal_state")
    public
    @ResponseBody
    ApiResult medal_state() {
        String id = getParaValue("id");
        String is_valid = getParaValue("is_valid");
        Medal entity = medalService.findById(Integer.valueOf(id));
        entity.setIs_valid(Integer.valueOf(is_valid));
        medalService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_MEDAL:DELETE')")
    @PostMapping("/medal_delete")
    public
    @ResponseBody
    ApiResult medal_delete() {
        String id = getParaValue("id");
        Medal entity = medalService.findById(Integer.valueOf(id));
        entity.setIs_delete(Constants.True);
        medalService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}