package com.wangtiansoft.KingDarts.modules.game.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.modules.game.service.GameService;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.Game;
import com.wangtiansoft.KingDarts.results.core.GameResult;

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
@RequestMapping("/a/game")
public class GameController extends BaseController {

    @Resource
    private GameService gameService;

    //  列表
    @PreAuthorize("hasPermission('','_GAME:VIEW')")
    @RequestMapping("/game_list")
    public String game_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/game/game_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_GAME:VIEW')")
    @PostMapping("/game_search")
    public
    @ResponseBody
    JQGirdPageResult game_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = gameService.queryGamePageList(paramMap, pageBean);
        return makePageResult(page, GameResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_GAME:VIEW')")
    @GetMapping("/game_view")
    public String game_view() {
    String id = getParaValue("id");
        Game entity = gameService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/game/game_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_GAME:EDIT')")
    @GetMapping("/game_edit")
    public String game_edit() {
        String id = getParaValue("id");
        Game entity = gameService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/game/game_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_GAME:EDIT')")
    @PostMapping("/game_edit")
    public
    @ResponseBody
    ApiResult game_edit(@ModelAttribute("entity") Game entity) {
        gameService.updateByIdSelective(entity);
        GameResult result = makeResult(entity, GameResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_GAME:ADD')")
    @GetMapping("/game_add")
    public String game_add() {
        return "/a/game/game_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_GAME:ADD')")
    @PostMapping("/game_add")
    public
    @ResponseBody
    ApiResult game_add(@ModelAttribute("entity") Game entity) {
        gameService.save(entity);
        GameResult result = makeResult(entity, GameResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_GAME:EDIT')")
    @PostMapping("/game_state")
    public
    @ResponseBody
    ApiResult game_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        Game entity = gameService.findById(Integer.valueOf(id));
        gameService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_GAME:DELETE')")
    @PostMapping("/game_delete")
    public
    @ResponseBody
    ApiResult game_delete() {
        String id = getParaValue("id");
        Game entity = gameService.findById(Integer.valueOf(id));
        gameService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



