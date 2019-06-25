package com.jacken.mongodb.controller;

import com.jacken.mongodb.Common.Result;
import com.jacken.mongodb.domain.RegistPageVisitRecord;
import com.jacken.mongodb.domain.User;
import com.jacken.mongodb.domain.UserLoginRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@RestController
public class UserController  {

    Logger logger=Logger.getLogger(UserController.class.getName());
    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/test")
    public ModelAndView test(ModelAndView modelAndView) {
        modelAndView.addObject("hello","这是我第一个使用Thymelead的页面！");
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String login() {
        logger.info("=====");
        return "home";
    }
    @RequestMapping("/getUser")
    public Result findUser(){
        long l = System.currentTimeMillis();
        List<User> list = mongoTemplate.findAll(User.class);
        long l1 = System.currentTimeMillis();
        logger.info("耗时:"+(l1-l));
       return  new Result(list);
    }
    @RequestMapping("/getUserLoginRecord")
    public  Result getUserLoginRecord(){
        long l = System.currentTimeMillis();
        List<UserLoginRecord> all = mongoTemplate.findAll(UserLoginRecord.class);
        long l1 = System.currentTimeMillis();
        logger.info("耗时:"+(l1-l));
        return   new Result(all);
    }


        @RequestMapping("/getRegistPageVisitRecord")
    public  Result getRegistPageVisitRecord(){
        long l = System.currentTimeMillis();
        List<RegistPageVisitRecord> all = mongoTemplate.findAll(RegistPageVisitRecord.class);
        logger.info("总共记录数:"+all.size());
        long l1 = System.currentTimeMillis();
        logger.info("耗时:"+(l1-l));
        return   new Result(all);
    }
    @RequestMapping("/insert")
    public  Result insert(){
        long l = System.currentTimeMillis();

        for (int i = 500000; i < 1000000; i++) {
            User user = new User(i+"","小明"+i);
           mongoTemplate.insert(user,"User");
        }

        long l1 = System.currentTimeMillis();
        logger.info("耗时:"+(l1-l));
         return new Result();
    }
    @RequestMapping("/getOne")
    public Result getOne(){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("小明567"));

        User user = mongoTemplate.findOne(query, User.class, "User");
        return new Result(user);
    }
    @RequestMapping("/findListByLike")
    public  Result findListByLike(){
        long l = System.currentTimeMillis();
        Query query = new Query();
        String name="小明5";
        Pattern pattern=Pattern.compile("^.*"+name+".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("name").regex(pattern));
        query.with(new Sort(Sort.Direction.ASC,"id"));
        Pageable pageable=new PageRequest(1,5);
        query.with(pageable);
        List<User> list = mongoTemplate.find(query, User.class, "User");
        logger.info("list--->>>>>>>>"+list);
        long l1 = System.currentTimeMillis();
        logger.info("耗时:"+(l1-l));
        return  new Result(list);


    }

}
