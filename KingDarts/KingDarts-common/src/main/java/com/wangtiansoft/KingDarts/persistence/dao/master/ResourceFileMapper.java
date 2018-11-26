package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.ResourceFile;

import java.util.List;
import java.util.Map;

public interface ResourceFileMapper extends BaseMapper<ResourceFile> {

    List<Map> queryResourceFileList(Map paramMap);

}