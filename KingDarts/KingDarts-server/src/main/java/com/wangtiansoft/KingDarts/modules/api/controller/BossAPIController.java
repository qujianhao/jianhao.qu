package com.wangtiansoft.KingDarts.modules.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.DateUtil;
import com.wangtiansoft.KingDarts.config.lftpay.api.util.StringUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.boss.service.BossEntityService;
import com.wangtiansoft.KingDarts.persistence.entity.BossEntity;

@Controller
@RequestMapping("/api/boss")
public class BossAPIController  extends BaseController{

	@Autowired
	private BossEntityService bossEntityService;
	
	/**
	 * boss信息
	 * @return
	 */
	@RequestMapping(value="bossInfo", method = RequestMethod.GET)
	public @ResponseBody ApiResult  bossInfo() {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
		        	String date=DateUtil.mongoDateToSave(new Date());
		        	Map<String,Object> paramMap=new HashMap<>();
		        	paramMap.put("application_time", date);
		        	Map mapBoass=bossEntityService.queryBossEntityInfo(paramMap);
		        	if(mapBoass!=null) {
		        		if(mapBoass.get("kill_status").equals(1)) {
		        			map.put("msg","boss已击杀！");
		        		}else {
		        		map.put("bossEntity", mapBoass);
		        		//剩余击杀时间
		        		map.put("timeLeft", DateUtil.getTodayTimeLeft());
		        		}
		        	}else {
		        		map.put("msg","无boss信息！");
		        	}
					return map;
				}
			});
		return result;
	}
	
	/**
	 * boss掉血
	 * @param int 点血量
	 * @param bossId 当前boss的uuid
	 * @return
	 */
	@RequestMapping(value="bloss", method = RequestMethod.GET)
	public @ResponseBody ApiResult  bloss(final int loss,final String bossId) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					if(StringUtil.isEmpty(bossId)) {
						throw new AppRuntimeException("错误的请求！");
					}
					BossEntity bossEntity=bossEntityService.findById(bossId);
					if(bossEntity==null) {
						throw new AppRuntimeException("boss不存在！");
					}
					if(loss>0) {
						if(bossEntity.getEvolume()!=0) {
						   long ev=bossEntity.getEvolume()-loss;
							if(ev<=0) {
								bossEntity.setKill_status(1);
								bossEntity.setKill_time(new Date());
								bossEntity.setKill_user_id(uuid);
								bossEntity.setEvolume((long) 0);
								map.put("msg","boss击杀" );
							}else {
								bossEntity.setEvolume(ev);
								map.put("msg","boss正击杀" );
							}
						}else {
							map.put("msg","boss已击杀" );
						}
						bossEntity.setUpdate_time(new Date());
						bossEntityService.updateByIdSelective(bossEntity);
					}
					return map;
				}
			});
		return result;
	}
	
	
}
