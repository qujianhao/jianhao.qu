package com.wangtiansoft.KingDarts.config.security;

import com.wangtiansoft.KingDarts.common.utils.PasswordUtil;
import com.wangtiansoft.KingDarts.config.captcha.CaptchaProvider;
import com.wangtiansoft.KingDarts.config.utils.ApplicationContextUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.persistence.base.BaseExample;
import com.wangtiansoft.KingDarts.persistence.dao.master.AccountMapper;
import com.wangtiansoft.KingDarts.persistence.dao.master.RoleEntityMapper;
import com.wangtiansoft.KingDarts.persistence.entity.Account;
import com.wangtiansoft.KingDarts.persistence.entity.RoleEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class SecurityAuthenticationProvider implements AuthenticationProvider {


    private Logger _logger = LoggerFactory.getLogger(SecurityAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        SecurityPrincipal securityPrincipal = null;
        try {
            CaptchaProvider captchaProvider = ApplicationContextUtil.getBean(CaptchaProvider.class);
            SecurityAuthenticationDetailsSource.SecurityWebAuthenticationDetails details = (SecurityAuthenticationDetailsSource.SecurityWebAuthenticationDetails) authentication.getDetails();
            if (details == null || !captchaProvider.validate(details.getSession_id(), details.getValidate_code())){
                throw new AuthenticationServiceException("验证码错误");
            }

            AccountMapper accountMapper = ApplicationContextUtil.getBean(AccountMapper.class);
            BaseExample example = new BaseExample(Account.class);
//            example.createCriteria().andEqualTo("username", authentication.getName()).andEqualTo("is_publish", Constants.True).andEqualTo("is_delete", Constants.False);
            example.createCriteria().andEqualTo("username", authentication.getName()).andEqualTo("is_delete", Constants.False);
            List<Account> accountList = accountMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(accountList)) {
                throw new UsernameNotFoundException("用户不存在");
            }
            Account account = accountList.get(0);
            if (account.getIs_publish() - Constants.False == 0){
                throw new BadCredentialsException("此用户已被禁用");
            }
            if (!PasswordUtil.validatePassword((String) authentication.getCredentials(), account.getPassword())){
            	throw new BadCredentialsException("账号密码错误");
            }

            Map<String, String> permMap = new HashMap<>();
            Map<String, String> roleMap = new HashMap<>();
            String permContent = "";
            String codeContent = "";
            if (account.getIs_spec().intValue() == Constants.True){
//                //  独立权限
//                permContent = accountEntity.getPremContent();
//                codeContent = accountEntity.getCodeContent();
            }else {
                //  角色权限
                RoleEntityMapper roleEntityMapper = ApplicationContextUtil.getBean(RoleEntityMapper.class);
                RoleEntity roleEntity = roleEntityMapper.selectByPrimaryKey(account.getRole_id());
                permContent = roleEntity.getPremContent();
                codeContent = roleEntity.getCodeContent();
                roleMap.put(roleEntity.getId().toString(), roleEntity.getId().toString());
            }

            if (StringUtils.isNotEmpty(codeContent)){
                String[] codeContentArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(codeContent, ",");
                for (String code : codeContentArray){
                    permMap.put(code, code);
                }
            }
            securityPrincipal = new SecurityPrincipal(account, permMap);

        }catch (Exception ex){
            _logger.error("登录失败", ex);
            if (ex instanceof AuthenticationException){
                throw ex;
            }else{
                throw new AuthenticationServiceException("登录失败");
            }
        }
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                securityPrincipal, authentication.getCredentials(), grantedAuthorityList);
        return result;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
