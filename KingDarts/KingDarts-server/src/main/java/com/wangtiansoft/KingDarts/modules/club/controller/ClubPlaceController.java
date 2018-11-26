package com.wangtiansoft.KingDarts.modules.club.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.club.service.ClubPlaceService;
import com.wangtiansoft.KingDarts.persistence.entity.ClubPlace;

@Controller
@RequestMapping("/a/clubplace")
public class ClubPlaceController extends BaseController  {
	
	@Resource
	private ClubPlaceService clubPlaceService;
	
	//  列表
    @PreAuthorize("hasPermission('','_CLUBPLACE:VIEW')")
    @RequestMapping("/clubPlace_Form")
    public String clubPlace_Form(@RequestParam Map<String, Object> paramMap) {
    	try {
			if(paramMap.containsKey("details")){
				paramMap.put("details", URLEncoder.encode(paramMap.get("details").toString(),"UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        request.setAttribute("entity", paramMap);
        return "/a/club/clubPlaceForm";
    }
    
    /***
	 * 打开百度地图
	 * @param clubPlace
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
    @RequestMapping("/showMap")
    public String showMap(@RequestParam Map<String, Object> paramMap,ClubPlace clubPlace) throws UnsupportedEncodingException {
		if (clubPlace.getProvince() != null) {
			clubPlace.setProvince(URLDecoder.decode(clubPlace.getProvince(), "utf-8"));
		}
		if (clubPlace.getCity() != null) {
			clubPlace.setCity(URLDecoder.decode(clubPlace.getCity(), "utf-8"));
		}
		if (clubPlace.getAreas() != null) {
			clubPlace.setAreas(URLDecoder.decode(clubPlace.getAreas(), "utf-8"));
		}
		if (clubPlace.getAddress() != null) {
			clubPlace.setAddress(URLDecoder.decode(clubPlace.getAddress(), "utf-8"));
		}
		return "a/club/area";
    }
}
