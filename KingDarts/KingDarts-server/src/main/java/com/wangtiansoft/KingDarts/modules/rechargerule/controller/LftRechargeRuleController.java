package com.wangtiansoft.KingDarts.modules.rechargerule.controller;

import java.math.BigDecimal;
import java.util.List;
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

import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.rechargerule.service.LftRechargeRuleService;
import com.wangtiansoft.KingDarts.persistence.entity.LftRechargeRule;
import com.wangtiansoft.KingDarts.results.core.LftRechargeRuleListResult;

@Controller
@RequestMapping("/a/rechargeRule")
public class LftRechargeRuleController extends BaseController {

	@Resource
	private LftRechargeRuleService lftRechargeRuleService;
	
	//获取充值规则
    @PreAuthorize("hasPermission('','_BQELECTRONREGISTER:VIEW')")
    @GetMapping("/archives_details")
    public String archives_details(@RequestParam Map<String, Object> paramMap) {
    	//登记信息
        paramMap.put("map", lftRechargeRuleService.getAllRechargeRule());
        request.setAttribute("paramMap", paramMap);
        return "/a/rechargerule/list";
    }
    
    //  新建页面
    @PreAuthorize("hasPermission('','_RECHARGEPAY:ADD')")
    @GetMapping("/rechargeRule_add")
    public String rechargeRule_add(@RequestParam Map<String, Object> map) {
    	List<LftRechargeRule> rechargeRulelist = lftRechargeRuleService.getAllRechargeRule();
    	map.put("rechargeRulelist", rechargeRulelist);
    	for(LftRechargeRule rechargePay : rechargeRulelist) {
    		//BigDecimal price = rechargePay.getMoney().divide(rechargePay.getGame_balance(),2,BigDecimal.ROUND_HALF_UP);
    		request.setAttribute("price", 1);
    	}
        request.setAttribute("map", map);
        return "/a/rechargePay/rechargeRule_add";
    }
	
	//  新建保存
    @PreAuthorize("hasPermission('','_LftRechargeRule:ADD')")
    @PostMapping("/rechargeRule_save")
    public
    @ResponseBody
    ApiResult rechargeRule_save(LftRechargeRuleListResult lftRechargeRuleListResult) {
    	List<LftRechargeRule> lftRechargeRuleList = lftRechargeRuleListResult.getRechargeRulelist();
    	for(LftRechargeRule rechargePay:lftRechargeRuleList) {
    		if(rechargePay.getId()==null) {
        		rechargePay.setIsvalid(1);
        		lftRechargeRuleService.save(rechargePay);
    		}else {
    			lftRechargeRuleService.updateByIdSelective(rechargePay);
    		}
    	}
        return ApiResult.success();
    }
    
    //  编辑保存
    @PreAuthorize("hasPermission('','_LftRechargeRule:EDIT')")
    @PostMapping("/rechargeRule_edit")
    public
    @ResponseBody
    ApiResult rechargeRule_edit(@ModelAttribute("entity") LftRechargeRule entity) {
    	lftRechargeRuleService.updateByIdSelective(entity);
        return ApiResult.success();
    }
    
    //  删除
    @PreAuthorize("hasPermission('','_LftRechargeRule:DELETE')")
    @PostMapping("/rechargeRule_delete")
    public
    @ResponseBody
    ApiResult rechargeRule_delete() {
        String id = getParaValue("id");
        LftRechargeRule entity = lftRechargeRuleService.findById(Integer.valueOf(id));
        lftRechargeRuleService.updateByIdSelective(entity);
        return ApiResult.success();
    }
}
