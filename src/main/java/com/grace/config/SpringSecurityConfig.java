package com.grace.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.grace.config.MyUserDetailsService;

/**
 * Created by winson on 17-2-14.
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DataSource dataSource;

    @Qualifier("myUserDetailsService")
    MyUserDetailsService myUserDetailsService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new MyPasswordEncoder("md5",true));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                    .antMatchers("/m/**").hasAuthority("MEMBER")
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/doLogin")
                        .failureUrl("/login-error")
                        .permitAll()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                        .permitAll();
    }

/*    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .groupAuthoritiesByUsername(
                        " select g.id, g.group_name, ga.authority " +
                                "from group_members gm LEFT " +
                                "JOIN groups g on g.id = gm.group_id LEFT " +
                                "JOIN users u on u.id = gm.user_id LEFT " +
                                "JOIN group_authorities ga on g.id = ga.group_id " +
                                "where u.username = ?;")
                .authoritiesByUsernameQuery(
                        " select u.username,a.authority " +
                                "from authorities  a  " +
                                "LEFT JOIN users u on u.id = a.user_id " +
                                "where u.username = ?;");
    }*/


}
