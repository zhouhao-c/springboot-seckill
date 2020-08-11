package com.zz.seckill.controller;

import com.zz.seckill.bean.Description;
import com.zz.seckill.bean.Goods;
import com.zz.seckill.bean.Page;
import com.zz.seckill.common.BaseController;
import com.zz.seckill.common.util.StringUtil;
import com.zz.seckill.service.DescriptionService;
import com.zz.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private DescriptionService descriptionService;

    @RequestMapping("/productList")
    public String productList(){
        return "/member/productList";
    }

    @RequestMapping("/details")
    public String details(Long id, Model model){
        Goods goods = goodsService.queryById(id);
        Description description = descriptionService.queryById(goods.getDescriptionId());
        model.addAttribute("goods",goods);
        model.addAttribute("des",description);
        return "/member/details";
    }

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
