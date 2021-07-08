/* Copyright © 2021 syske. All rights reserved. */
package io.github.syske.example20210708;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 每日一例2021.07.08
 *
 * @author syske
 * @version 1.0
 * @date 2021-07-08 8:16
 */
public class Example {
    private static AtomicInteger count = new AtomicInteger(0);
    private static final int SIZE_FIRST = 100;
    private static final int SIZE_SECOND = 50;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(SIZE_FIRST);
        for (int i = 0; i < SIZE_FIRST; i++) {
            executorService.execute(new Task1(countDownLatch));
        }
        countDownLatch.await();
        final CountDownLatch countDownLatch2 = new CountDownLatch(SIZE_SECOND);
        for (int i = 0; i < SIZE_SECOND; i++) {
            executorService.execute(new Task2(countDownLatch2));
        }
        countDownLatch2.await();
        for (int i = 0; i < SIZE_SECOND; i++) {
            executorService.execute(new Task3());
        }
        executorService.shutdown();
    }


    static class Task1 implements Runnable {
        private final CountDownLatch countDownLatch;

        Task1(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }


        @Override
        public void run() {
            System.out.println("Task1: " + count.getAndIncrement());
            this.countDownLatch.countDown();
        }
    }

    static class Task2 implements Runnable {
        private final CountDownLatch countDownLatch;

        public Task2(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("Task2: " + count.getAndIncrement());
            this.countDownLatch.countDown();
        }
    }

    static class Task3 implements Runnable {
        @Override
        public void run() {
            System.out.println("Task3: " + count.getAndIncrement());
        }
    }

}
