package com.wangtiansoft.KingDarts.modules.boss.controller;

import java.util.HashMap;
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
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.boss.service.BossEntityService;
import com.wangtiansoft.KingDarts.persistence.entity.BossEntity;
import com.wangtiansoft.KingDarts.results.core.BossEntityResult;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/bossEntity")
public class BossEntityController extends BaseController {

    @Resource
    private BossEntityService bossEntityService;

    //  列表
    @PreAuthorize("hasPermission('','_BOSSENTITY:VIEW')")
    @RequestMapping("/bossEntity_list")
    public String bossEntity_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/boss/bossEntity_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_BOSSENTITY:VIEW')")
    @PostMapping("/bossEntity_search")
    public
    @ResponseBody
    JQGirdPageResult bossEntity_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
    	Page<Map> page = bossEntityService.queryBossEntityPageList(paramMap, pageBean);
        return makePageResult(page, BossEntityResult.class);
    }
    
    //  详情页面
    @PreAuthorize("hasPermission('','_BOSSENTITY:VIEW')")
    @GetMapping("/bossEntity_view")
    public String bossEntity_view() {
        String uuid = getParaValue("uuid");
        BossEntity entity = bossEntityService.findById(uuid);
        request.setAttribute("entity", entity);
        //排名
    	Map paramMap=new HashMap<>();
    	paramMap.put("boss_id", entity.getUuid());
    	PageBean pageBean=new PageBean();
    	pageBean.setPage(0);
    	pageBean.setRows(10);
    	Page<Map> page=bossEntityService.queryBossScoreRankPageList(paramMap, pageBean);
    	request.setAttribute("rankPage", page);
        return "/a/boss/bossEntity_edit";
    }

}