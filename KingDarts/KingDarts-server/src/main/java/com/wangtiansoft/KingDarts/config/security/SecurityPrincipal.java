package com.wangtiansoft.KingDarts.config.security;

import com.wangtiansoft.KingDarts.persistence.entity.Account;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/5 0005.
 */
public class SecurityPrincipal implements Serializable{

    private Account account;
    private Map<String, String> permMap;
    private Map<String, String> roleMap;

    public SecurityPrincipal() {
        this.permMap = new HashMap<>();
        this.roleMap = new HashMap<>();
    }

    public SecurityPrincipal(Account account) {
        this.account = account;
        this.permMap = new HashMap<>();
        this.roleMap = new HashMap<>();
    }

    public SecurityPrincipal(Account account, Map<String, String> permMap) {
        this.account = account;
        this.permMap = permMap;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Map<String, String> getpermMap() {
        return permMap;
    }

    public void setpermMap(Map<String, String> permMap) {
        this.permMap = permMap;
    }

    public Map<String, String> getRoleMap() {
        return roleMap;
    }

    public void setRoleMap(Map<String, String> roleMap) {
        this.roleMap = roleMap;
    }

    public boolean hashRole(String role){
        return permMap.containsKey(role.toUpperCase());
    }

    public boolean hashPerm(String perm){
        return permMap != null && permMap.containsKey(perm.toUpperCase());
    }

}
