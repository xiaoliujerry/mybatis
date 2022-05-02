package com.jiangnan.dao;

import com.jiangnan.domain.User;
import com.jiangnan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MyDao {

    @Autowired
    private UserMapper userMapper;

    private User getUser(int id) {
        return userMapper.selectById(id);
    }
}
