package com.wangtiansoft.KingDarts.modules.api.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.core.utils.AuthUtil;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquAuthService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;
import com.wangtiansoft.KingDarts.modules.merchantAccount.service.MerchantAccountService;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;
import com.wangtiansoft.KingDarts.results.core.ClubInfoResult;
import com.wangtiansoft.KingDarts.results.core.EquAuthResult;

@Controller
@RequestMapping("/api/equ")
public class WeiXinEquController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(WeiXinEquController.class);
	
	@Autowired
	private EquAuthService equAuthService;
	
	@Autowired
	private EquInfoService equInfoService;
	
	@Autowired
	private MerchantAccountService merchantAccountService;
	
	@Autowired
	private ClubInfoService clubInfoServie;
	
	/**
	 * 俱乐部飞镖机管理
	 * @param cno俱乐部编号
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/equlist")
	public@ResponseBody ApiResult equlist(final String page,final String rows) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> paramMap=new HashMap<>();
				paramMap.put("cno", auth_no);
				PageBean pageBean=new PageBean();
				pageBean.setPage(Integer.parseInt(page));
				pageBean.setRows(Integer.parseInt(rows));
				Map<String,Object> map = new HashMap<>();
				Map<String,Object> equAuthPage=new HashMap<>();
				Page<Map> equAuthList=equAuthService.queryEquAuthPageList(paramMap,pageBean);
				for(Map m: equAuthList){
					if(m.get("isentity")!=null&&"0".equals(m.get("isentity").toString())){
						m.put("isline", 1);//虚拟设备，直接是已经联网
					}
				}
				equAuthPage.put("total", equAuthList.getTotal());
				equAuthPage.put("pageSize", equAuthList.getPageSize());
				equAuthPage.put("pageNum", equAuthList.getPageNum());
				equAuthPage.put("pages", equAuthList.getPages());
				equAuthPage.put("equAuthList", equAuthList);
				map.put("equAuthPage", equAuthPage);
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 通过设备编号获取游戏定价
	 * @param equno设备编号
	 * @return
	 */
	@RequestMapping("/getGamePprice")
	public@ResponseBody ApiResult getGamePprice(final String equno) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> map = new HashMap<>();
				map.put("GamePprice", equInfoService.getGamePpriceByEquNo(equno));
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 通过设备编号修改游戏定价
	 * @param equno设备编号
	 * @param game_price游戏定价
	 * @return
	 */
	@RequestMapping("/updateGamePprice")
	public@ResponseBody ApiResult updateGamePprice(final String equno,final String game_price) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				BigDecimal price=new BigDecimal(game_price);
				Map<String,Object> map = new HashMap<>();
				map.put("count", equInfoService.updateManagePrice(equno,price));
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 通过代理商编号获取未授权设备列表
	 * @param agno 代理商编号
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/getEquNoAuthListByAgno")
	public@ResponseBody ApiResult getEquNoAuthListByAgno(final String page,final String rows) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> paramMap=new HashMap<>();
				paramMap.put("auth_no", auth_no);
				PageBean pageBean=new PageBean();
				pageBean.setPage(Integer.parseInt(page));
				pageBean.setRows(Integer.parseInt(rows));
				Map<String,Object> map = new HashMap<>();
				
				Map<String,Object> EquNoAuthPage=new HashMap<>();
				Page<Map> EquNoAuthList=equAuthService.getEquNoAuthListByAgno(paramMap,pageBean);
				EquNoAuthPage.put("total", EquNoAuthList.getTotal());
				EquNoAuthPage.put("pageSize", EquNoAuthList.getPageSize());
				EquNoAuthPage.put("pageNum", EquNoAuthList.getPageNum());
				EquNoAuthPage.put("pages", EquNoAuthList.getPages());
				EquNoAuthPage.put("EquNoAuthList", EquNoAuthList);
				map.put("EquNoAuthPage", EquNoAuthPage);
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 通过代理商编号获取已授权设备列表
	 * @param agno 代理商编号
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/getEquYesAuthListByAgno")
	public@ResponseBody ApiResult getEquYesAuthListByAgno(final String cname,final String page,final String rows) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> paramMap=new HashMap<>();
				paramMap.put("cname", cname);
				paramMap.put("agno", auth_no);
				PageBean pageBean=new PageBean();
				pageBean.setPage(Integer.parseInt(page));
				pageBean.setRows(Integer.parseInt(rows));
				Map<String,Object> map = new HashMap<>();
				
				Map<String,Object> EquYesAuthPage=new HashMap<>();
				Page<Map> EquYesAuthList=equAuthService.getEquYesAuthListByAgno(paramMap,pageBean);
				EquYesAuthPage.put("total", EquYesAuthList.getTotal());
				EquYesAuthPage.put("pageSize", EquYesAuthList.getPageSize());
				EquYesAuthPage.put("pageNum", EquYesAuthList.getPageNum());
				EquYesAuthPage.put("pages", EquYesAuthList.getPages());
				EquYesAuthPage.put("EquYesAuthList", EquYesAuthList);
				map.put("EquYesAuthPage", EquYesAuthPage);
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 通过代理商编号，设备编号 取消设备俱乐部授权
	 * @param agno代理商编号
	 * @param equno设备编号
	 * @return
	 */
	@RequestMapping("/cancelAuth")
	public@ResponseBody ApiResult cancelAuth(final String equno) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				MerchantAccount merchantAccount = merchantAccountService.getUserByLoginName(auth_no);
				Map<String,Object> map = new HashMap<>();
				map.put("count", equAuthService.updateAgnoAuthByEquno(equno,auth_no,merchantAccount.getAccountnames()));
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 代理商下飞镖机授权给俱乐部
	 * @param clubInfoResult
	 * @return
	 */
	@RequestMapping("/clubscale")
	public@ResponseBody ApiResult clubscale(@RequestBody final ClubInfoResult clubInfoResult) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				//String list = getParaValue("equAuthResultList");
				Map<String,Object> map = new HashMap<>();
				ClubInfoResult clubInfoResults = clubInfoServie.updateCnoAuthByEquno(clubInfoResult);
				map.put("clubInfoResults", clubInfoResults);
				return map;
			}
		});
		return result;
	}
}
