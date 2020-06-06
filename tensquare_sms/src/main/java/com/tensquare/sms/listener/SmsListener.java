package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.util.SmsUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

//指定该类是消息监听类,参数指定监听哪个队列
@RabbitListener(queues = {"sms"})
@Component
public class SmsListener {
    @Value("${sms.aliyun.signname}")
    private String signname;
    @Value("${sms.aliyun.templatecode}")
    private String templatecode;

    //指定接收消息的方法
    @RabbitHandler
    public  void  recive(Map<String,String> map){

        //{"number":""}
        try {
            SmsUtils.sendSms(map.get("mobile"), "{\"number\":\""+map.get("checkcode")+"\"}",signname,templatecode);
        } catch (ClientException e) {
            e.printStackTrace();
        }

      /*  System.out.println(map.get("checkcode"));
        System.out.println(map.get("mobile"));*/
    }

}
