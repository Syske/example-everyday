/* Copyright © 2021 syske. All rights reserved. */
package io.github.syske.example20210707;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * countDownLatch测试
 *
 * @author syske
 * @version 1.0
 * @date 2021-07-07 8:03
 */
public class CountDownLatchTest {
//    static Integer count = 0;
    static AtomicInteger count = new AtomicInteger(0);
    private static final int SIZE = 100;
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread(new TaskPortion(startTime)).start();
        }
//        System.out.println("用时:" + (System.currentTimeMillis() - startTime));
        /*for (int i = 0; i < SIZE; i++) {
            executorService.execute(new TaskPortion());

        }*/
        executorService.shutdown();
    }

    static class TaskPortion implements Runnable {
        private long startTime;
        public TaskPortion() {
        }
        public TaskPortion(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
//            lock.lock();
            try {
                Thread.sleep(1000);
//                synchronized (count){
//                    System.out.println(count++);
                    System.out.println(count.getAndAdd(1));
                    if (count.get() == 99) {
                        System.out.println("用时:" + (System.currentTimeMillis() - startTime));
                    }
//                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            lock.unlock();
        }
    }
}
