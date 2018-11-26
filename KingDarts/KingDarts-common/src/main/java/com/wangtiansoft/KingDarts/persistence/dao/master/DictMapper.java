package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Dict;

import java.util.List;
import java.util.Map;

public interface DictMapper extends BaseMapper<Dict> {

    List<Map> queryDictList(Map paramMap);

}