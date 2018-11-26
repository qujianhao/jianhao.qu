package com.wangtiansoft.KingDarts.persistence.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.AppVersion;

public interface AppVersionMapper extends BaseMapper<AppVersion> {

	List<Map> queryAppVersionList(Map paramMap);
	
	@Select("select max(app_version) from darts_app_version")
	Integer getMaxVersion();
}