package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.MerchantWdcash;

import java.util.List;
import java.util.Map;

public interface MerchantWdcashMapper extends BaseMapper<MerchantWdcash> {

    List<Map> queryMerchantWdcashList(Map paramMap);

}