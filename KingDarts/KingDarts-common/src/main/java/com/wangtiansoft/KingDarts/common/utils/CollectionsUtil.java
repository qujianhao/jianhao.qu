package com.wangtiansoft.KingDarts.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionsUtil {

	public static String map2StrSort(Map map){
		List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(map.entrySet());
		Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
			//升序排序
			public int compare(Entry<String, String> o1,
					Entry<String, String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}

		});

		StringBuffer sb = new StringBuffer();
		for(Map.Entry<String,String> mapping:list){
			sb.append("&").append(mapping.getKey()).append("=").append(mapping.getValue());
//			System.out.println(mapping.getKey()+":"+mapping.getValue());
		} 
		
		if(sb.length()>0){
			return sb.substring(1);
		}
		return "";
	}
}
