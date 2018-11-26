package com.wangtiansoft.KingDarts.modules.boss.controller;

import java.util.Date;
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
import com.wangtiansoft.KingDarts.common.utils.DateUtil;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.boss.service.BossEntityService;
import com.wangtiansoft.KingDarts.modules.boss.service.BossService;
import com.wangtiansoft.KingDarts.persistence.entity.Boss;
import com.wangtiansoft.KingDarts.persistence.entity.BossEntity;
import com.wangtiansoft.KingDarts.results.core.BossResult;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/boss")
public class BossController extends BaseController {

    @Resource
    private BossService bossService;
    
    @Resource
    private BossEntityService bossEntityService;

    //  列表
    @PreAuthorize("hasPermission('','_BOSS:VIEW')")
    @RequestMapping("/boss_list")
    public String boss_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/boss/boss_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_BOSS:VIEW')")
    @PostMapping("/boss_search")
    public
    @ResponseBody
    JQGirdPageResult boss_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = bossService.queryBossPageList(paramMap, pageBean);
        return makePageResult(page, BossResult.class);
    }


    //  编辑页面
    @PreAuthorize("hasPermission('','_BOSS:EDIT')")
    @GetMapping("/boss_edit")
    public String boss_edit() {
        String id = getParaValue("id");
        Boss entity = bossService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/boss/boss_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_BOSS:EDIT')")
    @PostMapping("/boss_edit")
    public
    @ResponseBody
    ApiResult boss_edit(@ModelAttribute("entity") Boss entity) {
        bossService.updateByIdSelective(entity);
        BossResult result = makeResult(entity, BossResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_BOSS:ADD')")
    @GetMapping("/boss_add")
    public String boss_add() {
        return "/a/boss/boss_edit";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_BOSS:ADD')")
    @PostMapping("/boss_add")
    public
    @ResponseBody
    ApiResult boss_add(@ModelAttribute("entity") Boss entity) {
    	entity.setIs_use(0);
        bossService.save(entity);
        BossResult result = makeResult(entity, BossResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_BOSS:EDIT')")
    @PostMapping("/boss_state")
    public
    @ResponseBody
    ApiResult boss_state() {
        String id = getParaValue("id");
        String is_use = getParaValue("is_use");
        Boss entity = bossService.findById(Integer.valueOf(id));
        if(StringUtils.equals(is_use, "1")) {
        	//修改其它为未应用
        	bossService.updateBossIsNotUse();
        }
        entity.setIs_use(Integer.valueOf(is_use));
        bossService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_BOSS:DELETE')")
    @PostMapping("/boss_delete")
    public
    @ResponseBody
    ApiResult boss_delete() {
        String id = getParaValue("id");
        Boss entity = bossService.findById(Integer.valueOf(id));
        entity.setIs_delete(Constants.True);
        bossService.updateByIdSelective(entity);
        //删除新增的实体
    	String date=DateUtil.mongoDateToSave(DateUtil.addDate(new Date(),1));
    	Example example=new Example(BossEntity.class);
    	example.createCriteria().andEqualTo("application_time",date)
    	.andEqualTo("boss_id",entity.getId()).andEqualTo("is_delete",Constants.False);
    	BossEntity bossEntity=bossEntityService.findOneByExample(example);
    	if(bossEntity!=null) {
    		bossEntity.setIs_delete(Constants.True);
        	bossEntityService.updateByIdSelective(bossEntity);
    	}
        return ApiResult.success();
    }

}