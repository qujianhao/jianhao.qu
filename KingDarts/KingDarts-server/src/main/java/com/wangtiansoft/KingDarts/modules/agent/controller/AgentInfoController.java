package com.wangtiansoft.KingDarts.modules.agent.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.advert.service.AdvertInfoService;
import com.wangtiansoft.KingDarts.modules.agent.service.AgentInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquAuthService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;
import com.wangtiansoft.KingDarts.modules.merchantAccount.service.MerchantAccountService;
import com.wangtiansoft.KingDarts.modules.sequence.service.SequenceService;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.config.utils.MD5Util;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.AdvertInfo;
import com.wangtiansoft.KingDarts.persistence.entity.AgentInfo;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.persistence.entity.EquAuth;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;
import com.wangtiansoft.KingDarts.results.core.AdvertInfoResult;
import com.wangtiansoft.KingDarts.results.core.AgentInfoResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/agentInfo")
public class AgentInfoController extends BaseController {

    @Resource 
    private AgentInfoService agentInfoService;
    
    @Resource
    private SequenceService sequenceService;
    
    @Resource
    private EquInfoService equInfoService;
    
    @Resource
    private ClubInfoService clubInfoService;
    
    @Resource
    private EquAuthService equAuthService;
    
    @Resource
    private MerchantAccountService merchantAccountService;
    @Resource
    private AdvertInfoService advertInfoService;


    //  列表
    @PreAuthorize("hasPermission('','_AGENTINFO:VIEW')")
    @RequestMapping("/agentInfo_list")
    public String agentInfo_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/agent/agentInfo_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_AGENTINFO:VIEW')")
    @PostMapping("/agentInfo_search")
    public
    @ResponseBody
    JQGirdPageResult agentInfo_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = agentInfoService.queryAgentInfoPageList(paramMap, pageBean);
        return makePageResult(page, AgentInfoResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_AGENTINFO:VIEW')")
    @GetMapping("/agentInfo_view")
    public String agentInfo_view() {
    String id = getParaValue("id");
        AgentInfo entity = agentInfoService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/agent/agentInfo_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_AGENTINFO:EDIT')")
    @GetMapping("/agentInfo_edit")
    public String agentInfo_edit(@RequestParam Map<String, Object> paramMap,
    		@RequestParam(value="id",required=false) String id,@RequestParam(value="type",required=false) String type) {
        AgentInfo entity = agentInfoService.findById(Integer.valueOf(id));
        paramMap.put("entity", entity);
        paramMap.put("type",type);
        request.setAttribute("paramMap", paramMap);
        return "/a/agent/agentInfo_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_AGENTINFO:EDIT')")
    @PostMapping("/agentInfo_update")
    public
    @ResponseBody
    ApiResult agentInfo_edit(@ModelAttribute("entity") AgentInfo entity) {
        agentInfoService.updateByIdSelective(entity);
        if(entity.getAg_password()!=null) {
        	MD5Util md5Util = new MD5Util();
        	merchantAccountService.updatePasswordByMerno(md5Util.MD5Encode(entity.getAg_password(), "utf-8"), entity.getMobile(), entity.getAgname(), 2, entity.getAgno());
        }else {
        	merchantAccountService.updateAccountNameByMerno(entity.getMobile(), entity.getAgname(), 2, entity.getAgno());
        }
        AgentInfoResult result = makeResult(entity, AgentInfoResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_AGENTINFO:ADD')")
    @GetMapping("/agentInfo_add")
    public String agentInfo_add() {
        return "/a/agent/agentInfo_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_AGENTINFO:ADD')")
    @PostMapping("/agentInfo_save")
    public
    @ResponseBody
    ApiResult agentInfo_save(@ModelAttribute("entity") AgentInfoResult entity) {
    	AgentInfo agent = new AgentInfo();
    	BeanUtils.copyProperties(entity,agent);
    	String agno = agent.getAgno();
		if (agno == null || "".equals(agno)) {
			agno = sequenceService.getNextSeq("agent", 1, 8);
			agent.setAgno(agno);
		}
		MD5Util md5Util = new MD5Util();
		agent.setAg_password(md5Util.MD5Encode(agent.getAg_password(), "utf-8"));
		agent.setAdd_time(new Date());
		agent.setIsvalid(1);
		agent.setResum(new BigDecimal(0));
		agent.setIncomes(new BigDecimal(0));
		agent.setExpends(new BigDecimal(0));
        agentInfoService.save(agent);
        //保存商户登录账户及密码
  		MerchantAccount merchantAccount = new MerchantAccount();
  		merchantAccount.setMerno(agent.getAgno());
  		merchantAccount.setPassword(agent.getAg_password());
  		merchantAccount.setMobile(agent.getMobile());
  		merchantAccount.setMertype(2);
  		merchantAccount.setAccountnames(agent.getAgname());
  		merchantAccount.setCreatetime(new Date());
  		merchantAccount.setIsdelete(0);
  		merchantAccountService.save(merchantAccount);
        AgentInfoResult result = makeResult(entity, AgentInfoResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_AGENTINFO:EDIT')")
    @PostMapping("/agentInfo_state")
    public
    @ResponseBody
    ApiResult agentInfo_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        AgentInfo entity = agentInfoService.findById(Integer.valueOf(id));
        agentInfoService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_AGENTINFO:DELETE')")
    @PostMapping("/agentInfo_delete")
    public
    @ResponseBody
    ApiResult agentInfo_delete() {
        String id = getParaValue("id");
        AgentInfo entity = agentInfoService.findById(Integer.valueOf(id));
        //查询名下有无俱乐部
        Example example1=new Example(ClubInfo.class);
        Criteria cr1= example1.createCriteria();
        cr1.andEqualTo("agno", entity.getAgno());
        cr1.andEqualTo("isvalid", 1);
        List<ClubInfo> clubInfoList = clubInfoService.findAllByExample(example1);
        if(null == clubInfoList || clubInfoList.size() ==0) {
        	Example example=new Example(EquAuth.class);
            Criteria cr= example.createCriteria();
            cr.andEqualTo("auth_no", entity.getAgno());
            List<EquAuth> equAuthList = equAuthService.findAllByExample(example);
            if(null != equAuthList || equAuthList.size() !=0) {
            	
            	for(EquAuth equAuth:equAuthList) {
            		equAuthService.updateAgnoAuthByEquno(equAuth.getEquno(), null, null);//取消设备授权
            		
            		Example example2=new Example(EquInfo.class);
                    Criteria cr2= example2.createCriteria();
                    cr2.andEqualTo("equno", equAuth.getEquno());
                    EquInfo equInfo = equInfoService.findOneByExample(example2);
            		equInfo.setEqu_status(0);
            		equInfo.setIsactivation(0);
            		BigDecimal big = new BigDecimal("5");
            		equInfo.setGame_price(big);
            		equInfoService.updateByIdSelective(equInfo);//修改设备授权状态
            	}
            }
        	
        	//更改代理商状态为不可用
        	entity.setIsvalid(0);
        	agentInfoService.updateByIdSelective(entity);
        	
        	//更改登录状态
            MerchantAccount merchantAccount = merchantAccountService.getUserByLoginName(entity.getAgno());
            merchantAccount.setIsdelete(1);
            merchantAccountService.updateByIdSelective(merchantAccount);
        }else {
        	throw new AppRuntimeException("请先删除名下俱乐部！");
        }
        return ApiResult.success();
    }
    
    //  名下飞镖机
    @RequestMapping("/agentEquInfo_list")
    public String agentEquInfo_list(@RequestParam Map<String, Object> paramMap,
    		@RequestParam(value="agno",required=false) String agno,@RequestParam(value="type",required=false) String type) {
    	paramMap.put("agno", agno);
    	paramMap.put("type", type);
        request.setAttribute("paramMap", paramMap);
        return "/a/agent/agentEquInfo_list";
    }
    
    //  名下飞镖机分页
    @PostMapping("/agentEquInfo_search")
    public
    @ResponseBody
    JQGirdPageResult agentEquInfo_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = agentInfoService.queryAgentEquInfoList(paramMap, pageBean);
        return new JQGirdPageResult(page);
    }
    
    //  飞镖机详情
    @GetMapping("/agentEquInfo_view")
    public String agentEquInfo_view(@RequestParam Map<String, Object> paramMap,
    		@RequestParam(value="id",required=false) String id,@RequestParam(value="type",required=false) String type) {
        EquInfo entity = equInfoService.findById(id);
        request.setAttribute("entity", entity);
        return "/a/agent/agentEquInfo_view";
    }

    //  名下俱乐部
    @RequestMapping("/agentClubInfo_list")
    public String agentClubInfo_list(@RequestParam Map<String, Object> paramMap,
    		@RequestParam(value="agno",required=false) String agno,@RequestParam(value="type",required=false) String type) {
    	paramMap.put("agno", agno);
    	paramMap.put("type", type);
        request.setAttribute("paramMap", paramMap);
        return "/a/agent/agentClubInfo_list";
    }
    
    //  名下俱乐部分页
    @PostMapping("/agentClubInfo_search")
    public
    @ResponseBody
    JQGirdPageResult agentClubInfo_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = clubInfoService.queryAgentClubInfoList(paramMap, pageBean);
        return new JQGirdPageResult(page);
    }
    
    //  名下俱乐部删除
    //@PreAuthorize("hasPermission('','_CLUBINFO:DELETE')")
    @PostMapping("/AgentclubInfo_delete")
    public
    @ResponseBody
    ApiResult clubInfo_delete() {
        String id = getParaValue("id");
        ClubInfo entity = clubInfoService.findById(Integer.valueOf(id));
        
        Example example=new Example(AgentInfo.class);
        Criteria cr= example.createCriteria();
        cr.andEqualTo("agno", entity.getAgno());
        AgentInfo agentInfo = agentInfoService.findOneByExample(example);
        
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
        		equAuthService.updateAgnoAuthByEquno(equAuth.getEquno(), agentInfo.getAgno(), agentInfo.getAgname());//取消设备授权
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
    @RequestMapping(value = "/checkExistMobile")
    @ResponseBody
    public Boolean checkExistMobile(String mobile,String id) {
    	if(id==null) {
    		Example example=new Example(AgentInfo.class);
            Criteria cr= example.createCriteria();
            cr.andEqualTo("mobile", mobile);
            AgentInfo agentInfo=agentInfoService.findOneByExample(example);
            
            Example example1=new Example(MerchantAccount.class);
            Criteria cr1= example1.createCriteria();
            cr1.andEqualTo("mobile", mobile);
            MerchantAccount merchantAccount = merchantAccountService.findOneByExample(example1);
            //手机号不存在，校验有效
            if (agentInfo == null&&merchantAccount==null) {
                return true;
            } else {
                //手机号存在（存在的用户是当前用户，登录名一致，校验通过，否则校验不通过）
                    return false;
            }
    	}else {
    		Example example=new Example(AgentInfo.class);
            Criteria cr= example.createCriteria();
            cr.andEqualTo("mobile", mobile);
            AgentInfo agentInfo=agentInfoService.findOneByExample(example);
            
            Example example1=new Example(MerchantAccount.class);
            Criteria cr1= example1.createCriteria();
            cr1.andEqualTo("mobile", mobile);
            MerchantAccount merchantAccount = merchantAccountService.findOneByExample(example1);
            //手机号不存在，校验有效
            if (agentInfo == null&&merchantAccount==null) {
            	return true;
            }else {
            	if(agentInfo.getId()==Integer.parseInt(id)) {
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
    @RequestMapping(value = "/checkExisAgname")
    @ResponseBody
    public Boolean checkExisAgname(String agname,String id) {
    	if(id==null) {
    		Example example=new Example(AgentInfo.class);
            Criteria cr= example.createCriteria();
            cr.andEqualTo("agname", agname);
            AgentInfo agentInfo=agentInfoService.findOneByExample(example);
            //俱乐部名称不存在，校验有效
            if (agentInfo == null) {
                return true;
            } else {
                //俱乐部存在（存在的用户是当前用户，登录名一致，校验通过，否则校验不通过）
                    return false;
            }
    	}else {
    		Example example=new Example(AgentInfo.class);
            Criteria cr= example.createCriteria();
            cr.andEqualTo("agname", agname);
            AgentInfo agentInfo=agentInfoService.findOneByExample(example);
            if(agentInfo==null) {
            	return true;
            }else {
            	if(agentInfo.getId()==Integer.parseInt(id)) {
            		return true;
            	}else {
            		return false;
            	}
            }
    	}
        
    }
    
    
    
    
    //  编辑查看页面
    //@PreAuthorize("hasPermission('','_CLUBINFO:EDIT')")
    @GetMapping("/advert_manage")
    public String advert_manage(@RequestParam Map<String, Object> paramMap) {
;
           String id = getParaValue("id");
//           ClubInfo entity = clubInfoService.findById(Integer.valueOf(id));
           paramMap.put("belong_agent", id);
        request.setAttribute("paramMap", paramMap);
        request.setAttribute("belong_agent", id);
        return "/a/agent/advertInfo_list";
    }
    
    
    //  列表
    @PreAuthorize("hasPermission('','_ADVERTINFO:VIEW')")
    @RequestMapping("/advertInfo_list")
    public String advertInfo_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/agent/advertInfo_list";
    }
    
//
//
    //  列表分页
//    @PreAuthorize("hasPermission('','_ADVERTINFO:VIEW')")
    @PostMapping("/advertInfo_searchById")
    public
    @ResponseBody
    JQGirdPageResult advertInfo_searchById(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean,@RequestParam(value="id",required=false) String id) {

        Page<Map> page = equInfoService.queryAdvertInfoPageListByagentId(paramMap, pageBean);
        return makePageResult(page, AdvertInfoResult.class);
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
        String belong_agent = getParaValue("id");
        request.setAttribute("belong_agent",belong_agent);
        request.setAttribute("paramMap", paramMap);

        return "/a/agent/advertInfo_add";
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



