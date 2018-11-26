package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.PermEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PermEntityMapper extends BaseMapper<PermEntity> {
    //  根据父类型ID查询
    List<Map> selectPermTreeList(@Param("parent") String parent);
}