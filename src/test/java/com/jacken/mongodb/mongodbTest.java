package com.jacken.mongodb;

import com.jacken.mongodb.domain.User;
import com.jacken.mongodb.domain.UserLoginRecord;
import com.mongodb.client.result.UpdateResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

public class mongodbTest extends  MongodbApplicationTests{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    public void contextLoads() {

        List<User> list = mongoTemplate.findAll(User.class);
        System.out.println("user++++++++++++++"+list.size());
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public  void select(){
        Query query = new Query();
       query.addCriteria(Criteria.where("name").is("wangqiang"));
        User user = mongoTemplate.findOne(query, User.class);
        System.out.println(user);
    }

    @Test
    public  void insert(){
        List list = new ArrayList<>();
        list.add(new User("7","lisi3"));
        mongoTemplate.insert(list,"User");

    }

    @Test
    public  void find(){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is("12"));
        query.addCriteria(Criteria.where("name").is("list1"));
        List<User> list = mongoTemplate.find(query, User.class,"User");
        for (User user : list) {
            System.out.println(user);
        }

    }
    @Test
    public  void  findList(){
        Query query=new Query();
        query.addCriteria(Criteria.where("name").is("wangqiang"));
        List<User> list = mongoTemplate.find(query, User.class, "User");
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public  void update(){
        Query query =new Query();
        query.addCriteria(Criteria.where("_id").is(10));
        //有就更新没有就新增
        Update update = Update.update("name","lisi1231");
        UpdateResult result = mongoTemplate.upsert(query, update, User.class);
        System.out.println(result);

    }


    //模糊查询并分页排序
    @Test
    public  void findLike(){
        Query query = new Query();
        String name="lisi";
//        Pattern pattern=Pattern.compile("^.*"+name+".*$", Pattern.CASE_INSENSITIVE);
//        query.addCriteria(Criteria.where("name").regex(pattern));
        query.addCriteria(Criteria.where("name").is(name));
        //每页显示有几条
        Pageable pageable = new PageRequest(1, 3); // get 5 profiles on a page
        query.with(pageable);
        //排序
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"id")));
        List<User> list = mongoTemplate.find(query, User.class, "User");
        for (User user : list) {
            System.out.println(user);
        }

    }

//聚合查询
    @Test
    public  void findAggregation(){
        Criteria criteria = new Criteria();
//        String name="lisi";
//       Pattern pattern=Pattern.compile("^.*"+name+".*$", Pattern.CASE_INSENSITIVE);
//         criteria.regex(pattern);
        criteria.andOperator(Criteria.where("name").is("lisi5"));
        Aggregation aggregation = newAggregation(match(criteria));
//        List<User> user1 = mongoTemplate.aggregate(aggregation, "User", User.class).getMappedResults().stream().map(user -> new User(user.getId(), user.getName())).collect(Collectors.toList());
//        for (User user : user1) {
//            System.out.println(user);
//        }
        mongoTemplate.aggregate(aggregation,"User",User.class).getMappedResults().stream().forEach(user -> System.out.println(user));

    }
    @Test
    public  void findone(){
        String name="wangqiang";
        Criteria criteria = new Criteria();
        if(Optional.ofNullable(name).isPresent()){
            //Pattern pattern=Pattern.compile("^.*"+name+".*$", Pattern.CASE_INSENSITIVE);
            //criteria.and("name").is(name);
            criteria.andOperator(Criteria.where("name").is(name));
            Aggregation aggregation = newAggregation(match(criteria));
            mongoTemplate.aggregate(aggregation,"User",User.class).getMappedResults().stream().forEach(user -> System.out.println(user));
        }else {
            System.out.println("shu11111111111");
        }


    }

    @Test
    public  void findProject(){
        //查询的哪些字段
        ProjectionOperation operation = Aggregation.project("id", "name");
        String name="lisi";
        MatchOperation operation1 = match(Criteria.where("name").is(name));
        MatchOperation operation2 = match(Criteria.where("_id").gt(1));
        Aggregation aggregation = newAggregation(operation1, operation2, operation);
        AggregationResults<User> results = mongoTemplate.aggregate(aggregation, "User", User.class);
        results.getMappedResults().stream().forEach(user -> System.out.println(user));
    }

    @Test
    public  void count(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userPhone").is("14778563421"));
       // long count = mongoTemplate.count(query, UserLoginRecord.class, "user_login_recode");
        List<UserLoginRecord> list = mongoTemplate.find(query, UserLoginRecord.class, "user_login_recode");
        list.stream().forEach(s-> System.out.println(s));
        //System.out.println(count);
    }
    @Test
    public  void findPage(){
        Query query = new Query();
        String name="lisi";
        Pattern pattern=Pattern.compile("^.*"+name+".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("name").regex(pattern));
        Pageable pageable = new PageRequest(1,3);
        query.with(pageable);
        List<User> list = mongoTemplate.find(query, User.class,"User");
        list.stream().forEach(s-> System.out.println(s));
    }
    @Test
    public  void  sortDay(){
        Criteria criteria=Criteria.where("day").gte(20190613).lte(20190618);
        //聚合查询
//        Aggregation aggregation=newAggregation(match(criteria),
//                Aggregation.sort(Sort.Direction.DESC,"day"),
//                Aggregation.project("day","countNum").and("day").previousOperation(),
//                Aggregation.group("day").count().as("countNum"));



        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("day").count().as("countNum"),
                Aggregation.sort(Sort.Direction.ASC, "day"),
                Aggregation.project("day", "countNum").and("day").previousOperation()
        );
        AggregationResults<UserLoginRecord> user_login_recode = mongoTemplate.aggregate(aggregation, "user_login_recode", UserLoginRecord.class);
        user_login_recode.getMappedResults().stream().forEach(s-> System.out.println(s));
    }

    @Test
    public  void findPageList(){
        Query query = new Query();
        String name="lisi";
        Pattern pattern=Pattern.compile("^.*"+name+".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("name").regex(pattern));
        query.with(new Sort(Sort.Direction.ASC,"id"));
        //从0开始 0表示第一页
        query.with(new PageRequest(0,5));
        mongoTemplate.find(query, User.class, "User").stream().forEach(s-> System.out.println(s));

    }
}
