package com.wangtiansoft.KingDarts.core.support.wtuploader;

import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.config.utils.ApplicationContextUtil;
import com.wangtiansoft.KingDarts.core.extensions.files.FileService;
import com.wangtiansoft.KingDarts.core.stubs.PluginManager;
import com.wangtiansoft.KingDarts.core.stubs.file.BaseFilePluginStub;
import com.wangtiansoft.KingDarts.core.support.ueditor.define.ActionMap;
import com.wangtiansoft.KingDarts.core.support.ueditor.define.AppInfo;
import com.wangtiansoft.KingDarts.core.support.ueditor.define.BaseState;
import com.wangtiansoft.KingDarts.core.support.ueditor.define.State;
import com.wangtiansoft.KingDarts.core.support.wtuploader.upload.UploadRouter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weitong on 17/4/20.
 */
public class WtUploaderActionEnter {


    public final static String Action_Config = "config";
    public final static String Action_Checks = "checks";
    public final static String Action_Uploads = "uploads";

    private HttpServletRequest request = null;
    private String contextPath = null;
    private String actionType = null;
    private WtConfigManager configManager = null;

    public WtUploaderActionEnter(HttpServletRequest request) {
        this.request = request;
        this.actionType = request.getParameter("action");
        this.contextPath = request.getContextPath();
        this.configManager = WtConfigManager.getInstance(this.contextPath, request.getRequestURI());
    }

    public String exec() {

        String callbackName = this.request.getParameter("callback");
        if (callbackName != null) {
            if (!validCallbackName(callbackName)) {
                return new BaseState(false, AppInfo.ILLEGAL).toJSONString();
            }
            return callbackName + "(" + this.invoke() + ");";
        } else {
            return this.invoke();
        }
    }

    public boolean validCallbackName(String name) {
        return name.matches("^[a-zA-Z_]+[\\w0-9_]*$");
    }

    public String invoke() {

        BaseFilePluginStub filePluginStub = PluginManager.getInstance().getFilePluginStub();
        if (filePluginStub == null){
            return JSONObject.toJSONString(ApiResult.fail("上传插件未正确配置"));
        }
        State state = new BaseState( false, AppInfo.INVALID_ACTION );
        if (Action_Config.equals(actionType)){
            Map<String, Object> conf = this.configManager.getConfig( ActionMap.UPLOAD_FILE );
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("contextPath", contextPath);
            jsonObject.put("fileServerUrl", filePluginStub.getBaseFileUrl());
            jsonObject.put("maxSize", conf.get("maxSize"));
            return jsonObject.toJSONString();
        } else if (Action_Checks.equals(actionType)){
            String md5s = request.getParameter("md5s");
            FileService fileService = ApplicationContextUtil.getBean(FileService.class);
            HashMap<String, String> map = fileService.queryFileMapByMD5s(StringUtils.splitByWholeSeparatorPreserveAllTokens(md5s, ","));
            return JSONObject.toJSON(ApiResult.success(map)).toString();
        } else if (Action_Uploads.equals(actionType)){
            Map<String, Object> conf = this.configManager.getConfig( ActionMap.UPLOAD_FILE );
            state = new UploadRouter( request, conf ).doExec();
        }
        return state.toJSONString();

    }



}
