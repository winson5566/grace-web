package com.grace.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by winson on 17-2-14.
 */
@Component("templateEngine")
public class TemplateEngine extends SpringTemplateEngine {

    @Autowired
    private SpringSecurityDialect springSecurityDialect;
    
    public void init(){
        Set set = new HashSet();
        set.add(springSecurityDialect);
        this.setAdditionalDialects(set);
    }
}
