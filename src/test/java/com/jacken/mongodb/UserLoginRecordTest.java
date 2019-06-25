package com.jacken.mongodb;

import com.jacken.mongodb.domain.UserLoginRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import sun.dc.pr.PRError;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserLoginRecordTest extends  MongodbApplicationTests {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    public void findAll(){
        List<UserLoginRecord> list = mongoTemplate.findAll(UserLoginRecord.class);
        System.out.println("================="+list.size());
        for (UserLoginRecord userLoginRecord : list) {
            System.out.println(userLoginRecord);
        }
    }
    @Test
    public  void  Aggregation(){
        //聚合查询针对某一个字段做分组查询  groupby
        Criteria criteria=Criteria.where("day").gte(20190613).lte(20190614);
        Query query = new Query();
        query.addCriteria(criteria);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("day").count().as("countNum"),
                Aggregation.sort(Sort.Direction.ASC,"day"),
                Aggregation.project("day","countNum").and("day").previousOperation());
        List<UserLoginRecord> recordList = mongoTemplate.aggregate(aggregation, "user_login_recode", UserLoginRecord.class).getMappedResults();
        recordList.stream().forEach(userLoginRecord -> System.out.println(userLoginRecord
        ));
//        List<UserLoginRecord> recordList = mongoTemplate.find(query, UserLoginRecord.class, "user_login_recode");
//        recordList.stream().forEach(userLoginRecord -> System.out.println(userLoginRecord));
    }
}


