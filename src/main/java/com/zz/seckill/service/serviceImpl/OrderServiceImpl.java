package com.zz.seckill.service.serviceImpl;

import com.zz.seckill.Dao.OrderDao;
import com.zz.seckill.Dao.mapper.OrderMapper;
import com.zz.seckill.bean.Order;
import com.zz.seckill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    public Order queryOrderByGoodNumberAndName(String number, String userName) {
        return orderMapper.queryOrderByGoodNumberAndName(number,userName);
    }

    @Override
    public Order queryOrderByGoodNumber(String number) {
        return orderMapper.queryOrderByGoodNumber(number);
    }

    @Override
    public Order queryOrderByGoodCode(String orderCode) {
        return orderMapper.queryOrderByGoodCode(orderCode);
    }

}
