package com.tensquare.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//指定该类是消息监听类,参数指定监听哪个队列
@RabbitListener(queues = {"q2"})
@Component
public class RabbitListener2 {

    //指定接收消息的方法
    @RabbitHandler
    public  void  recive(String message){
        System.out.println("q2接收到消息:"+message);
    }

}
