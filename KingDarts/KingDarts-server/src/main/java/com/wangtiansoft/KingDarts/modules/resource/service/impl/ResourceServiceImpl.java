package com.wangtiansoft.KingDarts.modules.resource.service.impl;

import com.wangtiansoft.KingDarts.common.bean.ApiResult;
import com.wangtiansoft.KingDarts.common.utils.CoreUtil;
import com.wangtiansoft.KingDarts.config.netty.constants.Constants;
import com.wangtiansoft.KingDarts.modules.resource.service.ResourceService;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.dao.master.AppVersionMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.ResourceMapper;
import com.wangtiansoft.KingDarts.persistence.entity.AppVersion;
import com.wangtiansoft.KingDarts.persistence.entity.Resource;
import com.wangtiansoft.KingDarts.results.core.AppVersionRes;
import com.wangtiansoft.KingDarts.results.core.AppVersionResult;
import com.wangtiansoft.KingDarts.results.mapper.ResourceEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2018/7/3.
 */
@Service("resourceServiceImpl")
public class ResourceServiceImpl implements ResourceService {
    @javax.annotation.Resource
    private ResourceMapper resourceMapper;
    @javax.annotation.Resource
    private AppVersionMapper appVersionMapper;

    @Override
    public ApiResult getResources() {
        List<ResourceEntity> entityList = new ArrayList<>();
        BaseExample baseExample = new BaseExample(Resource.class);
        baseExample.createCriteria()
                .andEqualTo("is_publish", Constants.True)
                .andEqualTo("is_delete", Constants.False);
        List<Resource> resourceList = resourceMapper.selectByExample(baseExample);
        if (resourceList != null) {
            for (Resource resource : resourceList) {
                ResourceEntity resourceEntity = new ResourceEntity(resource);
                entityList.add(resourceEntity);
            }
        }
        return ApiResult.success(entityList);
    }

    /**
     * 获取App版本，数据库只能有一个是发布状态
     * @return
     */
    @Override
    public ApiResult getAppVersion() {
        BaseExample baseExample = new BaseExample(AppVersion.class);
        baseExample.createCriteria()
                .andEqualTo("is_publish", Constants.True);
        AppVersion appVersion = CoreUtil.firstOne(appVersionMapper.selectByExample(baseExample));
        if (appVersion == null){
            AppVersion version = new AppVersion();
            version.setApp_version(0);
            return ApiResult.success(new AppVersionRes(version));
        }
        AppVersionRes versionResult = new AppVersionRes(appVersion);
        return ApiResult.success(versionResult);
    }
}
