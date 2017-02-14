package com.grace.controller;

/**
 * Created by winson on 11/02/2017.
 */

import com.grace.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Action;
import java.util.Map;

@Controller
public class PublicController {

    @Autowired
    private AccountService accountService;

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping("")
    public String index(Model model) {
        return "/public/index";
    }

    /**
     * 登录页面
     * @param model
     * @return
     */
    @RequestMapping("login")
    public String loginPage(Model model){
        return "/public/login";
    }

    /**
     * 登录失败
     * @param model
     * @return
     */
    @RequestMapping("login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "/public/login";
    }


    /**
     * 注册页面
     * @param model
     * @return
     */
    @RequestMapping("signup")
    public String signup(Model model){
        return "/public/signup";
    }

    /**
     * 执行注册
     * @param model
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("doSignup")
    @ResponseBody
    public Map<String,Object> signup(Model model, String username, String password){
        return accountService.doSignup(username,password);
    }

}
