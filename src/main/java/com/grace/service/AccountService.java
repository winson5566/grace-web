package com.grace.service;

import com.grace.valid.SignupValid;

import java.util.Map;

/**
 * Created by winson on 13/02/2017.
 */
public interface AccountService {

    /**
     * 账户注册
     * @return
     */
    Map<String,Object> doSignup(SignupValid signupValid);
}
