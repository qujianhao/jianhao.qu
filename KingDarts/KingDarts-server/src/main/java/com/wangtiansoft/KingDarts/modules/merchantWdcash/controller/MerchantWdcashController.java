package com.wangtiansoft.KingDarts.modules.merchantWdcash.controller;

import com.github.pagehelper.Page;
import com.jfinal.weixin.sdk.kit.IpKit;
import com.wangtiansoft.KingDarts.modules.agent.service.AgentInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.merchantWdcash.service.MerchantWdcashService;
import com.wangtiansoft.KingDarts.modules.pay.service.LftPayService;
import com.wangtiansoft.KingDarts.modules.user.service.UserService;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.AgentInfo;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantWdcash;
import com.wangtiansoft.KingDarts.results.core.MerchantWdcashResult;
import com.wangtiansoft.KingDarts.results.core.UserResult;

import tk.mybatis.mapper.entity.Example;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/merchant")
public class MerchantWdcashController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(MerchantWdcashController.class);
	
    @Resource
    private MerchantWdcashService merchantWdcashService;
    @Resource
    private UserService userService;
    @Resource
    private ClubInfoService clubInfoService;
    @Resource
    private AgentInfoService agentInfoService;
    @Resource
    private LftPayService lftPayService;

    //  列表
    @PreAuthorize("hasPermission('','_MERCHANTWDCASH:VIEW')")
    @RequestMapping("/merchantWdcash_list")
    public String merchantWdcash_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/merchant/merchantWdcash_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_MERCHANTWDCASH:VIEW')")
    @PostMapping("/merchantWdcash_search")
    public
    @ResponseBody
    JQGirdPageResult merchantWdcash_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = merchantWdcashService.queryMerchantWdcashPageList(paramMap, pageBean);
        return makePageResult(page, MerchantWdcashResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_MERCHANTWDCASH:VIEW')")
    @GetMapping("/merchantWdcash_view")
    public String merchantWdcash_view() {
    String id = getParaValue("id");
        MerchantWdcash entity = merchantWdcashService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/merchant/merchantWdcash_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_MERCHANTWDCASH:EDIT')")
    @GetMapping("/merchantWdcash_edit")
    public String merchantWdcash_edit() {
        String id = getParaValue("id");
        MerchantWdcash entity = merchantWdcashService.findById(Integer.valueOf(id));
        if("1".equals(entity.getMer_type().toString())){
        	//俱乐部
        	Example example = new Example(ClubInfo.class);
        	example.createCriteria().andEqualTo("cno", entity.getMerno());
        	ClubInfo clubInfo = clubInfoService.findOneByExample(example);
        	
        	request.setAttribute("merName", clubInfo.getCname());
        	request.setAttribute("captain", clubInfo.getCaptain());
        	request.setAttribute("mobile", clubInfo.getMobile());
        }else{
        	//代理商
        	Example example = new Example(AgentInfo.class);
        	example.createCriteria().andEqualTo("agno", entity.getMerno());
        	AgentInfo agentInfo = agentInfoService.findOneByExample(example);
        	
        	request.setAttribute("merName", agentInfo.getAgname());
        	request.setAttribute("captain", agentInfo.getCaptain());
        	request.setAttribute("mobile", agentInfo.getMobile());
        }
        if(StringUtils.isNotEmpty(entity.getOpenid())){
        	UserResult user = userService.getUserByOpenid(entity.getOpenid());
        	if(user!=null){
        		request.setAttribute("nickname", user.getNickname());
                request.setAttribute("userMobile", user.getMobile());
        	}
        }
        
        
        request.setAttribute("entity", entity);
        return "/a/merchant/merchantWdcash_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_MERCHANTWDCASH:EDIT')")
    @PostMapping("/merchantWdcash_edit")
    public
    @ResponseBody
    ApiResult merchantWdcash_edit(@ModelAttribute("entity") MerchantWdcash entity) {
        merchantWdcashService.updateByIdSelective(entity);
        MerchantWdcashResult result = makeResult(entity, MerchantWdcashResult.class);
        return ApiResult.success(result);
    }
    
    @PostMapping("/merchantWdcash_transfers")
    public
    @ResponseBody
    ApiResult merchantWdcash_transfers(Integer id,String type) {
    	MerchantWdcash entity1 = merchantWdcashService.findById(Integer.valueOf(id));
        if(entity1.getWd_status()!=2) {
        	throw new AppRuntimeException("该登记已审核，请返回列表后刷新页面！");
        }
    	MerchantWdcash merchantWdcash = new MerchantWdcash();
    	merchantWdcash.setId(id);
    	if("unpass".equals(type)){
    		//审核不通过
    		merchantWdcash.setWd_status(Constants.wd_status_unpass);
    		merchantWdcashService.updateCashStatus(merchantWdcash);
    		return ApiResult.success();
    	}
    	
    	boolean hastransfers = false;
    	try {
    		MerchantWdcash entity = merchantWdcashService.findById(id);
    		String ip = IpKit.getRealIp(request);
    		if (StringUtils.isBlank(ip)) {
    			ip = "127.0.0.1";
    		}
    		//计算实际提现金额，扣取千分之3的手续费
    		BigDecimal amount =  entity.getCash_nums().multiply(new BigDecimal(0.997)).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
//    		System.out.println(amount.intValue()+"");
    		hastransfers = lftPayService.transfers(entity.getOpenid(), ip ,"", amount.intValue()+"");
		} catch (AppRuntimeException e) {
			merchantWdcash.setFail_reseaon(e.getMessage());
	    	merchantWdcash.setWd_status(Constants.wd_status_fail);
	    	merchantWdcash.setPay_types(1);//微信支付
	    	merchantWdcashService.updateCashStatus(merchantWdcash);
	    	return ApiResult.fail(e.getMsg());
		} catch (Exception e) {
			logger.error("",e);
		}
    	if(hastransfers){
    		merchantWdcash.setWd_status(Constants.wd_status_success);
    	}else{
//    		merchantWdcash.setWd_status(Constants.wd_status_fail);
    	}
    	merchantWdcash.setPay_types(1);//微信支付
    	merchantWdcashService.updateCashStatus(merchantWdcash);
    	return ApiResult.success();
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_MERCHANTWDCASH:ADD')")
    @GetMapping("/merchantWdcash_add")
    public String merchantWdcash_add() {
        return "/a/merchant/merchantWdcash_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_MERCHANTWDCASH:ADD')")
    @PostMapping("/merchantWdcash_add")
    public
    @ResponseBody
    ApiResult merchantWdcash_add(@ModelAttribute("entity") MerchantWdcash entity) {
        merchantWdcashService.save(entity);
        MerchantWdcashResult result = makeResult(entity, MerchantWdcashResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_MERCHANTWDCASH:EDIT')")
    @PostMapping("/merchantWdcash_state")
    public
    @ResponseBody
    ApiResult merchantWdcash_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        MerchantWdcash entity = merchantWdcashService.findById(Integer.valueOf(id));
        merchantWdcashService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_MERCHANTWDCASH:DELETE')")
    @PostMapping("/merchantWdcash_delete")
    public
    @ResponseBody
    ApiResult merchantWdcash_delete() {
        String id = getParaValue("id");
        MerchantWdcash entity = merchantWdcashService.findById(Integer.valueOf(id));
        merchantWdcashService.updateByIdSelective(entity);
        return ApiResult.success();
    }
    
    /**
     * 是否审核
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkExist")
    @ResponseBody
    public Boolean checkExistRevoke(String id) {
    	MerchantWdcash entity = merchantWdcashService.findById(Integer.valueOf(id));
        if (entity.getWd_status()==2) {
        	return true;
        }else {
        	return false;
        }
    }

}



