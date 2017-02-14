package com.grace.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

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

/*        Object obj =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) obj;
            System.out.println(userDetails.getUsername());
            System.out.println(userDetails.getPassword());
            System.out.println(userDetails.getAuthorities());
        }
        if (obj instanceof Principal) {
            Principal principal = (Principal) obj;
            System.out.println(principal.getName());
        }*/
        return "/member/index";
    }
}
