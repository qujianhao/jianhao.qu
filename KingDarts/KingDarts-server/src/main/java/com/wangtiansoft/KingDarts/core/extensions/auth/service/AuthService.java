package com.wangtiansoft.KingDarts.core.extensions.auth.service;

import com.github.pagehelper.Page;
import com.wangtiansoft.KingDarts.common.bean.PageBean;
import com.wangtiansoft.KingDarts.core.extensions.auth.bean.AuthSearchBean;
import com.wangtiansoft.KingDarts.persistence.entity.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public interface AuthService {

    //  查询用户权限列表
    LinkedHashMap<String, List<Module>> queryPermEntityList(Integer userId);
    //  查询用户信息
    Account findAccountEntityByUsername(String username);

    //  分层查询权限树，parentId为0表示根
    List<Map> queryPermTreeList(String parentId);

    //  查询权限树，parentId为0表示根
    LinkedHashMap<String, List<Module>> queryPermTreeListAll();

    //  保存权限
    void saveOrUpdatePerm(AuthSearchBean searchBean);

    //  删除权限
    void deletePerm(String permId);

    List<Map> queryPermTreeByAuthList(String id, Integer userId);

    //  查询角色信息
    RoleEntity findRoleById(String roleId);

    //  查询全部可用角色
    List<RoleEntity> queryAllEnableRoleEntityList();

    //  编辑角色权限
    void saveOrUpdateRole(AuthSearchBean searchBean);

    //  编辑角色状态
    void updateRoleStatus(AuthSearchBean searchBean);

    //  编辑账号
    Account saveOrUpdateAccount(AuthSearchBean searchBean);

    //  编辑账号状态
    void updateAccountStatus(AuthSearchBean searchBean);

    //  查询机构树
    List<Map> queryOrgTreeListByParentId(String parentId);

    //  分页查询机构列表
    Page queryOrgPage(AuthSearchBean searchBean, PageBean pageBean);

    //  查询机构信息
    OrgEntity findOrgEntityById(String orgId);

    //  新建机构
    void saveOrUpdateOrg(AuthSearchBean searchBean);

    //  编辑机构状态
    void updateOrgStatus(AuthSearchBean searchBean);

    void accountEditPasword(AuthSearchBean searchBean);

}
