package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

//指定该类是Controller层通知类
@ControllerAdvice
public class MyExceptionHandler {
    //结构以json返回
    @ResponseBody
    @ExceptionHandler(value=Exception.class)
    public Result handler(Exception e){
        return new Result(false, StatusCode.ERROR,"出错了:"+e.getMessage());
    }

}
