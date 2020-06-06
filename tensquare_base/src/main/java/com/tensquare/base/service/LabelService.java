package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;


    @Autowired
    private IdWorker idWorker;

    //增
    public void save(Label label){

        label.setId(idWorker.nextId()+"");

        labelDao.save(label);
    }
    //删
    public void delete(String labelId){
        labelDao.deleteById(labelId);
    }
    //改
    public void update(String labelId,Label label){
        label.setId(labelId);
        labelDao.save(label);
    }
    //查 根据id查
    public Label findById(String labelId){
        return labelDao.findById(labelId).get();
    }
    //查 查询所有
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    //分页条件查询
    public Page<Label> findPageByCondition(Label label,Integer page,Integer size){

       return labelDao.findAll((root,query,builder)->{
           //准备集合封装查询条件
            List<Predicate> list = new ArrayList<>();

           //labelname  先判断是否有该条件,再组装条件
           if(!StringUtils.isEmpty(label.getLabelname())){
             Predicate predicate = builder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
               list.add(predicate); //将条件放入集合
           }
           //recommend
           if(!StringUtils.isEmpty(label.getRecommend())){
               Predicate predicate = builder.equal(root.get("recommend").as(String.class), label.getRecommend());
                list.add(predicate);
           }
           //state
           if(!StringUtils.isEmpty(label.getState())){
               Predicate predicate = builder.equal(root.get("state").as(String.class), label.getState());
               list.add(predicate);
           }
           if (list.isEmpty()){ //集合为空
                return null; //没有条件
           }else{ //不为空,多条件使用and连接
               return builder.and(list.toArray(new Predicate[list.size()]));
           }
        }, PageRequest.of(page-1, size));

    }

}
