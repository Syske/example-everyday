/* Copyright © 2021 syske. All rights reserved. */
package io.github.syke.example20210709;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 每日一例 2021-07-09
 *
 * @author syske
 * @version 1.0
 * @date 2021-07-09 7:48
 */
public class Example {
    static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args) {
        AtomicInteger count2 = new AtomicInteger(0);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
                System.out.println("多线程循环完成" + count2.getAndIncrement());
            });
        for (int i = 0; i < 100; i++) {
            new Thread(new Task(cyclicBarrier)).start();
        }
    }

    static class Task implements Runnable {
        private final CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                synchronized (count) {
                    System.out.println(count.getAndIncrement());
                }
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
