package com.tensuquare.spit.controller;

import com.tensuquare.spit.pojo.Spit;
import com.tensuquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SpitService spitService;
    @Autowired
    private IdWorker idWorker;
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());}

    @RequestMapping(value = "/{spitId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(spitId));
    }
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"保存成功");
    }
    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit,@PathVariable String spitId){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"更新成功");
    }
    @RequestMapping(value = "/{spitId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/thumbup/{spitId}",method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId){
        spitService.thumbup(spitId);
        String userId="111";
        if (redisTemplate.opsForValue().get("thumpbup_"+userId)!=null){
            return new Result(false,StatusCode.REPERROR,"不能重复点赞");
        };
        redisTemplate.opsForValue().set("thumpbup_"+userId,1);
        return new Result(true,StatusCode.OK,"点赞成功");
    }
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result search(@RequestBody Spit spit){
        List<Spit> spitList= spitService.search(spit);
        return new Result(true,StatusCode.OK,"查询成功",spitList);
    }
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result searchPage(@RequestBody Spit spit,@PathVariable int page,@PathVariable int size){
        Page<Spit> spitPage= spitService.searchPage(spit,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(spitPage.getTotalElements(),spitPage.getContent()));
    }
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method = RequestMethod.GET)
    public Result comment(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit> spitPage= spitService.findByParentid(parentid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(spitPage.getTotalElements(),spitPage.getContent()));
    }
}
