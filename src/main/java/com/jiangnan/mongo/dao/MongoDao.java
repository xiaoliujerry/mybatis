package com.jiangnan.mongo.dao;

import com.jiangnan.mongo.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class MongoDao {

    private static final String COLLECTION_NAME = "test";

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    public List<User> findUsers() {
        Criteria criteria = Criteria.where("age").gt(10).orOperator(Criteria.where("name").is("James"), Criteria.where("name").is("Tom"));
//        Criteria criteria1 = Criteria.where("name").is("James");
        Query query = new Query();
        query.addCriteria(criteria);
//        query.addCriteria(criteria1);
        query.with(PageRequest.of(0, 10));
        query.with(Sort.by(Sort.Order.asc("age")));
        long count = mongoTemplate.count(query, User.class);
        System.out.println(count);
        return mongoTemplate.find(query, User.class);
    }

    public void aggUsers() {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("age").gt(10));
        GroupOperation groupOperation = Aggregation.group("gender").sum("age").as("totalAge").count().as("nums");
        Aggregation aggregation = TypedAggregation.newAggregation(matchOperation, groupOperation);
        //执行聚合查询，一map返回结果
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "test", Map.class);
        Iterator<Map> iterator = results.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public User insertUser(User user) {
        return mongoTemplate.insert(user);
    }
}
