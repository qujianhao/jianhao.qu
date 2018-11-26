package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.OrgEntity;
import com.wangtiansoft.KingDarts.results.core.OrgResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrgEntityMapper extends BaseMapper<OrgEntity> {
    //  通过parent_id查询相关机构列表
    List<OrgResult> queryOrgReulstListByParentId(@Param("parent_id") String parent_id);

    //  通过parent_id查询机构树
    List<Map> queryOrgMapListByParentId(@Param("parent_id") String parent_id);
}