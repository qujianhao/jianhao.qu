package com.wangtiansoft.KingDarts.config.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by weitong on 17/4/7.
 */
@Configuration
public class SecurityPermissionEvaluator implements PermissionEvaluator {

    protected Logger _logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean hasPermission(Authentication authentication, Object object, Object permissionObj) {
        boolean result = false;
        if (true)
            return true;
        SecurityPrincipal securityPrincipal = (SecurityPrincipal) authentication.getPrincipal();
        Map<String, String> permMap = securityPrincipal.getpermMap();
        String permissionValue = String.valueOf(permissionObj).toUpperCase();
        if (StringUtils.equalsIgnoreCase(object.toString(), "any")) {
            //  判断权限是否部分匹配
            if (StringUtils.contains(permissionValue, ",")) {
                for (String permission : StringUtils.splitByWholeSeparatorPreserveAllTokens(permissionValue, ",")) {
                    if (isPermissionAllow(permMap, permission)){
                        result = true;
                        break;
                    }
                }
            }else{
                result = isPermissionAllow(permMap, permissionValue);
            }
        }else if(StringUtils.equalsIgnoreCase(object.toString(), "all")){
            //  判断权限是否全部匹配
            boolean isAllAllow = true;
            if (StringUtils.contains(permissionValue, ",")) {
                for (String permission : StringUtils.splitByWholeSeparatorPreserveAllTokens(permissionValue, ",")) {
                    if (!isPermissionAllow(permMap, permission)){
                        isAllAllow = false;
                        break;
                    }
                }
                result = isAllAllow;
            }else{
                result = isPermissionAllow(permMap, permissionValue);
            }
        }else {
            result = isPermissionAllow(permMap, permissionValue);
        }
        return result;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }


    private boolean isPermissionAllow(Map<String, String> permMap, String permission) {
        return permMap.containsKey(permission);
    }


}
