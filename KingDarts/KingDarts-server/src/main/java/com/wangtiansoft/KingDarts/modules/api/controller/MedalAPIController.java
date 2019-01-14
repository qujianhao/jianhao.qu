package com.wangtiansoft.KingDarts.modules.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.advert.service.AdvertInfoService;
import com.wangtiansoft.KingDarts.modules.medal.service.MedalService;
import com.wangtiansoft.KingDarts.modules.medalWinner.service.MedalWinnerService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.entity.Medal;
import com.wangtiansoft.KingDarts.persistence.entity.MedalWinner;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/api/medal")
public class MedalAPIController  extends BaseController{

	@Resource
    private AdvertInfoService advertInfoService;
	
	@Resource
	private MedalService medalService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private MedalWinnerService medalWinnerService;
	
	@RequestMapping("/advert")
	public @ResponseBody ApiResult advert(final PageBean pageBean) {
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("is_publish", 1);
				pageBean.setRows(1);

	    		String equno = request.getHeader("equno");
		        
		        
		        Page<Map> page = null ;
				
		    String belongClub=    advertInfoService.queryEquclubByEquno(equno);
		        paramMap.put("belong_club",belongClub);
		        Page<Map> 	pageNow = advertInfoService.queryAdvertInfoPageListByclubId(paramMap, pageBean);
				
				if(pageNow.size()!=0){
//				if(page1.size()!=0){
					page=pageNow;
					
				}else {
//			        paramMap.put("belong_club","");
				 page = advertInfoService.queryAdvertInfoPageListNoclub(paramMap, pageBean);
				
				}
				Map<String,Object> map = new HashMap<>();
				if(page.size()!=0){
					map.put("file_url", page.getResult().get(0).get("file_url"));
					map.put("qr_left", page.getResult().get(0).get("qr_left"));
					map.put("qr_top", page.getResult().get(0).get("qr_top"));
					map.put("qrcode_url", page.getResult().get(0).get("qrcode_url"));
				}
				
				return map;
			}
		});
		return result;
	}
	
	/**
	 * 查询是否满足条件的勋章 并添加满足条件勋章 返回获得勋章
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="medalList", method = RequestMethod.GET)
	public @ResponseBody ApiResult  medalList() {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					
					Map<String,Object> map = new HashMap<>();
					//更新勋章获得
					//查找未获得勋章的勋章规则
					Example example=new Example(MedalWinner.class);
					example.createCriteria().andEqualTo("user_id",uuid);
					List<MedalWinner> medalWinners=medalWinnerService.findAllByExample(example);
					List<Integer> ids=new ArrayList<>();
					for (MedalWinner medalWinner : medalWinners) {
						ids.add(medalWinner.getMedal_id());
					}
					Example exampleM=new Example(Medal.class);
					Criteria criteria=exampleM.createCriteria();
					criteria.andEqualTo("is_valid",1);
					criteria.andEqualTo("is_delete",0);
					if(ids.size()>0) {
						criteria.andNotIn("id", ids);
					}
					List<Medal> medals=medalService.findAllByExample(exampleM);
					//查询是否满足勋章规则
					if(medals!=null) {
						for (Medal medal : medals) {
							Map<String,Object> paramMap=new HashMap<>();
							paramMap.put("user_id", uuid);
							paramMap.put("sqlStr", medal.getSql_str());
							Map mapMedal=medalService.selectMedalTerm(paramMap);
							//满足勋章规则 添加勋章获得
							if((Long) mapMedal.get("count")>=medal.getComplete_times()) {
								MedalWinner entity=new MedalWinner();
								//设置未领取
								entity.setIs_receive(0);
								entity.setMedal_id(medal.getId());
								entity.setMedal_name(medal.getMedal_name());
								entity.setMedal_url(medal.getMedal_url());
								entity.setUser_id(uuid);
								entity.setPrize_point(medal.getPrize_point());
								UserResult user=userService.getUserByUuid(uuid);
								entity.setUseraccount(user.getNickname());
								medalWinnerService.save(entity);
							}
						}
					}
					//更新后的 用户获得的所有勋章
					Map<String,Object> queryMap=new HashMap<>();
					queryMap.put("user_id", uuid);
					List<Map> medalWinnerList=medalWinnerService.queryMedalWinnerList(queryMap);
					map.put("medalWinnerList", medalWinnerList);
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 获取奖励
	 * @param medalId 勋章id
	 * @return
	 */
	@RequestMapping(value="medalPrizeReceive", method = RequestMethod.GET)
	public @ResponseBody ApiResult  medalPrizeReceive(final String medalId) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					Example example=new Example(MedalWinner.class);
					example.createCriteria().andEqualTo("user_id",uuid).andEqualTo("medal_id",medalId);
					MedalWinner medalWinner=medalWinnerService.findOneByExample(example);
					if(medalWinner==null) {
						throw new AppRuntimeException("未达到领取条件！");
					}
					medalWinner.setIs_receive(1);
					medalWinnerService.updateByIdSelective(medalWinner);
					userService.balanceChange(uuid,new BigDecimal(medalWinner.getPrize_point()), "勋章领取奖励");
					
					return map;
				}
			});
		return result;
	}
}
