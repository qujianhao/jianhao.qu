package com.wangtiansoft.KingDarts.common.utils;

import com.wangtiansoft.KingDarts.common.bean.PageBean;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/10/15 0015.
 */
public class SQLUtil {

    public final static String SQL_OrderSQL = "orderSql";

    public static String transactSQLInjection(String str) {
        return str.replaceAll(".*([';]+|(--)+).*", " ");
    }

    public static String orderBy(String name, String order) {
        if (StringUtils.isNotBlank(name)) {
            if (StringUtils.isBlank(order)) {
                order = "ASC";
            }
            return transactSQLInjection(String.format("ORDER BY %s %s", name, order));
        }
        return "";
    }

    public static String orderByCondition(String name, String order) {
        if (StringUtils.isNotBlank(name)) {
            if (StringUtils.isBlank(order)) {
                order = "ASC";
            }
            return transactSQLInjection(String.format("%s %s", name, order));
        }
        return "";
    }

    public static String orderBy(PageBean pageBean) {
        if (pageBean != null && StringUtils.isNotBlank(pageBean.getSidx())) {
            if (StringUtils.isBlank(pageBean.getSord())) {
                return transactSQLInjection(String.format("ORDER BY %s %s", pageBean.getSidx(), "ASC"));
            }
            return transactSQLInjection(String.format("ORDER BY %s %s", pageBean.getSidx(), pageBean.getSord()));
        }
        return "";
    }

    public static String orderByCondition(PageBean pageBean) {
        if (pageBean != null && StringUtils.isNotBlank(pageBean.getSidx())) {
            if (StringUtils.isBlank(pageBean.getSord())) {
                return transactSQLInjection(String.format("%s %s", pageBean.getSidx(), "ASC"));
            }
            return transactSQLInjection(String.format("%s %s", pageBean.getSidx(), pageBean.getSord()));
        }
        return "";
    }

    /**
     * 移除map中的value空值
     *
     * @param map
     * @return
     */
    public static void removeNullValue(Map map) {
        Set set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object obj = (Object) iterator.next();
            Object value = (Object) map.get(obj);
            if (value == null || StringUtils.isEmpty(value.toString().trim())) {
                iterator.remove();
            }
        }
    }

}
