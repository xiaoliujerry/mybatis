package com.jiangnan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangnan.domain.User;
import com.jiangnan.service.UserService;
import com.jiangnan.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author jerry
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2022-05-02 17:39:44
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




