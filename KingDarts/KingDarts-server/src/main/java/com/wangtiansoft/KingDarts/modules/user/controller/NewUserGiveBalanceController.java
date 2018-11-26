package com.wangtiansoft.KingDarts.modules.user.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.coupon.service.CouponService;
import com.wangtiansoft.KingDarts.modules.user.service.NewUserGiveBalanceService;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.persistence.entity.Coupon;
import com.wangtiansoft.KingDarts.persistence.entity.NewUserGiveBalance;
import com.wangtiansoft.KingDarts.results.core.NewUserGiveBalanceResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/a/newuser")
public class NewUserGiveBalanceController extends BaseController {

	@Resource
	private NewUserGiveBalanceService newUserGiveBalanceService;
	
	@RequestMapping("/newuser_give_balance_list")
    public String coupon_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/user/newuser_give_balance_list";
    }
	
	@PostMapping("/usergivebalance_search")
	public
	@ResponseBody
	JQGirdPageResult usergivebalance_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
		Page<Map> page = newUserGiveBalanceService.queryNewUserGiveBalanceList(paramMap, pageBean);
		return new JQGirdPageResult(page);
	}
	
	/**
	 * 新增新用户奖励规则
	 * @param paramMap
	 * @return
	 */
    @GetMapping("/newusergivebalance_add")
    public String newusergivebalance_add(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/user/newusergivebalance_add";
    }
    
    /**
     * 保存新用户奖励规则
     * @param coupon
     * @return
     */
    @PostMapping("/newusergivebalance_save")
    public
    @ResponseBody
    ApiResult newusergivebalance_save(@ModelAttribute("newUserGiveBalanceResult") NewUserGiveBalanceResult newUserGiveBalanceResult) {
    	NewUserGiveBalance newUserGiveBalance = new NewUserGiveBalance();
    	BeanUtils.copyProperties(newUserGiveBalanceResult,newUserGiveBalance);
    	newUserGiveBalance.setIs_publish(0);
    	newUserGiveBalance.setIsvalid(1);
    	newUserGiveBalance.setCreate_time(new Date());
    	newUserGiveBalance.setUpdate_time(new Date());
    	newUserGiveBalanceService.savegivebalance(newUserGiveBalance);
        return ApiResult.success(newUserGiveBalance);
    }
    
    /**
     * 删除
     * @return
     */
    @PostMapping("/newusergivebalance_delete")
	public
	@ResponseBody
	ApiResult newusergivebalance_delete() {
		String id = getParaValue("id");
		NewUserGiveBalance newUserGiveBalance = newUserGiveBalanceService.findById(Integer.parseInt(id));
		newUserGiveBalance.setIsvalid(0);
		newUserGiveBalanceService.updateByIdSelective(newUserGiveBalance);
		//        EquInfo entity = equInfoService.findById(id);
		//        equInfoService.updateByIdSelective(entity);
		return ApiResult.success();
	}
    
    /**
     * 是否应用
     * @return
     */
    @PostMapping("/update_is_publish")
    public
    @ResponseBody
    ApiResult update_is_publish() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        if(is_publish.equals("0")) {
        	//如果等于0时  则是取消应用，直接修改is_publish状态
        	NewUserGiveBalance newUserGiveBalance = newUserGiveBalanceService.findById(Integer.parseInt(id));
        	newUserGiveBalance.setIs_publish(Integer.valueOf(is_publish));
        	newUserGiveBalance.setUpdate_time(new Date());
        	newUserGiveBalanceService.updateByIdSelective(newUserGiveBalance);
        }else {
        	//如果等于1时，则是应用，查询是否有其他应用的记录，如果有则先取消其他应用，再应用此记录
    		Example example=new Example(NewUserGiveBalance.class);
            Criteria cr= example.createCriteria();
            cr.andEqualTo("is_publish", 1);
            cr.andEqualTo("isvalid", 1);
            NewUserGiveBalance newUserGiveBalance=newUserGiveBalanceService.findOneByExample(example);
            if(newUserGiveBalance==null) {
            	//如果没有应用状态则直接修改
            	NewUserGiveBalance newUserGiveBalance1 = newUserGiveBalanceService.findById(Integer.parseInt(id));
            	newUserGiveBalance1.setIs_publish(Integer.valueOf(is_publish));
            	newUserGiveBalance1.setUpdate_time(new Date());
            	newUserGiveBalanceService.updateByIdSelective(newUserGiveBalance1);
            }else {
            	//取消原有的应用
            	newUserGiveBalance.setIs_publish(0);
            	newUserGiveBalance.setUpdate_time(new Date());
            	newUserGiveBalanceService.updateByIdSelective(newUserGiveBalance);
            	//设定该记录的应用
            	NewUserGiveBalance newUserGiveBalance1 = newUserGiveBalanceService.findById(Integer.parseInt(id));
            	newUserGiveBalance1.setIs_publish(Integer.valueOf(is_publish));
            	newUserGiveBalance1.setUpdate_time(new Date());
            	newUserGiveBalanceService.updateByIdSelective(newUserGiveBalance1);
            }
        }
        return ApiResult.success();
    }
    

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
