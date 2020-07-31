package com.zz.seckill;


import com.zz.seckill.Dao.GoodsDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootSeckillApplicationTests {

    @Autowired
    private GoodsDao goodsDao;

    @Test
    public void contextLoads() {
        System.out.println(goodsDao.count());
    }

}
