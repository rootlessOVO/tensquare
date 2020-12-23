package com.tensuquare.spit.controller;

import com.tensuquare.spit.pojo.Spit;
import com.tensuquare.spit.service.SpitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());}
    @RequestMapping(value = "/spitId",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId){return new Result(true,StatusCode.OK,"查询成功",spitService.findById(spitId));}
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@PathVariable Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"保存成功");
    }
    @RequestMapping(value = "/spitId",method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit,@PathVariable String spitId){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"更新成功");
    }
    @RequestMapping(value = "/spitId",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
