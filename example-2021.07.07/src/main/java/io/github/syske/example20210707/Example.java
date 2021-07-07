/* Copyright © 2021 syske. All rights reserved. */
package io.github.syske.example20210707;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2021-07-07每日一例
 *
 * @author syske
 * @version 1.0
 * @date 2021-07-07 7:50
 */
public class Example {
    static int count = 0;
    private static final int SIZE = 100;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            new Thread(new TaskPortion(countDownLatch)).start();
        }
        /*countDownLatch.await();
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new TaskPortion(countDownLatch));

        }*/
        executorService.shutdown();
    }

    static class TaskPortion implements Runnable {
        private final CountDownLatch latch;

        TaskPortion(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(count++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }
}
