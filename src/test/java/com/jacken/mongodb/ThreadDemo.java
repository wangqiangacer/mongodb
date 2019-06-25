package com.jacken.mongodb;

import com.jacken.mongodb.domain.UserLoginRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class ThreadDemo extends MongodbApplicationTests implements Runnable  {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void run() {
        try {
          findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public  void findAll(){
        List<UserLoginRecord> list = mongoTemplate.findAll(UserLoginRecord.class);
        System.out.println("================="+list.size());
        for (UserLoginRecord userLoginRecord : list) {
            System.out.println(userLoginRecord);
        }
    }
}
