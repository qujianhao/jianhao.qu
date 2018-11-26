package com.wangtiansoft.KingDarts.core.extensions.auth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.common.exception.AppRuntimeException;
import com.wangtiansoft.KingDarts.common.utils.CoreUtil;
import com.wangtiansoft.KingDarts.common.utils.PasswordUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.extensions.auth.bean.AuthSearchBean;
import com.wangtiansoft.KingDarts.core.extensions.auth.service.AuthService;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.dao.master.AccountMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.ModuleMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.OrgEntityMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.RoleEntityMapper;
import com.wangtiansoft.KingDarts.persistence.entity.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@Transactional
@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RoleEntityMapper roleRepository;
    @Autowired
    private OrgEntityMapper orgRepository;
    @Autowired
    private ModuleMapper moduleMapper;


    @Override
    public LinkedHashMap<String, List<Module>> queryPermEntityList(Integer userId) {
        Account accountEntity = accountMapper.selectByPrimaryKey(userId);
        String permContent = accountEntity.getPermission_content();
        if (accountEntity.getIs_spec().intValue() == Constants.False) {
            RoleEntity roleEntity = roleRepository.selectByPrimaryKey(accountEntity.getRole_id());
            permContent = roleEntity.getPremContent();
        }
        permContent = StringUtils.defaultString(permContent, "");
        List<Integer> perms = CoreUtil.toIntegerList(StringUtils.splitByWholeSeparatorPreserveAllTokens(permContent, ","));
        List<Module> moduleList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(perms)) {
            Example example = new Example(Module.class);
            example.createCriteria()
                    .andIn("id", perms)
                    .andEqualTo("isPublish", Constants.True)
                    .andEqualTo("isDelete", Constants.False);
            example.setOrderByClause("parent , order_num desc");
            moduleList = moduleMapper.selectByExample(example);
        }
        LinkedHashMap<String, List<Module>> permEntityMap = new LinkedHashMap<>();
        for (Module module : moduleList) {
            String parentKey = String.valueOf(module.getParent());
            List<Module> tempList = new ArrayList<>();
            if (permEntityMap.containsKey(parentKey)) {
                tempList = permEntityMap.get(parentKey);
            }
            tempList.add(module);
            permEntityMap.put(parentKey, tempList);
        }
        return permEntityMap;
    }

    @Override
    public Account findAccountEntityByUsername(String mobile) {
        Example example = new Example(Account.class);
        example.createCriteria().andEqualTo("mobile", mobile).andEqualTo("isPublish", "1");
        List<Account> accountEntityList = accountMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(accountEntityList)) {
            return accountEntityList.get(0);
        }
        return null;
    }


    @Override
    public List<Map> queryPermTreeList(String parentId) {
        List<Map> resultList = moduleMapper.queryModuleMapListByParent(parentId);
        if (StringUtils.isEmpty(parentId) || "0".equals(parentId)) {
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
    public LinkedHashMap<String, List<Module>> queryPermTreeListAll() {
        Example example = new Example(PermEntity.class);
        example.createCriteria()
                .andEqualTo("isPublish", Constants.True)
                .andEqualTo("isDelete", Constants.False);
        example.setOrderByClause("parent , order_num desc");
        List<Module> permEntities = moduleMapper.selectByExample(example);
        LinkedHashMap<String, List<Module>> moduleMap = new LinkedHashMap<>();
        for (Module permEntity : permEntities) {
            String parentKey = String.valueOf(permEntity.getParent());
            List<Module> tempList = new ArrayList<>();
            if (moduleMap.containsKey(parentKey)) {
                tempList = moduleMap.get(parentKey);
            }
            tempList.add(permEntity);
            moduleMap.put(parentKey, tempList);
        }
        return moduleMap;
    }

    @Override
    public void saveOrUpdatePerm(AuthSearchBean searchBean) {

        if (StringUtils.isEmpty(searchBean.getUrl()))
            throw new AppRuntimeException("url不能为空");

        if (searchBean.getId() == null) {
            //  新建
            Module moduleEntity = new Module();
            moduleEntity.setName(searchBean.getName());
            moduleEntity.setCode(StringUtils.upperCase(searchBean.getCode()));
            moduleEntity.setParent(NumberUtils.toInt(searchBean.getParent()));
            moduleEntity.setIcon("");
            moduleEntity.setIs_delete(Constants.False);
            moduleEntity.setIs_publish(Constants.True);
            moduleEntity.setOrder_num(0);
            moduleEntity.setUpdate_time(new Date());
            moduleEntity.setUrl(searchBean.getUrl());
            moduleMapper.insert(moduleEntity);
        } else {
            //  保存
            Module moduleEntity = new Module();
            moduleEntity.setId(NumberUtils.toInt(searchBean.getId()));
            moduleEntity.setName(searchBean.getName());
            moduleEntity.setCode(StringUtils.upperCase(searchBean.getCode()));
            moduleEntity.setParent(NumberUtils.toInt(searchBean.getParent()));
            moduleEntity.setIs_publish(NumberUtils.toInt(searchBean.getIsPublish()));
            moduleEntity.setOrder_num(searchBean.getOrderNum());
            moduleEntity.setUpdate_time(new Date());
            moduleEntity.setUrl(searchBean.getUrl());
            moduleMapper.updateByPrimaryKeySelective(moduleEntity);
        }
    }

    @Override
    public void deletePerm(String permId) {
        Module moduleEntity = moduleMapper.selectByPrimaryKey(NumberUtils.toInt(permId));
        if (moduleEntity == null)
            throw new AppRuntimeException("数据不存在");

        BaseExample example = new BaseExample(PermEntity.class);
        example.createCriteria().andEqualTo(true, "isPublish", Byte.valueOf("1")).andEqualTo(true, "isDelete", Byte.valueOf("0")).andEqualTo("parent", moduleEntity.getId());
        int count = moduleMapper.selectCountByExample(example);
        if (count > 0)
            throw new AppRuntimeException("数据下仍然存在菜单，不能删除");

        moduleEntity.setIs_delete(Constants.True);
        moduleMapper.updateByPrimaryKeySelective(moduleEntity);
    }

    private Account checkAccountEntityByUsername(String username) {
        Example example = new Example(Account.class);
        example.createCriteria().andEqualTo("username", username);
        List<Account> accountEntityList = accountMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(accountEntityList)) {
            return accountEntityList.get(0);
        }
        return null;
    }

    @Override
    public List<Map> queryPermTreeByAuthList(String parentId, Integer userId) {
        Account accountEntity = accountMapper.selectByPrimaryKey(userId);
        List<Map> resultList = this.queryPermTreeList(parentId);
        String[] role_permissions = StringUtils.splitByWholeSeparatorPreserveAllTokens(StringUtils.trimToEmpty(accountEntity.getPermission_content()), ",");
        List<String> role_permissionList = Arrays.asList(role_permissions);
        for (Map map : resultList) {
            String id = map.get("id").toString();
            if (role_permissionList.contains(id)) {
                map.put("checked", "true");
            }
        }
        return resultList;
    }


    @Override
    public RoleEntity findRoleById(String roleId) {
        return roleRepository.selectByPrimaryKey(NumberUtils.toInt(roleId));
    }

    @Override
    public List<RoleEntity> queryAllEnableRoleEntityList() {
        BaseExample example = new BaseExample(RoleEntity.class);
        example.createCriteria()
                .andEqualTo(true, "isPublish", Byte.valueOf(String.valueOf(Constants.True)))
                .andEqualTo(true, "isDelete", Byte.valueOf(String.valueOf(Constants.False)));
        example.setOrderByClause("order_num desc");
        List<RoleEntity> roleEntityList = roleRepository.selectByExample(example);
        return roleEntityList;
    }

    @Override
    public void saveOrUpdateRole(AuthSearchBean searchBean) {

        Set<String> permSet = new HashSet<>();
        if (searchBean.getSubperm_codes() != null) {
            for (String subperm : searchBean.getSubperm_codes()) {
                if (StringUtils.isNotEmpty(subperm)) {
                    permSet.addAll(Arrays.asList(StringUtils.splitByWholeSeparatorPreserveAllTokens(subperm, ",")));
                }
            }
        }
        Set<String> methodCodeSet = new HashSet<>();
        if (searchBean.getPerm_codes() != null) {
            for (String code : searchBean.getPerm_codes()) {
                methodCodeSet.add(code);
            }
        }
        String[] codeArray = methodCodeSet.toArray(new String[]{});
        String codeContent = StringUtils.join(codeArray, ",");

        String[] permArray = permSet.toArray(new String[]{});
        String premContent = StringUtils.join(permArray, ",");

        if (StringUtils.isEmpty(searchBean.getRoleId())) {
            //  保存
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(searchBean.getName());
            roleEntity.setIsPublish(Byte.valueOf(searchBean.getIsPublish()));
            roleEntity.setPremContent(premContent);
            roleEntity.setCodeContent(codeContent);
            roleEntity.setIsDelete(Byte.valueOf(String.valueOf(Constants.False)));
            roleEntity.setOrderNum(0);
            roleEntity.setUpdateTime(new Date());
            roleRepository.insert(roleEntity);
        } else {
            //  更新
            RoleEntity roleEntity = roleRepository.selectByPrimaryKey(NumberUtils.toInt(searchBean.getRoleId()));
            if (roleEntity == null) {
                throw new AppRuntimeException("角色不存在");
            }
            roleEntity.setName(searchBean.getName());
            roleEntity.setIsPublish(Byte.valueOf(searchBean.getIsPublish()));
            roleEntity.setPremContent(premContent);
            roleEntity.setCodeContent(codeContent);
            roleEntity.setUpdateTime(new Date());
            roleRepository.updateByPrimaryKeySelective(roleEntity);
        }

    }

    @Override
    public void updateRoleStatus(AuthSearchBean searchBean) {
        RoleEntity roleEntity = roleRepository.selectByPrimaryKey(NumberUtils.toInt(searchBean.getRoleId()));
        if (roleEntity == null) {
            throw new AppRuntimeException("角色不存在");
        }
        if (StringUtils.isNotEmpty(searchBean.getIsPublish())) {
            int val = (Constants.True == NumberUtils.toInt(searchBean.getIsPublish())) ? Constants.False : Constants.True;
            roleEntity.setIsPublish(Byte.valueOf(String.valueOf(val)));
        }
        if (StringUtils.isNotEmpty(searchBean.getIsDelete())) {
            roleEntity.setIsDelete(Byte.valueOf(searchBean.getIsDelete()));
        }
        roleRepository.updateByPrimaryKeySelective(roleEntity);
    }

    @Override
    public Account saveOrUpdateAccount(AuthSearchBean searchBean) {

        if (StringUtils.isNotEmpty(searchBean.getPassword()) && !searchBean.getPassword().equals(searchBean.getPassword_confirm())) {
            throw new AppRuntimeException("两次输入的密码不一致");
        }

        Set<String> permSet = new HashSet<>();
        if (searchBean.getSubperm_codes() != null) {
            for (String subperm : searchBean.getSubperm_codes()) {
                if (StringUtils.isNotEmpty(subperm)) {
                    permSet.addAll(Arrays.asList(StringUtils.splitByWholeSeparatorPreserveAllTokens(subperm, ",")));
                }
            }
        }
        Set<String> methodCodeSet = new HashSet<>();
        if (searchBean.getPerm_codes() != null) {
            for (String code : searchBean.getPerm_codes()) {
                methodCodeSet.add(code);
            }
        }
        String[] codeArray = methodCodeSet.toArray(new String[]{});
        String codeContent = StringUtils.join(codeArray, ",");

        String[] permArray = permSet.toArray(new String[]{});
        String premContent = StringUtils.join(permArray, ",");


        BaseExample existExample = new BaseExample(Account.class);
        existExample.createCriteria().andEqualTo(true, "username", searchBean.getUsername());
        List<Account> existAccountList = accountMapper.selectByExample(existExample);
        Account existAccount = null;
        if (CollectionUtils.isNotEmpty(existAccountList)) {
            existAccount = existAccountList.get(0);
        }

        if (StringUtils.isEmpty(searchBean.getId())) {
            //  保存
            if (StringUtils.isEmpty(searchBean.getPassword())) {
                throw new AppRuntimeException("密码不能为空");
            }
            if (existAccount != null) {
                throw new AppRuntimeException("用户名:" + searchBean.getUsername() + "已经存在");
            }
            Account accountEntity = new Account();
            accountEntity.setRealname(searchBean.getRealname());
            accountEntity.setIs_delete(Constants.False);
            accountEntity.setOrder_num(0);
            accountEntity.setCreate_time(new Date());
            accountEntity.setUsername(searchBean.getUsername());
            accountEntity.setIs_publish(NumberUtils.toInt(searchBean.getIsPublish()));
            accountEntity.setRemark(searchBean.getRemark());
            accountEntity.setUpdate_time(new Date());
            accountEntity.setOrg_id(1);
            accountEntity.setPassword(PasswordUtil.createHash(searchBean.getPassword()));
            accountEntity.setIs_spec(searchBean.getIsSpec());
            if (searchBean.getIsSpec() != Constants.True) {
                //  角色用户
                accountEntity.setRole_id(NumberUtils.toInt(searchBean.getRoleId()));
            } else {
                //  独立权限用户
                accountEntity.setPermission_content(premContent);
                accountEntity.setModule_content(codeContent);
            }
            accountMapper.insert(accountEntity);
            return accountEntity;
        } else {
            //  更新
            Account accountEntity = accountMapper.selectByPrimaryKey(NumberUtils.toInt(searchBean.getId()));
            if (accountEntity == null) {
                throw new AppRuntimeException("用户:" + searchBean.getUsername() + "不存在");
            }
            if (existAccount != null && !existAccount.getId().toString().equals(searchBean.getId())) {
                throw new AppRuntimeException("用户名:" + searchBean.getUsername() + "已经存在");
            }
            accountEntity.setUsername(searchBean.getUsername());
            accountEntity.setRealname(searchBean.getRealname());
            accountEntity.setIs_publish(NumberUtils.toInt(searchBean.getIsPublish()));
            accountEntity.setRemark(searchBean.getRemark());
            accountEntity.setOrg_id(1);
            if (StringUtils.isNotEmpty(searchBean.getPassword())) {
                accountEntity.setPassword(PasswordUtil.createHash(searchBean.getPassword()));
            }
            accountEntity.setIs_spec(searchBean.getIsSpec());
            if (searchBean.getIsSpec() != Constants.True) {
                //  角色用户
                accountEntity.setRole_id(NumberUtils.toInt(searchBean.getRoleId()));
            } else {
                //  独立权限用户
                accountEntity.setPermission_content(premContent);
                accountEntity.setModule_content(codeContent);
            }
            accountEntity.setUpdate_time(new Date());
            accountMapper.updateByPrimaryKeySelective(accountEntity);
            return accountEntity;
        }

    }


    @Override
    public void updateAccountStatus(AuthSearchBean searchBean) {
        Account accountEntity = accountMapper.selectByPrimaryKey(NumberUtils.toInt(searchBean.getId()));
        if (accountEntity == null) {
            throw new AppRuntimeException("角色不存在");
        }
        if (StringUtils.isNotEmpty(searchBean.getIsPublish())) {
            int val = (Constants.True == NumberUtils.toInt(searchBean.getIsPublish())) ? Constants.False : Constants.True;
            accountEntity.setIs_publish(val);
        }
        if (StringUtils.isNotEmpty(searchBean.getIsDelete())) {
            accountEntity.setIs_delete(NumberUtils.toInt(searchBean.getIsDelete()));
        }
        accountMapper.updateByPrimaryKeySelective(accountEntity);
    }

    @Override
    public List<Map> queryOrgTreeListByParentId(String parentId) {
        List<Map> resultList = orgRepository.queryOrgMapListByParentId(parentId);
        return resultList;
    }

    @Override
    public Page queryOrgPage(AuthSearchBean searchBean, PageBean pageBean) {
        BaseExample example = new BaseExample(OrgEntity.class);
        example.createCriteria()
                .andEqualTo(true, "isPublish", Constants.True)
                .andEqualTo(true, "isDelete", Constants.False)
                .andEqualTo(StringUtils.isNotEmpty(searchBean.getId()), "parentId", searchBean.getId());
        example.setOrderByClause("parent_id, order_num desc");
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
        Page page = (Page) orgRepository.selectByExample(example);
        return page;
    }

    @Override
    public OrgEntity findOrgEntityById(String orgId) {
        return orgRepository.selectByPrimaryKey(NumberUtils.toInt(orgId));
    }

    @Override
    public void saveOrUpdateOrg(AuthSearchBean searchBean) {
        if (StringUtils.isEmpty(searchBean.getId())) {
            //  新建
            OrgEntity entity = new OrgEntity();
            entity.setIsDelete(Byte.valueOf(String.valueOf(Constants.False)));
            entity.setOrderNum(searchBean.getOrderNum());
            entity.setCreateTime(new Date());
            entity.setName(searchBean.getName());
            entity.setIsPublish(Byte.valueOf(searchBean.getIsPublish()));
            entity.setSummary(searchBean.getSummary());
            entity.setParentId(NumberUtils.toInt(searchBean.getParent()));
            orgRepository.insert(entity);
        } else {
            //  更新
            OrgEntity entity = orgRepository.selectByPrimaryKey(NumberUtils.toInt(searchBean.getId()));
            if (entity == null) {
                throw new AppRuntimeException("机构不存在");
            }
            entity.setName(searchBean.getName());
            entity.setIsPublish(Byte.valueOf(searchBean.getIsPublish()));
            entity.setSummary(searchBean.getSummary());
            entity.setOrderNum(searchBean.getOrderNum());
            orgRepository.updateByPrimaryKeySelective(entity);
        }
    }

    @Override
    public void updateOrgStatus(AuthSearchBean searchBean) {

    }

    @Override
    public void accountEditPasword(AuthSearchBean searchBean) {
        Account accountEntity = accountMapper.selectByPrimaryKey(Integer.valueOf(searchBean.getId()));
        if (accountEntity == null) {
            throw new AppRuntimeException("账号不存在");
        } else {
            if (!PasswordUtil.validatePassword(searchBean.getPassword(), accountEntity.getPassword())) {
                throw new AppRuntimeException("原始密码不对");
            }
            accountEntity.setPassword(PasswordUtil.createHash(searchBean.getPassword_confirm()));
        }
        accountMapper.updateByPrimaryKey(accountEntity);
    }


}
