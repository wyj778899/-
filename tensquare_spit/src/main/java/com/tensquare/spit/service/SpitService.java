package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import util.IdWorker;

import java.util.Date;
import java.util.List;

@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    //发布吐槽
    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");

        //初始化值
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        //判断是否为子吐槽
        if(!StringUtils.isEmpty(spit.getParentid())){
                //是子吐槽=>给父吐槽的回复数+1
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid())); // {"_id":spitid}
            //修改目标
            Update update = new Update();
            update.inc("comment", 1);

            template.updateFirst(query, update, "spit");
        }


        spitDao.save(spit);
    }
    //删
    public void delete(String spitId){
        spitDao.deleteById(spitId);
    }
    //改
    public void update(String spitId,Spit spit){
        spit.set_id(spitId);
        spitDao.save(spit);
    }
    //查 查询所有
    public List<Spit> findAll(){
        return spitDao.findAll();
    }
    //查 根据id查询
    public Spit findById(String spitId){
        return spitDao.findById(spitId).get();
    }

    //查 根据上级id 查询下级吐槽(评论)分页列表
    public Page<Spit> comment(String parentid ,Integer page,Integer size){
        return spitDao.findByParentid(parentid, PageRequest.of(page-1, size));
    }

    @Autowired
    private MongoTemplate template;

    //吐槽点赞  => $inc应用
    public void thumbup(String spitId){
    //查询条件
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId)); // {"_id":spitid}
    //修改目标
        Update update = new Update();
        update.inc("thumbup", 1);

        template.updateFirst(query, update, "spit");

    }

}
