package com.wangtiansoft.KingDarts.persistence.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by weitong on 17/3/23.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
