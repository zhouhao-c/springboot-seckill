package com.zz.seckill.service;

import com.zz.seckill.bean.Order;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * RabbitMQ发送消息服务
 */
@Service
public class RabbitSendService {
    public static final Logger log = LoggerFactory.getLogger(RabbitSendService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Environment env;
    @Autowired
    private OrderService orderService;
    /**
     * 秒杀成功异步发送邮件通用消息
     */
    public void sendKillSuccessEmailMsg(String order){
        log.info("秒杀成功异步发送邮件通用消息-准备发送消息：{}",order);

        try {
            Order dbOrder =  orderService.queryOrderByGoodNumber(order);
            if (dbOrder!=null){
                //TODO：rabbitmq发送消息的逻辑
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(env.getProperty("mq.kill.item.success.email.exchange"));
                rabbitTemplate.setRoutingKey(env.getProperty("mq.kill.item.success.email.routing.key"));

                //TODO：将info充当消息发送至队列
                rabbitTemplate.convertAndSend(dbOrder, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        MessageProperties messageProperties=message.getMessageProperties();
                        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,Order.class);
                        return message;
                    }
                });
            }

        }catch (Exception e){
            log.info("秒杀成功异步发送邮件通用消息-发生异常，消息为：{}",order,e.fillInStackTrace());
        }
    }

}
