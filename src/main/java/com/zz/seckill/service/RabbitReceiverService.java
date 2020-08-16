package com.zz.seckill.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * RabbitMQ接收消息服务
 */
@Service
public class RabbitReceiverService {
    public static final Logger log = LoggerFactory.getLogger(RabbitReceiverService.class);
}
