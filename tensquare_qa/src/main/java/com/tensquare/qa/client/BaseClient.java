package com.tensquare.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//配置当前接口为远程调用客户端接口,并指定要从哪个微服务中远程低啊用
@FeignClient(value="tensquare-base",fallback = BaseClientImpl.class )
public interface BaseClient {
    //直接使用SpringMVC注解定义调用的接口,传递的参数和返回值
    @RequestMapping(value="/label/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId);
}
