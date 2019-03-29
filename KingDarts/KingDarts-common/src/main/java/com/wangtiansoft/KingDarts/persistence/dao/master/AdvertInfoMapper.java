package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.AdvertInfo;

import java.util.List;
import java.util.Map;

public interface AdvertInfoMapper extends BaseMapper<AdvertInfo> {

    List<Map> queryAdvertInfoList(Map paramMap);
	Page<Map> queryAdvertInfoListByclubId(Map paramMap);
	Page<Map> queryAdvertInfoListNoclub(Map paramMap);
	Page<Map> queryAdvertInfoListByagentId(Map paramMap);
	Page<Map> queryAdvertInfoPageListnocora(Map<String, Object> paramMap);
 
}