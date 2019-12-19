package com.wangtiansoft.KingDarts.modules.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.coupon.service.CouponService;
import com.wangtiansoft.KingDarts.persistence.entity.ClubPlace;
import com.wangtiansoft.KingDarts.persistence.entity.Coupon;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/api/usercoupon")
public class UserCouponController extends BaseController{
	
	@Resource
	private CouponService couponService;
	
	/**
	 * 我的优惠券列表
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="couponList", method = RequestMethod.GET)
	public @ResponseBody ApiResult  raceList(final String page,final String rows) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> paramMap=new HashMap<>();
					paramMap.put("user_id", uuid);
					paramMap.put("coupon_status", 1);
					PageBean pageBean=new PageBean();
					pageBean.setPage(Integer.parseInt(page));
					pageBean.setRows(Integer.parseInt(rows));
					Map<String,Object> map = new HashMap<>();
					
					Map<String,Object> Page=new HashMap<>();
					Page<Map> list=couponService.queryCouponList(paramMap,pageBean);
					Page.put("total", list.getTotal());
					Page.put("pageSize", list.getPageSize());
					Page.put("pageNum", list.getPageNum());
					Page.put("pages", list.getPages());
					Page.put("list", list);
					map.put("racePage", Page);
					return map;
				}
			});
		return result;
	}
	
	/**
	 * 使用优惠券
	 * @param couponno优惠券编号
	 * @return
	 */
	@Transactional
	@RequestMapping(value="useCoupon", method = RequestMethod.GET)
	public @ResponseBody ApiResult  clubPlaceInfo(final String couponno) {
			ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
				@Override
				public Object execute(String uuid) throws Exception {
					Map<String,Object> map = new HashMap<>();
					Example example2=new Example(Coupon.class);
			        Criteria cr2= example2.createCriteria();
			        cr2.andEqualTo("couponno", couponno);
			        Coupon coupon = couponService.findOneByExample(example2);
			        Boolean canUseCoupon = couponService.canUseCoupon(uuid, coupon.getId()+"");
			        //判断优惠券是否被使用及优惠券是否存在
					/*
					 * if(coupon!=null) { if(coupon.getCoupon_status()==1) { throw new
					 * AppRuntimeException("无效优惠券，请重新输入！"); } }else { throw new
					 * AppRuntimeException("无效优惠券，请重新输入！"); }
					 */
			        if (coupon==null || !canUseCoupon) {
			        	throw new AppRuntimeException("优惠券无效或已使用过该优惠券！");
					}
			        couponService.recordUse(uuid, coupon.getId()+"");
					Integer count = couponService.useCouponByUser(couponno, uuid);
					map.put("count", count);
					return map;
				}
			});
		return result;
	}
}
