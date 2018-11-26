package com.wangtiansoft.KingDarts.config;

import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.exception.UnAuthorizedException;
import com.wangtiansoft.KingDarts.constants.Constants;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/3/14 0014.
 * 控制器扩展处理
 */
@ControllerAdvice(annotations = Controller.class)
public class ControllerAdviceConfiguration {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //  参数去掉首位空格
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        if (!(exception instanceof AppRuntimeException)){
            exception.printStackTrace();
        }
        if (request.getHeader("X-Requested-With") != null && request
                .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1) {
            response.setContentType("application/json;charset=UTF-8");
            try {
                String msg = "发生错误，请稍后再试";
                if (exception instanceof AppRuntimeException){
                    msg = exception.getLocalizedMessage();
                }
                PrintWriter writer = response.getWriter();
                writer.write(JSONObject.toJSONString(ApiResult.fail(msg)));
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return new ModelAndView("error/500");
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        exception.printStackTrace();
        if (request.getHeader("X-Requested-With") != null && request
                .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1) {
            response.setContentType("application/json;charset=UTF-8");
            try {
                PrintWriter writer = response.getWriter();
                writer.write(JSONObject.toJSONString(ApiResult.fail(exception.getLocalizedMessage())));
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return new ModelAndView("error/403");
    }


    @ExceptionHandler(value = UnAuthorizedException.class)
    public ModelAndView handleUnAuthorizedException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JSONObject.toJSONString(ApiResult.fail(Constants.kCode_SessionError, "登录过期")));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
