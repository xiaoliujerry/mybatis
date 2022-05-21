package com.jiangnan.service.impl;

import com.jiangnan.annotation.MyComponent;
import com.jiangnan.domain.User;
import com.jiangnan.mapper.UserMapper;
import com.jiangnan.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
@MyComponent
public class MyServiceImpl implements MyService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(value = "USER", keyGenerator = "userGenerator")
    public User getUser(int id) {
        log.info("getUser + id: " + id);
        return userMapper.selectById(id);
    }
}
