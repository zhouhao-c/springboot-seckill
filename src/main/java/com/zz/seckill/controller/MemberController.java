package com.zz.seckill.controller;

import ch.qos.logback.core.net.SyslogConstants;
import com.zz.seckill.bean.Description;
import com.zz.seckill.bean.Goods;
import com.zz.seckill.bean.Order;
import com.zz.seckill.bean.Page;
import com.zz.seckill.common.BaseController;
import com.zz.seckill.common.util.StringUtil;
import com.zz.seckill.service.DescriptionService;
import com.zz.seckill.service.GoodsService;
import com.zz.seckill.service.OrderService;
import com.zz.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

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

    @ResponseBody
    @PostMapping("/killSuccess")
    public Object killSuccess(Goods goods, HttpSession session){
        start();
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Goods dbgood = goodsService.queryById(goods.getId());
        try {
            Order order = new Order();
            //order.setCode();
            order.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            order.setGoodName(dbgood.getName());
            order.setGoodNumber(dbgood.getNumber());
            //获取用户名
            String userName = ((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
            order.setUserTelephone(userService.queryTelephoneByName(userName));
            order.setUserName(userName);
            //order.setStatus();

            //每个用户限购一次 商品编号goodNumber不能重复
            Order flg = orderService.queryOrderByGoodNumberAndName(dbgood.getNumber(),userName);
            if (flg!=null){
                //商品编号重复
                fail();
                data(101);//每个用户商品限购一次
                return end();
            }

            int res = orderService.insertOrder(order);
            if (res>0){
                //进行异步邮件消息的通知 rabbitMQ+email
            }
            success(res>0);
            //抢购成功商品库存减1
            goodsService.updateStockById(goods.getId());
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }

        return end();
    }
}
