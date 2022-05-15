package com.jiangnan;

import com.jiangnan.designpattern.handle.Handle;
import com.jiangnan.designpattern.handle.HandleAdapter;
import com.jiangnan.designpattern.processor.Step1;
import com.jiangnan.designpattern.processor.Step2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
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

    @Test
    public void testIntStream() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        int[] array = list.stream().mapToInt(Integer::intValue).toArray();
        List<Integer> result = IntStream.of(array).boxed().collect(Collectors.toList());
    }

    @Test
    public void testCollector() {
        String[] array = {"a", "b", "a", "c"};
        Map<String, Integer> countMap = Stream.of(array).collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
        countMap.entrySet().stream().sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue()).limit(2)
                .map(Map.Entry::getKey).forEach(System.out::println);
    }

    @Test
    public void testParallel() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            nums.add(i);
        }
        try {
            nums.parallelStream().forEach(num -> {
                System.out.println(Thread.currentThread().getName());
                if (num == 55) {
                    int result = num / 0;
                }
            });
        } catch (Exception e) {
//            log.info("error", e);
        }
    }

}
