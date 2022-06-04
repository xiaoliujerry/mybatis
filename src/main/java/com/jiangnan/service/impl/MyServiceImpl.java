package com.jiangnan.service.impl;

import com.jiangnan.annotation.MyComponent;
import com.jiangnan.domain.User;
import com.jiangnan.mapper.UserMapper;
import com.jiangnan.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
@Slf4j
@MyComponent
public class MyServiceImpl implements MyService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedissonClient redission;

    @Override
    @Cacheable(value = "USER", keyGenerator = "userGenerator")
    public User getUser(int id) {
        log.info("getUser + id: " + id);
        return userMapper.selectById(id);
    }

    public void testLock() throws InterruptedException {
//        RReadWriteLock readWriteLock = redission.getReadWriteLock("myLock");
//        RLock readLock = readWriteLock.readLock();
//        RLock writeLock = readWriteLock.writeLock();
        RLock rLock = redission.getLock("lock");
        boolean lock = rLock.tryLock(100, 10, TimeUnit.SECONDS);
        if (lock) {
            System.out.println("get lock!");
        }
    }

//    @Scheduled(fixedRate = 2, timeUnit = TimeUnit.SECONDS)
    public void schedule() {
        System.out.println("schedule...");
    }
}
