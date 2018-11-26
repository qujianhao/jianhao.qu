package com.wangtiansoft.KingDarts.modules.equip.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.core.utils.AuthUtil;
import com.wangtiansoft.KingDarts.modules.agent.service.AgentInfoService;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquAuthService;
import com.wangtiansoft.KingDarts.modules.equip.service.EquInfoService;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.common.utils.RandomUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.Account;
import com.wangtiansoft.KingDarts.persistence.entity.AgentInfo;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.persistence.entity.ClubPlace;
import com.wangtiansoft.KingDarts.persistence.entity.EquAuth;
import com.wangtiansoft.KingDarts.persistence.entity.EquInfo;
import com.wangtiansoft.KingDarts.results.core.EquAuthResult;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/equAuth")
public class EquAuthController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(EquAuthController.class);
    @Resource
    private EquAuthService equAuthService;
    @Resource
    private EquInfoService equInfoService;
    @Resource
    private ClubInfoService clubInfoService;
    @Resource
    private AgentInfoService agentInfoService;

    //  列表
//    @PreAuthorize("hasPermission('','_EQUAUTH:VIEW')")
    @RequestMapping("/equAuth_list")
    public String equAuth_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/equip/equAuth_list";
    }

    //  列表分页
//    @PreAuthorize("hasPermission('','_EQUAUTH:VIEW')")
    @PostMapping("/equAuth_search")
    public
    @ResponseBody
    JQGirdPageResult equAuth_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = equAuthService.queryEquAuthPageList(paramMap, pageBean);
//        return makePageResult(page, EquAuthResult.class);
        List<Map> resultList = new ArrayList<>();
        for (Map map : page.getResult()){
        	if(map.get("id")==null){
        		map.put("id", map.get("equno"));
        	}
        	resultList.add(map);
        }
        page.getResult().clear();
        page.getResult().addAll(resultList);
        return new JQGirdPageResult(page);
    }

    //  详情
//    @PreAuthorize("hasPermission('','_EQUAUTH:VIEW')")
    @GetMapping("/equAuth_view")
    public String equAuth_view() {
    String id = getParaValue("id");
        EquAuth entity = equAuthService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/equip/equAuth_view";
    }

    //  编辑页面
//    @PreAuthorize("hasPermission('','_EQUAUTH:EDIT')")
    @GetMapping("/equAuth_edit")
    public String equAuth_edit(String equno,String id,String type) {
    	//获取俱乐部列表
    	/*Example example1 = new BaseExample(ClubInfo.class);
        example1.createCriteria().andEqualTo("isvalid", 1);
    	List<ClubInfo> clubInfoList = clubInfoService.findAllByExample(example1);*/
    	//获取代理商列表
    	Example example2 = new BaseExample(AgentInfo.class);
        example2.createCriteria().andEqualTo("isvalid", 1);
    	List<AgentInfo> agentInfoList = agentInfoService.findAllByExample(example2);
    	
    	EquInfo equInfo =equInfoService.getEquInfoByNo(equno);
//    	request.setAttribute("clubInfoList", clubInfoList);
    	request.setAttribute("agentInfoList", agentInfoList);
    	request.setAttribute("equInfo", equInfo);
    	request.setAttribute("type", type);
    	if(StringUtils.isNotEmpty(id)&&!id.equals(equno)){
    		EquAuth equAuth = equAuthService.findById(Integer.parseInt(id));
    		request.setAttribute("equAuth", equAuth);
    	}
        return "/a/equip/equAuth_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_EQUAUTH:EDIT')")
    @PostMapping("/equAuth_edit")
    public
    @ResponseBody
    ApiResult equAuth_edit(EquAuthResult result) {
    	if("0".equals(result.getEqu_status().toString())
    			||"6".equals(result.getEqu_status().toString())){
    		result.setAuth_no("");
    		result.setAuth_name("");
    	}
    	equAuthService.saveOrUpdate(result);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_EQUAUTH:ADD')")
    @GetMapping("/equAuth_add")
    public String equAuth_add() {
        return "/a/equip/equAuth_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_EQUAUTH:ADD')")
    @PostMapping("/equAuth_add")
    public
    @ResponseBody
    ApiResult equAuth_add(EquAuthResult result) {
    	Account acc = AuthUtil.getAccount(request);
    	result.setAuth_acc(acc.getId());
    	result.setAuth_type(1);//授权人类别（1公司;2代理商）        
    	
        equAuthService.saveOrUpdate(result);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_EQUAUTH:EDIT')")
    @PostMapping("/equAuth_state")
    public
    @ResponseBody
    ApiResult equAuth_state() {
        String id = getParaValue("id");
        String is_publish = getParaValue("is_publish");
        EquAuth entity = equAuthService.findById(Integer.valueOf(id));
        equAuthService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_EQUAUTH:DELETE')")
    @PostMapping("/equAuth_delete")
    public
    @ResponseBody
    ApiResult equAuth_delete() {
        String id = getParaValue("id");
        EquAuth entity = equAuthService.findById(Integer.valueOf(id));
        equAuthService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    @RequestMapping("/qrcode")
    public String qrcode(String equno) {
    	String baseurl = request.getScheme()+"://"+request.getServerName();
		if(request.getServerPort()!=80){
			baseurl += ":"+request.getServerPort();
		}
        request.setAttribute("qrcodeurl", baseurl+"/wx/oauth?trade_no=equno_"+equno);
        request.setAttribute("equno", equno);
        return "/a/equip/qrcode";
    }
    
    //新增修改虚拟设备
    @GetMapping("/unentity_edit")
    public String unentity_edit(String equno,String id,String type) {
    	//获取俱乐部列表
    	/*Example example1 = new BaseExample(ClubInfo.class);
        example1.createCriteria().andEqualTo("isvalid", 1);
    	List<ClubInfo> clubInfoList = clubInfoService.findAllByExample(example1);*/
    	//获取代理商列表
    	Example example2 = new BaseExample(AgentInfo.class);
        example2.createCriteria().andEqualTo("isvalid", 1);
    	List<AgentInfo> agentInfoList = agentInfoService.findAllByExample(example2);
    	
    	if(StringUtils.isNoneEmpty(equno)){
    		EquInfo equInfo =equInfoService.getEquInfoByNo(equno);
    		request.setAttribute("equInfo", equInfo);
    	}
    	
//    	request.setAttribute("clubInfoList", clubInfoList);
    	request.setAttribute("agentInfoList", agentInfoList);
    	request.setAttribute("type", type);
    	if(StringUtils.isNotEmpty(id)&&!id.equals(equno)){
    		EquAuth equAuth = equAuthService.findById(Integer.parseInt(id));
    		request.setAttribute("equAuth", equAuth);
    	}
        return "/a/equip/unentity_edit";
    }
    
    //新增修改虚拟设备
    @PostMapping("/unentity_add")
    public @ResponseBody ApiResult unentity_add(@ModelAttribute("entity") EquInfo entity,EquAuthResult result) {
    	
    	if(StringUtils.isEmpty(entity.getEquno())){
			throw new AppRuntimeException("设备编码不能为空");
		}
		EquInfo equ = equInfoService.getEquInfoByNo(entity.getEquno());
		if(equ!=null){
			throw new AppRuntimeException("设备编码已存在");
		}
		
		entity.setId(entity.getEquno());
		//设置默认值
		if(entity.getGame_price()==null) {//设置默认游戏定价
			BigDecimal price = new BigDecimal(Constants.price_def);
			entity.setGame_price(price);
		}
		entity.setIsowed(Constants.True);
		entity.setIsallow(Constants.True);
		entity.setIsline(Constants.False);
		entity.setIsactivation(Constants.True);//虚拟设备默认激活
		entity.setIsvalid(Constants.True);
		entity.setAdd_time(new Date());
		entity.setEqu_status(Constants.equ_status_auth);
		entity.setIsentity(Constants.False);
		equInfoService.save(entity);
    	
		Account acc = AuthUtil.getAccount(request);
    	result.setAuth_acc(acc.getId());
    	result.setAuth_type(1);//授权人类别（1公司;2代理商）
		result.setEquno(entity.getEquno());
    	equAuthService.saveOrUpdate(result);
        return ApiResult.success(result);
    }
    //新增修改虚拟设备
    @PostMapping("/unentity_edit")
    public @ResponseBody ApiResult unentity_edit(@ModelAttribute("entity") EquInfo entity,EquAuthResult result) {
    	
    	entity.setId(entity.getEquno());
    	//设置默认值
    	if(entity.getGame_price()==null) {//设置默认游戏定价
    		BigDecimal price = new BigDecimal(Constants.price_def);
    		entity.setGame_price(price);
    	}
    	entity.setEqu_status(Constants.equ_status_auth);
    	entity.setIsactivation(Constants.True);//虚拟设备默认激活
    	entity.setIsvalid(Constants.True);
    	entity.setEqu_status(Constants.equ_status_auth);
    	entity.setIsentity(Constants.False);
    	equInfoService.updateByIdSelective(entity);
    	
    	result.setEquno(entity.getEquno());
    	equAuthService.saveOrUpdate(result);
    	return ApiResult.success(result);
    }
}



