package com.grace.controller;

import com.grace.config.TestProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by winson on 17-2-16.
 */
@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestProperties testConfig;

    @RequestMapping("")
    @ResponseBody
    public Map<String,Object> test(){
        Map<String,Object> map = new HashMap<String,Object>();
/*        map.put("annotation-key",key);
        map.put("annotation-value",value);*/
        map.put("testConfig-key",testConfig.getKey());
        map.put("testConfig-value",testConfig.getValue());
        return map;
    }
}
