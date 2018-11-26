package com.wangtiansoft.KingDarts.modules.api.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.club.service.ClubInfoService;
import com.wangtiansoft.KingDarts.modules.merchantAccount.service.MerchantAccountService;
import com.wangtiansoft.KingDarts.persistence.entity.ClubInfo;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantAccount;
import com.wangtiansoft.KingDarts.results.core.ClubInfoResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.wangtiansoft.KingDarts.core.support.ueditor.define.FileType;
import com.wangtiansoft.KingDarts.core.stubs.PluginManager;
import com.wangtiansoft.KingDarts.core.stubs.file.BaseFilePluginStub;

@Controller
@RequestMapping("/api/club/")
public class WeixinClubController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(WeixinClubController.class);
	
	@Resource
	private ClubInfoService clubInfoServie;
	
	@Resource
    private MerchantAccountService merchantAccountService;
	
	/**
	 * 代理商添加俱乐部
	 * @param clubInfoResult
	 * @return
	 */
	@RequestMapping(value="/addClub",consumes = "application/json; charset=utf-8")
	public@ResponseBody ApiResult addClub(@RequestBody final ClubInfoResult clubInfoResult) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String agno) throws Exception {
				clubInfoResult.setAgno(agno);
				if(clubInfoResult.getAcac()==null) {
					clubInfoResult.setAcac(0);
				}
				
				//俱乐部名验证
				Example example=new Example(ClubInfo.class);
	            Criteria cr= example.createCriteria();
	            cr.andEqualTo("cname", clubInfoResult.getCname());
	            ClubInfo clubInfo=clubInfoServie.findOneByExample(example);
	            //俱乐部名称不存在，校验有效
	            if (clubInfo == null) {
	            	//手机号验证
	            	Example example2=new Example(ClubInfo.class);
		            Criteria cr2= example2.createCriteria();
		            cr2.andEqualTo("mobile", clubInfoResult.getMobile());
		            ClubInfo clubInfo2=clubInfoServie.findOneByExample(example2);
		            
		            Example example1=new Example(MerchantAccount.class);
		            Criteria cr1= example1.createCriteria();
		            cr1.andEqualTo("mobile", clubInfoResult.getMobile());
		            MerchantAccount merchantAccount = merchantAccountService.findOneByExample(example1);
		            //手机号不存在，校验有效
		            if (clubInfo2 == null&&merchantAccount==null) {
		            	//验证通过 保存信息
		            	ClubInfoResult clubInfoResults = clubInfoServie.saveClubInfo(clubInfoResult);
						return clubInfoResults;
		            } else {
		                //手机号存在（存在的用户是当前用户，登录名一致，校验通过，否则校验不通过）
		            	throw new AppRuntimeException("手机号码不可用！");
		            }
	            } else {
	                //俱乐部存在（存在的用户是当前用户，登录名一致，校验通过，否则校验不通过）
	            	throw new AppRuntimeException("俱乐部名称不可用！");
	            }
				//文件上传
//				BaseFilePluginStub filePluginStub = PluginManager.getInstance().getFilePluginStub();
//			    String fileId = filePluginStub.upload(clubInfoResult.getLogofile());
//			    clubInfoResult.setLogo(fileId);
				
			}
		});
		return result;
	}
	
	
	
	/**
     * 校验当前手机号的唯一性
     * @param mobile 手机号
     * @return
     */
	@RequestMapping("/checkUnique")
	public@ResponseBody ApiResult checkExist(final String mobile,final String id) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Boolean execute(String agno) throws Exception {
				if(id==null) {
		    		Example example=new Example(ClubInfo.class);
		            Criteria cr= example.createCriteria();
		            cr.andEqualTo("mobile", mobile);
		            ClubInfo clubInfo=clubInfoServie.findOneByExample(example);
		            
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
		            ClubInfo clubInfo=clubInfoServie.findOneByExample(example);
		            
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
		});
		return result;
	}
    
    /**
     * 校验当前俱乐部名称的唯一性
     * @param cname 俱乐部名称
     * @return
     */
	@RequestMapping("/checkExistCname")
	public@ResponseBody ApiResult checkExistCname(final String cname,final String id) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Boolean execute(String agno) throws Exception {
				if(id==null) {
		    		Example example=new Example(ClubInfo.class);
		            Criteria cr= example.createCriteria();
		            cr.andEqualTo("cname", cname);
		            ClubInfo clubInfo=clubInfoServie.findOneByExample(example);
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
		            ClubInfo clubInfo=clubInfoServie.findOneByExample(example);
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
		});
		return result;
	}
}
