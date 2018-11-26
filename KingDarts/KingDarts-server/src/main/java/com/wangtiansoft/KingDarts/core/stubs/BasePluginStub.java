package com.wangtiansoft.KingDarts.core.stubs;

import com.wangtiansoft.KingDarts.persistence.entity.Plugins;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/10/21 0021.
 */
public abstract class BasePluginStub {

    protected Logger _logger = LoggerFactory.getLogger(this.getClass());

    public enum StubState {
        NotConfig, Pending, Active
    }

    private String stubName;    //  插件名称
    private String stubType;    //  插件类型
    private StubState stubState;   //  插件状态
    private Plugins plugins;    //  插件详情
    private LinkedHashMap<String, PluginAttr> attrMap = new LinkedHashMap<>();  //  属性详情

    public BasePluginStub() {
    }

    public void setup(Plugins plugins) {
        this.plugins = plugins;
        setup();
    }

    public abstract void setup();

    public abstract void config();

    public abstract String getStubName();

    public abstract String getStubType();

    public abstract int getStubStateValue();


    public Plugins getPlugins() {
        return plugins;
    }

    public void setPlugins(Plugins plugins) {
        this.plugins = plugins;
    }

    public LinkedHashMap<String, PluginAttr> getAttrMap() {
        return attrMap;
    }

    public void setAttrMap(LinkedHashMap<String, PluginAttr> attrMap) {
        this.attrMap = attrMap;
    }
}
