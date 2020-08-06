package com.zz.seckill.config.security;

import com.zz.seckill.handler.LoginSuccessHandler;
import com.zz.seckill.interceptor.MyFilterSecurityInterceptor;
import com.zz.seckill.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Service;


/**
 * @EnableWebSecurity: 禁用Boot的默认Security配置，配合@Configuration启用自定义配置（需要扩展WebSecurityConfigurerAdapter）
 * @EnableGlobalMethodSecurity(prePostEnabled = true): 启用Security注解，例如最常用的@PreAuthorize
 * configure(HttpSecurity): Request层面的配置，对应XML Configuration中的<http>元素
 * configure(WebSecurity): Web层面的配置，一般用来配置无需安全检查的路径
 * configure(AuthenticationManagerBuilder): 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
 */
@EnableWebSecurity
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Bean
    @Primary
    public DefaultWebInvocationPrivilegeEvaluator customWebInvocationPrivilegeEvaluator() {
        return new DefaultWebInvocationPrivilegeEvaluator(myFilterSecurityInterceptor);
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // javaconfig 配置是这样 set 进去的.
        web.securityInterceptor(myFilterSecurityInterceptor);
        web.privilegeEvaluator(customWebInvocationPrivilegeEvaluator());
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/resources/**","/**/*.html","/bootstrap/**","/layer/**","/jquery/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers( "/","/index","/doAjaxLogin","/welcome","/resources/public/**").permitAll()//访问：这些路径 无需登录认证权限
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
                //.antMatchers("/admin/**").hasAuthority("admin") //登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示
                .and()
                .formLogin()
                .loginPage("/")//指定登录页是”/”
                .permitAll()
                .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/") //退出登录后的默认网址是”/home”
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        // .and()
        //.rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
        //.tokenValiditySeconds(1209600);
        http.rememberMe().rememberMeParameter("remember");
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //指定密码加密所使用的加密器为passwordEncoder()
        //需要将密码加密后写入数据库
        auth.userDetailsService(myUserDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
