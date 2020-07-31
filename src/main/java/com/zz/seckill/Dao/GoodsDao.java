package com.zz.seckill.Dao;

import com.zz.seckill.bean.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface GoodsDao extends JpaRepository<Goods, Long> {

}
