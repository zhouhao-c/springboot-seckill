package com.zz.seckill.service;

import com.zz.seckill.bean.MailDto;
import com.zz.seckill.bean.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * RabbitMQ接收消息服务
 */
@Service
public class RabbitReceiverService {
    public static final Logger log = LoggerFactory.getLogger(RabbitReceiverService.class);
    @Autowired
    private MailService mailService;

    @Autowired
    private Environment env;

    /**
     * 秒杀异步邮件通知-接收消息
     */
    @RabbitListener(queues = {"${mq.kill.item.success.email.queue}"},containerFactory = "singleListenerContainer")
    public void consumeEmailMsg(Order order){
        try {
            log.info("秒杀异步邮件通知-接收消息:{}",order);

            //TODO:真正的发送邮件....
            //MailDto dto=new MailDto(env.getProperty("mail.kill.item.success.subject"),"这是测试内容",new String[]{info.getEmail()});
            //mailService.sendSimpleEmail(dto);

            final String content=String.format(env.getProperty("mail.kill.item.success.content"),order.getGoodName(),order.getGoodNumber());
            MailDto dto=new MailDto(env.getProperty("mail.kill.item.success.subject"),content,new String[]{order.getUserEmail()});
            mailService.sendHTMLMail(dto);

        }catch (Exception e){
            log.error("秒杀异步邮件通知-接收消息-发生异常：",e.fillInStackTrace());
        }
    }
}
