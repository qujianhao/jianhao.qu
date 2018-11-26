package com.wangtiansoft.KingDarts.config.auth;

import com.alibaba.fastjson.JSON;
import com.wangtiansoft.KingDarts.common.auth.ApiTokenResult;
import com.wangtiansoft.KingDarts.common.exception.UnAuthorizedException;
import com.wangtiansoft.KingDarts.config.utils.ApplicationContextUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.extensions.token.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by weitong on 17/3/29.
 */
@Configuration
public class AuthorizationInterceptor implements HandlerInterceptor {

    protected Logger _logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        _logger.info(String.format("[REQUEST_INFO]请求方法[%s],请求路径[%s],请求参数[%s]",request.getMethod(),request.getServletPath(), JSON.toJSONString(request.getParameterMap())));
        //如果不是映射到方法直接通过
        if (! (handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        TokenService tokenService = ApplicationContextUtil.getBean(TokenService.class);
        //从header中得到token
        String xAccessToken = request.getHeader(Constants.kAuth_xAccessToken);
        if (method.getAnnotation(WtAuthorization.class) != null) {
            ApiTokenResult apiTokenResult = tokenService.checkToken(xAccessToken);
            if (apiTokenResult == null)
                throw new UnAuthorizedException();
            request.setAttribute(Constants.kAuth_tokenResult, apiTokenResult);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
