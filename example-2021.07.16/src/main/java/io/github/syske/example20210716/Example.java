/* Copyright © 2021 syske. All rights reserved. */
package io.github.syske.example20210716;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 每日一例 2021-07-16
 *
 * @author syske
 * @version 1.0
 * @date 2021-07-16 8:05
 */
public class Example {
    public static void main(String[] args) throws InterruptedException {
        int corePoolSize = 10;
        int maximumPoolSize = 30;
        long keepAliveTime = 20000;
        TimeUnit unit = TimeUnit.MICROSECONDS;
        ThreadFactory build = new ThreadFactoryBuilder().setNameFormat("syske-task-%d").build();
        BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>(170);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new MyThreadFactory());
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
                System.out.println("线程运行结束" + name + " # " + threadPoolExecutor);
                threadPoolExecutor.shutdown();
            });
        }
        countDownLatch.await();
        System.out.println("循环完成，现在的线程池状态 # " + threadPoolExecutor);
        Thread.sleep(10000);
        System.out.println("循环完成，休眠10秒线程池状态 # " + threadPoolExecutor);
        Thread.sleep(10000);
        System.out.println("循环完成，休眠20秒线程池状态 # " + threadPoolExecutor);
    }

    static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "syske-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
