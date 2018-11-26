package com.wangtiansoft.KingDarts.core.extensions.plugins.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.utils.ExcelRenderUtil;
import com.wangtiansoft.KingDarts.common.utils.RandomUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.extensions.plugins.service.PluginsService;
import com.wangtiansoft.KingDarts.core.stubs.BasePluginStub;
import com.wangtiansoft.KingDarts.core.stubs.PluginManager;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.results.core.PluginsResult;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.entity.Plugins;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wt-templete-helper.
 */
@Controller
@RequestMapping("/a/plugins")
public class PluginsController extends BaseController {

    @Resource
    private PluginsService pluginsService;

    //  列表
    @PreAuthorize("hasPermission('','PLUGINS:VIEW')")
    @RequestMapping("/plugins_list")
    public String plugins_list(@RequestParam Map<String, Object> paramMap) {
        request.setAttribute("filePluginsKeySet", PluginManager.getInstance().getFilePluginsKeySet());
        request.setAttribute("filePluginStub", PluginManager.getInstance().getFilePluginStub());
        request.setAttribute("allPluginStubMap", PluginManager.getInstance().getAllPluginStubMap());
        return "/a/plugins/plugins_list";
    }

    //  详情
    @PreAuthorize("hasPermission('','PLUGINS:VIEW')")
    @GetMapping("/plugins_view")
    public String plugins_view() {
        String id = getParaValue("id");
        Plugins entity = pluginsService.findById(Integer.valueOf(id));
        request.setAttribute("entity", entity);
        return "/a/plugins/plugins_view";
    }

    //  编辑页面
    @PreAuthorize("hasPermission('','PLUGINS:EDIT')")
    @GetMapping("/plugins_edit")
    public String plugins_edit() {
        String mapKey = getParaValueCheck("mapKey", "缺少mapKey参数");
        String mapType = getParaValueCheck("mapType", "缺少mapType参数");
        if (StringUtils.equalsIgnoreCase(mapType, "file")) {
            LinkedHashMap<String, BasePluginStub> map = PluginManager.getInstance().getAllPluginStubMap();
            BasePluginStub pluginStub = map.get(mapKey);
            request.setAttribute("entity", pluginStub);
        }
        request.setAttribute("mapKey", mapKey);
        request.setAttribute("mapType", mapType);
        return "/a/plugins/plugins_edit";
    }

    //  编辑保存
    @PreAuthorize("hasPermission('','PLUGINS:EDIT')")
    @PostMapping("/plugins_edit")
    public
    @ResponseBody
    ApiResult plugins_edit(@RequestParam Map<String, Object> paramMap) {
        String mapKey = getParaValueCheck("mapKey", "缺少mapKey参数");
        BasePluginStub pluginStub = PluginManager.getInstance().getAllPluginStubMap().get(mapKey);
        if (pluginStub == null)
            return ApiResult.fail("插件不存在");

        JSONObject jsonObject = new JSONObject();
        for (String key : request.getParameterMap().keySet()) {
            jsonObject.put(key, getParaValue(key));
        }

        BaseExample example = new BaseExample(Plugins.class);
        example.createCriteria().andEqualTo("plugin_key", mapKey);
        Plugins plugins = pluginsService.findOneByExample(example);
        if (plugins == null) {
            plugins = new Plugins();
            plugins.setPlugin_key(mapKey);
            plugins.setIs_delete(Constants.False);
            plugins.setIs_publish(Constants.False);
            plugins.setOrder_num(0);
            plugins.setCreate_time(new Date());
            pluginsService.save(plugins);
        }
        plugins.setUpdate_time(new Date());
        plugins.setAttrs(jsonObject.toJSONString());
        pluginsService.updateByIdSelective(plugins);
        PluginManager.getInstance().refresh();
        return ApiResult.success("");
    }

    //  新建页面
    @PreAuthorize("hasPermission('','PLUGINS:ADD')")
    @GetMapping("/plugins_add")
    public String plugins_add() {
        return "/a/plugins/plugins_add";
    }

    //  新建保存
    @PreAuthorize("hasPermission('','PLUGINS:ADD')")
    @PostMapping("/plugins_add")
    public
    @ResponseBody
    ApiResult plugins_add(@ModelAttribute("entity") Plugins entity) {
        pluginsService.updateByIdSelective(entity);
        PluginsResult result = makeResult(entity, PluginsResult.class);
        return ApiResult.success(result);
    }

    //  修改发布状态
    @PreAuthorize("hasPermission('','PLUGINS:EDIT')")
    @PostMapping("/plugins_state")
    public
    @ResponseBody
    ApiResult plugins_state() {
        String mapKey = getParaValueCheck("mapKey", "缺少mapKey参数");
        String state = getParaValue("state");
        BasePluginStub pluginStub = PluginManager.getInstance().getAllPluginStubMap().get(mapKey);
        if (pluginStub == null)
            return ApiResult.fail("插件不存在");
        BaseExample example = new BaseExample(Plugins.class);
        example.createCriteria().andEqualTo("plugin_key", mapKey);
        Plugins plugins = pluginsService.findOneByExample(example);
        if (plugins == null) {
            return ApiResult.fail("请先配置插件");
        }
        plugins.setIs_publish(NumberUtils.toInt(state));
        plugins.setUpdate_time(new Date());
        pluginsService.updateByIdSelective(plugins);
        PluginManager.getInstance().refresh();
        return ApiResult.success("");
    }

    //  删除
    @PreAuthorize("hasPermission('','PLUGINS:DELETE')")
    @PostMapping("/plugins_delete")
    public
    @ResponseBody
    ApiResult plugins_delete() {
        String id = getParaValue("id");
        Plugins entity = pluginsService.findById(Integer.valueOf(id));
        entity.setIs_delete(Constants.True);
        pluginsService.updateByIdSelective(entity);
        return ApiResult.success();
    }


    //  示例页面
    @PreAuthorize("hasPermission('','PLUGINS:DELETE')")
    @GetMapping("/plugins_demo")
    public String plugins_demo() {
        String mapType = getParaValueCheck("mapType", "缺少mapType参数");
        String mapKey = getParaValue("mapKey");
        if (StringUtils.equalsIgnoreCase("file", mapType)) {
            return "/a/plugins/plugins_demo_wtuploader";    //  上传组件
        } else if (StringUtils.equalsIgnoreCase("code", mapType)) {
            if (StringUtils.equalsIgnoreCase("detail", mapKey))
                return "/a/plugins/plugins_demo_detail";    //  详情页面
            else if (StringUtils.equalsIgnoreCase("wtselect", mapKey))
                return "/a/plugins/plugins_demo_wtselect";  //  下拉列表
            else if (StringUtils.equalsIgnoreCase("wtchecktable", mapKey))
                return "/a/plugins/plugins_demo_wtchecktable";  //  选择表格
            else if (StringUtils.equalsIgnoreCase("download", mapKey))
                return "/a/plugins/plugins_demo_download";  //  下载文件
            else if (StringUtils.equalsIgnoreCase("export", mapKey))
                return "/a/plugins/plugins_demo_export";  //  导出文件
            else if (StringUtils.equalsIgnoreCase("workflow", mapKey))
                return "/a/plugins/plugins_demo_workflow";  //  工作流
            else if (StringUtils.equalsIgnoreCase("wtorgpicker", mapKey))
                return "/a/plugins/plugins_demo_wtorgpicker";  //  机构选择
        }
        return "/a/plugins/plugins_demo";
    }

    @PreAuthorize("hasPermission('','PLUGINS:VIEW')")
    @PostMapping("/plugins_demo_wtselect")
    public
    @ResponseBody
    ApiResult plugins_demo_wtselect() {
        String type = getParaValue("type");
        String id = getParaValue("id");
        List<Map> list = new ArrayList<>();
        if (StringUtils.isBlank(id) && !StringUtils.equals(type, "0"))
            return ApiResult.success(list);
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", type + "_" + RandomStringUtils.randomAlphabetic(2));
            map.put("value", i);
            list.add(map);
        }
        return ApiResult.success(list);
    }

    @PreAuthorize("hasPermission('','PLUGINS:VIEW')")
    @PostMapping("/plugins_demo_wtchecktable")
    public
    @ResponseBody
    ApiResult plugins_demo_wtchecktable() {
        String type = getParaValue("type");
        JSONObject data = new JSONObject();
        JSONArray colArray = JSONArray.parseArray("[{\"name\": \"机油+机滤\", \"key\": 1},{\"name\": \"空气滤芯\", \"key\": 2},{\"name\": \"空调滤芯\", \"key\": 3},{\"name\": \"汽油滤芯\", \"key\": 4},{\"name\": \"制动油\", \"key\": 5},{\"name\": \"变速箱\", \"key\": 6},{\"name\": \"洗油路\", \"key\": 7},{\"name\": \"火花塞\", \"key\": 8},{\"name\": \"皮带\", \"key\": 9},{\"name\": \"四轮定位\", \"key\": 10},{\"name\": \"变速油箱\", \"key\": 11},{\"name\": \"助力油\", \"key\": 12}]");
        JSONArray rowArray = JSONArray.parseArray("[{\"name\":\"0.5\",\"value\":1},{\"name\":\"1\",\"value\":2},{\"name\":\"1.5\",\"value\":4},{\"name\":\"2\",\"value\":8},{\"name\":\"2.5\",\"value\":16},{\"name\":\"3\",\"value\":32},{\"name\":\"3.5\",\"value\":64},{\"name\":\"4\",\"value\":128},{\"name\":\"4.5\",\"value\":256},{\"name\":\"5\",\"value\":512},{\"name\":\"5.5\",\"value\":1024},{\"name\":\"6\",\"value\":2048}]");
        data.put("title", "时间/年");
        data.put("colList", colArray);
        data.put("rowList", rowArray);
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("1", 1);
        bodyJson.put("2", 3);
        bodyJson.put("3", RandomUtil.getRandom(5000));
        bodyJson.put("4", 130);
        bodyJson.put("5", RandomUtil.getRandom(5000));
        bodyJson.put("6", RandomUtil.getRandom(5000));
        bodyJson.put("7", RandomUtil.getRandom(5000));
        bodyJson.put("8", RandomUtil.getRandom(5000));
        bodyJson.put("9", RandomUtil.getRandom(5000));
        bodyJson.put("10", RandomUtil.getRandom(5000));
        bodyJson.put("11", RandomUtil.getRandom(5000));
        bodyJson.put("12", RandomUtil.getRandom(5000));
        data.put("tableData", bodyJson);
        return ApiResult.success(data);
    }

    @RequestMapping("/plugins_demo_download_file")
    public ResponseEntity<org.springframework.core.io.Resource> plugins_demo_download_file() {
        String fileName = "测试文件.png";
        String fileUri = "group1/M00/00/5C/eSqQlFnrSryAHekJAACZhjgQjtc966.png";
        Long fileLength = null; //  fileService.findFileLengthByFileUrl(fileUri);
        return renderFile(fileName, fileUri, fileLength);
    }

    @RequestMapping("/plugins_demo_download_excel")
    public void plugins_demo_download_excel() {

        List<Map> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map map = new HashMap();
            map.put("username", "测试人" + (i + 1));
            map.put("phone", "186" + RandomUtils.nextInt(1000, 9999) + RandomUtils.nextInt(1000, 9999));
            map.put("email", "test" + (i + 1) + "@test.com");
            map.put("balance", RandomUtils.nextInt(0, 1000));
            map.put("follow_time", new Date().toString());
            list.add(map);
        }
        String[] header = {"用户名称", "电话", "邮箱", "余额", "关注时间"};
        String[] columns = {"username", "phone", "email", "balance", "follow_time"};
        ExcelRenderUtil renderUtil = ExcelRenderUtil.me(request, response, list).fileName("测试列表.xls").sheetName("信息表").headers(header).columns(columns);
        renderUtil.render();
    }


}



