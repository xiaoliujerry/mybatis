package com.jiangnan.service.impl;

import com.jiangnan.annotation.MyComponent;
import com.jiangnan.service.TestService;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.retry.annotation.Retryable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@MyComponent
public class TestServiceImpl implements TestService, InitializingBean {

    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newScheduledThreadPool(4);

    private static final LinkedBlockingQueue<MergedRequest> BLOCKING_QUEUE = new LinkedBlockingQueue<>();

    @Override
    @Retryable(Exception.class)
    public void test() {
        System.out.println("test...");
        int i = 10 / 0;
    }

    public static void main(String[] args) throws InterruptedException {
        TestServiceImpl service = new TestServiceImpl();
        service.afterPropertiesSet();

        List<Integer> request1 = Arrays.asList(1, 2);
        List<Integer> request2 = Arrays.asList(3, 4);

        new Thread(() -> {
            service.getData(request1);
        }).start();
        new Thread(() -> {
            service.getData(request2);
        }).start();
    }

    // 对外查询接口，阻塞
    public List<Integer> getData(List<Integer> requests) {

        if (CollectionUtils.isEmpty(requests)) {
            return Collections.emptyList();
        }

        MergedRequest mergedRequest = new MergedRequest();
        CompletableFuture<List<Integer>> future = new CompletableFuture<>();
        mergedRequest.setRequest(requests);
        mergedRequest.setResult(future);
        BLOCKING_QUEUE.offer(mergedRequest);
        List<Integer> result = future.join();
        System.out.println(new Date());
        System.out.println(result);
        return result;
    }

    // 执行查询请求
    public List<Integer> get(MergedRequest mergedRequest) {
        return mergedRequest.getRequest().stream().map(i -> i * i).collect(Collectors.toList());
    }

    // 获取查询结果
    public void getResult(List<MergedRequest> mergedRequests) {

        if (CollectionUtils.isEmpty(mergedRequests)) {
            return;
        }

        mergedRequests.forEach(mergedRequest -> {
            mergedRequest.getResult().complete(get(mergedRequest));
        });
        mergedRequests.clear();
    }

    @Override
    public void afterPropertiesSet() throws InterruptedException {
        // 定时任务执行批量查询请求
        SCHEDULED_EXECUTOR_SERVICE.scheduleWithFixedDelay(() -> {
            if (BLOCKING_QUEUE.isEmpty()) {
                return;
            }
            List<MergedRequest> mergedRequests = new ArrayList<>();
            mergedRequests.add(BLOCKING_QUEUE.poll());
            getResult(mergedRequests);
        }, 1, 1, TimeUnit.SECONDS);
    }

    @Data
    private static class MergedRequest {
        private List<Integer> request;
        private CompletableFuture<List<Integer>> result;
    }
}
