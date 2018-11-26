package com.wangtiansoft.KingDarts.modules.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.StringUtils;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.core.utils.GameUtil;
import com.wangtiansoft.KingDarts.modules.api.token.Token;
import com.wangtiansoft.KingDarts.modules.api.token.TokenManager;
import com.wangtiansoft.KingDarts.modules.challenge.service.ChallengeService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;
import com.wangtiansoft.KingDarts.modules.game.service.GameOrderService;
import com.wangtiansoft.KingDarts.modules.game.service.GameResPlayerService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.modules.weixin.utils.AppAccessKey;
import com.wangtiansoft.KingDarts.modules.weixin.utils.AppAccessTokenApi;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.results.core.UserResult;


@Controller
@RequestMapping("/api/equ")
public class EquipAPIController  extends BaseController{

	private final static Logger logger = LoggerFactory.getLogger(EquipAPIController.class);

	@Autowired
    private RedisTemplate redisTemplate;
	@Resource
    private EquInfoService equInfoService;
	@Resource
	private UserService userService;
	@Resource
	private GameResPlayerService gameResPlayerService;
	@Resource
	private GameOrderService gameOrderService;
	@Resource
	private ChallengeService challengeService;

	/**
	 * 游戏结束结果上传
	 * @param params
	 * @return
	 */
	@RequestMapping("/gameres")
	public @ResponseBody ApiResult gameres(@RequestBody final Map params) {
		ApiResult result = this.buildEquAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String equno) throws Exception {
				
				if(params.get("order_no")==null||params.get("results")==null){
					throw new AppRuntimeException("order_no，results不能为空");
				}
				String orderNo = params.get("order_no").toString();
				String win = params.get("is_win")!=null?params.get("is_win").toString():"";
				
				GameOrder gameOrder = new GameOrder();
				gameOrder.setOrder_no(params.get("order_no").toString());
				gameOrder = gameOrderService.getGameOrderByNo(gameOrder);
				if(gameOrder==null){
					throw new AppRuntimeException(Constants.kCode_NoOrder,"订单不存在");
				}
				if(gameOrder.getStatus()>Constants.gorder_status_ingame){
					//如果是网络对战，网络对战2台机器都会上传结果，前一个已经将订单关闭
					Map<String,Object> map = new HashMap<>();
					map.put("msg", "成绩已提交");
					return map;
					
					/*if(StringUtils.isNotEmpty(gameOrder.getNet_no())){
						//如果是网络对战，网络对战2台机器都会上传结果，前一个已经将订单关闭
						Map<String,Object> map = new HashMap<>();
						map.put("msg", "提交成功");
						return map;
					}
					throw new AppRuntimeException(Constants.kCode_NoOrder,"状态错误");*/
				}
				
				//如果是网络对战，则取消绑定
				boolean isChallenge = false;
				if(GameUtil.isNetGame(gameOrder.getGame_code())){
					Object target = redisTemplate.opsForHash().get(Constants.equno_net, equno);
					if(target!=null){
						redisTemplate.opsForHash().delete(Constants.equno_net, equno);
						redisTemplate.opsForHash().delete(Constants.equno_net, target.toString());
					}
					
					//判断是否是约战,如果是约战，更新约战成绩
					Object targetOrder = redisTemplate.opsForHash().get(Constants.challenge_orderno, orderNo);
					if(targetOrder!=null){
						isChallenge = true;
						
						redisTemplate.opsForHash().delete(Constants.challenge_orderno, orderNo);
						redisTemplate.opsForHash().delete(Constants.challenge_orderno, targetOrder.toString());
					}
				}
				
				//保存成绩
				if(params.get("attach")!=null){
					//包含大富豪游戏数据
					gameResPlayerService.saveGameResult(orderNo,(List)params.get("results"),gameOrder.getNet_no(),win,(Map)params.get("attach"));
				}else{
					gameResPlayerService.saveGameResult(orderNo,(List)params.get("results"),gameOrder.getNet_no(),win);
				}
				
				
				if(isChallenge){
					//是约战，则更新约战成绩
					challengeService.updateWin(orderNo, win);
				}
				
				Map<String,Object> map = new HashMap<>();
				map.put("msg", "提交成功");
				return map;
			}
		});
		return result;
	}
	/**
	 * 取消游戏
	 * @param orderNo	游戏订单
	 * @return
	 */
	@RequestMapping("/game/cancel")
	public @ResponseBody ApiResult cancel(final String orderNo) {
		ApiResult result = this.buildEquAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String equno) throws Exception {
				if(StringUtils.isEmpty(orderNo)){
					throw new AppRuntimeException(Constants.kCode_Null,"orderNo不能为空");
				}
				GameOrder gameOrder = new GameOrder();
				gameOrder.setOrder_no(orderNo);
				gameOrder = gameOrderService.getGameOrderByNo(gameOrder);
				if(gameOrder==null){
					throw new AppRuntimeException(Constants.kCode_NoOrder,"订单不存在");
				}
				if(!equno.equals(gameOrder.getEquno())){
					throw new AppRuntimeException(Constants.kCode_NoOrder,"订单不存在");
				}
				if(gameOrder.getStatus()>Constants.gorder_status_ingame){
					throw new AppRuntimeException(Constants.kCode_NoOrder,"状态错误");
				}
				
				//判断用户当前是否是约战
				Object obj = redisTemplate.opsForHash().get(Constants.game_user_wait, gameOrder.getUser_id());
				if(obj!=null){
					//是约战,则取消等待用户列表
					redisTemplate.opsForHash().delete(Constants.game_user_wait, gameOrder.getUser_id()) ;
				}else{
					//不是约战，则从待匹配列表里删除
					redisTemplate.opsForHash().delete(Constants.game_equno_wait+gameOrder.getGame_code(), equno) ;
				}
				gameOrderService.cancel(gameOrder.getId(),gameOrder.getUser_id(),gameOrder.getCost());
				
				Map<String,Object> map = new HashMap<>();
				map.put("msg", "取消成功");
				return map;
			}
		});
		return result;
	}
	/**
	 * 终端取消包机
	 * @return
	 */
	@RequestMapping("/unbooked")
	public @ResponseBody ApiResult unbooked() {
		ApiResult result = this.buildEquAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String equno) throws Exception {
				
				equInfoService.unBooked(null,equno);
				
				Map<String,Object> map = new HashMap<>();
				map.put("msg", "取消成功");
				return map;
			}
		});
		return result;
	}
	
	
	
	/**
	 * 包机列表
	 * @return
	 */
	@RequestMapping("/booked/list")
	public @ResponseBody ApiResult bookedEquList() {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String userId) throws Exception {
				
				UserResult user = userService.getUserByUuid(userId);
				
				Map<String,Object> map = new HashMap<>();
				map.put("msg", "提交成功");
				map.put("balance", user.getBalance());
				map.put("bookedEqu", equInfoService.queryBookedList(userId));
				return map;
			}
		});
		return result;
	}
	/**
	 * 用户取消包机
	 * @param equno 设备编号
	 * @return
	 */
	@RequestMapping("/booked/cancel")
	public @ResponseBody ApiResult bookedCancel(final String equno) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String userId) throws Exception {
				if(StringUtils.isEmpty(equno)){
					throw new AppRuntimeException("equno不能为空");
				}
				
				equInfoService.unBooked(userId,equno);
				
				Map<String,Object> map = new HashMap<>();
				map.put("msg", "取消成功");
				return map;
			}
		});
		return result;
	}
	
}
