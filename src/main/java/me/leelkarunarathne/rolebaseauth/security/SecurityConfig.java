package me.leelkarunarathne.rolebaseauth.security;

import me.leelkarunarathne.rolebaseauth.user.persistent.ROLE;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService()
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home").hasRole(String.valueOf(ROLE.ROLE_ONE))
                .antMatchers("/user1").hasRole(String.valueOf(ROLE.ROLE_TWO))
                .antMatchers("/user2").hasRole(String.valueOf(ROLE.ROLE_THREE))
                .and().formLogin();
    }
}
