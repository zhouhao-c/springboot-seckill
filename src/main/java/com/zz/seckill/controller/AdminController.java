package com.zz.seckill.controller;

import com.zz.seckill.bean.Goods;
import com.zz.seckill.bean.Page;
import com.zz.seckill.common.util.StringUtil;
import com.zz.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController extends AjaxLoginController{

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/productList")
    public String productList(){
        return "/admin/productList";
    }
//    @PostMapping("/login")
//    public String login(String username, String password, Model model, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        User user = userService.findUserName(username);
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            model.addAttribute("error", "用户名或密码错误");
//            return "index";
//        }
//        // 这句代码会自动执行咱们自定义的 ```MyUserDetailService.java``` 类
//        Authentication authentication = myAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//
//        if (!authentication.isAuthenticated()) {
//            throw new BadCredentialsException("Unknown username or password");
//        }
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authentication);
//        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
//        session.setAttribute("SESSION_OPERATOR", user);
//        userService.saveOperateLog(user, request.getRemoteAddr());
//        return "admin/productList";
//    }

    @ResponseBody
    @PostMapping("/pageQuery")
    public Object pageQuery(Integer pageno,Integer pagesize,String queryText){
        start();
        try {
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("start",(pageno-1)*pagesize);
            paramMap.put("size",pagesize);
            //特殊字符转义
            if (!StringUtil.isEmpty(queryText)){
                if (queryText.contains("\\")){
                    queryText = queryText.replaceAll("\\\\","\\\\\\\\");
                }
                if (queryText.contains("%")){
                    queryText = queryText.replaceAll("%","\\\\%");
                }
                if (queryText.contains("_")){
                    queryText = queryText.replaceAll("_","\\\\_");
                }
            }
            paramMap.put("queryText",queryText);

            //查询总数
            int totalsize = goodsService.queryPageCount(paramMap);

            int totalno = 0;
            if (totalsize % pagesize == 0){
                totalno = totalsize / pagesize;
            }else {
                totalno = totalsize / pagesize + 1;
            }

            //获取分页数据
            List<Goods> goods = goodsService.queryPageData(paramMap);
            Page<Goods> page = new Page<Goods>();
            page.setDatas(goods);
            page.setTotalsize(totalsize);
            page.setTotalno(totalno);
            page.setPageno(pageno);
            page.setPagesize(pagesize);

            data(page);
            success();
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
       return end();
    }

}
