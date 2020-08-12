package com.zz.seckill.Dao;

import com.zz.seckill.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderDao extends JpaRepository<Order,Long> {
}
