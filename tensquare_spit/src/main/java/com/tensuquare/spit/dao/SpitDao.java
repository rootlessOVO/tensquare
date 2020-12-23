package com.tensuquare.spit.dao;

import com.tensuquare.spit.pojo.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitDao extends MongoRepository<Spit,String> {

}
