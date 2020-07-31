package com.zz.seckill.controller;

import com.zz.seckill.bean.User;
import com.zz.seckill.common.BaseController;
import com.zz.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AjaxLoginController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager myAuthenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ResponseBody
    @PostMapping("/doAjaxLogin")
    public Object doAjaxLogin(String username, String password, HttpServletRequest request){
        start();
        try{
            HttpSession session = request.getSession();
            User user = userService.findUserName(username);
            if (!passwordEncoder.matches(password, user.getPassword())) {
                fail();
            }
            // 这句代码会自动执行咱们自定义的 ```MyUserDetailService.java``` 类
            Authentication authentication =
                    myAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (!authentication.isAuthenticated()) {
                throw new BadCredentialsException("Unknown username or password");
            }
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            userService.saveOperateLog(user, request.getRemoteAddr());
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            success();
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        return end();
    }
}
