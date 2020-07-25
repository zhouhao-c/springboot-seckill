package com.zz.seckill.controller;

import com.zz.seckill.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController extends BaseController {

    @GetMapping("/admin/productList")
    public String productList(){
        return "admin/productList";
    }

    @ResponseBody
    @PostMapping("/admin/doAjaxLogin")
    public Object doAjaxLogin(String loginacct, String userpswd, HttpSession session){
        start();
        try{

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        return end();
    }
}
