package com.wangtiansoft.KingDarts.common.utils.convertReqBeans;

import com.jfinal.kit.StrKit;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;


public class ConvertBeans {
    public static <T> T getBean(Class<T> beanClass, String beanName,HttpServletRequest request) {
        return (T)injectBean(beanClass, beanName, request, false);
    }

    public static final <T> T injectBean(Class<T> beanClass, String beanName, HttpServletRequest request, boolean skipConvertError) {
        Object bean = createInstance(beanClass);
        String modelNameAndDot = StrKit.notBlank(beanName) ? beanName + "." : null;

        Map<String, String[]> parasMap = request.getParameterMap();
        Method[] methods = beanClass.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("set") == false || methodName.length() <= 3) {	// only setter method
                continue;
            }
            Class<?>[] types = method.getParameterTypes();
            if (types.length != 1) {						// only one parameter
                continue;
            }

            String attrName = StrKit.firstCharToLowerCase(methodName.substring(3));
            String paraName = modelNameAndDot != null ? modelNameAndDot + attrName : attrName;
            if (parasMap.containsKey(paraName)) {
                try {
                    String paraValue = request.getParameter(paraName);
                    Object value = paraValue != null ? TypeConverter.convert(types[0], paraValue) : null;
                    method.invoke(bean, value);
                } catch (Exception e) {
                    if (skipConvertError == false) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return (T)bean;
    }

    private static <T> T createInstance(Class<T> objClass) {
        try {
            return objClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
