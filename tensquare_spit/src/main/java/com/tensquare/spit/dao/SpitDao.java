package com.tensquare.spit.dao;

import com.mongodb.Mongo;
import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitDao extends MongoRepository<Spit,String> {
    //根据上级id 查询下级吐槽(评论)分页列表
    Page<Spit> findByParentid(String parentid, Pageable pageable);
}
