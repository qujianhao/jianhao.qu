package com.wangtiansoft.KingDarts.core.support.common;


import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public interface IBaseService<T, K extends Serializable> {

    T findById(K id);

    T updateByIdSelective(T entity);

    void save(T entity);

    void remove(T entity);

    Page<T> findPage(Example example, PageBean pageBean);

    List<T> findAllByExample(Example example);

    T findOneByExample(Example example);
}
