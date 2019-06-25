package com.jacken.mongodb;

import com.jacken.mongodb.domain.User;
import com.jacken.mongodb.domain.UserLoginRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbApplicationTests    {

    @Autowired
    private  MongoTemplate mongoTemplate;
  @Test
  public  void test(){

      Criteria criteria=Criteria.where("name").is("小明345");
      Aggregation aggregation=Aggregation.newAggregation(Aggregation.match(criteria));
      mongoTemplate.aggregate(aggregation,"User",User.class).getMappedResults().stream().forEach(user -> System.out.println(user));
  }
  @Test
  public  void findPage(){
      Query query = new Query();
      query.addCriteria(Criteria.where("id").is("200"));
     // query.with(new Sort(Sort.Direction.DESC,"id"));
     mongoTemplate.find(query,User.class,"User").stream().forEach(user -> System.out.println(user));
  }
  @Test
  public  void findpageLike(){
      //模糊查询加分页
      Query query = new Query();
      String name="lisi";
      Pattern pattern=Pattern.compile("^.*"+name+".*$", Pattern.CASE_INSENSITIVE);
      query.addCriteria(Criteria.where("name").regex(pattern));
      query.with(new Sort(Sort.Direction.ASC,"id"));
      List<User> list = mongoTemplate.find(query, User.class, "User");
      for (User user : list) {
          System.out.println(user);
      }
  }
  @Test
  public  void findListToMap(){
      //list转map
      Query query = new Query();
      query.addCriteria(Criteria.where("day").gt(20190613));
      List<UserLoginRecord> user_login_recode = mongoTemplate.find(query, UserLoginRecord.class, "user_login_recode");
      List<String> collect = user_login_recode.stream().map(UserLoginRecord::getUserPhone).collect(Collectors.toList());
      collect.stream().forEach(s -> System.out.println(s));
  }
}
