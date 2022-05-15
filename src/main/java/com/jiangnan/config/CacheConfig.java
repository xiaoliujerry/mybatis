package com.jiangnan.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.jiangnan.common.CacheEnum;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caffeineCaches = new ArrayList<>();
        for (CacheEnum cacheEnum : CacheEnum.values()) {
            caffeineCaches.add(new CaffeineCache(cacheEnum.name()
                    , Caffeine.newBuilder()
                            .expireAfterWrite(cacheEnum.getExpires(), cacheEnum.getTimeUnit())
                            .build()));
        }
        cacheManager.setCaches(caffeineCaches);
        return cacheManager;
    }

    @Bean
    public KeyGenerator userGenerator() {
        return (target, method, params) -> method.getName() + "_" + Stream.of(params).map(Object::toString).collect(Collectors.joining("_"));
    }
}
