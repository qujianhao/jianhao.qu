package com.wangtiansoft.KingDarts.modules.boss.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.BossEntity;

/**
* Created by wt-templete-helper.
 */
public interface BossEntityService extends IBaseService<BossEntity, String> {

    // 分页查询BossEntity
    Page<Map> queryBossEntityPageList(Map paramMap, PageBean pageBean);
    
    Map queryBossEntityInfo(Map paramMap);

	Map queryBossEntityInfo();

	void lossLife(String bossId, Integer loss, String uuid);
	
	//查询boss击杀贡献排汗版
	Page<Map> queryBossScoreRankPageList(Map paramMap, PageBean pageBean);
}

