package com.wangtiansoft.KingDarts.modules.coupon.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.coupon.service.CouponService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.CouponMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UseCouponRecordMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserBalanceMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.UserMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Coupon;
import com.wangtiansoft.KingDarts.persistence.entity.UseCouponRecord;
import com.wangtiansoft.KingDarts.persistence.entity.User;
import com.wangtiansoft.KingDarts.persistence.entity.UserBalance;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Transactional
@Service("couponService")
public class CouponServiceImpl extends BaseService<Coupon, Integer> implements CouponService{

	@Autowired
    private CouponMapper couponMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserBalanceMapper userBalanceMapper;
	
	@Resource
	private UserService userService;
	
	@Autowired
	private UseCouponRecordMapper useCouponRecordMapper;
	
	@Override
	public BaseMapper getBaseMapper() {
		return couponMapper;
	}
	
	@Override
    public Page<Map> queryCouponList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        if("".equals(paramMap.get(SQLUtil.SQL_OrderSQL))){
        	paramMap.put(SQLUtil.SQL_OrderSQL, "create_time desc");
        }
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) couponMapper.queryCouponList(paramMap);
    }
	
	@Override
	public Integer useCouponByUser(String couponno,String user_id) {
        UserResult user = userService.getUserByUuid(user_id);
		
        Coupon coupon = couponMapper.getCouponByCouponno(couponno);
		coupon.setUser_id(user_id);
		coupon.setCoupon_status(1);
		coupon.setApply_time(new Date());
		couponMapper.updateByPrimaryKey(coupon);
		
		Map<String,Object> map = new HashMap<>();
    	map.put("uuid", user_id);
    	map.put("balance", 0);
    	map.put("coupon_balance", coupon.getGame_balance());
		if(userMapper.consumeRecharge(map) != 1){
    		throw new AppRuntimeException("系统错误，请联系管理员！");
    	}
		
		//记录金额变动日志
    	UserBalance record = new UserBalance();
    	record.setUser_id(user_id);
    	record.setAmount(coupon.getGame_balance());
    	record.setLog_time(new Date());
    	record.setType(Constants.amount_type_consume);
    	record.setRemark("优惠券充值");
    	record.setBalance(new BigDecimal(0));
    	record.setBalance_pre(user.getBalance());
    	record.setGive_balance(new BigDecimal(0));
    	record.setGive_balance_pre(user.getGive_balance());
    	record.setCoupon_balance(coupon.getGame_balance());
    	record.setCoupon_balance_pre(user.getCoupon_balance());
    	userBalanceMapper.insert(record);
		return null;
	}

	@Override
	public Boolean canUseCoupon(String uuid, String couponId) {
		// 是否使用过该优惠券
		UseCouponRecord record = useCouponRecordMapper.getByUuidCouponId(uuid, couponId);
		if (record!=null) {
			return false;
		}
		return true;
	}

	@Override
	public void recordUse(String uuid, String couponId) {
		UseCouponRecord record = new UseCouponRecord();
		record.setCouponId(couponId);
		record.setUuid(uuid);
		int selective = useCouponRecordMapper.insertSelective(record);
		if (selective==0) {
			throw new AppRuntimeException("系统错误，请联系管理员！");
		}
		
	}

}
