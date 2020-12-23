package com.tensuquare.spit.service;

import com.tensuquare.spit.dao.SpitDao;
import com.tensuquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.List;

@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    public  List<Spit> findAll(){
        return spitDao.findAll();
    }
    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spitDao.save(spit);
    }
    public void deleteById(String id){
        spitDao.deleteById(id);
    }

    public void update(Spit spit) {
        spitDao.save(spit);
    }
}
