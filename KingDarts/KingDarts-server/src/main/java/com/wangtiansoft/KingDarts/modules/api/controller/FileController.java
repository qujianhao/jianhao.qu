package com.wangtiansoft.KingDarts.modules.api.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.core.support.ueditor.define.FileType;
import com.wangtiansoft.KingDarts.core.stubs.PluginManager;
import com.wangtiansoft.KingDarts.core.stubs.file.BaseFilePluginStub;

@Controller
@RequestMapping("/api/file")
public class FileController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(FileController.class);

	/**
	 * 文件上传
	 * @param files
	 * @return
	 */
	@RequestMapping("/upload")
	public@ResponseBody ApiResult upload(final MultipartFile[] files) {
		ApiResult result = this.buildMobileAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String agno) throws Exception {

				if(files==null||files.length == 0){ 
					throw new AppRuntimeException("文件不能为空");
				} 

				List<Map> list = new ArrayList<>();
				for(int i = 0;i<files.length;i++){ 
					MultipartFile file = files[i]; 
					list.add(saveFile(file));
				} 

				Map<String,Object> map = new HashMap<>();
				map.put("files", list);
				return map;
			}
		});
		return result;
	}
	
	@RequestMapping("/equ/upload")
	public@ResponseBody ApiResult equupload(final MultipartFile[] files) {
		ApiResult result = this.buildEquAuthAjaxResponse(new IWebAuthResponseHandler() {
			@Override
			public Object execute(String equno) throws Exception {

				if(files==null||files.length == 0){ 
					throw new AppRuntimeException("文件不能为空");
				} 

				List<Map> list = new ArrayList<>();
				for(int i = 0;i<files.length;i++){ 
					MultipartFile file = files[i]; 
					list.add(saveFile(file));
				} 

				Map<String,Object> map = new HashMap<>();
				map.put("files", list);
				return map;
			}
		});
		return result;
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
			
			
			//  验证文件大小是否合法
			//  保存文件路径
			File physicalPath = new File(System.getProperty("java.io.tmpdir") + "wtfiles" + File.separator);
			if (!physicalPath.exists()) physicalPath.mkdirs();
			String physicalFileString = physicalPath.getAbsolutePath() + File.separator + UUID.randomUUID().toString().replaceAll("-", "") + suffix;
			//  存储到本地
			physicalFile = new File(physicalFileString);
			file.transferTo(physicalFile); 

			//  文件落地
			BaseFilePluginStub filePluginStub = PluginManager.getInstance().getFilePluginStub();
			String fileId = filePluginStub.upload(physicalFile);
			map.put("url", fileId);
			map.put("type", suffix);
			map.put("original", originFileName + suffix);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (physicalFile != null && physicalFile.exists()){
				physicalFile.delete();
			}
		}

		return map;

	}

}
