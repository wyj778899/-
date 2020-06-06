package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    //保存文章
    public void save(Article article){
        articleDao.save(article);
    }
    //搜索文章
    public Page<Article> findByKeyword(String  kewword,Integer page,Integer size){

        return  articleDao.findByTitleLikeOrContentLike(kewword,kewword, PageRequest.of(page-1, size));

    }

}
