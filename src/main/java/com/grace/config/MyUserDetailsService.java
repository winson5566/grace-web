package com.grace.config;

import com.grace.entity.Users;
import com.grace.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winson on 17-2-15.
 */
@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username);
        //TODO 获取用户对应的所有权限
        List<GrantedAuthority> authorities =new  ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        authorities.add(new SimpleGrantedAuthority("MEMBER"));
        //TODO 拼装成UserDetails
        MyUserDetail userDetails = new MyUserDetail(users.getUsername(),users.getUsername(),users.getSalt(),true,true, true,true,authorities);
        return userDetails;
    }
}
