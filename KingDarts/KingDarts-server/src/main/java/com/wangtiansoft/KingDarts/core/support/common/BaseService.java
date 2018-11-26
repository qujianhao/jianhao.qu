package com.wangtiansoft.KingDarts.core.support.common;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.BeanUtil;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public abstract class BaseService<T, K extends Serializable> implements IBaseService<T, K> {

    protected Logger _logger = LoggerFactory.getLogger(BaseService.class);

    public abstract BaseMapper<T> getBaseMapper();

    @Override
    public T findById(K id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

    @Override
    public T updateByIdSelective(T entity) {
        getBaseMapper().updateByPrimaryKeySelective(entity);
        return entity;
    }

    @Override
    public void save(T entity) {
        if (entity != null){
            BeanUtil.entityInit(entity);
            getBaseMapper().insert(entity);
        }

    }

    @Override
    public void remove(T entity) {
        if (entity != null)
            getBaseMapper().delete(entity);
    }

    @Override
    public Page<T> findPage(Example example, PageBean pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows(), true);
        if (StringUtils.isNotBlank(pageBean.getSidx())){
            String orderSql = StringUtils.trimToEmpty(example.getOrderByClause()) + pageBean.getSidx() + " " + pageBean.getSord();
            example.setOrderByClause(orderSql);
        }
        Page<T> list = (Page<T>) getBaseMapper().selectByExample(example);
        return list;
    }

    @Override
    public List<T> findAllByExample(Example example) {
        return (List<T>) getBaseMapper().selectByExample(example);
    }

    @Override
    public T findOneByExample(Example example) {
        PageHelper.startPage(0, 1, false);
        List<T> list = (List<T>) getBaseMapper().selectByExample(example);
        if (list == null || list.size() <= 0)
            return null;
        return list.get(0);
    }

}
