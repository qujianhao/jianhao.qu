package com.wangtiansoft.KingDarts.modules.merchantWdcash.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.support.common.IBaseService;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantWdcash;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* Created by wt-templete-helper.
 */
public interface MerchantWdcashService extends IBaseService<MerchantWdcash, Integer> {

    // 分页查询MerchantWdcash
    Page<Map> queryMerchantWdcashPageList(Map paramMap, PageBean pageBean);

	void updateCashStatus(MerchantWdcash merchantWdcash);
}

