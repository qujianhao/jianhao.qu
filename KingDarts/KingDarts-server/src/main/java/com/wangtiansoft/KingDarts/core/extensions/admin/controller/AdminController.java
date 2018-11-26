package com.wangtiansoft.KingDarts.core.extensions.admin.controller;

import com.wangtiansoft.KingDarts.config.captcha.CaptchaProvider;
import com.wangtiansoft.KingDarts.config.security.SecurityPrincipal;
import com.wangtiansoft.KingDarts.config.utils.ApplicationContextUtil;
import com.wangtiansoft.KingDarts.constants.Constants;
import com.wangtiansoft.KingDarts.core.extensions.account.service.AccountService;
import com.wangtiansoft.KingDarts.core.extensions.module.service.ModuleService;
import com.wangtiansoft.KingDarts.core.support.common.BaseController;
import com.wangtiansoft.KingDarts.persistence.entity.Account;
import com.wangtiansoft.KingDarts.persistence.entity.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
@Controller
@RequestMapping("/a")
public class AdminController extends BaseController {

    @Autowired
    private CaptchaProvider captchaProvider;
    @Autowired
    private AccountService accountService;

    @RequestMapping("login")
    public String login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!"anonymousUser".equals(authentication.getPrincipal())){
            return "redirect:/a";
        }
        return "/a/login";
    }

    @RequestMapping(value = "logout")
    public String logout() {
        return "redirect:/a/login";
    }

    @RequestMapping(value = "captcha")
    public void captcha(HttpSession session, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = captchaProvider.createBufferedImage(session);
        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", servletOutputStream);
            servletOutputStream.flush();
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                servletOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping
    public String welcome(){
        setupSessions();
        return "/a/index";
    }

    @GetMapping(value = "index")
    public String index(){
        setupSessions();
        return "/a/index";
    }

    @RequestMapping(value = "main")
    public String main() {
        return "/a/main/main";
    }

    private void setupSessions(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityPrincipal securityPrincipal = (SecurityPrincipal) authentication.getPrincipal();
        ModuleService moduleService = ApplicationContextUtil.getBean(ModuleService.class);
        LinkedHashMap<String, List<Module>> moduleMap = moduleService.queryModuleMap(securityPrincipal.getAccount().getId());
        HttpSession session = request.getSession();
        session.setAttribute("SESSION_PERM_MAP", moduleMap);
        
        if(session.getAttribute(Constants.session_account)!=null){
        	return;
        }
        //获取登录用户信息
        Account entity = accountService.findById(Integer.valueOf(securityPrincipal.getAccount().getId()));
        entity.setPassword(null);
        session.setAttribute(Constants.session_account, entity);
        //账号类型，1俱乐部； 2 代理商；3管理员
        /*if(entity.getAcc_type().equals(1)){
        	
        }else if(entity.getAcc_type().equals(2)){
        	
        }else if(entity.getAcc_type().equals(3)){
        	
        }*/
    }

}

