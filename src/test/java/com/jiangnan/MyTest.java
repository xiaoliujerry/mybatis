package com.jiangnan;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class MyTest {

    @Test
    public void test() {
        System.out.println(Runtime.getRuntime().availableProcessors());

    }

    private String get(int i, Function<Integer, String> converter) {
        return converter.apply(i);
    }
}
