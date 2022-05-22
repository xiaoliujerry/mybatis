package com.jiangnan.service.impl;

import com.jiangnan.annotation.MyComponent;
import com.jiangnan.service.TestService;
import org.springframework.retry.annotation.Retryable;

@MyComponent
public class TestServiceImpl implements TestService {
    @Override
    @Retryable(Exception.class)
    public void test() {
        System.out.println("test...");
        int i = 10 / 0;
    }
}
