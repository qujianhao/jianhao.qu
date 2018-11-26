package com.wangtiansoft.KingDarts.modules.game.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.game.service.GameOrderService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.GameOrderMapper;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.results.core.GameOrderResult;

import tk.mybatis.mapper.entity.Example;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("gameOrderService")
public class GameOrderServiceImpl extends BaseService<GameOrder, Long> implements GameOrderService{

    @Autowired
    private GameOrderMapper gameOrderMapper;
    @Autowired
    private UserService userService;

    @Override
    public BaseMapper getBaseMapper() {
        return gameOrderMapper;
    }

    @Override
    public Page<Map> queryGameOrderPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) gameOrderMapper.queryGameOrderList(paramMap);
    }
    
    @Override
    public Page<Map> queryGameCountList(Map paramMap, PageBean pageBean) {
    	paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
    	PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
    	return (Page<Map>) gameOrderMapper.queryGameCount(paramMap);
    }
    @Override
    public Page<Map> queryGameTypeCountList(Map paramMap, PageBean pageBean) {
    	paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
    	PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
    	return (Page<Map>) gameOrderMapper.queryGameTypeCount(paramMap);
    }
    
    @Override
    public GameOrder getGameOrderByNo(GameOrder gameOrder){
        return gameOrderMapper.selectOne(gameOrder);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByNoSelective(GameOrder gameOrder){
    	if(StringUtils.isEmpty(gameOrder.getOrder_no())){
    		throw new AppRuntimeException("订单编号不能为空");
    	}
    	String orderNo = gameOrder.getOrder_no();
    	gameOrder.setOrder_no(null);
    	Example example = new Example(GameOrder.class);
    	example.createCriteria().andEqualTo("order_no", orderNo);
    	if(gameOrderMapper.updateByExampleSelective(gameOrder, example) != 1){
    		throw new AppRuntimeException("更新失败");
    	}
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void netGameStart(String orderNo1,String orderNo2){
    	//更新状态并关联订单
    	GameOrder gameOrder1 = new GameOrder();
		gameOrder1.setOrder_no(orderNo1);
		gameOrder1.setStatus(Constants.gorder_status_ingame);
		gameOrder1.setStart_time(new Date());
		gameOrder1.setNet_no(orderNo2);
		updateByNoSelective(gameOrder1);

		GameOrder gameOrder2 = new GameOrder();
		gameOrder2.setOrder_no(orderNo2);
		gameOrder2.setStatus(Constants.gorder_status_ingame);
		gameOrder2.setStart_time(new Date());
		gameOrder1.setNet_no(orderNo1);
		updateByNoSelective(gameOrder2);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancel(Long orderId,String userId,BigDecimal cost){
    	
    	GameOrder gameOrder = new GameOrder();
    	gameOrder.setStatus(Constants.gorder_status_loginout);
    	gameOrder.setEnd_time(new Date());
    	gameOrder.setId(orderId);
    	if(gameOrderMapper.updateByPrimaryKeySelective(gameOrder) != 1){
    		throw new AppRuntimeException("更新失败");
    	}
    	
    	userService.balanceChange(userId, cost, "取消网络游戏",orderId);
    }
}
