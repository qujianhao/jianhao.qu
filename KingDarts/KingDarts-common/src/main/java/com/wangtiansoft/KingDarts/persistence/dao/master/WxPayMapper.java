package com.wangtiansoft.KingDarts.persistence.dao.master;

import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.entity.WxPay;

import java.util.List;
import java.util.Map;

public interface WxPayMapper extends BaseMapper<WxPay> {

    List<Map> queryWxPayList(Map paramMap);

}