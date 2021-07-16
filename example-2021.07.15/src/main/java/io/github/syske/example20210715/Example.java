/* Copyright © 2021 syske. All rights reserved. */
package io.github.syske.example20210715;

import java.util.concurrent.*;

/**
 * 每日一例 2021-07-15
 *
 * @author syske
 * @version 1.0
 * @date 2021-07-15 7:07
 */
public class Example {
    public static void main(String[] args) throws InterruptedException {
        int corePoolSize = 10;
        int maximumPoolSize = 30;
        long keepAliveTime = 20000;
        TimeUnit unit = TimeUnit.MICROSECONDS;
        BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>(170);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            System.out.println(i + " # " + threadPoolExecutor);
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String name = Thread.currentThread().getName();
                System.out.println("hello threadPool: "+ name);
                countDownLatch.countDown();
//                System.out.println("线程运行结束" + name + " # " + threadPoolExecutor);
            });
        }
        countDownLatch.await();
        System.out.println("循环完成，现在的线程池状态 # " + threadPoolExecutor);
        Thread.sleep(10000);
        System.out.println("循环完成，休眠10秒线程池状态 # " + threadPoolExecutor);
        Thread.sleep(10000);
        System.out.println("循环完成，休眠20秒线程池状态 # " + threadPoolExecutor);
//        threadPoolExecutor.shutdown();
    }
}
