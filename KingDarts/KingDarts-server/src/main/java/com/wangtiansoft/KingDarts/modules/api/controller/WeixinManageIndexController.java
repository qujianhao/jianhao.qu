package com.wangtiansoft.KingDarts.modules.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.agent.service.AgentInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubMemberService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquAuthService;
import com.wangtiansoft.KingDarts.modules.game.service.GameOrderService;
import com.wangtiansoft.KingDarts.modules.game.service.GamePrizeService;
import com.wangtiansoft.KingDarts.modules.pay.service.CommissionService;
import com.wangtiansoft.KingDarts.modules.race.service.RaceInfoService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.entity.AgentInfo;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchPrize;
import com.wangtiansoft.KingDarts.persistence.entity.GamePrize;
import com.wangtiansoft.KingDarts.results.core.AwardUserInfo;
import com.wangtiansoft.KingDarts.core.stubs.PluginManager;
import com.wangtiansoft.KingDarts.core.stubs.file.BaseFilePluginStub;

import tk.mybatis.mapper.entity.Example;

/**
 * 商户端首页
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/manage")
public class WeixinManageIndexController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(WeixinManageIndexController.class);
	
	@Autowired
	private EquAuthService equAuthService;
	
	@Autowired
	private CommissionService commissionService;
	
	@Autowired
	private ClubMemberService clubMemberService;
	
	@Autowired
	private ClubInfoService clubInfoService;
	
	@Autowired
	private AgentInfoService agentInfoService;
	
	@Autowired
	private RaceInfoService raceInfoService;
	
	@Autowired
	private GamePrizeService gamePrizeService;
	
	@Autowired
	private GameOrderService gameOrderService;

	@Autowired
	private UserService userService;
	
	/**
	 * 俱乐部首页
	 * @param cno 俱乐部编号
	 * @return
	 */
	@RequestMapping("/clubindex")
	public@ResponseBody ApiResult clubindex() {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				//System.out.println(uuid);
				Map<String,Object> map = new HashMap<>();
				Example example=new Example(ClubInfo.class);
				example.createCriteria().andEqualTo("cno",auth_no);
				ClubInfo clubInfo=clubInfoService.findOneByExample(example);
				
				Integer activation = equAuthService.getYesActivationByAuthNo(auth_no);//已激活设备数量
				Integer notActivation = equAuthService.getNoActivationByAuthNo(auth_no);//未激活设备数量
				BigDecimal totalAmount = commissionService.getClubTotalAmountByCno(auth_no);//总充值金额
				BigDecimal partitionTotalAmount = commissionService.getClubPartitionTotalAmountByCno(auth_no);//总分成金额
				Integer count = clubMemberService.queryMemberCount(auth_no, null);//会员总数
				Integer raceNum = raceInfoService.getCountByCno(auth_no);//发布的比赛总数
				//文件跟目录
		        BaseFilePluginStub filePluginStub = PluginManager.getInstance().getFilePluginStub();
		        map.put("fileBase", filePluginStub.getBaseFileUrl());
				map.put("activation", activation);
				map.put("notActivation", notActivation);
				map.put("totalAmount", totalAmount);
				map.put("partitionTotalAmount", partitionTotalAmount);
				map.put("count", count);
				map.put("clubInfo", clubInfo);
		        map.put("raceNum", raceNum);
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 代理商首页
	 * @param agno 代理商编号
	 * @return
	 */
	@RequestMapping("/agentindex")
	public@ResponseBody ApiResult agentindex() {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> map = new HashMap<>();
				Example example=new Example(AgentInfo.class);
				example.createCriteria().andEqualTo("agno",auth_no);
				AgentInfo agentInfo=agentInfoService.findOneByExample(example);
				
				BigDecimal totalAmount = commissionService.getAgentTotalAmountByAgno(auth_no);//总充值金额
				BigDecimal partitionTotalAmount = commissionService.getAgentPartitionTotalAmountByAgno(auth_no);//总分成金额
				Integer count = clubMemberService.queryMemberCount(null, auth_no);//会员总数
				Integer equAuthNotCount = equAuthService.getEquNoAuthCountByAgno(auth_no);
				Integer equAuthYesCount = equAuthService.getEquYesAuthCountByAgno(auth_no);
				map.put("equAuthCount",equAuthNotCount+equAuthYesCount);//飞镖机总数
				//文件跟目录
		        BaseFilePluginStub filePluginStub = PluginManager.getInstance().getFilePluginStub();
		        map.put("fileBase", filePluginStub.getBaseFileUrl());
				map.put("totalAmount", totalAmount);
				map.put("partitionTotalAmount", partitionTotalAmount);
				map.put("count", count);
				map.put("agentInfo", agentInfo);
				return map;
			}
		});
		return result;
	}
	
	
	/**
	 * 获取奖品信息
	 * @return
	 */
	@RequestMapping("/gamePrize")
	@ResponseBody
	public ApiResult<JSONArray> gamePrize() {
		ApiResult<JSONArray> result = new ApiResult<JSONArray>();
		JSONArray array = new JSONArray();
		try {
			array = gamePrizeService.listValidPrizes();
			result.setMsg("success");
			result.setCode("0");
		} catch (Exception e) {
			result.setMsg("fail");
			result.setCode("1");
		}
		result.setData(array);
		return result;
	}
	
	
	/**
	 * 在该俱乐部的机器下获取某类奖品的人
	 */
	@RequestMapping("getAwardUserInfo")
	@ResponseBody
	public ApiResult getAwardUserInfo(final int id){
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String auth_no) throws Exception {
				Map<String,Object> map = new HashMap<>();
				if ( id==0 || auth_no == null || auth_no == "") {
					logger.info("getAwardUserInfo param info----id={},auth_no={}",id,auth_no);
					logger.error("参数不能为空");
					return map;
				}
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("id", id);
				paraMap.put("auth_no", auth_no);
				
				JSONObject resultJson = new JSONObject();
				resultJson = gameOrderService.getAwardUserInfo(paraMap);
				map.put("data", resultJson);
				
				return map;
			}});
		return result;

	} 
	
	/**
	 * 获取云川俱乐部十一之前的钱前40名排名信息
	 * @return
	 */
	/*
	 * @RequestMapping("getYunChuanRank")
	 * 
	 * @ResponseBody public ApiResult getYunChuanRank() { ApiResult result =
	 * this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
	 * 
	 * @Override public Object execute(String auth_no) throws Exception {
	 * Map<String,Object> map = new HashMap<>(); JSONArray array =
	 * userService.getYunChuanRank(auth_no, "2019-10-01"); map.put("data", array);
	 * return map; } }); return result; }
	 */
	
	
	
}
