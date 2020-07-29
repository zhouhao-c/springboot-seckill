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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager myAuthenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/productList")
    public String productList(){
        return "admin/productList";
    }

    @ResponseBody
    @PostMapping("/doAjaxLogin")
    public Object doAjaxLogin(String loginacct, String userpswd, HttpSession session){
        start();
        try{

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        return end();
    }

    @PostMapping("/login")
    public String login(String userName, String password, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = userService.findByUserName(userName);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("error", "用户名或密码错误");
            return "index";
        }
        // 这句代码会自动执行咱们自定义的 ```MyUserDetailService.java``` 类
        Authentication authentication = myAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Unknown username or password");
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        session.setAttribute("SESSION_OPERATOR", user);
        userService.saveOperateLog(user, request.getRemoteAddr());
        return "admin/productList";
    }


}
