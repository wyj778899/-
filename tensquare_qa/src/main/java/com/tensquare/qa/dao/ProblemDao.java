package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    //最新回答的问题列表 => 需求分析：最新回复的问题显示在上方， 按回复时间降序排序。

    @Query("select  p from  Problem p , PL pl  where p.id = pl.problemid and pl.labelid = ?1 order by p.replytime desc  ")
    Page<Problem> newList(String labelId, Pageable pageable);

    //热门问题列表 按回复数降序排序
    @Query("select  p from  Problem p , PL pl  where p.id = pl.problemid and pl.labelid = ?1 order by p.reply desc  ")
    Page<Problem> hotList(String labelId, Pageable pageable);


    //等待回答问题列表  安创建时间降序排列
    @Query("select  p from  Problem p , PL pl  where p.id = pl.problemid and pl.labelid = ?1  and p.reply = 0 order by p.createtime desc  ")
    Page<Problem> waitList(String labelId, Pageable pageable);
	
}
