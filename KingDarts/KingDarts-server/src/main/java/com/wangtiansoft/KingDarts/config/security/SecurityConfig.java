package com.wangtiansoft.KingDarts.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    protected static Logger _logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Configuration
    @Order(1)
    public static class AdminWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/a/**")
                    .authorizeRequests()
                    .antMatchers("/app/**").permitAll()
                    .antMatchers("/a/captcha").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .authenticationDetailsSource(new SecurityAuthenticationDetailsSource())
                    .loginPage("/a/login")
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/a");
                        }
                    })
                    .failureHandler(new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                            httpServletRequest.getSession().setAttribute("LOGIN_MSG", e.getLocalizedMessage());
                            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/a/login");
                        }
                    })
                    .permitAll()
                    .and()
                    .logout().logoutUrl("/a/logout")
                    .and().rememberMe().key("9D119EE5A2B7DAF6B4DC1EF871D0AC3C")
                    .and().csrf().disable();

            http.headers().frameOptions().sameOrigin();

        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/assets/**");
        }
    }


    @Configuration
    public static class ManagerWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/a/login")
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            _logger.info("m onAuthenticationSuccess  " + httpServletRequest.getServletPath());
                        }
                    })
                    .failureHandler(new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                            _logger.info("m onAuthenticationFailure  " + httpServletRequest.getServletPath());
                        }
                    })
                    .permitAll()
                    .and().rememberMe().key("9D119EE5A2B7DAF6B4DC1EF871DOAC3C")
                    .and().csrf().disable();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/assets/**").antMatchers("/wx/**").antMatchers("/api/**").antMatchers("/*.txt");
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new SecurityAuthenticationProvider());
    }
}
