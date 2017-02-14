package com.grace.service;

import java.util.Map;

/**
 * Created by winson on 13/02/2017.
 */
public interface AccountService {

    /**
     * 账户注册
     * @param username
     * @param password
     * @return
     */
    Map<String,Object> doSignup(String username,String password);
}
