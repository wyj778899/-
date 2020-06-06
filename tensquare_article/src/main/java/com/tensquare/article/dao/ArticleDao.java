package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    //文章审核
    @Modifying//标识该方法需要修改数据库
    @Query(nativeQuery = true,value=" update tb_article set state = '1' where id = ?1 ")//nativeQuery:是否使用SQL语句.默认值:false
    void examine(String articleId);
    //文章点赞
    @Modifying//标识该方法需要修改数据库
    @Query(nativeQuery = true,value=" update tb_article set thumbup = thumbup+1 where id = ?1 ")
    void thumbup(String articleId);
	
}
