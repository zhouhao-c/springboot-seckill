package com.zz.seckill.Dao;

import com.zz.seckill.bean.Description;
import com.zz.seckill.bean.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface GoodsDao extends JpaRepository<Goods, Long> {
}
