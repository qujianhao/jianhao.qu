package com.wangtiansoft.KingDarts.modules.equip.controller;

import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.client.service.EquipClientService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.RandomUtil;
import com.wangtiansoft.KingDarts.common.utils.SmsUtil;
import com.wangtiansoft.KingDarts.common.utils.date.DateUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;
import com.wangtiansoft.KingDarts.results.core.EquInfoResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/equInfo")
public class EquInfoController extends BaseController {

	@Resource
	private EquInfoService equInfoService;
	@Resource
	private EquipClientService equipClientService;

	//  列表
	//    @PreAuthorize("hasPermission('','_EQUINFO:VIEW')")
	@RequestMapping("/equInfo_list")
	public String equInfo_list(@RequestParam Map<String, Object> paramMap) {
		request.setAttribute("paramMap", paramMap);
		return "/a/equip/equInfo_list";
	}

	//  列表分页
	//    @PreAuthorize("hasPermission('','_EQUINFO:VIEW')")
	@PostMapping("/equInfo_search")
	public
	@ResponseBody
	JQGirdPageResult equInfo_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
		Page<Map> page = equInfoService.queryEquInfoPageList(paramMap, pageBean);
		return makePageResult(page, EquInfoResult.class);
	}

	//  详情
	//    @PreAuthorize("hasPermission('','_EQUINFO:VIEW')")
	@GetMapping("/equInfo_view")
	public String equInfo_view() {
		String id = getParaValue("id");
		EquInfo entity = equInfoService.findById(id);
		request.setAttribute("entity", entity);
		return "/a/equip/equInfo_view";
	}

	//  编辑页面
	//    @PreAuthorize("hasPermission('','_EQUINFO:EDIT')")
	@GetMapping("/equInfo_edit")
	public String equInfo_edit() {
		String id = getParaValue("id");
		EquInfo entity = equInfoService.findById(id);
		request.setAttribute("entity", entity);
		return "/a/equip/equInfo_edit";
	}

	//  编辑保存
	@PreAuthorize("hasPermission('','_EQUINFO:EDIT')")
	@PostMapping("/equInfo_edit")
	public
	@ResponseBody
	ApiResult equInfo_edit(@ModelAttribute("entity") EquInfo entity) {
		equInfoService.updateByIdSelective(entity);
		EquInfoResult result = makeResult(entity, EquInfoResult.class);
		return ApiResult.success(result);
	}

	//  新建页面
	@PreAuthorize("hasPermission('','_EQUINFO:ADD')")
	@GetMapping("/equInfo_add")
	public String equInfo_add() {
		return "/a/equip/equInfo_add";
	}

	//  新建保存
	@PreAuthorize("hasPermission('','_EQUINFO:ADD')")
	@PostMapping("/equInfo_add")
	public
	@ResponseBody
	ApiResult equInfo_add(@ModelAttribute("entity") EquInfo entity) {
		if(StringUtils.isEmpty(entity.getEquno())){
			throw new AppRuntimeException("设备编码不能为空");
		}
		EquInfo equ = equInfoService.getEquInfoByNo(entity.getEquno());
		if(equ!=null){
			throw new AppRuntimeException("设备编码已存在");
		}
		
		entity.setId(RandomUtil.createUUID());
		//设置默认值
		if(entity.getGame_price()==null) {//设置默认游戏定价
			BigDecimal price = new BigDecimal(Constants.price_def);
			entity.setGame_price(price);
		}
		entity.setIsowed(Constants.True);
		entity.setIsallow(Constants.True);
		entity.setIsline(Constants.False);
		entity.setIsactivation(Constants.False);
		entity.setIsvalid(Constants.True);
		entity.setAdd_time(new Date());
		entity.setEqu_status(Constants.equ_status_unauth);
		entity.setIsentity(Constants.True);

		equInfoService.save(entity);
		EquInfoResult result = makeResult(entity, EquInfoResult.class);
		return ApiResult.success(result);
	}

	//  修改发布状态
	//    @PreAuthorize("hasPermission('','_EQUINFO:EDIT')")
	@PostMapping("/equInfo_state")
	public
	@ResponseBody
	ApiResult equInfo_state() {
		String id = getParaValue("id");
		String is_publish = getParaValue("is_publish");
		EquInfo entity = equInfoService.findById(id);
		equInfoService.updateByIdSelective(entity);
		return ApiResult.success();
	}

	//  删除
	//    @PreAuthorize("hasPermission('','_EQUINFO:DELETE')")
	@PostMapping("/equInfo_delete")
	public
	@ResponseBody
	ApiResult equInfo_delete() {
		String id = getParaValue("id");
		EquInfo equInfo = equInfoService.findById(id);
		
		EquInfo entity = new EquInfo();
		entity.setId(id);
		entity.setEquno(equInfo.getEquno()+"_"+RandomUtil.getRandom(5));
		entity.setIsvalid(0);
		equInfoService.updateByIdSelective(entity);
		//        EquInfo entity = equInfoService.findById(id);
		//        equInfoService.updateByIdSelective(entity);
		return ApiResult.success();
	}


	//  开机提醒列表
	//  @PreAuthorize("hasPermission('','_EQUINFO:VIEW')")
	@RequestMapping("/line/equLine_list")
	public String line_equInfo_list(@RequestParam Map<String, Object> paramMap) {
		request.setAttribute("paramMap", paramMap);
		return "/a/equip/equLine_list";
	}

	//  开机提醒列表分页
	//  @PreAuthorize("hasPermission('','_EQUINFO:VIEW')")
	@PostMapping("/line/equInfo_search")
	public
	@ResponseBody
	JQGirdPageResult line_equInfo_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
		if(paramMap.get("stype")!=null&&!"".equals(paramMap.get("stype"))){
			if("1".equals(paramMap.get("stype"))){
				paramMap.put("isline", Constants.True);
			}else if("2".equals(paramMap.get("stype"))){
				paramMap.put("isline", Constants.False);
			}else if("3".equals(paramMap.get("stype"))){
				paramMap.put("isline", Constants.False);
				paramMap.put("diftime", 12);
			}else if("4".equals(paramMap.get("stype"))){
				paramMap.put("isline", Constants.False);
				paramMap.put("diftime", 24);
			}else if("5".equals(paramMap.get("stype"))){
				paramMap.put("isline", Constants.False);
				paramMap.put("diftime", new BigDecimal(paramMap.get("day").toString()).multiply(new BigDecimal(24)));
			}
		}
		paramMap.put("equ_status", 1);
		Page<Map> page = equInfoService.queryEquOnliePageList(paramMap, pageBean);
		return new JQGirdPageResult(page);
	}

	@PostMapping("/sendOffSms")
	public @ResponseBody ApiResult sendOffSms(String mobiles) {
		if(StringUtils.isEmpty(mobiles)){
			throw new AppRuntimeException("无发送手机号");
		}
		String[] str = mobiles.split(",");
		for(String mobile:str){
			try {
				if(StringUtils.isNotEmpty(mobile)){
					System.out.println(mobile);
					String[] mstr = mobile.split(";");
					if(mstr.length==3&&StringUtils.isNotEmpty(mstr[0])){
						SmsUtil.sendOffSms(mstr[0],mstr[1],mstr[2]);
					}
					//SmsUtil.sendOffSms(mobile);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ApiResult.success();
	}


	@RequestMapping("/equStatistics_list")
	public String equStatistics_list(@RequestParam Map<String, Object> paramMap) {
		paramMap.put("time_start", DateUtil.DateToString(DateUtil.addDay(new Date(), -7), "yyyy-MM-dd"));
		paramMap.put("time_end", DateUtil.DateToString(new Date(), "yyyy-MM-dd"));
		request.setAttribute("paramMap", paramMap);
		return "/a/equip/equStatistics_list";
	}

	//  列表分页
	//  @PreAuthorize("hasPermission('','_EQUINFO:VIEW')")
	@PostMapping("/equStatistics_search")
	public
	@ResponseBody
	JQGirdPageResult equStatistics_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
		if(paramMap.get("time_end")!=null&&!"".equals(paramMap.get("time_end").toString())){
			// paramMap.put("time_end",paramMap.get("time_end").toString()+" 23:59:59");
			paramMap.put("time_end",paramMap.get("time_end").toString());
		}
		Page<Map> page = equInfoService.queryEquStatistics(paramMap, pageBean);
		return new JQGirdPageResult(page);
	}
	
	//强制下线
	@PostMapping("/offline")
	public @ResponseBody ApiResult offline(String equno) {
		if(StringUtils.isEmpty(equno)){
			throw new AppRuntimeException("设备编码不能为空");
		}
		equipClientService.offline(equno);
		return ApiResult.success();
	}
}



