package test;

import com.tensquare.sms.SmsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//创建容器
@RunWith(SpringRunner.class)
//指定创建容器的配置文件(类)
@SpringBootTest(classes = SmsApplication.class)
public class RabbitTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    //直接模式-> 发送消息
    public void fun1() {
        //参数1: 队列名称
        //参数2: 消息内容
        rabbitTemplate.convertAndSend("q1", "hello rabbit!");

    }
    @Test
    //分列模式-> 发送消息
    public void fun2() {
        //参数1: 交换器名称
        //参数2: 不用管
        //参数3: 消息内容
        rabbitTemplate.convertAndSend("myfanout", "", "hello fanout!");

    }

    @Test
    //主题模式-> 发送消息
    public void fun3() {
        //参数1: 交换器名称
        //参数2: routingkey
        //参数3:消息内容
//        rabbitTemplate.convertAndSend("mytopic", "haha.hehe", "hello topic!");
//        rabbitTemplate.convertAndSend("mytopic", "haha.heihei", "hello topic!");
        rabbitTemplate.convertAndSend("mytopic", "heihei.hehe", "hello topic!");


    }

    @Test
    //接收消息
    public void recive() {
        while (true){}
    }
}
