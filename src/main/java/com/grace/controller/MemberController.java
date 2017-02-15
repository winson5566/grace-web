package com.grace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by winson on 11/02/2017.
 */
@Controller
@RequestMapping("m")
public class MemberController {
    /**
     * 会员管理首页
     * @param model
     * @return
     */
    @RequestMapping("")
    public String index(Model model){

//        Object obj =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (obj instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) obj;
//            model.addAttribute("username",userDetails.getUsername());
//            model.addAttribute("authorities",userDetails.getAuthorities());
//        }
//        if (obj instanceof Principal) {
//            Principal principal = (Principal) obj;
//            System.out.println(principal.getName());
//        }
        return "/member/index";
    }
}
