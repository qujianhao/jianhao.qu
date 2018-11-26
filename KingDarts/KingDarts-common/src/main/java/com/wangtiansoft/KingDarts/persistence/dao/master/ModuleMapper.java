package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ModuleMapper extends BaseMapper<Module> {

    List<Map> queryModuleList(Map paramMap);

    //  根据父类型ID查询子节点
    List<Map> queryModuleMapList(@Param("parent") String parent);

    //  根据父类型ID查询子节点
    List<Map> queryModuleMapListByParent(@Param("parentId") String parentId);


}