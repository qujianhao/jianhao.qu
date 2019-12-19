package com.wangtiansoft.KingDarts.modules.game.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.game.service.GameOrderService;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.date.DateUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.results.core.GameOrderResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.Map;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/gameOrder")
public class GameOrderController extends BaseController {

	@Resource
	private GameOrderService gameOrderService;

	//  列表
	@PreAuthorize("hasPermission('','_GAMEORDER:VIEW')")
	@RequestMapping("/gameOrderNum_list")
	public String gameOrderNum_list(@RequestParam Map<String, Object> paramMap) {
		paramMap.put("order_time_start", DateUtil.DateToString(DateUtil.addDay(new Date(), -7), "yyyy-MM-dd"));
		paramMap.put("order_time_end", DateUtil.DateToString(new Date(), "yyyy-MM-dd"));
		request.setAttribute("paramMap", paramMap);
		return "/a/game/gameOrderNum_list";
	}

	//  列表分页
	@PreAuthorize("hasPermission('','_GAMEORDER:VIEW')")
	@PostMapping("/gameOrderNum_search")
	public
	@ResponseBody
	JQGirdPageResult gameOrderNum_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
		System.out.println("游戏数据统计参数"+paramMap.toString());
		Page<Map> page = gameOrderService.queryGameCountList(paramMap, pageBean);
		return new JQGirdPageResult(page);
	}

	@PreAuthorize("hasPermission('','_GAMEORDER:VIEW')")
	@RequestMapping("/gameTypeNum_list")
	public String gameTypeNum_list(@RequestParam Map<String, Object> paramMap) {
		request.setAttribute("paramMap", paramMap);
		return "/a/game/gameTypeNum_list";
	}

	//  列表分页
	@PreAuthorize("hasPermission('','_GAMEORDER:VIEW')")
	@PostMapping("/gameTypeNum_search")
	public
	@ResponseBody
	JQGirdPageResult gameTypeNum_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
		Page<Map> page = gameOrderService.queryGameTypeCountList(paramMap, pageBean);
		return new JQGirdPageResult(page);
	}


	//  列表
	@PreAuthorize("hasPermission('','_GAMEORDER:VIEW')")
	@RequestMapping("/gameOrder_list")
	public String gameOrder_list(@RequestParam Map<String, Object> paramMap) {
		request.setAttribute("paramMap", paramMap);
		return "/a/gameOrder/gameOrderNum_list";
	}

	//  列表分页
	@PreAuthorize("hasPermission('','_GAMEORDER:VIEW')")
	@PostMapping("/gameOrder_search")
	public
	@ResponseBody
	JQGirdPageResult gameOrder_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
		Page<Map> page = gameOrderService.queryGameOrderPageList(paramMap, pageBean);
		return makePageResult(page, GameOrderResult.class);
	}

	//  详情
	@PreAuthorize("hasPermission('','_GAMEORDER:VIEW')")
	@GetMapping("/gameOrder_view")
	public String gameOrder_view() {
		String id = getParaValue("id");
		GameOrder entity = gameOrderService.findById(Long.valueOf(id));
		request.setAttribute("entity", entity);
		return "/a/gameOrder/gameOrder_view";
	}

	//  编辑页面
	@PreAuthorize("hasPermission('','_GAMEORDER:EDIT')")
	@GetMapping("/gameOrder_edit")
	public String gameOrder_edit() {
		String id = getParaValue("id");
		GameOrder entity = gameOrderService.findById(Long.valueOf(id));
		request.setAttribute("entity", entity);
		return "/a/gameOrder/gameOrder_edit";
	}

	//  编辑保存
	@PreAuthorize("hasPermission('','_GAMEORDER:EDIT')")
	@PostMapping("/gameOrder_edit")
	public
	@ResponseBody
	ApiResult gameOrder_edit(@ModelAttribute("entity") GameOrder entity) {
		gameOrderService.updateByIdSelective(entity);
		GameOrderResult result = makeResult(entity, GameOrderResult.class);
		return ApiResult.success(result);
	}

	//  新建页面
	@PreAuthorize("hasPermission('','_GAMEORDER:ADD')")
	@GetMapping("/gameOrder_add")
	public String gameOrder_add() {
		return "/a/gameOrder/gameOrder_add";
	}

	//  新建保存
	@PreAuthorize("hasPermission('','_GAMEORDER:ADD')")
	@PostMapping("/gameOrder_add")
	public
	@ResponseBody
	ApiResult gameOrder_add(@ModelAttribute("entity") GameOrder entity) {
		gameOrderService.save(entity);
		GameOrderResult result = makeResult(entity, GameOrderResult.class);
		return ApiResult.success(result);
	}

	//  修改发布状态
	@PreAuthorize("hasPermission('','_GAMEORDER:EDIT')")
	@PostMapping("/gameOrder_state")
	public
	@ResponseBody
	ApiResult gameOrder_state() {
		String id = getParaValue("id");
		String is_publish = getParaValue("is_publish");
		GameOrder entity = gameOrderService.findById(Long.valueOf(id));
		gameOrderService.updateByIdSelective(entity);
		return ApiResult.success();
	}

	//  删除
	@PreAuthorize("hasPermission('','_GAMEORDER:DELETE')")
	@PostMapping("/gameOrder_delete")
	public
	@ResponseBody
	ApiResult gameOrder_delete() {
		String id = getParaValue("id");
		GameOrder entity = gameOrderService.findById(Long.valueOf(id));
		gameOrderService.updateByIdSelective(entity);
		return ApiResult.success();
	}

}



