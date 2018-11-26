package com.wangtiansoft.KingDarts.common.utils;

import com.wangtiansoft.KingDarts.constants.Constants;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/7.
 */
public class BeanUtil {

    static Logger _logger = LoggerFactory.getLogger(BeanUtil.class);

    public static <T> T entityInit(T entity) {
        try {
            Class entityClass = entity.getClass();
            invokeMethod(entityClass, loadSetMethodName("is_publish"), Integer.class, entity, Constants.True);
            invokeMethod(entityClass, loadSetMethodName("is_delete"), Integer.class, entity, Constants.False);
            invokeMethod(entityClass, loadSetMethodName("uuid"), String.class, entity, CoreUtil.uuid());
            invokeMethod(entityClass, loadSetMethodName("create_time"), Date.class, entity, new Date());
            invokeMethod(entityClass, loadSetMethodName("update_time"), Date.class, entity, new Date());
        }catch (Exception ex){
            _logger.warn(ex.getLocalizedMessage());
        }
        return entity;
    }

    private static String loadSetMethodName(String filedName){
        return "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
    }

    private static void invokeMethod(Class<?> entityClass, String methodName, Class<?> paramaterType, Object entity, Object param){
        try {
            entityClass.getMethod(methodName, paramaterType).invoke(entity, param);
        } catch (Exception e) {
        }
    }


    private static DozerBeanMapper mapper = new DozerBeanMapper();
    public static <T> T cast(Object source, Class<T> targetClass){
        T result = mapper.map(source, targetClass);
        return result;
    }
    
    public static void copyProperties(Object source,Object target){
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }

}
