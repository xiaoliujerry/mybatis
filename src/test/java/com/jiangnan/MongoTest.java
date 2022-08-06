package com.jiangnan;

import com.jiangnan.mongo.dao.MongoDao;
import com.jiangnan.mongo.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongoTest {

    @Autowired
    private MongoDao mongoDao;

    @Test
    public void testGetAllUsers() {
        mongoDao.getAllUsers().forEach(System.out::println);
    }

    @Test
    public void testFindUsers() {
        mongoDao.findUsers().forEach(System.out::println);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setName("Jim");
        user.setAge(10);
        user.setGender("男");
        System.out.println(mongoDao.insertUser(user));
    }

    @Test
    public void testAgg() {
        mongoDao.aggUsers();
    }
}
