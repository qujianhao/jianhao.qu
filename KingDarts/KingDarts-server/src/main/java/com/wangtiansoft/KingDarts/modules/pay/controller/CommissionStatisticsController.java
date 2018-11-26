package com.wangtiansoft.KingDarts.modules.pay.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.DateUtil;
import com.wangtiansoft.KingDarts.common.utils.ExcelRenderUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.pay.service.CommissionService;

@Controller
@RequestMapping("/a/commission")
public class CommissionStatisticsController extends BaseController {

	@Autowired
	private CommissionService commissionService;
	
	 /**
     * 俱乐部流水列表
     */
    @PreAuthorize("hasPermission('','commission:list')")
    @RequestMapping("/club_commission_list")
    public String  club_commission_list(@RequestParam Map<String, Object> paramMap) {
    	if(paramMap.get("query_month")==null || paramMap.get("query_month")=="") {
    		paramMap.put("query_month", DateUtil.formatDateBy("yyyy-MM",new Date()));
    	}
    	request.setAttribute("paramMap", paramMap);
		return "/a/commission/commission_list";
    }
    
    /**
     * 俱乐部流水查询
     */
    @PreAuthorize("hasPermission('','commission:list')")
    @PostMapping("/club_commission_search")
    public
    @ResponseBody
    JQGirdPageResult  club_commission_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
    	Page<Map> page=commissionService.queryClubCommissionDetailPageList(paramMap,pageBean);
    	return new JQGirdPageResult(page);
    }
    
    //  俱乐部流水列表导出
    @PreAuthorize("hasPermission('','commission:list')")
    @RequestMapping("/club_commission_down")
    public void club_commission_down_excel(@RequestParam Map<String, Object> paramMap,Integer type) {
    	if(paramMap.get("query_month")==null || paramMap.get("query_month")=="") {
    		paramMap.put("query_month", DateUtil.formatDateBy("yyyy-MM",new Date()));
    	}
    	Date date=DateUtil.formatDate(paramMap.get("query_month")+"-01".toString(),"yyyy-MM-dd");
    	List<String> timeList=DateUtil.allDateToMonth(date,"yyyy-MM-dd");
    	paramMap.put("timeList", timeList);
    	List<Map> listRegister=commissionService.queryClubCommissionDetailList(timeList,paramMap,type);
    	int dayNum=Integer.parseInt(DateUtil.formatDateBy("dd", DateUtil.dateToMonthLast(new Date())));
    		String[] header= {"序号","单位名称","俱乐部编号","区域","类别",
   		 "机器数量（台）","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
   		"20","21","22","23","24","25","26","27","28","29","30","31","合计","备注"};
		if(type==1) {
			String[] columns = {"row_num","cname","cno","city","type","eq_num",
            		"day1","day2","day3","day4","day5","day6","day7","day8","day9","day10","day11","day12","day13","day14","day15","day16",
            		"day17","day18","day19","day20","day21","day22","day23","day24","day25","day26","day27","day28","day29","day30","day31","month_amount"};
			ExcelRenderUtil renderUtil = ExcelRenderUtil.me 
					(request, response, listRegister).fileName("俱乐部流水"+paramMap.get("query_month")+".xlsx").headers(header).sheetName("俱乐部流水").columns(columns);
			renderUtil.render();
		}else {
			String[] columns = {"row_num","cname","cno","city","type","eq_num",
            		"day1","day2","day3","day4","day5","day6","day7","day8","day9","day10","day11","day12","day13","day14","day15","day16",
            		"day17","day18","day19","day20","day21","day22","day23","day24","day25","day26","day27","day28","day29","day30","day31","month_pay_amount"};
			ExcelRenderUtil renderUtil = ExcelRenderUtil.me 
					(request, response, listRegister).fileName("俱乐部流水"+paramMap.get("query_month")+".xlsx").headers(header).sheetName("俱乐部流水").columns(columns);
			renderUtil.render();
		}
    }
}