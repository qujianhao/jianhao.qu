package com.wangtiansoft.KingDarts.config.auth;

import com.wangtiansoft.KingDarts.common.auth.ApiTokenResult;
import com.wangtiansoft.KingDarts.constants.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by weitong on 17/3/29.
 */
@Configuration
public class AuthUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是User并且有AuthUser注解则支持
        if (parameter.getParameterType().isAssignableFrom(ApiTokenResult.class) && parameter.hasParameterAnnotation(AuthToken.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return nativeWebRequest.getAttribute(Constants.kAuth_tokenResult, RequestAttributes.SCOPE_REQUEST);
    }
}
