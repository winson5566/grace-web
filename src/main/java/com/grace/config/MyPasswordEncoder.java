package com.grace.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by winson on 17-2-15.
 */
@Service("myPasswordEncoder")
public class MyPasswordEncoder extends MessageDigestPasswordEncoder {

    public MyPasswordEncoder(String algorithm) {
        super(algorithm);
    }

    @Override
    public boolean isPasswordValid(String savePass, String submitPass, Object salt) {
        return savePass.equals(new Md5PasswordEncoder().encodePassword(submitPass, salt.toString()));
    }
}
