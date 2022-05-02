package com.jiangnan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jiangnan.domain.User;
import com.jiangnan.mapper.UserMapper;
import com.jiangnan.service.UserService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
* @author jerry
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2022-05-02 17:39:44
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(30, TimeUnit.SECONDS).build();
    LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(30, TimeUnit.SECONDS)
            .build(CacheLoader.from(this::getName));

    public String getName(String s) {
        String cacheResult = cache.getIfPresent(s);
        if (StringUtils.isNotBlank(cacheResult)) {
            return cacheResult;
        } else {
            String result = "cache" + s;
            cache.put(s, result);
            return "Tom" + s;
        }
    }
}




