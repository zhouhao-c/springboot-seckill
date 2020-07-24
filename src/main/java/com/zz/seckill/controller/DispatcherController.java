package com.zz.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 调度控制器
 */
@Controller
public class DispatcherController {

    @RequestMapping(value= {"/index", "", "/", "/index/"})
    public String index() {
        return "index";
    }

}
