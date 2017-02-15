package com.grace.controller;

/**
 * Created by winson on 11/02/2017.
 */

import com.grace.service.AccountService;
import com.grace.valid.SignupValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
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
        model.addAttribute("loginError", "账号或密码错误");
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
     * @return
     */
    @RequestMapping("doSignup")
    public String signup(Model model, @Validated SignupValid signupValid, BindingResult bindingResult){
        System.out.print("123");
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            model.addAttribute("allErrors", errors);
            return ("/public/signup");
        }
        Map<String,Object> map = accountService.doSignup(signupValid);
        if ("0".equals(map.get("code"))){
            model.addAttribute("error", map.get("msg"));
            return ("/public/signup");
        }
        return ("redirect:/m");

    }

}
