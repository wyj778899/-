package com.tensquare.base.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
演示Spring定时器(与十次方无关)
 */
@Component
public class TaskDemo {
    /*
    @Scheduled 定时注解,描述方法什么时候执行
        String cron() default "";       填写Cron表达式

        String zone() default "";       指定时区的,默认使用系统所在时区

//--------------------------------------------------------------------------------------------------

        long fixedDelay() default -1L;
        String fixedDelayString() default "";   方法的执行间隔

            fixedDelay=2000 每隔2秒执行一次


        long fixedRate() default -1L;
        String fixedRateString() default "";    方法的执行间隔

            fixedRate=2000 每隔2秒执行一次

        long initialDelay() default -1L;
        String initialDelayString() default "";  方法第一次执行的延迟时间

            initialDelay=5000 spring容器启动成功5秒后才第一次执行
     */
//    @Scheduled()
    public void task(){
        System.out.println("定时任务方法执行了~");
    }

}
