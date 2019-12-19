package com.wangtiansoft.KingDarts.modules.coupon.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.RandomUtil;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.coupon.service.CouponService;
import com.wangtiansoft.KingDarts.persistence.entity.Coupon;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;
import com.wangtiansoft.KingDarts.results.core.CouponResult;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/a/coupon")
public class CouponController extends BaseController {
	
	@Resource
	private CouponService couponService;
	
	@RequestMapping("/coupon_list")
    public String coupon_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/coupon/coupon_list";
    }
	
	@PostMapping("/coupon_search")
	public
	@ResponseBody
	JQGirdPageResult coupon_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
		Page<Map> page = couponService.queryCouponList(paramMap, pageBean);
		return new JQGirdPageResult(page);
	}
	
	/**
	 * 生成优惠券
	 * @return
	 */
    @GetMapping("/coupon_add")
    public String coupon_add(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/coupon/coupon_add";
    }
    
    /**
     * 新增优惠券
     * @param coupon
     * @return
     */
    @PostMapping("/coupon_save")
    public
    @ResponseBody
    ApiResult coupon_save(@ModelAttribute("coupon") Coupon coupon) {
    	coupon.setCouponno(RandomStringUtils.random(10, false, true));
    	// coupon.setCouponno(RandomStringUtils.randomAlphanumeric(32));
    	coupon.setCoupon_status(0);
    	coupon.setIsvalid(1);
    	coupon.setCreate_time(new Date());
    	coupon.setUpdate_time(new Date());
    	couponService.save(coupon);
        return ApiResult.success(coupon);
    }
    
    /**
     * 删除
     * @return
     */
    @PostMapping("/coupon_delete")
	public
	@ResponseBody
	ApiResult coupon_delete() {
		String id = getParaValue("id");
		Coupon coupon = couponService.findById(Integer.parseInt(id));
		if(coupon.getCoupon_status()==1) {
			throw new AppRuntimeException("该优惠券已使用，不可删除！");
		}
		
		coupon.setIsvalid(0);
		couponService.updateByIdSelective(coupon);
		//        EquInfo entity = equInfoService.findById(id);
		//        equInfoService.updateByIdSelective(entity);
		return ApiResult.success();
	}
}
