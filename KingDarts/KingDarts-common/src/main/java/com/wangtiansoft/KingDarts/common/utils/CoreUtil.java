package com.wangtiansoft.KingDarts.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.dozer.DozerBeanMapper;

import java.util.*;

/**
 * Created by weitong on 17/3/15.
 */
public class CoreUtil {


    /**
     * 字符串数组转int列表
     * @param array
     * @return
     */
    public static List<Integer> toIntegerList(String[] array){
        List<Integer> list = new ArrayList<Integer>();
        if (array != null && array.length > 0){
            for (String str : array){
                list.add(Integer.valueOf(str));
            }
        }
        return list;
    }


    public static <T> Map<String, T> listToMap(List<T> list, String key) {
        Map<String, T> map = new HashedMap();
        for (T object : list) {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(object);
            if (jsonObject.containsKey(key)) {
                map.put(jsonObject.getString(key), object);
            }
        }
        return map;
    }


    /**
     * 拆分列表
     * @param targeList
     * @param size
     * @return
     */
    public static List<List<String>> subList(List<String> targeList, int size) {
        List<List<String>> listArr = new ArrayList<List<String>>();
        //获取被拆分的数组个数
        int arrSize = targeList.size() % size == 0 ? targeList.size() / size : targeList.size() / size + 1;
        for (int i = 0; i < arrSize; i++) {
            List<String> sub = new ArrayList<String>();
            //把指定索引数据放入到list中
            for (int j = i * size; j <= size * (i + 1) - 1; j++) {
                if (j <= targeList.size() - 1) {
                    sub.add(targeList.get(j));
                }
            }
            listArr.add(sub);
        }
        return listArr;
    }


    //  TODO 本地线程对象
    private static DozerBeanMapper dozerMapper = new DozerBeanMapper();

    public synchronized static Page toList(Page sourcePage, Class targetClass) {
        if (sourcePage != null){
            List list = new ArrayList();
            for (Object obj : sourcePage){
                Object target = dozerMapper.map(obj, targetClass);
                list.add(target);
            }
            sourcePage.clear();
            sourcePage.addAll(list);
        }
        return sourcePage;
    }

    public synchronized static PageResult toList(PageResult sourcePage, Class targetClass) {
        PageResult resultPage = new PageResult();
        if (sourcePage != null){
            List list = new ArrayList();
            for (Object obj : sourcePage.getList()){
                Object target = dozerMapper.map(obj, targetClass);
                list.add(target);
            }
            resultPage.getList().clear();
            resultPage.getList().addAll(list);
            resultPage.setPager(sourcePage.getPager());
        }
        return resultPage;
    }

    public synchronized static List toList(List listData, Class targetClass) {
        List list = new ArrayList();
        if (listData != null){
            for (Object obj : listData){
                Object target = dozerMapper.map(obj, targetClass);
                list.add(target);
            }

        }
        return list;
    }

    public synchronized static <T> T toVO(Object sourceObj, Class<T> targetClass) {
        if (sourceObj != null){
            return dozerMapper.map(sourceObj, targetClass);
        }
        return null;
    }


    public static <T> T firstOne(List<T> beanList){
        if (CollectionUtils.isNotEmpty(beanList)){
            return beanList.get(0);
        }
        return null;
    }


    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(new Date().getTime());
        System.out.println(CoreUtil.uuid());
    }

}
