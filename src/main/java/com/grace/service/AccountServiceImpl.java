package com.grace.service;

import com.grace.entity.GroupMembers;
import com.grace.entity.Users;
import com.grace.repository.GroupMembersRepository;
import com.grace.repository.UsersRepository;
import com.grace.valid.SignupValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by winson on 13/02/2017.
 */
@Service("AccountService")
public class AccountServiceImpl implements AccountService{

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private GroupMembersRepository groupMembersRepository;

    @Transactional
    public Map<String,Object> doSignup(SignupValid signupValid){
        Map<String,Object> map = new HashMap<String,Object>();

        //判断是否存在该用户
        long count = usersRepository.countByUsername(signupValid.getUsername());
        if (count>0){
            map.put("code",0);
            map.put("msg","用户已存在");
            return map;
        }
        Users user = new Users();
        user.setUsername(signupValid.getUsername());
        user.setPassword(signupValid.getPassword());
        user.setEnabled(1);
        Users users = usersRepository.save(user);
        if (users!=null){
            //设置默认权限
            GroupMembers groupMembers = new GroupMembers();
            groupMembers.setUserId(users.getId());
            groupMembers.setGroupId(BigInteger.valueOf(2));
            GroupMembers groupMembersResult = groupMembersRepository.save(groupMembers);
            if (groupMembersResult!=null){
            map.put("code",1);
            map.put("msg","注册成功");
                return  map;
            }
        }
        map.put("code",0);
        map.put("msg","注册失败");
        return  map;

    }

}
