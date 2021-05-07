package io.github.syske.example20210507;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: example-2021.05.07
 * @description: 2021-05-07 example
 * @author: syske
 * @date: 2021-05-07 11:24
 */
public class Example {
    private static final ReentrantLock mainLock = new ReentrantLock();
    private static int i;
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(100));
        for (int j = 0; j < 1000; j++) {
            Thread.sleep(10L);
            final int finalJ = j;
            executor.submit(() -> testLock(finalJ));
        }
        executor.shutdown();
//        System.out.println(i);
    }

    public static void testLock(int j) {
//        final ReentrantLock reentrantLock = mainLock;
//        reentrantLock.lock();
        try {
            System.out.println("==第" + j + "次调用==start");
            i ++;
            Thread.sleep(20L);
            i ++;
            System.out.println(i);
            System.out.println("==第" + j + "次调用==end");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            reentrantLock.unlock();
        }
    }

}
