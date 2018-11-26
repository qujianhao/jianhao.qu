package com.wangtiansoft.KingDarts.modules.club.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.ClubMonthRank;

/**
* Created by wt-templete-helper.
 */
public interface ClubMonthRankService extends IBaseService<ClubMonthRank, Integer> {

    // 分页查询ClubMothRank
    Page<Map> queryClubMonthRankPageList(Map paramMap, PageBean pageBean);
    
    Page<Map> queryAthleticsClubMonthRankInfoPageList(Map paramMap, PageBean pageBean);
    
    Page<Map> queryStrengthClubMonthRankInfoPageList(Map paramMap, PageBean pageBean);
    
    //插入俱乐部上一个月的 历史记录 定时器调用
    void insertClubMonthRankList(Map paramMap);
}

