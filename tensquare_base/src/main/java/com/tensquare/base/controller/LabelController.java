package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//将该类加入重新加载配置列表
@RefreshScope
//允许跨域访问
@CrossOrigin
@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    //增
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){

        labelService.save(label);
        return new Result(true, StatusCode.OK,"保存成功");
    }
    //删
    @RequestMapping(value="/{labelId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String labelId ){
        labelService.delete(labelId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    //修改
    @RequestMapping(value="/{labelId}",method = RequestMethod.PUT)
    public Result update(@PathVariable String labelId ,@RequestBody Label label){
        labelService.update(labelId, label);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    //根据id查询
    @RequestMapping(value="/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId){
        System.out.println("NO.2");
        //int i = 1/0;
        return new Result(true, StatusCode.OK,"查询成功",labelService.findById(labelId));
//        return new Result(false, StatusCode.ERROR,"查询出错了");

    }

    //根据id查询
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){

        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
        }
    @Value("${haha.hehe}")
    private String hehe;

    //分页条件查询
    @RequestMapping(value="/search/{page}/{size}",method = RequestMethod.POST)
    public Result search(@RequestBody Label label,@PathVariable Integer page,@PathVariable Integer size){
        System.out.println(hehe);
        //调用service获得分页数据
        Page<Label> pageBean = labelService.findPageByCondition(label, page, size);


        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Label>(pageBean.getTotalElements(),pageBean.getContent()));
    }

}
