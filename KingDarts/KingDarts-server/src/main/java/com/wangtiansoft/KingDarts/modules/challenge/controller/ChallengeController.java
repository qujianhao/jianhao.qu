package com.wangtiansoft.KingDarts.modules.challenge.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.modules.challenge.service.ChallengeService;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.Challenge;
import com.wangtiansoft.KingDarts.results.core.ChallengeResult;

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
@RequestMapping("/a/challenge")
public class ChallengeController extends BaseController {

    @Resource
    private ChallengeService challengeService;

    //  列表
    @PreAuthorize("hasPermission('','_CHALLENGE:VIEW')")
    @RequestMapping("/challenge_list")
    public String challenge_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/challenge/challenge_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_CHALLENGE:VIEW')")
    @PostMapping("/challenge_search")
    public
    @ResponseBody
    JQGirdPageResult challenge_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = challengeService.queryChallengePageList(paramMap, pageBean);
        return makePageResult(page, ChallengeResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_CHALLENGE:VIEW')")
    @GetMapping("/challenge_view")
    public String challenge_view() {
    String id = getParaValue("id");
        Challenge entity = challengeService.findById(Long.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/challenge/challenge_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_CHALLENGE:EDIT')")
    @GetMapping("/challenge_edit")
    public String challenge_edit() {
        String id = getParaValue("id");
        Challenge entity = challengeService.findById(Long.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/challenge/challenge_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_CHALLENGE:EDIT')")
    @PostMapping("/challenge_edit")
    public
    @ResponseBody
    ApiResult challenge_edit(@ModelAttribute("entity") Challenge entity) {
        challengeService.updateByIdSelective(entity);
        ChallengeResult result = makeResult(entity, ChallengeResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_CHALLENGE:ADD')")
    @GetMapping("/challenge_add")
    public String challenge_add() {
        return "/a/challenge/challenge_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_CHALLENGE:ADD')")
    @PostMapping("/challenge_add")
    public
    @ResponseBody
    ApiResult challenge_add(@ModelAttribute("entity") Challenge entity) {
        challengeService.save(entity);
        ChallengeResult result = makeResult(entity, ChallengeResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_CHALLENGE:EDIT')")
    @PostMapping("/challenge_state")
    public
    @ResponseBody
    ApiResult challenge_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        Challenge entity = challengeService.findById(Long.valueOf(id));
        challengeService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_CHALLENGE:DELETE')")
    @PostMapping("/challenge_delete")
    public
    @ResponseBody
    ApiResult challenge_delete() {
        String id = getParaValue("id");
        Challenge entity = challengeService.findById(Long.valueOf(id));
        challengeService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



