package com.jiangnan.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Getter
public enum CacheEnum {

    USER(30, TimeUnit.SECONDS);

    private int expires;
    private TimeUnit timeUnit;
}
