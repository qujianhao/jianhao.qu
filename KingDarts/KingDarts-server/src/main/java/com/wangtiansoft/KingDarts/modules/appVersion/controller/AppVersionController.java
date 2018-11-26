package com.wangtiansoft.KingDarts.modules.appVersion.controller;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.core.stubs.PluginManager;
import com.wangtiansoft.KingDarts.core.stubs.file.BaseFilePluginStub;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.core.support.ueditor.define.AppInfo;
import com.wangtiansoft.KingDarts.core.support.ueditor.define.BaseState;
import com.wangtiansoft.KingDarts.core.support.ueditor.define.FileType;
import com.wangtiansoft.KingDarts.modules.appVersion.service.AppVersionService;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.OSSUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.AdvertInfo;
import com.wangtiansoft.KingDarts.persistence.entity.AppVersion;
import com.wangtiansoft.KingDarts.results.core.AppVersionResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/appversion")
public class AppVersionController extends BaseController {

//	ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
	
    @Resource
    private AppVersionService appVersionService;

    //  列表
    @PreAuthorize("hasPermission('','_APPVERSION:VIEW')")
    @RequestMapping("/appVersion_list")
    public String appVersion_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("paramMap", paramMap);
        return "/a/appversion/appVersion_list";
    }

    //  列表分页
    @PreAuthorize("hasPermission('','_APPVERSION:VIEW')")
    @PostMapping("/appVersion_search")
    public
    @ResponseBody
    JQGirdPageResult appVersion_search(@RequestParam Map<String, Object> paramMap, @ModelAttribute PageBean pageBean) {
        Page<Map> page = appVersionService.queryAppVersionPageList(paramMap, pageBean);
        return makePageResult(page, AppVersionResult.class);
    }

    //  详情
    @PreAuthorize("hasPermission('','_APPVERSION:VIEW')")
    @GetMapping("/appVersion_view")
    public String appVersion_view() {
    String id = getParaValue("id");
        AppVersion entity = appVersionService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/appversion/appVersion_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','_APPVERSION:EDIT')")
    @GetMapping("/appVersion_edit")
    public String appVersion_edit() {
        String id = getParaValue("id");
        AppVersion entity = appVersionService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/appversion/appVersion_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','_APPVERSION:EDIT')")
    @PostMapping("/appVersion_edit")
    public
    @ResponseBody
    ApiResult appVersion_edit(@ModelAttribute("entity") AppVersion entity) {
        appVersionService.updateByIdSelective(entity);
        AppVersionResult result = makeResult(entity, AppVersionResult.class);
        return ApiResult.success(result);
    }

    //  新建页面
    @PreAuthorize("hasPermission('','_APPVERSION:ADD')")
    @GetMapping("/appVersion_add")
    public String appVersion_add() {
    	request.setAttribute("version", appVersionService.getMaxVersion());
        return "/a/appversion/appVersion_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','_APPVERSION:ADD')")
    @PostMapping("/appVersion_add")
    public
    @ResponseBody
    ApiResult appVersion_add(AppVersion entity,MultipartFile file) {
    	if(file==null){ 
			throw new AppRuntimeException("安装文件不能为空");
		} 
    	Map<String,String> map = saveFile(file);
    	
    	entity.setFile_name(file.getOriginalFilename());
    	entity.setCreate_time(new Date());
    	entity.setIs_publish(0);
    	entity.setApp_url(map.get("url"));
        appVersionService.save(entity);
        
        entity.setIs_publish(Constants.False);
        appVersionService.updateByIdSelective(entity);
        AppVersionResult result = makeResult(entity, AppVersionResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','_APPVERSION:EDIT')")
    @PostMapping("/appVersion_state")
    public
    @ResponseBody
    ApiResult appVersion_state(Integer id,Integer is_publish) {
    	if(is_publish.equals(1)){
    		Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("is_publish", 1);
			Page<Map> page = appVersionService.queryAppVersionPageList(paramMap, new PageBean());
			for(Map map:page.getResult()){
				AppVersion entity = new AppVersion();
				entity.setId(Integer.parseInt(map.get("id").toString()));
				entity.setIs_publish(0);
				appVersionService.updateByIdSelective(entity);
			}
    	}
    	AppVersion entity = appVersionService.findById(id);
        entity.setIs_publish(is_publish);
        appVersionService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    //  删除
    @PreAuthorize("hasPermission('','_APPVERSION:DELETE')")
    @PostMapping("/appVersion_delete")
    public
    @ResponseBody
    ApiResult appVersion_delete() {
        String id = getParaValue("id");
        AppVersion entity = appVersionService.findById(Integer.valueOf(id));
        entity.setIs_delete(Constants.True);
        appVersionService.updateByIdSelective(entity);
        return ApiResult.success();
    }

    private Map<String,String> saveFile(MultipartFile file){
		String separator = "/";
		if (file.isEmpty()) { 
			return null;
		}
		Map<String,String> map = new HashMap<>();

		File physicalFile = null;
		InputStream is = null;
		try {

			String originFileName = file.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);
			originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
			//  验证文件类型是否合法
			String[] allowFiles = {".apk",".APK"};
			if (!validType(suffix, allowFiles)) {
                throw new AppRuntimeException("安装文件格式不对");
            }
			//  验证文件大小是否合法
			//  保存文件路径
			File physicalPath = new File(System.getProperty("java.io.tmpdir") + "wtfiles" + File.separator);
			if (!physicalPath.exists()) physicalPath.mkdirs();
			String physicalFileString = physicalPath.getAbsolutePath() + File.separator + UUID.randomUUID().toString().replaceAll("-", "") + suffix;
			//  存储到本地
			physicalFile = new File(physicalFileString);
			file.transferTo(physicalFile); 

			//获取文件地址
			final String filepath = physicalFile.getPath();
			String type = com.wangtiansoft.KingDarts.common.utils.FileType.getFileType(physicalFile.getPath());
			if(type==null) {
	    		type="others";
	    	}
	    	Calendar cal = Calendar.getInstance();
	    	String savePath=cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+type+"/"+physicalFile.getName();
	    	
			//  文件落地
	    	System.out.println("开始上传"+savePath);
			OSSUtil.uploadFile(physicalFile, savePath);
	    	System.out.println("文件上传OSS成功"+savePath);
	    	/*fixedThreadPool.execute(new Runnable() {  
				@Override
				public void run() {  
					BaseFilePluginStub filePluginStub = PluginManager.getInstance().getFilePluginStub();
					File pfile = new File(filepath);
					try {
						String fileId = filePluginStub.upload(pfile);
						System.out.println("文件上传OSS成功"+fileId);
					} catch (IOException e) {
						e.printStackTrace();
					}finally {
						if (pfile != null && pfile.exists()){
							pfile.delete();
						}
					}
				}
	    	});*/
			
			map.put("url", savePath);
			map.put("type", suffix);
			map.put("original", originFileName + suffix);
			return map;
		} catch (AppRuntimeException e) {
            throw new AppRuntimeException(e.getMsg());
        }  catch (Exception e) {
			e.printStackTrace();
			throw new AppRuntimeException("文件上传错误");
		} finally {
			if (is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
//			if (physicalFile != null && physicalFile.exists()){
//				physicalFile.delete();
//			}
		}
	}
    
    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);
        return list.contains(type);
    }
}



