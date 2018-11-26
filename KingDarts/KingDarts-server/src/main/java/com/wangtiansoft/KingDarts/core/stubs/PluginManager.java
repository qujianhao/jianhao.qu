package com.wangtiansoft.KingDarts.core.stubs;

import com.wangtiansoft.KingDarts.config.utils.ApplicationContextUtil;
import com.wangtiansoft.KingDarts.core.extensions.plugins.service.PluginsService;
import com.wangtiansoft.KingDarts.core.stubs.file.*;
import com.wangtiansoft.KingDarts.persistence.entity.Plugins;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Administrator on 2017/10/21 0021.
 */
public class PluginManager {

    private static PluginManager instance;
    private LinkedHashMap<String, BasePluginStub> allPluginStubMap = new LinkedHashMap<>();
    private LinkedHashSet<String> filePluginsKeySet = new LinkedHashSet<>();
    private BaseFilePluginStub filePluginStub = null;

    private PluginManager() {
        init();
    }

    public static PluginManager getInstance() {
        if (instance == null)
            instance = new PluginManager();
        return instance;
    }

    public void init(){
        PluginsService pluginsService = ApplicationContextUtil.getBean(PluginsService.class);
        List<Plugins> pluginsList = (List<Plugins>) pluginsService.findAllByExample(null);

        allPluginStubMap.put(FastDFSPluginStub.class.getSimpleName(), new FastDFSPluginStub());
        allPluginStubMap.put(AliyunOSSPluginStub.class.getSimpleName(), new AliyunOSSPluginStub());
        allPluginStubMap.put(QiniuKODOPluginStub.class.getSimpleName(), new QiniuKODOPluginStub());
        allPluginStubMap.put(TFSPluginStub.class.getSimpleName(), new TFSPluginStub());

        for (Plugins plugins : pluginsList){
            if (allPluginStubMap.containsKey(plugins.getPlugin_key())){
                allPluginStubMap.get(plugins.getPlugin_key()).setup(plugins);
            }
        }

        filePluginStub = null;
        for (BasePluginStub pluginStub : allPluginStubMap.values()){
            if (StringUtils.equalsIgnoreCase(pluginStub.getStubType(), "file")){
                filePluginsKeySet.add(pluginStub.getClass().getSimpleName());
                if (pluginStub.getStubStateValue() == BasePluginStub.StubState.Active.ordinal()){
                    filePluginStub = (BaseFilePluginStub) pluginStub;
                }
            }
        }
    }

    public void refresh(){
        init();
        for (BasePluginStub stub : allPluginStubMap.values()){
            stub.config();
        }
    }

    public LinkedHashMap<String, BasePluginStub> getAllPluginStubMap() {
        return allPluginStubMap;
    }

    public void setAllPluginStubMap(LinkedHashMap<String, BasePluginStub> allPluginStubMap) {
        this.allPluginStubMap = allPluginStubMap;
    }

    public LinkedHashSet<String> getFilePluginsKeySet() {
        return filePluginsKeySet;
    }

    public void setFilePluginsKeySet(LinkedHashSet<String> filePluginsKeySet) {
        this.filePluginsKeySet = filePluginsKeySet;
    }


    public BaseFilePluginStub getFilePluginStub() {
        return filePluginStub;
    }

    public void setFilePluginStub(BaseFilePluginStub filePluginStub) {
        this.filePluginStub = filePluginStub;
    }
}
