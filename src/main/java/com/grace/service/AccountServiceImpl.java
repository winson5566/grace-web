package com.grace.service;

import com.grace.entity.Users;
import com.grace.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by winson on 13/02/2017.
 */
@Service("AccountService")
public class AccountServiceImpl implements AccountService{

/*    @Autowired
    private UsersRepository usersRepository;*/

    @Transactional
    public Map<String,Object> doSignup(String username,String password){
        Map<String,Object> map = new HashMap<String,Object>();

  /*      Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(1);
        Users users = usersRepository.save(user);
        if (users!=null){
            map.put("code",1);
            map.put("msg","注册成功");
        }else {
            map.put("code",0);
            map.put("msg","注册失败");
        }*/
        return  map;

    }

}
