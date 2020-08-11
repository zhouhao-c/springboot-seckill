package com.zz.seckill.controller;

import com.zz.seckill.Dao.mapper.GoodsMapper;
import com.zz.seckill.bean.Description;
import com.zz.seckill.bean.Goods;
import com.zz.seckill.bean.Page;
import com.zz.seckill.common.util.FileUpload;
import com.zz.seckill.common.util.StringUtil;
import com.zz.seckill.service.DescriptionService;
import com.zz.seckill.service.GoodsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController extends AjaxLoginController{

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private DescriptionService descriptionService;

    @RequestMapping("/productList")
    public String productList(){
        return "/admin/productList";
    }

    @RequestMapping("/add")
    public String add(){
        return "/admin/add";
    }

    @RequestMapping("/edit")
    public String edit(Long id,Model model){
        Goods good = goodsService.queryById(id);
        model.addAttribute("good",good);
        return "/admin/edit";
    }

    @RequestMapping("/addDescription")
    public String addDescription() {
        return "/admin/addDescription";
    }

    /**
     * AJAX分页查询
     * @param pageno
     * @param pagesize
     * @param queryText
     * @return
     */
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
    @PostMapping("/insert")
    public Object insert(Goods goods,Model model){
        start();
        try {
            goods.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            goods.setDescriptionId(descriptionService.queryId());
            goodsService.insertGoodsCategory(goods);
            success();
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
       return end();
    }

    @PostMapping("/insertDes")
    public String insertDes(Description description){
        MultipartFile imgFile = description.getImgFile();
        String imgFileName = imgFile.getOriginalFilename();
        String filePath = "D:\\IDEAworkspace\\springboot-seckill\\src\\main\\resources\\upload\\";
        String uuid = UUID.randomUUID().toString();
        String suffix = imgFileName.substring(imgFileName.lastIndexOf("."));
        String targetFileName = uuid+suffix;
        File file = new File(filePath+targetFileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            imgFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/upload/"+targetFileName;
        description.setIconpath(filename);
        descriptionService.insertDescription(description);
        return "/admin/add";
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(Goods goods){
        start();
        try {
            int cnt = goodsService.updateGoods(goods);
            success(cnt==1);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        return end();
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(Integer id){
        start();
        try {
            int cnt = goodsService.deleteGoodsById(id);
            success(cnt == 1);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        return end();
    }

}
