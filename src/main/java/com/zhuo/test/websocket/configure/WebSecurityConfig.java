package com.zhuo.test.websocket.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            //设置拦截规则
            .antMatchers("/","/login")   ////1根路径和/login路径不拦截
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            //开启默认登录页面
            .formLogin()
            //默认登录页面
            .loginPage("/login")
            //默认登录成功跳转页面
            .defaultSuccessUrl("/broadcast/index")
            .permitAll()
            .and()
            //设置注销
            .logout()
            .permitAll();
    }
    //设置用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("111").password("111").roles("USER")
            .and()
            .withUser("222").password("222").roles("USER")
            .and()
            .withUser("333").password("333").roles("USER")
            .and()
            .withUser("444").password("444").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置不拦截规则
        web.ignoring().antMatchers("/resources/static/**");
    }
}