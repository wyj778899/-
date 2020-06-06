package com.tensquare.friend.client;

import entity.Result;
import entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("tensquare-user")
public interface UserClient {

    @RequestMapping(value="/user/follow/{userid}/{count}",method= RequestMethod.PUT)
    public Result updateFollow(@PathVariable("userid") String userid, @PathVariable("count") Integer count);

    @RequestMapping(value="/user/fans/{userid}/{count}",method= RequestMethod.PUT)
    public Result updateFans(@PathVariable("userid") String userid,@PathVariable("count") Integer count);
}
