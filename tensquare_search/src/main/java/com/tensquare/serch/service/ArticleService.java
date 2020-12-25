package com.tensquare.serch.service;

import com.tensquare.serch.dao.ArticleDao;
import com.tensquare.serch.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private IdWorker idWorker;

    public void save(Article article){
        articleDao.save(article);
    }

    public Page<Article> findByKey(String key,int page,int size) {
        Pageable pageable= PageRequest.of(page,size);
       return articleDao.findByTitleOrContentLike(key,key,pageable);
    }
}
