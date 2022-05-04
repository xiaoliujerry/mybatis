package com.jiangnan;

import com.jiangnan.designpattern.handle.Handle;
import com.jiangnan.designpattern.handle.HandleAdapter;
import com.jiangnan.designpattern.processor.Step1;
import com.jiangnan.designpattern.processor.Step2;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class MyTest {

    @Test
    public void test() {
//        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(get(2, String::valueOf));
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("async");
        }).join();
    }

    private String get(int i, Function<Integer, String> converter) {
        return converter.apply(i);
    }

    @Test
    public void testProcessor() {
        Step2 step2 = new Step2(null);
        Step1 step1 = new Step1(step2);
        step1.handle();
    }

    @Test
    public void testHandle() {
        Handle handle3 = () -> System.out.println("handle3 complete");
        Handle handle4 = () -> System.out.println("handle4 complete");
        HandleAdapter adapter = new HandleAdapter();
        adapter.register(handle3);
        adapter.register(handle4);
        adapter.handle();
    }
}
