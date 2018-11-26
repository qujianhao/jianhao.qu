package com.wangtiansoft.KingDarts.config.freemarker;

import com.wangtiansoft.KingDarts.config.freemarker.tags.AuthTag;
import com.wangtiansoft.KingDarts.core.stubs.PluginManager;
import com.wangtiansoft.KingDarts.core.stubs.file.BaseFilePluginStub;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weitong on 17/4/5.
 */
@Configuration
public class FreeMarkerConfig {

    public final static String kBaseFileUrl = "baseFileUrl";

    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private AuthTag authTag;

    public static Map<String, Object> map;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        map = new HashMap<String, Object>();
        map.put("wtAuth", authTag);
        map.put(kBaseFileUrl, "");
        configuration.setSharedVaribles(map);
    }

    public freemarker.template.Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(freemarker.template.Configuration configuration) {
        this.configuration = configuration;
    }
}
