package com.wangtiansoft.KingDarts.modules.weixin.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.weixin.sdk.api.MenuApi;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.config.netty.model.NettyMessage;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.core.utils.AuthUtil;
import com.wangtiansoft.KingDarts.modules.client.service.GameClientService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubMemberService;
import com.wangtiansoft.KingDarts.modules.game.service.GameOrderService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.modules.weixin.utils.XMLParser;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.persistence.entity.User;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/wx/user")
public class WeixinUserController  extends BaseController{

	@Resource
	private UserService userService;
	@Resource
	private GameOrderService gameOrderService;
	@Resource
	private GameClientService gameClientService;
	@Resource
	private ClubMemberService clubMemberService;
	
	@RequestMapping("/consume")
	public @ResponseBody ApiResult consume(final String orderNo,final String type) {
		
		ApiResult result = this.buildAjaxResponse(new IWebResponseHandler() {
			@Override
			public Object execute() throws Exception {
				Integer userId = AuthUtil.getLoginMember(request).getId();
				String uuid = AuthUtil.getLoginMember(request).getUuid();
				
				if(StringUtils.isEmpty(orderNo)){
					throw new AppRuntimeException("订单不能为空");
				}
				
				//查询订单
				GameOrder gameOrder = new GameOrder();
				gameOrder.setOrder_no(orderNo);
				gameOrder.setPay_status( Constants.gorder_paystatus_nopay);
				gameOrder = gameOrderService.getGameOrderByNo(gameOrder);
		        if(gameOrder == null){
		        	throw new AppRuntimeException("订单不存在或已经支付");
		        }
		        
				//订单类型
		        Integer orderType = Constants.gorder_type_single;
		    	if(Constants.consume_type_booked.equals(type) ){
		    		orderType = Constants.gorder_type_booked;
		    	}else if(Constants.consume_type_single.equals(type)){
		    		orderType = Constants.gorder_type_single;
		    	}else{
		    		throw new AppRuntimeException("类型错误");
		    	}
		    	
				//消费扣款
				userService.consume(uuid,gameOrder.getId(), gameOrder.getCost(),orderType,gameOrder.getGame_code()
						,gameOrder.getGame_mode(),gameOrder.getEquno());
				
				//判断是否是会员，如果不是俱乐部会员则成为会员
		    	clubMemberService.makeMember(gameOrder.getAuth_no(), uuid);
				
				User u = userService.findById(userId);
				Map<String,Object> map = new HashMap<>();
				map.put("balance", u.getBalance());
				map.put("give_balance", u.getGive_balance());
				map.put("coupon_balance", u.getCoupon_balance());
				
				//通知设备
				NettyMessage message = gameClientService.pushGameOrder(gameOrder.getEquno(),null, orderNo,orderType
						,u.getPoints(),u.getBalance().add(u.getGive_balance().add(u.getCoupon_balance())).toString(),gameOrder.getGame_code());
				if(!message.getCode().equals(com.wangtiansoft.KingDarts.config.netty.constants.Constants.code_Success)){
					throw new AppRuntimeException("扣款成功，通知设备失败");
				}
				
				return map;
			}
		});
		return result;
	}
	
	
}
