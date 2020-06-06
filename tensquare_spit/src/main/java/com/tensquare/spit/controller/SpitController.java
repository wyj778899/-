package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }
    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public Result findOne(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(id));
    }
    /**
     * 增加
     * @param spit
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody Spit spit ){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"增加成功");
    }
    /**
     北京市昌平区建材城西路金燕龙办公楼一层 电话：400-618-9090
     4.2.3 根据上级ID查询吐槽列表
     （1）SpitDao新增方法定义
     （2）SpitService新增方法
     * 修改
     * @param spit
     */
    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public Result update(@RequestBody Spit spit,@PathVariable String id )
    {
        spitService.update(id,spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }
    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id ){
        spitService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }


    /**
     * 根据父级id查询子吐槽分页列表
     * @return
     */
    @RequestMapping(value="/comment/{parentid}/{page}/{size}",method= RequestMethod.GET)
    public Result comment(@PathVariable String parentid,@PathVariable Integer page,@PathVariable Integer  size){
        Page<Spit> pageBean = spitService.comment(parentid, page, size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Spit>(pageBean.getTotalElements(),pageBean.getContent()));
    }

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 吐槽点赞  => 控制不能重复点赞
     * @param spitId
     */
    @RequestMapping(value="/thumbup/{spitId}",method=RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId ){
        //模拟登陆用户
        String userid = "888";

        //点赞前,从redis中获得点赞记录
        Object o = redisTemplate.opsForValue().get("spit_thumbup:" + userid + "_" + spitId);

        if (o!=null){
            //获得到=> 返回错误信息,提示不能重复点赞
            return new Result(false,StatusCode.REPERROR,"请勿重复点赞");
        }
        //获得不到=>点赞,放入redis
        spitService.thumbup(spitId);
        //放入redis
        redisTemplate.opsForValue().set("spit_thumbup:" + userid + "_" + spitId,"");
        return new Result(true,StatusCode.OK,"点赞成功");
    }
}
