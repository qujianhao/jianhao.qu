package com.wangtiansoft.KingDarts.config.freemarker.tags;

import com.wangtiansoft.KingDarts.config.security.SecurityPrincipal;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/5 0005.
 * freemarker授权验证用标签
 */
@Component
public class AuthTag implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map map, TemplateModel[] templateModels, TemplateDirectiveBody body) throws TemplateException, IOException {
        String roleValue = map.get("hasRole") == null? "" : map.get("hasRole").toString();
        String permValue = map.get("hasPrem") == null? "" : map.get("hasPrem").toString();
        boolean isAuth = true;
        SecurityPrincipal securityPrincipal = (SecurityPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (securityPrincipal != null && StringUtils.isNotEmpty(roleValue)){ //  判断权限
            isAuth = securityPrincipal.hashRole(roleValue);
        }
        if (securityPrincipal != null && StringUtils.isNotEmpty(permValue)){ //  判断角色
            isAuth = securityPrincipal.hashPerm(permValue);
        }
        isAuth = true;
        if (body != null && isAuth) {
            body.render(env.getOut());
        }
    }
}

