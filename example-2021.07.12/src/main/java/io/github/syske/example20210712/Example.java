/* Copyright © 2021 syske. All rights reserved. */
package io.github.syske.example20210712;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 每日一例 2021-07-10
 *
 * @author syske
 * @version 1.0
 * @date 2021-07-12 7:18
 */
public class Example {
    private static final int THREAD_SIZE = 30;
    private static final Semaphore s = new Semaphore(2);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);
        for (int i = 0; i < THREAD_SIZE*2; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    System.out.println("Semaphore out before: " + finalI + " currentTimeMillis: "+ System.currentTimeMillis());
                    boolean b = s.tryAcquire();
                    System.out.println("tryAcquire: " + b + ", " + Thread.currentThread().getName() + " currentTimeMillis: "+ System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("tryAcquire: " + b + ", " + "thread count: " + finalI);
                    s.release();
                    System.out.println("tryAcquire: " + b + ", " + "Semaphore out after: " + finalI + " currentTimeMillis: "+ System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
