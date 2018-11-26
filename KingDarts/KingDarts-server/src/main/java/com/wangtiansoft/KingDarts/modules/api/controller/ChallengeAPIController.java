package com.wangtiansoft.KingDarts.modules.api.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.core.utils.GameUtil;
import com.wangtiansoft.KingDarts.modules.challenge.service.ChallengeWeixinService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.dao.master.DictMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Challenge;
import com.wangtiansoft.KingDarts.persistence.entity.Dict;
import com.wangtiansoft.KingDarts.results.core.ChallengeResult;
import com.wangtiansoft.KingDarts.results.core.UserResult;

@CrossOrigin
@Controller
@RequestMapping("/api/challenge")
public class ChallengeAPIController  extends BaseController{
	@Autowired
	private ChallengeWeixinService challengeWeixinService;
	@Autowired
	private UserService userService;
	@Autowired
	private DictMapper dictMapper;
	
	private static List<Map> challengeGames = null;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	
	
	/**
	 * 约战对象信息
	 * @param useraccount	用户ID
	 * @param response
	 */
	@RequestMapping("/target_info")
	public @ResponseBody ApiResult targetInfo(final String useraccount) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String userId) throws Exception {
				Map<String, Object> map = Maps.newHashMap();
				//被约战对象信息
				UserResult user = userService.getUserByUuid(useraccount);
				map.put("useraccount", user.getUuid());
				map.put("nickname", user.getNickname());
				map.put("points", user.getPoints());	//游戏积分
				map.put("headimgurl", user.getHeadimgurl());
				
				//被约战对象的勋章情况
//				List<DartsMedalWinner> winner = medalWeChatAppService.findList(useraccount);
//				map.put("medalList", winner);
//				map.put("medalCount", winner.size());
				
				//baseUrl
//				map.put("baseUrl", Global.getUserfilesBaseDir());
				return map;
			}
		});
		
		return result;
		
	}
	
	/**
	 * 获取游戏类型
	 * @param response
	 */
	@RequestMapping("/game_type")
	public @ResponseBody ApiResult gameType() {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String userId) throws Exception {
				Map<String,Object> params = new HashMap<>();
				params.put("group_key","game_type");
				List<Map> list = dictMapper.queryDictList(params);
				return list;
			}
		});
		
		return result;
	}
	/**
	 * 获取游戏信息
	 * @param response
	 */
	@RequestMapping("/game")
	public @ResponseBody ApiResult game() {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String userId) throws Exception {
				if(challengeGames == null){
					challengeGames = new ArrayList<>();
					for(int i=0;i<GameUtil.netCodes.length;i++){
						Map map = new HashMap();
//						map.put(GameUtil.netCodes[i], GameUtil.netNames[i]);
						map.put("dict_key", GameUtil.netCodes[i]);
						map.put("dict_value", GameUtil.netNames[i]);
						challengeGames.add(map);
					}
				}
				return challengeGames;
			}
		});
		
		return result;
	}
	
	/**
	 * 保存约战信息
	 * @param dartsChallenge
	 * @param response
	 */
	@RequestMapping("/save")
	public @ResponseBody ApiResult save(final ChallengeResult dartsChallenge) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String userId) throws Exception {
				
				UserResult user = userService.getUserByUuid(userId);
				if(user == null) {
					throw new AppRuntimeException("该用户不存在！");
				}
				//设置游戏类型
				if(StringUtils.isEmpty(dartsChallenge.getGame_type_id())){
					throw new AppRuntimeException("游戏类型编码不能为空");
				}
				Challenge challenge = new Challenge();
				BeanUtil.copyProperties(dartsChallenge, challenge);
				challenge.setGame_type_value(GameUtil.getNetName(challenge.getGame_type_id()));
				challengeWeixinService.insertChallenge(challenge, user);
				
				//TODO 发送约战推送
				
				
				Map<String, Object> map = Maps.newHashMap();
				map.put("msg", "发送约战成功");
				return map;
			}
		});
		
		return result;
		
	}
	
	/**
	 * 有人约战，登录用户作为约战对象的约战列表
	 * @param pageNo
	 * @param pageSize
	 * @param response
	 */
	@RequestMapping("/my_receiver")
	public @ResponseBody ApiResult myReceiverList(final PageBean pageBean) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String userId) throws Exception {
				Map<String, Object> map = Maps.newHashMap();
				
				ChallengeResult challenge = new ChallengeResult();
				challenge.setReceiver_useraccount(userId);
				Page<ChallengeResult> challenges = challengeWeixinService.findReceiverChallengeList(challenge, pageBean);
				map.put("challenge", challenges.getResult());
				map.put("pageSize", challenges.getPageSize());
				map.put("total", challenges.getTotal());
				map.put("pageNum", challenges.getPageNum());
				return map;
			}
		});
		
		return result;
	}
	
	/**
	 * 单个的约战信息
	 * @param id
	 * @param response
	 */
	@RequestMapping("/info")
	public @ResponseBody ApiResult ChallengeInfo(final String id) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String str) throws Exception {
				Map<String, Object> map = Maps.newHashMap();
				Challenge challenge = challengeWeixinService.queryChallenge(id);
				
				//约战发起人信息
//				String sponsorUseraccount = challenge.getSponsorUseraccount();
				
				return challenge;
			}
		});
		
		return result;
	}
	
	/**
	 * 设置拒绝/应战状态
	 * @param id
	 * @param receiveStatus
	 * @param response
	 */
	@RequestMapping("/set_receive_status")
	public @ResponseBody ApiResult setReceiveStatus(final String id, final String receiveStatus) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String str) throws Exception {
				if(receiveStatus == null) {
					throw new AppRuntimeException("应战状态不能为空！");
				}
				if(!receiveStatus.equals(Constants.receive_status_agree+"")&&!receiveStatus.equals(Constants.receive_status_refuse+"") ){
					throw new AppRuntimeException("应战状态错误！");
				}
				
				challengeWeixinService.updateReceiveStatus(id, Integer.parseInt(receiveStatus));
				
				Map<String, Object> map = Maps.newHashMap();
				map.put("msg", "设置成功");
				return map;
			}
		});
		
		return result;
	}
	
	/**
	 * 我的约战
	 * @param pageBean
	 * @return
	 */
	@RequestMapping("/my_all")
	public @ResponseBody ApiResult mySponsorList(final PageBean pageBean) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String userId) throws Exception {
				Map<String, Object> map = Maps.newHashMap();
				ChallengeResult challenge = new ChallengeResult();
				challenge.setSponsor_useraccount(userId);
				Page<ChallengeResult> challenges = challengeWeixinService.findAllList(challenge, pageBean);
				map.put("challenge", challenges.getResult());
				map.put("pageSize", challenges.getPageSize());
				map.put("total", challenges.getTotal());
				map.put("pageNum", challenges.getPageNum());
				return map;
			}
		});
		return result;
	}
	
	
	/**
	 * 超过浮动时间后，将此约战信息设置为过期
	 * @param id
	 * @param response
	 */
	@RequestMapping("/set_pass_due")
	public @ResponseBody ApiResult setPassDue(final String id) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String id) throws Exception {
				challengeWeixinService.setPassDueStatus(id);
				return null;
			}
		});
		return result;
	}
	
	/**
	 * 返回爽约次数
	 * @param response
	 */
	@RequestMapping("/miss_times")
	public @ResponseBody ApiResult missTimes() {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {

			@Override
			public Object execute(String userId) throws Exception {
				Map<String, Object> map = Maps.newHashMap();
				/*UserResult user = userService.getUserByUuid(userId);
				if(user == null) {
					throw new AppRuntimeException("该用户不存在！");
				}*/
				Integer missTimes = challengeWeixinService.missTimes(userId);
				missTimes = missTimes==null?0:missTimes;
				if(missTimes.intValue() >= 2) {
					map.put("msg", "已失约两次，本周无法约战！");
				}
				map.put("missTimes", missTimes);
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 失约设置
	 * @param id
	 * @param response
	 */
	@RequestMapping("/set_ismiss")
	public @ResponseBody ApiResult setIsmiss(final String id) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String userId) throws Exception {
				challengeWeixinService.updateIsMiss(id, userId);
				Map<String, Object> map = Maps.newHashMap();
				map.put("msg", "设置成功");
				return map;
			}
		});
		
		return result;
	}
}