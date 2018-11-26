package com.wangtiansoft.KingDarts.core.extensions.common.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.extensions.common.service.impl.CommonServiceImpl;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;

/**
 * Created by weitong on 17/3/24.
 */
public interface CommonService {


    /**
     * 通用分页查询
     * @param pageBean  分页bean
     * @param entityClass   数据库中entity
     * @param voClass   对应的数据对象
     * @param iBaseExample  数据库查询example语句
     * @param <T>   数据库中entity类型
     * @return  Page<VO>
     */
    <T> Page<? extends BaseResult> commonPage(PageBean pageBean, Class<T> entityClass, Class<? extends BaseResult> voClass, CommonServiceImpl.IBaseExample iBaseExample);


    /**
     * 通用单独对象查询
     * @param entityClass   数据库中entity
     * @param voClass   对应的数据对象
     * @param iBaseExample  数据库查询example语句
     * @param <T>   数据库中entity类型
     * @return
     */
    <T> BaseResult commonFind(Class<T> entityClass, Class<? extends BaseResult> voClass, CommonServiceImpl.IBaseExample iBaseExample);


    /**
     *  通用查询
     * @param <T>
     * @param entityClass
     * @param iBaseExample
     * @return
     */
    <T> T commonFindOne(Class<T> entityClass, CommonServiceImpl.IBaseExample iBaseExample);

    /**
     * 通用删除
     * @param entityClass
     * @param iBaseExample
     * @param <T>
     */
    <T> void delete(Class<T> entityClass, CommonServiceImpl.IBaseExample iBaseExample);
}
