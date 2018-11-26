package com.wangtiansoft.KingDarts.core.extensions.manager.controller;

import com.wangtiansoft.KingDarts.config.security.SecurityPrincipal;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
@Controller
@RequestMapping("/m")
public class ManagerController extends BaseController {

    @GetMapping("login")
    public String login(){
        return "m/login";
    }

    @RequestMapping(value = "logout")
    public String logout() {
        return "redirect:login";
    }

    @GetMapping
    public String index(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityPrincipal securityPrincipal = (SecurityPrincipal) authentication.getPrincipal();
        return "index";
    }

}
