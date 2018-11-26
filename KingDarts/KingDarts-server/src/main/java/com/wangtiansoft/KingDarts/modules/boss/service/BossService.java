package com.wangtiansoft.KingDarts.modules.boss.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.Boss;

/**
* Created by wt-templete-helper.
 */
public interface BossService extends IBaseService<Boss, Integer> {

    // 分页查询Boss
    Page<Map> queryBossPageList(Map paramMap, PageBean pageBean);
    
    //全部数据取消应用
    void  updateBossIsNotUse();
    
    //获取应用的的boss信息
    Boss getUseBoss();
}

