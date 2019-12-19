package com.wangtiansoft.KingDarts.modules.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jfinal.kit.PropKit;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.common.utils.RandomUtil;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.config.lftpay.api.util.StringUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;
import com.wangtiansoft.KingDarts.modules.game.service.GameOrderService;
import com.wangtiansoft.KingDarts.modules.pay.service.CommissionService;
import com.wangtiansoft.KingDarts.modules.user.service.NewUserGiveBalanceService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.modules.weixin.utils.WeiXinUtils;
import com.wangtiansoft.KingDarts.core.extensions.account.service.AccountService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.core.utils.GameUtil;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.CommissionMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserBalanceMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserGamePointsMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserOpenidMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserPointsMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserPointsMonthMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Commission;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;
import com.wangtiansoft.KingDarts.persistence.entity.GameOrder;
import com.wangtiansoft.KingDarts.persistence.entity.NewUserGiveBalance;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.persistence.entity.UserBalance;
import com.wangtiansoft.KingDarts.persistence.entity.UserOpenid;
import com.wangtiansoft.KingDarts.persistence.entity.UserPoints;
import com.wangtiansoft.KingDarts.results.core.UserOpenidResult;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("userService")
public class UserServiceImpl extends BaseService<User, Integer> implements UserService{

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserOpenidMapper userOpenidMapper;
    
    @Resource
    private AccountService accountService;
    @Resource
    private GameOrderService gameOrderService;
    @Resource
    private EquInfoService equInfoService;
    @Resource
    private NewUserGiveBalanceService newUserGiveBalanceService;
    @Resource
    private UserBalanceMapper userBalanceMapper;
    @Resource
    private UserGamePointsMapper userGamePointsMapper;
    @Resource
    private UserPointsMapper userPointsMapper;
    @Resource
    private UserPointsMonthMapper userPointsMonthMapper;
    @Resource
    private CommissionMapper commissionMapper;
    @Resource
    private CommissionService commissionService;

    @Override
    public BaseMapper getBaseMapper() {
        return userMapper;
    }

    @Override
    public Page<Map> queryUserPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) userMapper.queryUserList(paramMap);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(UserResult userResult){
    	User user = new User();
    	BeanUtil.copyProperties(userResult, user);
    	
    	if(user.getId()==null&&StringUtils.isNotEmpty(user.getUuid())){
    		User record = new User();
			record.setUuid(user.getUuid());
			record = userMapper.selectOne(record);
			if(record == null){
				throw new AppRuntimeException(Constants.kCode_NoUser,"用户不存在！");
			}
			user.setId(record.getId());
    	}
    	int cou = 0;
    	if(user.getId()==null){
    		//设置默认值
    		user.setUuid(RandomUtil.createUUID());
    		user.setUpdate_time(new Date());
    		if(user.getAmount()==null){
    			user.setAmount(new BigDecimal(0));
    		}
    		if(user.getBalance()==null){
    			user.setBalance(new BigDecimal(0));
    		}
    		if(user.getGive_balance()==null){
    			user.setGive_balance(new BigDecimal(0));
    		}
    		if(user.getCoupon_balance()==null){
    			user.setCoupon_balance(new BigDecimal(0));
    		}
    		user.setUsername(user.getNickname());//用户新增时用户名  默认为昵称
    		user.setFrozen(new BigDecimal(0));
    		user.setPoints(0);
    		user.setIs_publish(1);
    		user.setIs_delete(0);
    		user.setVersion(1);
    		cou = userMapper.insert(user);
    		
    		//插入openId
    		if(userResult.getOpenids()!=null){
    			for(UserOpenidResult  uor : userResult.getOpenids()){
    				UserOpenid userOpenid = new UserOpenid();
    	    		userOpenid.setOpenid(uor.getOpenid());
    	    		userOpenid.setUser_id(user.getUuid());
    	    		userOpenid.setAppid(uor.getAppid());
    	    		userOpenidMapper.insert(userOpenid);
    			}
    		}
    		
    	}else{
    		cou = userMapper.updateByPrimaryKeySelective(user);
    		if(userResult.getOpenids()!=null){
    			for(UserOpenidResult  uor:userResult.getOpenids()){
    				UserOpenid userOpenid = new UserOpenid();
    	    		userOpenid.setOpenid(uor.getOpenid());
    	    		UserOpenid uo = userOpenidMapper.selectOne(userOpenid);
    				if(uo==null){
    					userOpenid.setUser_id(user.getUuid());
    					userOpenid.setAppid(uor.getAppid());
        	    		userOpenidMapper.insert(userOpenid);
    				}
    			}
    		}
    	}
		
		if(cou != 1) {
			throw new AppRuntimeException("保存失败！");
		}
    }
    
    @Override
    public UserResult getUserByUuid(String uuid){
    	User u = new User();
    	u.setUuid(uuid);
    	u = userMapper.selectOne(u);
    	
    	if(u!=null){
    		UserResult ur = new UserResult();
    		BeanUtil.copyProperties(u, ur);
    		Map paramMap = new HashMap<>();
    		paramMap.put("userId", ur.getUuid());
    		ur.setOpenids(userOpenidMapper.selectUserOpenidList(paramMap));
    		return ur;
    	}
    	return null;
    }
    
    @Override
    public UserResult getUserByUnionid(String unionid){
    	User u = new User();
    	u.setUnionid(unionid);
    	u = userMapper.selectOne(u);
    	
    	if(u!=null){
    		UserResult ur = new UserResult();
    		BeanUtil.copyProperties(u, ur);
    		Map paramMap = new HashMap<>();
    		paramMap.put("userId", ur.getUuid());
    		ur.setOpenids(userOpenidMapper.selectUserOpenidList(paramMap));
    		return ur;
    	}
    	return null;
    }
    
    @Override
    public UserResult getUserByOpenid(String openid){
    	if(StringUtils.isEmpty(openid)){
    		throw new AppRuntimeException("openid不能为空");
    	}
    	UserOpenid record = new UserOpenid();
    	record.setOpenid(openid);
    	record =userOpenidMapper.selectOne(record);
    	
    	if(record!=null){
    		User u = new User();
        	u.setUuid(record.getUser_id());
        	u = userMapper.selectOne(u);
        	UserResult ur = new UserResult();
    		BeanUtil.copyProperties(u, ur);
    		/*Map paramMap = new HashMap<>();
    		paramMap.put("", ur.getUuid());
    		ur.setOpenids(userOpenidMapper.selectUserOpenidList(paramMap));*/
    		return ur;
    	}
    	return null;
    }
    
    @Override
    public String hasWxOpenid(UserResult user){
    	String appId= PropKit.get("appId");
    	if(user.getOpenids()==null){
    		return "";
    	}
    	System.out.println("appId="+appId+" "+user.getOpenids().size());
    	for(UserOpenidResult upr: user.getOpenids()){
			if(appId!=null&&appId.equals(upr.getAppid())){
				return appId;
			}
    	}
    	return "";
    }
    
    @Override
    public void addOrUpdateUserOpenId(UserResult user,String openId,String appId){
    	if(user.getOpenids()!=null){
			boolean hasOpenId = false;
			boolean hasAppId = false;
			Integer id = null;
			for(UserOpenidResult upr: user.getOpenids()){
				if(openId.equals(upr.getOpenid())){
					id = upr.getId();
					hasOpenId = true;
					if(StringUtils.isNotEmpty(upr.getAppid())){
						hasAppId = true;
					}
				}
			}
			
			if(hasOpenId&&hasAppId){
				//有openid和appid不做处理
			}else{
				if(!hasOpenId){
					//如果没有appid则插入
					UserOpenid userOpenid = new UserOpenid();
    	    		userOpenid.setOpenid(openId);
    	    		userOpenid.setUser_id(user.getUuid());
    	    		userOpenid.setAppid(appId);
    	    		userOpenidMapper.insert(userOpenid);
				}else if(!hasAppId){
					//如果没保存appId，则更新
					UserOpenid userOpenid = new UserOpenid();
					userOpenid.setId(id);
    	    		userOpenid.setAppid(appId);
					userOpenidMapper.updateByPrimaryKeySelective(userOpenid);
				}
			}
		}
    }
    
    @Override
    public void addOrUpdateFromWeiXin(String appid,String openId,String nickName,String unionid,String headimgurl
    		,String country,String city,String province,Integer gender,Integer id,Map hisUser){
    	UserResult newuser = new UserResult();
		newuser.setNickname(nickName);
		newuser.setUnionid(unionid);
		newuser.setHeadimgurl(headimgurl);
		newuser.setCountry(country);
		newuser.setCity(city);//与小程序里选择项相同
		newuser.setProvince(province);//与小程序里选择项相同
		newuser.setGender(gender);
		
		if(hisUser!=null){
			//存在未同步到新用户的历史用户数据 ,积分是通过sql计算出来的
			Integer points = hisUser.get("istest")==null?0:Integer.parseInt(hisUser.get("istest").toString());
			BigDecimal ramount = hisUser.get("ramount")==null?new BigDecimal(0):new BigDecimal(hisUser.get("ramount").toString());//余额
			BigDecimal pramount = hisUser.get("pramount")==null?new BigDecimal(0):new BigDecimal(hisUser.get("pramount").toString());//赠币余额
			
			newuser.setAmount(ramount);//充值总金额
			newuser.setBalance(ramount);//充值余额
			newuser.setGive_balance(pramount);//赠送余额
			newuser.setPoints(points);//积分
		}
		
		List<UserOpenidResult> uorlist = new ArrayList<>();
		UserOpenidResult uor = new UserOpenidResult();
		uor.setOpenid(openId);
		uor.setAppid(appid);
		uorlist.add(uor);
		newuser.setOpenids(uorlist);
		
		if(id!=null){
			//更新用户
			newuser.setId(id);
		}else{
			if(StringUtils.isNotEmpty(unionid)){
				User u = new User();
		    	u.setUnionid(unionid);
		    	u = userMapper.selectOne(u);
				if(u!=null){
					//更新用户，新增用户openid
					newuser.setId(u.getId());
					newuser.setUuid(u.getUuid());
				}
			}else{
				//新用户,新增用户openid
			}
		}
		
		boolean isNewUser = false;
		try {
			if(newuser.getId()==null){//是新用户
				isNewUser = true;
			}
			this.saveOrUpdate(newuser);
		} catch (Exception e) {
			e.printStackTrace();
			//如果是昵称字符集错误，替换后再进行保存
			StringBuffer sb = new StringBuffer("");
			for(int i=0;i<nickName.length();i++){
				sb.append("?");
			}
			newuser.setNickname(sb.toString());
			this.saveOrUpdate(newuser);
		}
		
		//新注册用户赠送20个游戏点
		if(isNewUser){
			//查询新用户赠送游戏点规则
			Example example=new Example(NewUserGiveBalance.class);
            Criteria cr= example.createCriteria();
            cr.andEqualTo("is_publish", 1);
            cr.andEqualTo("isvalid", 1);
            NewUserGiveBalance newUserGiveBalance=newUserGiveBalanceService.findOneByExample(example);
            if(newUserGiveBalance!=null) {
            	//将时间转换成Int类型再做比较
    			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    			String nowD = dateFormat.format(new Date());
    			String starttime = dateFormat.format(newUserGiveBalance.getStart_time());
    			String endtime = dateFormat.format(newUserGiveBalance.getEnd_time());
    			int nowD1 = Integer.parseInt(nowD);//当前时间
    			int start1 = Integer.parseInt(starttime);//奖励开始时间
    			int end1 = Integer.parseInt(endtime);//奖励结束时间
    			if(start1<=nowD1&&end1>=nowD1) {
    				//当前时间在奖励时间内
    				UserOpenid record = new UserOpenid();
    		    	record.setOpenid(openId);
    		    	record =userOpenidMapper.selectOne(record);
    				this.balanceChange(record.getUser_id(),newUserGiveBalance.getGive_game_balance(),"新用户奖励",null);
    			}
            }
			
		}
    }
    
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void consume(String userId,Long orderId,BigDecimal consume,Integer orderType,String gameCode,Integer gameMode,String equno){
    	Map<String,Object> map = new HashMap<>();
    	map.put("uuid", userId);
    	
    	//判断余额是否充足
    	User user = new User();
    	user.setUuid(userId);
    	user = userMapper.selectOne(user);
    	if(user==null){
    		throw new AppRuntimeException("用户不存在");
    	}
    	
    	if((user.getBalance().add(user.getGive_balance())).add(user.getCoupon_balance()).subtract(consume).compareTo(new BigDecimal(0)) == -1){
    		//如果用户余额减去扣款，小于0，则余额不足
    		throw new AppRuntimeException("余额不足，请充值");
    	}
    	map.put("version", user.getVersion()!=null?user.getVersion():1);
    	
    	//优先扣除优惠券金额其次是赠送金额
    	map.putAll(couAmount(consume, user.getGive_balance(),user.getCoupon_balance()));
    	
    	//消费成功返回积分
    	Integer points = GameUtil.countPoints(gameMode);
    	map.put("points", points);
    	
    	if(userMapper.consumeRecharge(map) != 1){
    		throw new AppRuntimeException("消费失败");
    	}
    	
    	//记录积分日志表，记录游戏编码
    	userPoints(userId, orderId, gameCode, points,GameUtil.getGameName(gameCode)+"游戏奖励");
    	
    	//标记设备已经被包机,已经标记的可以不用重复标记
    	if(StringUtils.isNotEmpty(equno)&&orderType-Constants.gorder_type_booked == 0){
    		EquInfo equInfo = new EquInfo();
        	equInfo.setEquno(equno);
        	equInfo.setBooked_user(user.getUuid());
        	equInfoService.updateByNoSelective(equInfo);
    	}
    	
    	//记录金额变动日志
    	UserBalance record = new UserBalance();
    	record.setUser_id(userId);
    	record.setAmount(consume.negate());
    	record.setEquno(equno);
    	record.setLog_time(new Date());
    	record.setOrder_id(orderId);
    	record.setType(Constants.amount_type_consume);
    	record.setRemark(GameUtil.getGameName(gameCode)+"游戏消费");
    	record.setBalance(new BigDecimal(map.get("balance").toString()));
    	record.setGive_balance(new BigDecimal(map.get("give_balance").toString()));
    	record.setCoupon_balance(new BigDecimal(map.get("coupon_balance").toString()));
    	record.setBalance_pre(user.getBalance());
    	record.setGive_balance_pre(user.getGive_balance());
    	record.setCoupon_balance_pre(user.getCoupon_balance());
    	userBalanceMapper.insert(record);
    	
    	//更新订单状态
    	GameOrder gameOrder = new GameOrder();
    	gameOrder.setId(orderId);
    	gameOrder.setUser_id(user.getUuid());
    	gameOrder.setOrder_type(orderType);
    	gameOrder.setPay_status(Constants.gorder_paystatus_haspay);
    	gameOrderService.updateByIdSelective(gameOrder);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recharge(String userId,Long payId,BigDecimal amount,BigDecimal give_balance,Long orderId,String equno,String remark){
    	Map<String,Object> map = new HashMap<>();
    	map.put("uuid", userId);
    	map.put("balance", amount);//实际支付金额
    	map.put("amount", amount);//支付金额
    	map.put("give_balance", give_balance);//赠送游戏点
    	
    	if(userMapper.consumeRecharge(map) != 1){
    		throw new AppRuntimeException("消费失败");
    	}
    	
    	User user = new User();
    	user.setUuid(userId);
    	user = userMapper.selectOne(user);
    	if(user==null){
    		throw new AppRuntimeException("用户不存在");
    	}
    	
    	//记录金额变动日志
    	UserBalance record = new UserBalance();
    	record.setUser_id(userId);
    	record.setAmount(amount.add(give_balance));
    	record.setEquno(equno);
    	record.setLog_time(new Date());
    	record.setOrder_id(orderId);
    	record.setPay_id(payId);
    	record.setType(Constants.amount_type_recharge);
    	record.setRemark(remark);
    	record.setBalance(amount);
    	record.setGive_balance(give_balance);
    	record.setBalance_pre(user.getBalance());
    	record.setGive_balance_pre(user.getGive_balance());
    	record.setCoupon_balance(new BigDecimal(0));
    	record.setCoupon_balance_pre(new BigDecimal(0));
    	userBalanceMapper.insert(record);
    	
    	//计算代理商和俱乐部分成，通过扫码充值的才能有分成
    	if(StringUtils.isNotEmpty(equno)){
    		commissionService.saveCommission(userId, payId, amount, equno,"扫码充值");
    	}else {
    		Commission commission = commissionMapper.getNewOneCommissionByUserId(userId);
    		//判断之前有无扫码充值，有则取之前的扫码过的设备编号
    		if(commission!=null) {
    			commissionService.saveCommission(userId, payId, amount, commission.getEquno(),"微信充值");
    		}else {
    			commissionService.saveCommission(userId, payId, amount, null,"微信充值");
    		}
    	}
    }
    
    @Override
    public void balanceChange(String userId,BigDecimal amount,String remark){
    	this.balanceChange(userId,amount,remark,null);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void balanceChange(String userId,BigDecimal amount,String remark,Long orderId){
    	Map<String,Object> map = new HashMap<>();
    	map.put("uuid", userId);
    	
    	User user = new User();
    	user.setUuid(userId);
    	user = userMapper.selectOne(user);
    	if(user==null){
    		throw new AppRuntimeException("用户不存在");
    	}
    	
    	if(amount.compareTo(new BigDecimal(0)) == 1){//充值
    		//除了实际充值，其他充值都充入赠送金额
			map.put("balance", 0);
    		map.put("give_balance", amount);
    		map.put("coupon_balance", 0);
    		
    		if(orderId!=null){
    			//如果是取消网络游戏，退还金额 ，退还积分
            	if(user.getGive_balance().compareTo(new BigDecimal(0)) != 1){
            		//如果赠送金额等于0，则返还金额至余额，其他的返还至赠送金额
            		map.put("balance", amount);
            		map.put("give_balance", 0);
            		map.put("coupon_balance", 0);
            	}
            	GameOrder gameOrder = gameOrderService.findById(orderId);
            	
            	Integer points = -GameUtil.countPoints(gameOrder.getGame_mode());
            	map.put("points", points);
            	
            	//记录积分日志表，记录游戏编码
            	userPoints(userId, orderId, gameOrder.getGame_code(), points,GameUtil.getGameName(gameOrder.getGame_code())+"取消");
    		}

    	}else{//消费
    		//判断余额是否充足
        	
        	BigDecimal consume = amount.negate();//消费金额
        	if((user.getBalance().add(user.getGive_balance())).add(user.getCoupon_balance()).subtract(consume).compareTo(new BigDecimal(0)) == -1){
        		//如果用户余额减去扣款，小于0，则余额不足
        		throw new AppRuntimeException("余额不足，请充值");
        	}
        	map.put("version", user.getVersion()!=null?user.getVersion():1);
        	
        	//优先扣除优惠券金额其次是赠送金额
        	map.putAll(couAmount(consume, user.getGive_balance(),user.getCoupon_balance()));
    	}
    	
    	if(userMapper.consumeRecharge(map) != 1){
    		throw new AppRuntimeException("消费失败");
    	}
    	
    	//记录金额变动日志
    	UserBalance record = new UserBalance();
    	record.setUser_id(userId);
    	record.setAmount(amount);
    	record.setLog_time(new Date());
    	record.setOrder_id(orderId);
    	record.setType(Constants.amount_type_consume);
    	record.setBalance(new BigDecimal(map.get("balance").toString()));
    	record.setGive_balance(new BigDecimal(map.get("give_balance").toString()));
    	record.setCoupon_balance(new BigDecimal(map.get("coupon_balance")==null?"0":map.get("coupon_balance").toString()));
    	record.setBalance_pre(user.getBalance());
    	record.setGive_balance_pre(user.getGive_balance());
    	record.setCoupon_balance_pre(user.getCoupon_balance());
    	record.setRemark(remark);
    	userBalanceMapper.insert(record);
    }
    
    
    public void userPoints(String userId,Long orderId,String gameCode,Integer points,String remark){
    	
    	UserPoints userPoints = new UserPoints();
    	userPoints.setLog_time(new Date());
    	userPoints.setOrder_id(orderId);
    	userPoints.setPoints(points);
    	userPoints.setUser_id(userId);
    	userPoints.setGame_code(gameCode);
    	userPoints.setGame_type(GameUtil.getGameType(gameCode));
    	userPoints.setPoints_type(0);
    	userPoints.setRemark(remark);
    	userPointsMapper.insert(userPoints);
    }

    @Override
	public Page<Map> queryUserPointsPageList(Map paramMap,PageBean pageBean) {
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) userPointsMapper.queryUserPointsList(paramMap);
	}
    
    
	@Override
	public Page<Map> queryUserRankByPointsPageList(Map paramMap,PageBean pageBean) {
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) userPointsMapper.getUserRankByPoints(paramMap);
	}
	
	@Override
	public Page<Map> queryUserRankByMonthPointsPageList(Map paramMap,PageBean pageBean) {
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		return (Page<Map>) userPointsMonthMapper.getUserRankByHistoryPoints(paramMap);
	}

	@Override
	public Page<Map> queryUserBalancePageList(Map paramMap, PageBean pageBean) {
		paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
		return (Page<Map>) userBalanceMapper.queryUserBalanceList(paramMap);
	}
	
	@Override
	public Map getHisUserByUuid(String unionid){
		Map paramMap = new HashMap<>();
		paramMap.put("unionid", unionid);
		List<Map> list = userMapper.getHisUserList(paramMap);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Map getHisUserByOpenid(String openid){
		Map paramMap = new HashMap<>();
		paramMap.put("wcopenid", openid);
		List<Map> list = userMapper.getHisUserList(paramMap);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public void updateHisUser(String openid,String unionid){
		Map paramMap = new HashMap<>();
		paramMap.put("openid", openid);
		paramMap.put("unionid", unionid);
		userMapper.updateHisUserUnionid(paramMap);
	}
	
	private Map<String,Object> couAmount(BigDecimal consume,BigDecimal give_balance,BigDecimal coupon_balance){
		Map<String,Object> map = new HashMap<>();
		if(coupon_balance.compareTo(consume) != -1) {
			//如果优惠券金额大于等于消费金额
			map.put("balance", 0);
			map.put("give_balance", 0);
    		map.put("coupon_balance", consume.negate());
		}else {
			if(coupon_balance.compareTo(new BigDecimal(0)) == 1) {
				//如果优惠券金额大于0，且小于消费金额
				if(give_balance.compareTo(consume.subtract(coupon_balance)) != -1) {
					//如果优惠券金额加上赠送金额大于消费金额
					map.put("balance", 0);
		    		map.put("give_balance", consume.subtract(coupon_balance).negate());
		    		map.put("coupon_balance", coupon_balance.negate());
				}else {
					if(give_balance.compareTo(new BigDecimal(0)) == 1){
		    			//如果赠送金额大于0，且小于消费金额
		    			map.put("give_balance", give_balance.negate());
			    		map.put("coupon_balance", coupon_balance.negate());
		    			map.put("balance", (consume.subtract(coupon_balance)).subtract(give_balance).negate());
		    		}else{
		    			map.put("balance", consume.subtract(coupon_balance).negate());
		    			map.put("coupon_balance", coupon_balance.negate());
		    			map.put("give_balance", 0);
		    		}
				}
			}else {
				if(give_balance.compareTo(consume) != -1){
		    		//如果赠送金额大于等于消费金额
		    		map.put("balance", 0);
		    		map.put("give_balance", consume.negate());
		    		map.put("coupon_balance", 0);
		    	}else{
		    		if(give_balance.compareTo(new BigDecimal(0)) == 1){
		    			//如果赠送金额大于0，且小于消费金额
		    			map.put("give_balance", give_balance.negate());
		    			map.put("balance", consume.subtract(give_balance).negate());
		    			map.put("coupon_balance", 0);
		    		}else{
		    			map.put("balance", consume.negate());
		    			map.put("give_balance", 0);
		    			map.put("coupon_balance", 0);
		    		}
		    	}
			}
		}
		
		return map;
	}

	@Override
	public void insertUserPointsMonth(Map paramMap) {
		userPointsMonthMapper.insertUserPointsMonth(paramMap);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void balanceChangedd(String userId){
    	Map<String,Object> map = new HashMap<>();
    	map.put("uuid", userId);
    	
    	User user = new User();
    	user.setUuid(userId);
    	user = userMapper.selectOne(user);
    	if(user==null){
    		throw new AppRuntimeException("用户不存在");
    	}
    	
        System.out.println(123213131);

		BigDecimal b=new BigDecimal(user.getCoupon_balance().toString());
		
		
		int a = b.intValue();
		int c  = a+10;
		
		BigDecimal coupon_balance = new BigDecimal(0);
		int value=c;
		coupon_balance=BigDecimal.valueOf((int)value);

	//	System.out.println(coupon_balance);
//    	BigDecimal coupon_balance=new BigDecimal(Integer.toString(Integer.valueOf(user.getCoupon_balance().toString())+5));
   
    	
//    System.out.println(coupon_balance);
    	
    	map.put("Coupon_balance", coupon_balance);
    	userMapper.consumeRechargeaddfive(map) ;
	//	System.out.println(coupon_balance);

    }

//	@Override
//	@Transactional
//	public void transmit(String userId, String newUserId, String token) {
//    	Map map=new HashMap();
//    	Map map1=new HashMap();
//    	map1.put("userId",userId);
//    	map1.put("clickUserId",newUserId);
//     	System.out.println("用户ID2"+userId);
//    	List<UserPoints> list=this.userPointsMapper.selectByclickUserId(map1);
//        if(list.size()>0){
//        	return;
//		}
//		int points=0;
//    	if(StringUtil.isNotEmpty(token)){
//          points=30;
//		}else {
//          points=10;
//		}
//		UserPoints userPoints = new UserPoints();
//		userPoints.setLog_time(new Date());
//		userPoints.setPoints(points);
//		userPoints.setUser_id(userId);
//		userPoints.setClick_user_id(newUserId);
//		if(points==30){
//			userPoints.setPoints_type(3);
//			userPoints.setRemark("新用户点击链接");
//		}else {
//			userPoints.setPoints_type(4);
//			userPoints.setRemark("老用户点击链接");
//		}
//		userPointsMapper.insert(userPoints);
//		System.out.println("完成增加"+userId);
//		map.put("uuid",userId);
//		map.put("points",points);
//		this.userMapper.updatePointByuuid(map);
//		map.put("uuid",newUserId);
//		this.userMapper.updatePointByuuid(map);
//	 	System.out.println("完成保存"+userId);
//	}
//
	@Override
	public void video(String userId) {
		System.out.println("进入VIDEO"+userId);
		
		User user=this.userMapper.selectByVideoTime(userId);
		if(!isNotBlank(user)){
			return;
		}
		Date date=new Date();
		//SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd:HH:ss:mm");
		if(!isNotBlank(user.getVideo_time())){
			user.setVideo_time(date);
		}
		long now=date.getTime();
		long end =user.getVideo_time().getTime();
		long result=1000*60*5;
		System.out.println("是否进入VIDEO"+userId);
//		if((now-end)>result||(now-end)==0||!isNotBlank(end)){
			Map map=new HashMap();
			map.put("uuid",userId);
			map.put("points",10);
			map.put("videoTime",date);
			
			BigDecimal b=new BigDecimal(user.getCoupon_balance().toString());
			
			
			int a = b.intValue();
			int c  = a+2;
			
//			int c  = a+10;
//			
			BigDecimal coupon_balance = new BigDecimal(0);
			int value=c;
			coupon_balance=BigDecimal.valueOf((int)value);

			System.out.println(coupon_balance);
//	    	BigDecimal coupon_balance=new BigDecimal(Integer.toString(Integer.valueOf(user.getCoupon_balance().toString())+5));
	   
	    	
//	    System.out.println(coupon_balance);
	    	
	    	map.put("Coupon_balance", coupon_balance);
	    	userMapper.consumeRechargeaddfive(map) ;
			this.userMapper.updatePointByuuidandVideoTime(map);
			System.out.println("进入成功VIDEO"+userId);
//		}else {
//			System.out.println("进入失败VIDEO"+userId);
//			return;
//		}
	}
	public boolean isNotBlank(Object str){
		if(str==null) {
			return false;
		}
		return true;
	}


	@Override
	@Transactional
	public void transmit(String userId, String newUserId, String token) {
    	Map map=new HashMap();
    	Map map1=new HashMap();
    	map1.put("userId",userId);
    	map1.put("clickUserId",newUserId);
    	if(userId.equals(newUserId)){
    		return;
		}
    	List<UserPoints> list=this.userPointsMapper.selectByclickUserId(map1);
        if(list.size()>0){
        	return;
		}
		int points=0;
    	if(StringUtil.isNotEmpty(token)){
          points=30;
		}else {
          points=10;
		}
		UserPoints userPoints = new UserPoints();
		userPoints.setLog_time(new Date());
		userPoints.setPoints(points);
		userPoints.setUser_id(userId);
		userPoints.setClick_user_id(newUserId);
		if(points==30){
			userPoints.setPoints_type(3);
			userPoints.setRemark("新用户点击链接");
		}else {
			userPoints.setPoints_type(4);
			userPoints.setRemark("老用户点击链接");
		}
		userPointsMapper.insert(userPoints);
		map.put("uuid",userId);
		map.put("points",points);
		this.userMapper.updatePointByuuid(map);
		map.put("uuid",newUserId);
		this.userMapper.updatePointByuuid(map);
	}

	@Override
	public JSONArray getYunChuanRank(String cno, String date) {
		Map paramMap = new HashMap<>();
		JSONArray array = new JSONArray();
		paramMap.put("cno", cno);
		paramMap.put("end_time",date);
		List<Map> list = userPointsMapper.getYunChuanRank(paramMap);
		if (list.size()==0) {
			return array;
		}
		for (Map map : list) {
			JSONObject object = new JSONObject();
			object.put("rank", map.get("rank"));
			object.put("headimgurl", map.get("headimgurl"));
			object.put("nickname", map.get("nickname"));
			object.put("points", map.get("points"));
			array.add(object);
		}
		
		return array;
	}

//	@Override
//	public void video(String userId) {
//		User user=this.userMapper.selectByVideoTime(userId);
//		if(!isNotBlank(user)){
//			return;
//		}
//		Date date=new Date();
//		//SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd:HH:ss:mm");
//		if(!isNotBlank(user.getVideo_time())){
//			user.setVideo_time(date);
//		}
//		long now=date.getTime();
//		long end =user.getVideo_time().getTime();
//		long result=1000*60*5;
//		if((now-end)>result||(now-end)==0){
//			Map map=new HashMap();
//			map.put("uuid",userId);
//			map.put("videoTime",date);
//			this.userMapper.updatePointByuuidandVideoTime(map);
//		}else {
//			return;
//		}
//	}
//	public boolean isNotBlank(Object str){
//		if(str==null) {
//			return false;
//		}
//		return true;
//	} 
//    
}
