package com.grace.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by winson on 17-2-15.
 */
public class MyUserDetail implements UserDetails {

    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private List<GrantedAuthority> authorities;
    private String salt;

    public MyUserDetail(String username, String password, String salt,boolean enabled, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.authorities = authorities;
        this.salt =salt;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {return this.accountNonExpired;}

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled();
    }

    public String getSalt() {return this.salt;}

}
