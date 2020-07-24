package com.zz.seckill.controller;

import com.zz.seckill.bean.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @RequestMapping("/productList")
    public String productList(){
        return "admin/productList";
    }

    @ResponseBody
    @RequestMapping("/doAjaxLogin")
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
