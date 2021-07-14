/* Copyright © 2021 syske. All rights reserved. */
package io.github.syske.example20210714;

import java.util.concurrent.*;

/**
 * 每日一例 2021-07-14
 *
 * @author syske
 * @version 1.0
 * @date 2021-07-14 7:31
 */
public class Example {
    public static void main(String[] args) {
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);


        int corePoolSize = 10;
        int maximumPoolSize = 20;
        long keepAliveTime = 1000;
        TimeUnit unit = TimeUnit.MICROSECONDS;
        BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>(50);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        for (int i = 0; i < 50; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String name = Thread.currentThread().getName();
                System.out.println("hello threadPool: "+ name);
            });
        }
        threadPoolExecutor.shutdown();
    }
}
