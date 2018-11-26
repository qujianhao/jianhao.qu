package com.wangtiansoft.KingDarts.modules.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.club.service.ClubMemberService;
import com.wangtiansoft.KingDarts.modules.merchantAccount.service.MerchantAccountService;
import com.wangtiansoft.KingDarts.modules.pay.service.CommissionService;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;

/**
 * 会员管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/member")
public class WeixinMemberController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(WeixinMemberController.class);
	
	@Autowired
	private ClubMemberService clubMemberService;
	
	@Autowired
	private CommissionService commissionService;
	
	@Resource
	private MerchantAccountService merchantAccountService;
	
	/**
	 * 根据代理商编号或者俱乐部编号获取会员列表
	 * @param cno  俱乐部编号
	 * @param agno  代理商编号
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	public@ResponseBody ApiResult clublist(final String cno,final String page,final String rows) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> paramMap=new HashMap<>();
				MerchantAccount merchantAccount = merchantAccountService.getUserByLoginName(auth_no);
				if(merchantAccount.getMertype()==2) {
					paramMap.put("cno", cno);
					paramMap.put("agno", auth_no);
				}else {
					paramMap.put("cno", auth_no);
					paramMap.put("agno", null);
				}
				PageBean pageBean=new PageBean();
				pageBean.setPage(Integer.parseInt(page));
				pageBean.setRows(Integer.parseInt(rows));
				Map<String,Object> map = new HashMap<>();
				
				Map<String,Object> Page=new HashMap<>();
				Page<Map> list=clubMemberService.queryMemberList(paramMap,pageBean);
				Page.put("total", list.getTotal());
				Page.put("pageSize", list.getPageSize());
				Page.put("pageNum", list.getPageNum());
				Page.put("pages", list.getPages());
				Page.put("list", list);
				map.put("rechargePage", Page);
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 复购率
	 * @param cno俱乐部编号
	 * @param agno代理商编号
	 * @return
	 */
	@RequestMapping("/SencondProbability")
	public@ResponseBody ApiResult SencondProbability(final String cno) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Float SencondProbability;
				MerchantAccount merchantAccount = merchantAccountService.getUserByLoginName(auth_no);
				if(merchantAccount.getMertype()==2) {
					SencondProbability = commissionService.getSencondProbability(auth_no, cno);
				}else {
					SencondProbability = commissionService.getSencondProbability(null, auth_no);
				}
				Map<String,Object> map = new HashMap<>();
				map.put("SencondProbability", SencondProbability);
				return map;
			}
		});
		return result;
	}
}
