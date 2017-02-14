package com.grace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by winson on 13/02/2017.
 */

@Controller
@RequestMapping("admin")
public class AdminController {
    /**
     * 后台管理员首页
     * @return
     */
    @RequestMapping("")
    public String index(){
        return "/admin/index";
    }
}
