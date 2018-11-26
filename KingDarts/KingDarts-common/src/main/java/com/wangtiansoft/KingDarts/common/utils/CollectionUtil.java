package com.wangtiansoft.KingDarts.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

public class CollectionUtil {

	/**
	 * map排序
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {  
		Map<String, String> sortedMap = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String obj1, String obj2) {
						return obj1.compareTo(obj2);
					}
				});
		sortedMap.putAll(map);  
		return sortedMap;
	}  

	/**
	 * 拆分集合
	 * @param <T>
	 * @param targe  要拆分的集合
	 * @param size	每个集合的元素个数
	 * @return  返回拆分后的各个集合
	 */
	public static  <T> List<List<T>> split(List<T> targe,int size){
		if(targe==null ||size<1)
			return  null ;

		List<List<T>> listArr = new ArrayList<List<T>>();  
		//获取被拆分的数组个数  
		int arrSize = targe.size()%size==0?targe.size()/size:targe.size()/size+1;  
		for(int i=0;i<arrSize;i++) {  
			List<T>  sub = new ArrayList<T>();  
			//把指定索引数据放入到list中  
			for(int j=i*size;j<=size*(i+1)-1;j++) {  
				if(j<=targe.size()-1) {  
					sub.add(targe.get(j));  
				}  
			}  
			listArr.add(sub);  
		}  
		return listArr;

	}

	private String[] removeArrayEmptyTextBackNewArray(String[] strArray) {
		List<String> strList= Arrays.asList(strArray);
		List<String> strListNew=new ArrayList<>();
		for (int i = 0; i <strList.size(); i++) {
			if (strList.get(i)!=null&&!strList.get(i).equals("")){
				strListNew.add(strList.get(i));
			}
		}
		String[] strNewArray = strListNew.toArray(new String[strListNew.size()]);
		return   strNewArray;
	}

	/**
	 * map转对象
	 * @param map
	 * @param beanClass
	 * @return
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {    
		if (map == null)  
			return null;  

		Object obj = null;
		try {
			obj = beanClass.newInstance();  

			BeanUtils.populate(obj, map);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  

		return obj;  
	}    

	/**
	 * 对象转map
	 * @param obj
	 * @return
	 */
	public static Map<?, ?> objectToMap(Object obj) {  
		if(obj == null)  
			return null;   
		return new BeanMap(obj);  
	}    

}  
