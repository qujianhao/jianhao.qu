package com.wangtiansoft.KingDarts.modules.api.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.wangtiansoft.KingDarts.modules.funcatchPrize.service.FuncatchPrizeService;
import com.wangtiansoft.KingDarts.modules.funcatchWinner.service.FuncatchWinnerService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchPrize;
import com.wangtiansoft.KingDarts.persistence.entity.FuncatchWinner;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.results.core.FuncatchWinnerResult;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/api/funcatch")
public class FunCatchAPIController  extends BaseController{

	@Autowired
	private FuncatchPrizeService funcatchPrizeService;
	
	@Autowired
	private FuncatchWinnerService funcatchWinnerService;
	
	@Autowired
	private UserService userService;
	
	

	/**
	 * 娃娃机开始游戏
	 * @return
	 */
	@RequestMapping(value="beginAdvert", method = RequestMethod.GET)
	public @ResponseBody ApiResult  beginAdvert() {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {					
					Map<String,Object> map = new HashMap<>();
					UserResult user=userService.getUserByUuid(uuid);
					if(user==null) {
						throw new AppRuntimeException("该用户不存在！");
					}
					userService.balanceChangedd(uuid);
					return map;
					}
			});
		return result;
	}
	
	
	
	/**
	 * 娃娃机初始化
	 * @return
	 */
	@RequestMapping(value="point", method = RequestMethod.GET)
	public @ResponseBody ApiResult  point() {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					UserResult user=userService.getUserByUuid(uuid);
					if(user==null) {
						throw new AppRuntimeException("该用户不存在！");
					}
					map.put("point", user.getBalance());
					Example example=new Example(FuncatchPrize.class);
					Criteria crieria=example.createCriteria();
					crieria.andEqualTo("is_delete", 0).andEqualTo("is_valid",1);
					List<FuncatchPrize> prizeList=funcatchPrizeService.findAllByExample(example);
					map.put("dedcutPoint", funcatchPrizeService.getConsumeBalance());
					map.put("prizeList", prizeList);
					return map;
					}
			});
		return result;
	}
	
	/**
	 * 娃娃机开始游戏
	 * @return
	 */
	@RequestMapping(value="begin", method = RequestMethod.GET)
	public @ResponseBody ApiResult  begin() {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					
					System.out.println("21321");	
					System.out.println(uuid);
					
					Map<String,Object> map = new HashMap<>();
					UserResult user=userService.getUserByUuid(uuid);
					if(user==null) {
						throw new AppRuntimeException("该用户不存在！");
					}
					List<FuncatchPrize> funcatchprizes=funcatchPrizeService.findValidFuncatchPrizeList();
					if(funcatchprizes==null) {
						throw new AppRuntimeException("无奖品信息！");
					}
					//获奖奖品信息
					FuncatchPrize funcatchPrize=funcatchPrizeService.returnWinPrize(funcatchprizes);
					//点数消耗记录
					userService.balanceChange(uuid, new BigDecimal("0").subtract(funcatchPrizeService.getConsumeBalance()), "娃娃机消耗游戏点数");
					//记录中奖记录 
					FuncatchWinner	funcatchWinner=null;
					if(funcatchPrize.getWorth().intValue()>0) {
						if(funcatchPrize.getIs_physical().equals("Y")) {
//							if(funcatchPrize.getStock()<=0) {
//								throw new AppRuntimeException("实物奖品库存数量不足！");
//							}
//							funcatchPrize.setStock(funcatchPrize.getStock()-1);
//							funcatchPrizeService.updateByIdSelective(funcatchPrize);
						}else {
							//点数消耗记录
							userService.balanceChange(uuid,funcatchPrize.getWorth(), "娃娃机中奖获得游戏点数");
						}
						funcatchWinner=funcatchWinnerService.insertFuncatchWinner(user,funcatchPrize);
					}
					map.put("funcatchPrize", funcatchPrize);
					map.put("funcatchWinner", funcatchWinner);
					return map;
					}
			});
		return result;
	}
	
	/**
	 * 读取娃娃机用户中奖列表
	 * @param page 页数
	 * @param rows 每一页显示条数
	 * @return
	 */
	@RequestMapping(value="record", method = RequestMethod.GET)
	public @ResponseBody ApiResult  record(final PageBean pageBean) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> paramMap=new HashMap<>();
//					PageBean pageBean=new PageBean();
//					pageBean.setPage(Integer.parseInt(page));
//					pageBean.setRows(Integer.parseInt(rows));
					Map<String,Object> map=new HashMap<>();
					Map<String,Object> winnerMap=new HashMap<>();
//					paramMap.put("orderSql","create_time desc" );
					Page<Map> pageRecords=funcatchWinnerService.queryFuncatchWinnerPageList(paramMap, pageBean);
					winnerMap.put("total", pageRecords.getTotal());
					winnerMap.put("pageSize", pageRecords.getPageSize());
					winnerMap.put("pageNum", pageRecords.getPageNum());
					winnerMap.put("pages", pageRecords.getPages());
					winnerMap.put("winnerList", pageRecords);
					map.put("winnerPage", winnerMap);
					return map;
					}
			});
		return result;
	}
	
	
	/**
	 * 
	 * 保存中奖实物的领取信息
	 * @param id 中奖记录id
	 * @param receive_name 领取人用户名
	 * @param receive_address 领取人地址
	 * @param receive_phone 领取人手机
	 * @return
	 */
	@RequestMapping(value="receiveinfo", method = RequestMethod.POST)
	public @ResponseBody ApiResult  record(final String id,final String receive_name,
			final String receive_address,final String receive_phone) {
		System.out.println("45353hello你好");
		
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					FuncatchWinner entity=funcatchWinnerService.findById(Long.parseLong(id));
					if(entity==null) {
						throw new AppRuntimeException("未找到获奖信息！");
					}
					entity.setReceive_name(receive_name);
					entity.setReceive_address(receive_address);
					entity.setReceive_phone(receive_phone);
					funcatchWinnerService.updateByIdSelective(entity);
					return map;
					}
			});
		return result;
	}
}
