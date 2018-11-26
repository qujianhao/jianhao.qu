package com.wangtiansoft.KingDarts.modules.funcatchWinner.controller;

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
import com.wangtiansoft.KingDarts.modules.funcatchPrize.service.FuncatchPrizeService;
import com.wangtiansoft.KingDarts.modules.funcatchWinner.service.FuncatchWinnerService;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchPrize;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchWinner;
import com.wangtiansoft.KingDarts.results.core.FuncatchWinnerResult;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/funcatchWinner")
public class FuncatchWinnerController extends BaseController {

    @Resource
    private FuncatchWinnerService funcatchWinnerService;
    
    @Resource
    private FuncatchPrizeService funcatchPrizeService;

    //  列表
    @PreAuthorize("hasPermission('','_FUNCATCHWINNER:VIEW')")
    @RequestMapping("/funcatchWinner_list")
    public String funcatchWinner_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/funcatchPrize/funcatchWinner_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_FUNCATCHWINNER:VIEW')")
    @PostMapping("/funcatchWinner_search")
    public
    @ResponseBody
    JQGirdPageResult funcatchWinner_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = funcatchWinnerService.queryFuncatchWinnerPageList(paramMap, pageBean);
        return new JQGirdPageResult(page);
    }

    //  详情
    @PreAuthorize("hasPermission('','_FUNCATCHWINNER:VIEW')")
    @GetMapping("/funcatchWinner_view")
    public String funcatchWinner_view() {
    String id = getParaValue("id");
        FuncatchWinner entity = funcatchWinnerService.findById(Long.valueOf(id));
        request.setAttribute("entity", entity);
        FuncatchPrize  funcatchPrize=funcatchPrizeService.findById(entity.getPrize_id());
        request.setAttribute("funcatchPrize", funcatchPrize);
        return "/a/funcatchPrize/funcatchWinner_edit";
    }


    //  编辑保存
    @PreAuthorize("hasPermission('','_FUNCATCHWINNER:EDIT')")
    @PostMapping("/funcatchWinner_edit")
    public
    @ResponseBody
    ApiResult funcatchWinner_edit(@ModelAttribute("entity") FuncatchWinner entity) {
        funcatchWinnerService.updateByIdSelective(entity);
        FuncatchWinnerResult result = makeResult(entity, FuncatchWinnerResult.class);
        return ApiResult.success(result);
    }

}



