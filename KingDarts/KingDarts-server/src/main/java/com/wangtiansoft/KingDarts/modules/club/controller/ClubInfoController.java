package com.wangtiansoft.KingDarts.modules.club.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.advert.service.AdvertInfoService;
import com.wangtiansoft.KingDarts.modules.agent.service.AgentInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubPlaceService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquAuthService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;
import com.wangtiansoft.KingDarts.modules.merchantAccount.service.MerchantAccountService;
import com.wangtiansoft.KingDarts.modules.sequence.service.SequenceService;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.date.DateUtil;
import com.wangtiansoft.KingDarts.config.utils.MD5Util;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.AdvertInfo;
import com.wangtiansoft.KingDarts.persistence.entity.AgentInfo;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.persistence.entity.ClubPlace;
import com.wangtiansoft.KingDarts.persistence.entity.EquAuth;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;
import com.wangtiansoft.KingDarts.results.core.AdvertInfoResult;
import com.wangtiansoft.KingDarts.results.core.ClubInfoResult;
import com.wangtiansoft.KingDarts.results.core.ClubPlaceResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/clubInfo")
public class ClubInfoController extends BaseController {

    @Resource
    private ClubInfoService clubInfoService;
    
    @Resource
    private ClubPlaceService clubPlaceService;
    
    @Resource
    private SequenceService sequenceService;
    
    @Resource
    private MerchantAccountService merchantAccountService;
    
    @Resource
    private EquAuthService equAuthService;
    
    @Resource
    private EquInfoService equInfoService;
    @Resource
    private AgentInfoService agentInfoService;
    
    @Resource
    private AdvertInfoService advertInfoService;


    //  列表
    //@PreAuthorize("hasPermission('','_CLUBINFO:VIEW')")
    @RequestMapping("/clubInfo_list")
    public String clubInfo_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/club/clubInfo_list";
    }
    
    @RequestMapping("/select/clubInfo_list")
    public String selectclubInfo_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/club/clubInfo_select_list";
    }

    //  列表分页
    //@PreAuthorize("hasPermission('','_CLUBINFO:VIEW')")
    @PostMapping("/clubInfo_search")
    public
    @ResponseBody
    JQGirdPageResult clubInfo_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = clubInfoService.queryAccountClubInfoList(paramMap, pageBean);
        return makePageResult(page, ClubInfoResult.class);
    }

    //  编辑查看页面
    //@PreAuthorize("hasPermission('','_CLUBINFO:EDIT')")
    @GetMapping("/clubInfo_edit")
    public String clubInfo_edit(@RequestParam Map<String, Object> paramMap,
    		@RequestParam(value="id",required=false) String id,@RequestParam(value="type",required=false) String type) {
    	Map map = clubInfoService.getClubInfoView(Integer.valueOf(id));
    	map.put("type", type);
        request.setAttribute("map", map);
        
        List<Map> aglist = agentInfoService.queryAgentInfoList(new HashMap());
        request.setAttribute("aglist", aglist);
        return "/a/club/clubInfo_edit";
    }

    //  编辑保存
    //@PreAuthorize("hasPermission('','_CLUBINFO:EDIT')")
    @PostMapping("/clubInfo_edit")
    public
    @ResponseBody
    ApiResult clubInfo_edit(@ModelAttribute("entity") ClubInfoResult entity) {
    	ClubInfo clubInfo = new ClubInfo();
    	BeanUtils.copyProperties(entity,clubInfo);
        if(clubInfo.getC_password()!=null) {
        	MD5Util md5Util = new MD5Util();
        	merchantAccountService.updatePasswordByMerno(md5Util.MD5Encode(clubInfo.getC_password(), "utf-8"), clubInfo.getMobile(), clubInfo.getCname(), 1, clubInfo.getCno());
        	clubInfo.setC_password(md5Util.MD5Encode(clubInfo.getC_password(), "utf-8"));
        }else {
        	merchantAccountService.updateAccountNameByMerno(clubInfo.getMobile(), clubInfo.getCname(), 1, clubInfo.getCno());
        }
        if(clubInfo.getAgno()==null){
        	clubInfo.setAgno("");
        }
        if(clubInfo.getCardid()==null){
        	clubInfo.setCardid("");
        }
        if(clubInfo.getMobile()==null){
        	clubInfo.setMobile("");
        }
        clubInfoService.updateByIdSelective(clubInfo);
        //获取营业场所信息
        List<ClubPlaceResult> clubPlaceResultList = entity.getClubPlaceResultList();
        for(ClubPlaceResult clubPlaceResult:clubPlaceResultList) {
        	ClubPlace clubPlace = new ClubPlace();
        	BeanUtils.copyProperties(clubPlaceResult, clubPlace);
        	clubPlace.setPmobile(StringUtils.isEmpty(clubPlace.getPmobile())?"":clubPlace.getPmobile());
        	clubPlace.setConsdes(StringUtils.isEmpty(clubPlace.getConsdes())?"":clubPlace.getConsdes());
        	clubPlace.setPcaptain(StringUtils.isEmpty(clubPlace.getPcaptain())?"":clubPlace.getPcaptain());
        	clubPlace.setPtelno(StringUtils.isEmpty(clubPlace.getPtelno())?"":clubPlace.getPtelno());
        	clubPlaceService.updateByIdSelective(clubPlace);
        }
        ClubInfoResult result = makeResult(clubInfo, ClubInfoResult.class);
        return ApiResult.success(result);
    }
    
    //  游戏定价页面
    //@PreAuthorize("hasPermission('','_CLUBINFO:EDIT')")
    @GetMapping("/clubInfo_manage_game_price")
    public String clubInfo_manage_game_price(@RequestParam Map<String, Object> paramMap,
    		@RequestParam(value="id",required=false) String id) {
    	ClubInfo entity = clubInfoService.findById(Integer.valueOf(id));
        paramMap.put("entity", entity);
        request.setAttribute("paramMap", paramMap);
        return "/a/club/clubInfo_manage_price";
    }
    
    //  游戏定价
    //@PreAuthorize("hasPermission('','_CLUBINFO:EDIT')")
    @PostMapping("/clubInfo_manage_price")
    public
    @ResponseBody
    ApiResult clubInfo_manage_price(@ModelAttribute("entity") ClubInfoResult entity) {
    	ClubInfo clubInfo = new ClubInfo();
    	BeanUtils.copyProperties(entity,clubInfo);
        clubInfoService.updateByIdSelective(clubInfo);
        ClubInfoResult result = makeResult(clubInfo, ClubInfoResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    //@PreAuthorize("hasPermission('','_CLUBINFO:ADD')")
    @GetMapping("/clubInfo_add")
    public String clubInfo_add() {
        return "/a/club/clubInfo_add";
    }

    //  新建保存
    //@PreAuthorize("hasPermission('','_CLUBINFO:ADD')")
    @PostMapping("/clubInfo_save")
    public
    @ResponseBody
    ApiResult clubInfo_add(@ModelAttribute("entity") ClubInfoResult entity) {
    	ClubInfoResult clubInfoResult = clubInfoService.saveClubInfo(entity);
        return ApiResult.success();
    }

    //  修改ACAC
    //@PreAuthorize("hasPermission('','_CLUBINFO:EDIT')")
    @PostMapping("/update_acac")
    public
    @ResponseBody
    ApiResult update_acac() {
        String id = getParaValue("id");
        String acac = getParaValue("acac");
        ClubInfo entity = clubInfoService.findById(Integer.valueOf(id));
        entity.setAcac(Integer.parseInt(acac));
        clubInfoService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    //@PreAuthorize("hasPermission('','_CLUBINFO:DELETE')")
    @PostMapping("/clubInfo_delete")
    public
    @ResponseBody
    ApiResult clubInfo_delete() {
        String id = getParaValue("id");
        ClubInfo entity = clubInfoService.findById(Integer.valueOf(id));
        //判断俱乐部是否为平台(凯帝狮爱镖体育)
        if(entity.getCno()=="11000059") {
        	throw new AppRuntimeException("该俱乐部不可删除！");
        }
        
        Example example1=new Example(EquAuth.class);
        Criteria cr1= example1.createCriteria();
        cr1.andEqualTo("auth_no", entity.getCno());
        List<EquAuth> equAuthList = equAuthService.findAllByExample(example1);
        if(null == equAuthList || equAuthList.size() ==0) {
        	//更改俱乐部状态
        	entity.setIsvalid(0);
            clubInfoService.updateByIdSelective(entity);
            //更改登录状态
            MerchantAccount merchantAccount = merchantAccountService.getUserByLoginName(entity.getCno());
            merchantAccount.setIsdelete(1);
            merchantAccountService.updateByIdSelective(merchantAccount);
        }else {
        	for(EquAuth equAuth:equAuthList) {
        		equAuthService.updateCnoAuthByEquno(equAuth.getEquno(), null, null);//取消设备授权
        		
        		Example example=new Example(EquInfo.class);
                Criteria cr= example.createCriteria();
                cr.andEqualTo("equno", equAuth.getEquno());
                EquInfo equInfo = equInfoService.findOneByExample(example);
        		equInfo.setEqu_status(0);
        		equInfo.setIsactivation(0);
        		BigDecimal big = new BigDecimal("5");
        		equInfo.setGame_price(big);
        		equInfoService.updateByIdSelective(equInfo);//修改设备授权状态
        	}
        	//更改俱乐部状态
        	entity.setIsvalid(0);
            clubInfoService.updateByIdSelective(entity);
            //更改登录状态
            MerchantAccount merchantAccount = merchantAccountService.getUserByLoginName(entity.getCno());
            merchantAccount.setIsdelete(1);
            merchantAccountService.updateByIdSelective(merchantAccount);
        }
        
        
        return ApiResult.success();
    }
    
    /**
     * 校验当前手机号的唯一性
     * @param mobile 手机号
     * @return
     */
    @RequestMapping(value = "/checkUnique")
    @ResponseBody
    public Boolean checkExist(String mobile,String id) {
    	if(id==null) {
    		Example example=new Example(ClubInfo.class);
            Criteria cr= example.createCriteria();
            cr.andEqualTo("mobile", mobile);
            ClubInfo clubInfo=clubInfoService.findOneByExample(example);
            
            Example example1=new Example(MerchantAccount.class);
            Criteria cr1= example1.createCriteria();
            cr1.andEqualTo("mobile", mobile);
            MerchantAccount merchantAccount = merchantAccountService.findOneByExample(example1);
            //手机号不存在，校验有效
            if (clubInfo == null&&merchantAccount==null) {
                return true;
            } else {
                //手机号存在（存在的用户是当前用户，登录名一致，校验通过，否则校验不通过）
                    return false;
            }
    	}else {
    		Example example=new Example(ClubInfo.class);
            Criteria cr= example.createCriteria();
            cr.andEqualTo("mobile", mobile);
            ClubInfo clubInfo=clubInfoService.findOneByExample(example);
            
            Example example1=new Example(MerchantAccount.class);
            Criteria cr1= example1.createCriteria();
            cr1.andEqualTo("mobile", mobile);
            MerchantAccount merchantAccount = merchantAccountService.findOneByExample(example1);
            if(clubInfo==null&&merchantAccount==null) {
            	return true;
            }else {
            	if(clubInfo.getId()==Integer.parseInt(id)) {
            		return true;
            	}else {
            		return false;
            	}
            }
    	}
        
    }
    
    /**
     * 校验当前俱乐部名称的唯一性
     * @param cname 俱乐部名称
     * @return
     */
    @RequestMapping(value = "/checkExistCname")
    @ResponseBody
    public Boolean checkExistCname(String cname,String id) {
    	if(id==null) {
    		Example example=new Example(ClubInfo.class);
            Criteria cr= example.createCriteria();
            cr.andEqualTo("cname", cname);
            ClubInfo clubInfo=clubInfoService.findOneByExample(example);
            //俱乐部名称不存在，校验有效
            if (clubInfo == null) {
                return true;
            } else {
                //俱乐部存在（存在的用户是当前用户，登录名一致，校验通过，否则校验不通过）
                    return false;
            }
    	}else {
    		Example example=new Example(ClubInfo.class);
            Criteria cr= example.createCriteria();
            cr.andEqualTo("cname", cname);
            ClubInfo clubInfo=clubInfoService.findOneByExample(example);
            if(clubInfo==null) {
            	return true;
            }else {
            	if(clubInfo.getId()==Integer.parseInt(id)) {
            		return true;
            	}else {
            		return false;
            	}
            }
    	}
        
    }
    
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    
    //  列表
    @RequestMapping("/clubOrder_list")
    public String clubOrder_list(@RequestParam Map<String, Object> paramMap) {
    	paramMap.put("time_end", DateUtil.DateToString(DateUtil.addDay(new Date(), 1), "yyyy-MM-dd"));
		paramMap.put("time_start", DateUtil.DateToString(new Date(), "yyyy-MM-dd"));
        request.setAttribute("paramMap", paramMap);
        return "/a/club/clubOrder_list";
    }

    //  列表分页
    //@PreAuthorize("hasPermission('','_CLUBINFO:VIEW')")
    @PostMapping("/clubOrder_search")
    public
    @ResponseBody
    JQGirdPageResult clubOrder_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = clubInfoService.queryClubOrder(paramMap, pageBean);
        return new JQGirdPageResult(page);
    }
    
    //  编辑查看页面
    //@PreAuthorize("hasPermission('','_CLUBINFO:EDIT')")
    @GetMapping("/advert_manage")
    public String advert_manage(@RequestParam Map<String, Object> paramMap) {
;
           String id = getParaValue("id");
           ClubInfo entity = clubInfoService.findById(Integer.valueOf(id));
           paramMap.put("belong_club", id);
        request.setAttribute("paramMap", paramMap);
        request.setAttribute("belong_club", id);
        return "/a/club/advertInfo_list";
    }
    


    //  列表分页
//    @PreAuthorize("hasPermission('','_ADVERTINFO:VIEW')")
    @PostMapping("/advertInfo_searchById")
    public
    @ResponseBody
    JQGirdPageResult advertInfo_searchById(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean,@RequestParam(value="id",required=false) String id) {
//        String belong_club = getParaValue("id");
//        paramMap.put("belong_club",belong_club);
        Page<Map> page = advertInfoService.queryAdvertInfoPageListByclubId(paramMap, pageBean);
        return makePageResult(page, AdvertInfoResult.class);
    }   
    
    
    

    
    //  编辑查看页面
    //@PreAuthorize("hasPermission('','_CLUBINFO:EDIT')")
    @GetMapping("/packets_manage")
    public String packets_manage(@RequestParam Map<String, Object> paramMap,
    		@RequestParam(value="id",required=false) String id,@RequestParam(value="type",required=false) String type) {
    	Map map = clubInfoService.getClubInfoView(Integer.valueOf(id));
    	map.put("type", type);
        request.setAttribute("map", map);
        
        List<Map> aglist = agentInfoService.queryAgentInfoList(new HashMap());
        request.setAttribute("aglist", aglist);
        return "/a/club/packets_manage";
    }
    
    
    
    
    
    

    //  编辑页面
    @PreAuthorize("hasPermission('','_ADVERTINFO:EDIT')")
    @GetMapping("/advertInfo_edit")
    public String advertInfo_edit() {
        String id = getParaValue("id");
        AdvertInfo entity = advertInfoService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/advert/advertInfo_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_ADVERTINFO:EDIT')")
    @PostMapping("/advertInfo_edit")
    public
    @ResponseBody
    ApiResult advertInfo_edit(@ModelAttribute("entity") AdvertInfo entity) {
        advertInfoService.updateByIdSelective(entity);
        AdvertInfoResult result = makeResult(entity, AdvertInfoResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_ADVERTINFO:ADD')")
    @GetMapping("/advertInfo_add")
    public String advertInfo_add( @RequestParam Map<String, Object> paramMap) {
        String belong_club = getParaValue("id");
        request.setAttribute("belong_club",belong_club);
        request.setAttribute("paramMap", paramMap);
        return "/a/club/advertInfo_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_ADVERTINFO:ADD')")
    @PostMapping("/advertInfo_add")
    public
    @ResponseBody
    ApiResult advertInfo_add(@ModelAttribute("entity") AdvertInfo entity) {
    	entity.setAdd_time(new Date());
    	entity.setUpdate_time(new Date());
        advertInfoService.save(entity);
        entity.setIs_publish(Constants.False);
        advertInfoService.updateByIdSelective(entity);
        AdvertInfoResult result = makeResult(entity, AdvertInfoResult.class);
        return ApiResult.success(result);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}



