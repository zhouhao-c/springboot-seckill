package com.zz.seckill.Dao;

import com.zz.seckill.bean.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsDao extends JpaRepository<Goods, Long> {

}
