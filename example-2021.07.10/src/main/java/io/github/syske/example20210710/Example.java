/* Copyright © 2021 syske. All rights reserved. */
package io.github.syske.example20210710;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 每日一例 2021-07-10
 *
 * @author syske
 * @version 1.0
 * @date 2021-07-10 17:12
 */
public class Example {
    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 写操作1：写锁
        executorService.execute(() -> {
            writeLock.lock();
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("写count1: " + count.incrementAndGet());
            }
            writeLock.unlock();
        });
        // 读操作1：读锁
        executorService.execute(() -> {
            readLock.lock();
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("读count1: " + count.get());
            }
            readLock.unlock();
        });
        // 读操作2：读锁
        executorService.execute(() -> {
            readLock.lock();
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("读count2: " + count.get());
            }
            readLock.unlock();
        });
        // 写操作2：写锁
        executorService.execute(() -> {
            writeLock.lock();
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("写count2: " + count.incrementAndGet());
            }
            writeLock.unlock();
        });
        // 写操作3：写锁
        executorService.execute(() -> {
            writeLock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println("写count3: " + count.incrementAndGet());
            }
            writeLock.unlock();
        });

        executorService.shutdown();

    }
}
