package com.zz.seckill.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求授权规则
        http.authorizeRequests().antMatchers("/**").permitAll();
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated();

        //http.formLogin().loginPage("/index");

        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");//注销成功后来到的页面

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,enabled from users WHERE username=?")
//                .authoritiesByUsernameQuery("select username,authority from authorities where username=?")
//                .passwordEncoder(new BCryptPasswordEncoder());
//        System.out.println(dataSource);
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
    }
}
