package com.zz.seckill.Dao.mapper;

import com.zz.seckill.bean.Order;
import org.springframework.stereotype.Component;

@Component
public interface OrderMapper {
    int insertOrder(Order order);


    Order queryOrderByGoodNumberAndName(String number, String userName);

    Order queryOrderByGoodNumber(String number);
}
