package com.zz.seckill.service;

import com.zz.seckill.bean.Order;

public interface OrderService {
    int insertOrder(Order order);

    Order queryOrderByGoodNumberAndName(String number, String userName);

    Order queryOrderByGoodNumber(String number);
}
