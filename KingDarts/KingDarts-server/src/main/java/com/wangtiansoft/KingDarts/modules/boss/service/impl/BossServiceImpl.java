package com.wangtiansoft.KingDarts.modules.boss.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.modules.boss.service.BossService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.BossMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Boss;

/**
* Created by wt-templete-helper.
 */
@Transactional
@Service("bossService")
public class BossServiceImpl extends BaseService<Boss, Integer> implements BossService{

    @Autowired
    private BossMapper bossMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return bossMapper;
    }

    @Override
    public Page<Map> queryBossPageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) bossMapper.queryBossList(paramMap);
    }
    
    @Override
    public void  updateBossIsNotUse() {
    	bossMapper.updateBossIsNotUse();
    }

	@Override
	public Boss getUseBoss() {
		Boss boss=new Boss();
		boss.setIs_use(Constants.True);
		return bossMapper.selectOne(boss);
	}
}
