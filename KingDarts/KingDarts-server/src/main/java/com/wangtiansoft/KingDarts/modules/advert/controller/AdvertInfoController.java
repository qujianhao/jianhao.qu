package com.wangtiansoft.KingDarts.modules.advert.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.results.core.AdvertInfoResult;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.advert.service.AdvertInfoService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.AdvertInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/advertInfo")
public class AdvertInfoController extends BaseController {

    @Resource
    private AdvertInfoService advertInfoService;
    @Resource
    private EquInfoService equInfoService;
    

    //  列表
    @PreAuthorize("hasPermission('','_ADVERTINFO:VIEW')")
    @RequestMapping("/advertInfo_list")
    public String advertInfo_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/advert/advertInfo_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_ADVERTINFO:VIEW')")
    @PostMapping("/advertInfo_search")
    public
    @ResponseBody
    JQGirdPageResult advertInfo_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = advertInfoService.queryAdvertInfoPageListnocora(paramMap, pageBean);
        return makePageResult(page, AdvertInfoResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_ADVERTINFO:VIEW')")
    @GetMapping("/advertInfo_view")
    public String advertInfo_view() {
    String id = getParaValue("id");
        AdvertInfo entity = advertInfoService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/advert/advertInfo_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_ADVERTINFO:EDIT')")
    @GetMapping("/advertInfo_edit")
    public String advertInfo_edit() {
        String id = getParaValue("id");
        AdvertInfo entity = advertInfoService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/advert/advertInfo_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_ADVERTINFO:EDIT')")
    @PostMapping("/advertInfo_edit")
    public
    @ResponseBody
    ApiResult advertInfo_edit(@ModelAttribute("entity") AdvertInfo entity) {
        advertInfoService.updateByIdSelective(entity);
        AdvertInfoResult result = makeResult(entity, AdvertInfoResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_ADVERTINFO:ADD')")
    @GetMapping("/advertInfo_add")
    public String advertInfo_add() {
        return "/a/advert/advertInfo_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_ADVERTINFO:ADD')")
    @PostMapping("/advertInfo_add")
    public
    @ResponseBody
    ApiResult advertInfo_add(@ModelAttribute("entity") AdvertInfo entity) {
    	entity.setAdd_time(new Date());
    	entity.setUpdate_time(new Date());
        advertInfoService.save(entity);
        entity.setIs_publish(Constants.False);
        advertInfoService.updateByIdSelective(entity);
        AdvertInfoResult result = makeResult(entity, AdvertInfoResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_ADVERTINFO:EDIT')")
    @PostMapping("/advertInfo_state")
    public
    @ResponseBody
    ApiResult advertInfo_state(Integer id,Integer is_publish) {
    	if(is_publish.equals(1)){
    		Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("is_publish", 1);		
			Page<Map> page = advertInfoService.queryAdvertInfoPageListnocora(paramMap, new PageBean());

			for(Map map:page.getResult()){
				AdvertInfo entity = new AdvertInfo();
				entity.setId((Integer)map.get("id"));
				entity.setIs_publish(0);
				advertInfoService.updateByIdSelective(entity);
			}
    	}
        AdvertInfo entity = advertInfoService.findById(id);
        entity.setIs_publish(is_publish);
        advertInfoService.updateByIdSelective(entity);
        
        
        return ApiResult.success();
    }
    

    //  修改发布状态
    @PreAuthorize("hasPermission('','_ADVERTINFO:EDIT')")
    @PostMapping("/advertInfo_state_isClub")
    public
    @ResponseBody
    ApiResult advertInfo_state_isClub(Integer id,Integer is_publish,Integer clubId) {
    	if(is_publish.equals(1)){
    		Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("is_publish", 1);
			
			paramMap.put("belong_club", clubId);
//			paramMap.put("belong_agent", "");		
			Page<Map> page = equInfoService.queryAdvertInfoPageListByclubId(paramMap, new PageBean());						
//			Page<Map> page = advertInfoService.queryAdvertInfoPageList(paramMap, new PageBean());
			for(Map map:page.getResult()){
				AdvertInfo entity = new AdvertInfo();
				entity.setId((Integer)map.get("id"));
				entity.setIs_publish(0);
				advertInfoService.updateByIdSelective(entity);
			}
    	}
        AdvertInfo entity = advertInfoService.findById(id);
        entity.setIs_publish(is_publish);
        advertInfoService.updateByIdSelective(entity);       
        return ApiResult.success();
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_ADVERTINFO:EDIT')")
    @PostMapping("/advertInfo_state_isAgent")
    public
    @ResponseBody
    ApiResult advertInfo_state_isAgent(Integer id,Integer is_publish,Integer agentId) {
    	if(is_publish.equals(1)){
    		Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("is_publish", 1);			
//			paramMap.put("belong_club", "");
			paramMap.put("belong_agent", agentId);			
			Page<Map> page = equInfoService.queryAdvertInfoPageListByagentId(paramMap, new PageBean());
			for(Map map:page.getResult()){
				AdvertInfo entity = new AdvertInfo();
				entity.setId((Integer)map.get("id"));
				entity.setIs_publish(0);
				advertInfoService.updateByIdSelective(entity);
			}
    	}
        AdvertInfo entity = advertInfoService.findById(id);
        entity.setIs_publish(is_publish);
        advertInfoService.updateByIdSelective(entity);     
        return ApiResult.success();
    }


    //  删除
    @PreAuthorize("hasPermission('','_ADVERTINFO:DELETE')")
    @PostMapping("/advertInfo_delete")
    public
    @ResponseBody
    ApiResult advertInfo_delete() {
        String id = getParaValue("id");
        AdvertInfo entity = advertInfoService.findById(Integer.valueOf(id));
        entity.setIs_delete(1);
        advertInfoService.updateByIdSelective(entity);
        return ApiResult.success();
    }

}



