package com.wangtiansoft.KingDarts.core.extensions.module.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.utils.CoreUtil;
import com.wangtiansoft.KingDarts.common.utils.SQLUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.extensions.module.service.ModuleService;
import com.wangtiansoft.KingDarts.core.support.common.BaseService;
import com.wangtiansoft.KingDarts.persistence.base.BaseMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.AccountMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.ModuleMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.RoleEntityMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Account;
import com.wangtiansoft.KingDarts.persistence.entity.Module;
import com.wangtiansoft.KingDarts.persistence.entity.RoleEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by wt-templete-helper.
 */
@Transactional
@Service("moduleService")
public class ModuleServiceImpl extends BaseService<Module, Integer> implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RoleEntityMapper roleEntityMapper;

    @Override
    public BaseMapper getBaseMapper() {
        return moduleMapper;
    }

    @Override
    public Page<Map> queryModulePageList(Map paramMap, PageBean pageBean) {
        paramMap.put(SQLUtil.SQL_OrderSQL, SQLUtil.orderByCondition(pageBean));
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        return (Page<Map>) moduleMapper.queryModuleList(paramMap);
    }

    public List<Map> queryModuleTreeList(String parentId, Integer userId) {
        Account account = accountMapper.selectByPrimaryKey(userId);
        List<Map> resultList = this.queryModuleMapList(parentId);
        String[] role_permissions = StringUtils.splitByWholeSeparatorPreserveAllTokens(StringUtils.trimToEmpty(account.getModule_content()), ",");
        List<String> role_permissionList = Arrays.asList(role_permissions);
        for (Map map : resultList) {
            String id = map.get("id").toString();
            if (role_permissionList.contains(id)) {
                map.put("checked", "true");
            }
        }

        return resultList;
    }

    public List<Map> queryModuleMapList(String parentId) {
        List<Map> resultList = moduleMapper.queryModuleMapList(parentId);
        if (StringUtils.isEmpty(parentId)) {
            Map map = new HashMap();
            map.put("id", 0);
            map.put("pId", 0);
            map.put("name", "后台管理系统");
            map.put("open", "true");
            map.put("collapse", "false");
            resultList.add(0, map);
        }
        return resultList;
    }

    @Override
    public LinkedHashMap<String, List<Module>> queryModuleMap(Integer userId) {
        Account account = accountMapper.selectByPrimaryKey(userId);
        String permContent = "";
        if (account.getIs_spec().intValue() == Constants.False) {
            RoleEntity roleEntity = roleEntityMapper.selectByPrimaryKey(account.getRole_id());
            permContent = roleEntity.getPremContent();
        }
        permContent = StringUtils.defaultString(permContent, "");
        List<Integer> moduleIdList = CoreUtil.toIntegerList(StringUtils.splitByWholeSeparatorPreserveAllTokens(permContent, ","));
        List<Module> moduleList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(moduleIdList)) {
            Example example = new Example(Module.class);
            example.createCriteria()
                    .andIn("id", moduleIdList)
                    .andEqualTo("is_publish", Constants.True)
                    .andEqualTo("is_delete", Constants.False);
            example.setOrderByClause("parent , order_num desc");
            moduleList = moduleMapper.selectByExample(example);
        }
        LinkedHashMap<String, List<Module>> permEntityMap = new LinkedHashMap<>();
        for (Module moduleEntity : moduleList) {
            String parentKey = String.valueOf(moduleEntity.getParent());
            List<Module> tempList = new ArrayList<>();
            if (permEntityMap.containsKey(parentKey)) {
                tempList = permEntityMap.get(parentKey);
            }
            tempList.add(moduleEntity);
            permEntityMap.put(parentKey, tempList);
        }
        return permEntityMap;
    }
}
