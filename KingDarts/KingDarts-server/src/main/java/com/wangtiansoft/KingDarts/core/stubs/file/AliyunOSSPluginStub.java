package com.wangtiansoft.KingDarts.core.stubs.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.wangtiansoft.KingDarts.common.utils.FileType;
import com.wangtiansoft.KingDarts.config.freemarker.FreeMarkerConfig;
import com.wangtiansoft.KingDarts.config.utils.ApplicationContextUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.stubs.PluginAttr;

/**
 * Created by Administrator on 2017/10/21 0021.
 */
public class AliyunOSSPluginStub extends BaseFilePluginStub {

	private  final static String OSS_END_POINT="endPoint";
	
	private  final static String OSS_ACCESS_KEY_ID="accessKeyId";
	
	private  final static String OSS_ACCESS_KEY_SECRET="accessKeySecret";
	
	private  final static String OSS_BUCKET_NAME="bucketName";
	
	private  final static String OSS_FILE_VIEW_URL="fileViewUrl";
	
	private static boolean isConfiged = false;
	
	private static OSSClient ossClient = null;
	
    @Override
    public String getStubName() {
        return "Aliyun OSS";
    }

    @Override
    public String getStubType() {
        return "file";
    }

    @Override
    public void setup() {
    	JSONObject dbAttrJson = new JSONObject();
        if (this.getPlugins() != null && StringUtils.isNotBlank(this.getPlugins().getAttrs())) {
            dbAttrJson = JSONObject.parseObject(this.getPlugins().getAttrs());
        }
        LinkedHashMap<String, PluginAttr> attrsMap = new LinkedHashMap<>();
        attrsMap.put(OSS_END_POINT, new PluginAttr(OSS_END_POINT, OSS_END_POINT, StringUtils.defaultIfBlank(dbAttrJson.getString(OSS_END_POINT), "http://oss.aliyuncs.com"), "oss连接点"));
        attrsMap.put(OSS_ACCESS_KEY_ID, new PluginAttr(OSS_ACCESS_KEY_ID, OSS_ACCESS_KEY_ID, StringUtils.defaultIfBlank(dbAttrJson.getString(OSS_ACCESS_KEY_ID), "xxxx"), "oss钥匙"));
        attrsMap.put(OSS_ACCESS_KEY_SECRET, new PluginAttr(OSS_ACCESS_KEY_SECRET, OSS_ACCESS_KEY_SECRET, StringUtils.defaultIfBlank(dbAttrJson.getString(OSS_ACCESS_KEY_SECRET), "xxxx"), "oss秘钥"));
        attrsMap.put(OSS_BUCKET_NAME, new PluginAttr(OSS_BUCKET_NAME, OSS_BUCKET_NAME, StringUtils.defaultIfBlank(dbAttrJson.getString(OSS_BUCKET_NAME), "xxx"), "文件域"));
        attrsMap.put(OSS_FILE_VIEW_URL, new PluginAttr(OSS_FILE_VIEW_URL, OSS_FILE_VIEW_URL, StringUtils.defaultIfBlank(dbAttrJson.getString(OSS_FILE_VIEW_URL), "xxx"), "文件浏览根目录"));
        
        this.setAttrMap(attrsMap);

        if (!isConfiged) {
            config();
            isConfiged = true;
        }
    }

    @Override
    public void config() {
    	try {
    		if (ossClient != null) {
                ossClient.shutdown();
            }
            _logger.info("初始化OSS配置");
            ossClient = new OSSClient(getAttrMap().get(OSS_END_POINT).getValue(), getAttrMap().get(OSS_ACCESS_KEY_ID).getValue(), getAttrMap().get(OSS_ACCESS_KEY_SECRET).getValue());
            FreeMarkerConfig freeMarkerConfig = ApplicationContextUtil.getBean(FreeMarkerConfig.class);
            FreeMarkerConfig.map.put(FreeMarkerConfig.kBaseFileUrl, getAttrMap().get(OSS_FILE_VIEW_URL).getValue());
            freeMarkerConfig.getConfiguration().setSharedVaribles(FreeMarkerConfig.map);

        } catch (Exception ex) {
            _logger.error("初始化OSS配置失败", ex.getLocalizedMessage());
        }
    }

    @Override
    public int getStubStateValue() {
        if (this.getPlugins() == null){
            return StubState.NotConfig.ordinal();
        }else if (this.getPlugins().getIs_publish() == Constants.False){
            return StubState.Pending.ordinal();
        }
        return StubState.Active.ordinal();
    }

    @Override
    public String getBaseFileUrl() {
    	if (this.getAttrMap() != null && this.getAttrMap().containsKey(OSS_FILE_VIEW_URL)) {
            return this.getAttrMap().get(OSS_FILE_VIEW_URL).getValue();
        }
        return "";
    }


    @Override
    public String upload(File file) {
    	String type = FileType.getFileType(file.getPath());
    	if(type==null) {
    		type="others";
    	}
    	Calendar cal = Calendar.getInstance();
    	String savePath=cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+type+"/"+file.getName();
    	ossClient.putObject(getAttrMap().get(OSS_BUCKET_NAME).getValue(),savePath, file);
    	return savePath;
    }

    @Override
    public String upload(FileInputStream fileInputStream) {
        return null;
    }
    
    public String setFilePath(File file) {
    	String fileType=StringUtils.substringAfterLast(file.getName(), ".");
    	return fileType;
    }
}
