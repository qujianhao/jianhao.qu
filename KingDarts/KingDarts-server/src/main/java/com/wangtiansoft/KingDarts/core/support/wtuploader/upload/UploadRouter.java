package com.wangtiansoft.KingDarts.core.support.wtuploader.upload;

import com.wangtiansoft.KingDarts.core.support.ueditor.define.State;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/20 0020.
 */
public class UploadRouter {

    private HttpServletRequest request = null;
    private Map<String, Object> conf = null;

    public UploadRouter(HttpServletRequest request, Map<String, Object> conf) {
        this.request = request;
        this.conf = conf;
    }

    public final State doExec() {
        return BaseUploader.save(this.request, this.conf);
    }
}
