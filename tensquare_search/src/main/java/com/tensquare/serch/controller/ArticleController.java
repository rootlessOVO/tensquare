package com.tensquare.serch.controller;

import com.tensquare.serch.pojo.Article;
import com.tensquare.serch.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleService.save(article);
        return new Result(true, StatusCode.OK,"保存成功");
    }

    @RequestMapping(value = "/{key}/{page}/{size}",method = RequestMethod.GET)
    public Result findByKey(@PathVariable String  key,@PathVariable int page,@PathVariable int size){
       Page<Article> articles= articleService.findByKey(key,page,size);
        return new Result(true, StatusCode.OK,"保存成功",new PageResult<Article>(articles.getTotalElements(),articles.getContent()));
    }
}
