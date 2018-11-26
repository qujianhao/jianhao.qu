package com.wangtiansoft.KingDarts.core.support.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.bean.JQGirdPageResult;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.common.utils.FileRenderUtil;
import com.wangtiansoft.KingDarts.common.utils.convertReqBeans.ConvertBeans;
import com.wangtiansoft.KingDarts.config.security.SecurityPrincipal;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.stubs.PluginManager;
import com.wangtiansoft.KingDarts.core.stubs.file.BaseFilePluginStub;
import com.wangtiansoft.KingDarts.core.support.bean.OnlineChannel;
import com.wangtiansoft.KingDarts.modules.api.token.Token;
import com.wangtiansoft.KingDarts.modules.api.token.TokenManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public class BaseController {

    protected Logger _logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected TokenManager tokenManager;
    @Autowired
	private RedisTemplate redisTemplate;
    
    private static String AUTHORIZATION = "x-access-token";
    private static String AUTHEQUNO = "equno";

    protected ApiResult buildAjaxResponse(IWebResponseHandler invokeInterface) {
        try {
            Object data = invokeInterface.execute();
            return ApiResult.success(data);
        } catch (AppRuntimeException e) {
            e.printStackTrace();
            return ApiResult.fail(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.fail("系统异常，请联系管理员!");
        }
    }
    
    protected ApiResult buildMobileAuthAjaxResponse(IWebAuthResponseHandler invokeInterface){
		try{
			String authorization = request.getHeader(AUTHORIZATION);
			Token model = tokenManager.getToken(authorization);
	        if(!tokenManager.checkToken(model)) {
	        	return ApiResult.fail(Constants.kCode_SessionError, "登录超时！");
	        }
			Object data = invokeInterface.execute(model.getUserId());
			if(data != null){
    			if(data.getClass().getName().contains("JSONObject")){
    				JSONObject json = (JSONObject)data;
    				if(json.get("msg")!=null){
    					String msg = json.get("msg").toString();
    					json.remove("msg");
    					return ApiResult.success(data,msg);
    				}
    			}else if(data.getClass().getName().contains("HashMap")){
    				Map m = (Map)data;
    				if(m.get("msg")!=null){
    					String msg = m.get("msg").toString();
    					m.remove("msg");
    					return ApiResult.success(data,msg);
    				}
    			}
			}
			
			return ApiResult.success(data);
		}catch (AppRuntimeException e){
			e.printStackTrace();
			return ApiResult.fail(e.getMsg());
		}catch (Exception e){
			e.printStackTrace();
			return ApiResult.fail("系统异常，请联系管理员!");
		}
	}
    protected ApiResult buildEquAuthAjaxResponse(IWebAuthResponseHandler invokeInterface){
    	try{
    		String authorization = request.getHeader(AUTHORIZATION);
    		String equno = request.getHeader(AUTHEQUNO);
    		if(StringUtils.isEmpty(authorization)||StringUtils.isEmpty(equno)){
    			return ApiResult.fail("参数错误!");
    		}
    		Object obj = redisTemplate.opsForHash().get(Constants.online_channel, equno);
    		if(obj==null){
    			return ApiResult.fail("登录异常!");
    		}
    		OnlineChannel onlineChannel = (OnlineChannel)obj;
    		if(!authorization.equals(onlineChannel.getToken())){
    			return ApiResult.fail("登录异常!");
    		}
    		Object data = invokeInterface.execute(equno);
    		if(data != null){
    			if(data.getClass().getName().contains("JSONObject")){
    				JSONObject json = (JSONObject)data;
    				if(json.get("msg")!=null){
    					String msg = json.get("msg").toString();
    					json.remove("msg");
    					return ApiResult.success(data,msg);
    				}
    			}else if(data.getClass().getName().contains("HashMap")){
    				Map m = (Map)data;
    				if(m.get("msg")!=null){
    					String msg = m.get("msg").toString();
    					m.remove("msg");
    					return ApiResult.success(data,msg);
    				}
    			}
			}
    		return ApiResult.success(data);
    	}catch (AppRuntimeException e){
    		e.printStackTrace();
    		return ApiResult.fail(e.getMsg());
    	}catch (Exception e){
    		e.printStackTrace();
    		return ApiResult.fail("系统异常，请联系管理员!");
    	}
    }

    protected interface IWebResponseHandler {
        Object execute() throws Exception;
    }
    protected interface IWebAuthResponseHandler {
    	Object execute(String str) throws Exception;
    }

    public String getParaValueCheck(String name, String msg) {
        String value = this.request.getParameter(name);
        if (StringUtils.isBlank(value))
            throw new AppRuntimeException(msg);
        return value;
    }
    public String getParaValue(String name) {
        return this.request.getParameter(name);
    }

    public Integer getParaIntegerValue(String name) {
        return NumberUtils.toInt(this.request.getParameter(name));
    }

    public String[] getParaValues(String name) {
        return this.request.getParameterValues(name);
    }

    public List<Map<String, String>> getParaMapValues(String ... names) {
        List<Map<String, String>> paramMapList = new ArrayList<>();
        if (names != null && names.length > 0){
            String[] values = request.getParameterValues(names[0]);
            if (values == null){
                return paramMapList;
            }
            for (String value : values){
                Map<String, String> map = new HashMap<>();
                map.put(names[0], value);
                paramMapList.add(map);
            }
            if (names.length > 1){
                for (int i = 1; i < names.length; i++) {
                    values = request.getParameterValues(names[i]);
                    int index = 0;
                    for (String value : values){
                        Map<String, String> map = paramMapList.get(index);
                        map.put(names[i], value);
                        index ++;
                    }
                }
            }
        }
        return paramMapList;
    }

    public JSON getRequestJSON() {
        return (JSON) JSON.parse(request.getParameter("jsonObject"));
    }

    public String getParaValue(String name, String defaultValue) {
        String result = this.request.getParameter(name);
        return result != null && !"".equals(result) ? result : defaultValue;
    }




    public <T> JQGirdPageResult makePageResult(Page page, Class<T> tClass) {
        List<T> resultList = new ArrayList<>();
        for (Object obj : page.getResult()){
            resultList.add(BeanUtil.cast(obj, tClass));
        }
        page.getResult().clear();
        page.getResult().addAll(resultList);
        return new JQGirdPageResult(page);
    }

    public <T> T makeResult(Object source, Class<T> tClass) {
        return BeanUtil.cast(source, tClass);
    }

    protected ResponseEntity<org.springframework.core.io.Resource> renderFile(String fileName, String fileUri, Long fileLength){
        org.springframework.core.io.Resource resource = null;
        InputStream input = null;
        try {
            BaseFilePluginStub filePluginStub = PluginManager.getInstance().getFilePluginStub();
            String fileUrl = filePluginStub.getBaseFileUrl() + fileUri;
            input = new URL(fileUrl).openStream();
            resource = new InputStreamResource(input);
        } catch (Exception e) {
            throw new AppRuntimeException("下载失败");
        } finally {

        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept-Ranges", "bytes");
        httpHeaders.set("Content-disposition", "attachment; " + FileRenderUtil.encodeFileName(this.request, fileName));
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.parseMediaType("application/octet-stream"));
        if (fileLength != null && fileLength.longValue() > 0)
            bodyBuilder = bodyBuilder.contentLength(fileLength);
        return bodyBuilder.body(resource);
    }

    /**
     * 获取前端传来的数组对象并响应成Bean列表
     */
    public <T> List<T> getBeans(Class<T> beanClass, String beanName) {
        List<String> indexes = getIndexes(beanName);
        List<T> list = new ArrayList<T>();
        for (String index : indexes) {
            T b = ConvertBeans.getBean(beanClass, beanName + "[" + index + "]",request);
            if (b != null) {
                list.add(b);
            }
        }
        return list;
    }


    /**
     * 提取model对象数组的标号
     */
    private List<String> getIndexes(String modelName) {
        // 提取标号
        List<String> list = new ArrayList<String>();
        String modelNameAndLeft = modelName + "[";
        Map<String, String[]> parasMap = request.getParameterMap();
        for (Map.Entry<String, String[]> e : parasMap.entrySet()) {
            String paraKey = e.getKey();
            if (paraKey.startsWith(modelNameAndLeft)) {
                String subKey = paraKey.substring(0, modelNameAndLeft.length() + 3);
                String no = paraKey.substring(paraKey.lastIndexOf("[") + 1, subKey.lastIndexOf("]"));
                //String no = paraKey.substring(paraKey.lastIndexOf("[") + 1, paraKey.lastIndexOf("]"));

                if (!list.contains(no)) {
                    list.add(no);
                }
            }
        }
        return list;
    }

}
