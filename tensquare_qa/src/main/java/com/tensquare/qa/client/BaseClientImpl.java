package com.tensquare.qa.client;

import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findById(String labelId) {
        //备用方案
        System.out.println("原方案调用失败,启用备用方案!!!!");
        return new Result(true, StatusCode.OK,"执行备用方案了!!!!!");
    }
}
