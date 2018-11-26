package com.wangtiansoft.KingDarts.core.stubs.file;

import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.config.freemarker.FreeMarkerConfig;
import com.wangtiansoft.KingDarts.config.utils.ApplicationContextUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.stubs.PluginAttr;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

/**
 * Created by Administrator on 2017/10/21 0021.
 */
public class FastDFSPluginStub extends BaseFilePluginStub {


    private final static String PROP_KEY_CONNECT_TIMEOUT = "connect_timeout";
    private final static String PROP_KEY_NETWORK_TIMEOUT = "network_timeout";
    private final static String PROP_KEY_CHARSET = "charset";
    private final static String PROP_KEY_TRACKER_HTTP_PORT = "http.tracker_http_port";
    private final static String PROP_KEY_ANTI_STEAL_TOKEN = "http.anti_steal_token";
    private final static String PROP_KEY_SECRET_KEY = "secret_key";
    private final static String PROP_KEY_TRACKER_SERVER = "tracker_server";
    private final static String PROP_KEY_FILE_SERVER = "file_server";

    private static boolean isConfiged = false;
    private static TrackerServer trackerServer = null;


    @Override
    public String getStubName() {
        return "FastDFS";
    }

    @Override
    public String getStubType() {
        return "file";
    }

    @Override
    public int getStubStateValue() {
        if (this.getPlugins() == null) {
            return StubState.NotConfig.ordinal();
        } else if (this.getPlugins().getIs_publish() == Constants.False) {
            return StubState.Pending.ordinal();
        }
        return StubState.Active.ordinal();
    }

    @Override
    public void setup() {

        JSONObject dbAttrJson = new JSONObject();
        if (this.getPlugins() != null && StringUtils.isNotBlank(this.getPlugins().getAttrs())) {
            dbAttrJson = JSONObject.parseObject(this.getPlugins().getAttrs());
        }
        LinkedHashMap<String, PluginAttr> attrsMap = new LinkedHashMap<>();
        attrsMap.put(PROP_KEY_CONNECT_TIMEOUT, new PluginAttr(PROP_KEY_CONNECT_TIMEOUT, PROP_KEY_CONNECT_TIMEOUT, StringUtils.defaultIfBlank(dbAttrJson.getString(PROP_KEY_CONNECT_TIMEOUT), "2"), "请求连接超时时间，单位秒，默认值为2"));
        attrsMap.put(PROP_KEY_NETWORK_TIMEOUT, new PluginAttr(PROP_KEY_NETWORK_TIMEOUT, PROP_KEY_NETWORK_TIMEOUT, StringUtils.defaultIfBlank(dbAttrJson.getString(PROP_KEY_NETWORK_TIMEOUT), "30"), "网络连接超时时间，单位秒，默认值为30"));
        attrsMap.put(PROP_KEY_CHARSET, new PluginAttr(PROP_KEY_CHARSET, PROP_KEY_CHARSET, StringUtils.defaultIfBlank(dbAttrJson.getString(PROP_KEY_CHARSET), "utf-8"), "编码，默认为utf-8"));
        attrsMap.put(PROP_KEY_TRACKER_HTTP_PORT, new PluginAttr(PROP_KEY_TRACKER_HTTP_PORT, PROP_KEY_TRACKER_HTTP_PORT, StringUtils.defaultIfBlank(dbAttrJson.getString(PROP_KEY_TRACKER_HTTP_PORT), "80"), "预留字段，默认为80"));
        attrsMap.put(PROP_KEY_ANTI_STEAL_TOKEN, new PluginAttr(PROP_KEY_ANTI_STEAL_TOKEN, PROP_KEY_ANTI_STEAL_TOKEN, StringUtils.defaultIfBlank(dbAttrJson.getString(PROP_KEY_ANTI_STEAL_TOKEN), "no"), "预留字段，默认为no"));
        attrsMap.put(PROP_KEY_SECRET_KEY, new PluginAttr(PROP_KEY_SECRET_KEY, PROP_KEY_SECRET_KEY, StringUtils.defaultIfBlank(dbAttrJson.getString(PROP_KEY_SECRET_KEY), "xxxxxxxxxx"), "上传用secret_key"));
        attrsMap.put(PROP_KEY_TRACKER_SERVER, new PluginAttr("tracker地址", PROP_KEY_TRACKER_SERVER, StringUtils.defaultIfBlank(dbAttrJson.getString(PROP_KEY_TRACKER_SERVER), "xxx.xxx.xxx.xx:xxx"), "tracker地址，如ip:port"));
        attrsMap.put(PROP_KEY_FILE_SERVER, new PluginAttr("文件访问地址", PROP_KEY_FILE_SERVER, StringUtils.defaultIfBlank(dbAttrJson.getString(PROP_KEY_FILE_SERVER), "http://xxx/"), "文件访问地址，如http://xxx/"));
        this.setAttrMap(attrsMap);


        if (!isConfiged) {
            config();
            isConfiged = true;
        }
    }

    @Override
    public void config() {
        try {
            if (trackerServer != null) {
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            _logger.info("初始化FastDFS配置");
            Properties props = new Properties();
            props.put(ClientGlobal.PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS, getAttrMap().get(PROP_KEY_CONNECT_TIMEOUT).getValue());
            props.put(ClientGlobal.PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS, getAttrMap().get(PROP_KEY_NETWORK_TIMEOUT).getValue());
            props.put(ClientGlobal.PROP_KEY_CHARSET, getAttrMap().get(PROP_KEY_CHARSET).getValue());
            props.put(ClientGlobal.PROP_KEY_HTTP_ANTI_STEAL_TOKEN, getAttrMap().get(PROP_KEY_ANTI_STEAL_TOKEN).getValue());
            props.put(ClientGlobal.PROP_KEY_HTTP_SECRET_KEY, getAttrMap().get(PROP_KEY_SECRET_KEY).getValue());
            props.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, getAttrMap().get(PROP_KEY_TRACKER_SERVER).getValue());
            ClientGlobal.initByProperties(props);

            TrackerClient tracker = new TrackerClient();
            trackerServer = tracker.getConnection();

            FreeMarkerConfig freeMarkerConfig = ApplicationContextUtil.getBean(FreeMarkerConfig.class);
            FreeMarkerConfig.map.put(FreeMarkerConfig.kBaseFileUrl, getAttrMap().get(PROP_KEY_FILE_SERVER).getValue());
            freeMarkerConfig.getConfiguration().setSharedVaribles(FreeMarkerConfig.map);

        } catch (Exception ex) {
            _logger.error("初始化FastDFS配置失败", ex.getLocalizedMessage());
        }
    }

    @Override
    public String getBaseFileUrl() {
        if (this.getAttrMap() != null && this.getAttrMap().containsKey(PROP_KEY_FILE_SERVER)) {
            return this.getAttrMap().get(PROP_KEY_FILE_SERVER).getValue();
        }
        return "";
    }

    @Override
    public String upload(File file) throws IOException {
        String fileId = "";
        try {
            StorageServer storageServer = null;
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            fileId = client.upload_file1(file.getAbsolutePath(), StringUtils.substringAfterLast(file.getName(), "."), null);
        } catch (Exception ex) {
            throw new RuntimeException("上传文件失败: " + ex.getLocalizedMessage());
        } finally {
        }
        return fileId;
    }

    @Override
    public String upload(FileInputStream fileInputStream) {
        return null;
    }
}
