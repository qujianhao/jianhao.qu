package com.wangtiansoft.KingDarts.core.extensions.common.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.CoreUtil;
import com.wangtiansoft.KingDarts.config.utils.ApplicationContextUtil;
import com.wangtiansoft.KingDarts.core.extensions.common.service.CommonService;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.base.BaseResult;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by weitong on 17/3/24.
 */
@Transactional
@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public <T> Page<? extends BaseResult> commonPage(PageBean pageBean, Class<T> entityClass, Class<? extends BaseResult> voClass, IBaseExample iBaseExample) {
        Page<T> sourcePage = commonPage(pageBean, entityClass, iBaseExample);
        return CoreUtil.toList(sourcePage, voClass);
    }


    @Override
    public <T> BaseResult commonFind(Class<T> entityClass, Class<? extends BaseResult> voClass, IBaseExample iBaseExample) {
        if (iBaseExample == null)
            throw new AppRuntimeException("接口没有实现");
        BaseResult resultVO = null;
        try {
            BaseExample example = iBaseExample.example();
            BaseMapper baseMapper = buildBaseMapper(entityClass);
            List<T> list = baseMapper.selectByExample(example);
            resultVO = CoreUtil.toVO(CoreUtil.firstOne(list), voClass);
        }catch (Exception ex){
            throw new AppRuntimeException("通用查询构造失败 " + ex.getLocalizedMessage());
        }
        return resultVO;
    }

    @Override
    public <T> T commonFindOne(Class<T> entityClass, IBaseExample iBaseExample) {
        try {
            BaseExample example = iBaseExample.example();
            BaseMapper baseMapper = buildBaseMapper(entityClass);
            List<T> list = baseMapper.selectByExample(example);
            return CoreUtil.firstOne(list);
        }catch (Exception ex){
            throw new AppRuntimeException("通用查询构造失败 " + ex.getLocalizedMessage());
        }
    }

    private  <T> Page<T> commonPage(PageBean pageBean, Class<T> entityClass, IBaseExample iBaseExample) {
        if (iBaseExample == null)
            throw new AppRuntimeException("接口没有实现");
        Page<T> page = null;
        try {
            BaseExample example = iBaseExample.example();
            BaseMapper baseMapper = buildBaseMapper(entityClass);
            PageHelper.startPage(pageBean.getPage(), pageBean.getRows(), true);
            page = (Page<T>) baseMapper.selectByExample(example);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new AppRuntimeException("通用查询构造失败 " + ex.getLocalizedMessage());
        }
        return page;
    }

    @Override
    public <T> void delete(Class<T> entityClass, IBaseExample iBaseExample) {
        try {
            BaseExample example = iBaseExample.example();
            BaseMapper baseMapper = buildBaseMapper(entityClass);
            baseMapper.deleteByExample(example);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new AppRuntimeException("通用删除构造失败 " + ex.getLocalizedMessage());
        }
    }

    private <T> BaseMapper buildBaseMapper(Class<T> entityClass) throws ClassNotFoundException {
        String entityName = entityClass.getSimpleName();
        String packageName = entityClass.getPackage().getName();
        if (StringUtils.endsWith(packageName, "entity")){
            packageName = StringUtils.removeEnd(packageName, "entity") + "dao.master";
        }
        String repositoryClassName =  packageName + "." + entityName + "Mapper";
        Class repositoryClass = ClassUtils.getClass(repositoryClassName);
        BaseMapper baseMapper = (BaseMapper) ApplicationContextUtil.getBean(repositoryClass);
        return baseMapper;
    }

    public interface IBaseExample {
        BaseExample example();
    }
}
