package com.wangtiansoft.KingDarts.modules.resource.controller;

import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.modules.resource.service.ResourceService;
import com.wangtiansoft.KingDarts.results.core.AppVersionResult;
import com.wangtiansoft.KingDarts.results.mapper.ResourceEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by apple on 2018/7/3.
 */
@Api(value = "资源相关", description = "ResourceController")
@RestController
@RequestMapping("/api/resource")
public class ResourceController extends BaseController {
    @Resource
    private ResourceService resourceService;

    @ApiOperation(value = "获取资源", response = ResourceEntity.class)
    @GetMapping("/getResource")
    public @ResponseBody
    ApiResult getResource() {
        return resourceService.getResources();
    }

    @ApiOperation(value = "获取版本更新", response = AppVersionResult.class)
    @GetMapping("/getAppVersion")
    public @ResponseBody
    ApiResult getAppVersion() {
        return resourceService.getAppVersion();
    }
}
