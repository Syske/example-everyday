/* Copyright © 2021 syske. All rights reserved. */
package io.github.syske.example20210713;

import java.util.concurrent.*;

/**
 * 每日一例 2021-07-13
 *
 * @author syske
 * @version 1.0
 * @date 2021-07-13 8:09
 */
public class Example {
    private final static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(() -> {
            String taskStr = "10只羊";
            try {
                System.out.println("我是task1，正在等待交换，我有" + taskStr );
                String exchange = exchanger.exchange(taskStr);
                System.out.println("交换完成，task1获得：" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            String taskStr = "一头牛";
            try {
                System.out.println("我是task2，正在等待交换，我有" + taskStr );
                String exchange = exchanger.exchange(taskStr);
                System.out.println("交换完成，task2获得：" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            String taskStr = "50只鸡";
            try {
                System.out.println("我是task3，正在等待交换，我有" + taskStr );
                String exchange = exchanger.exchange(taskStr, 25, TimeUnit.MICROSECONDS);
                System.out.println("交换完成，task3获得：" + exchange);
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        });
     /*   executorService.execute(() -> {
            String taskStr = "40只鸭";
            try {
                System.out.println("我是task4，正在等待交换，我有" + taskStr );
                String exchange = exchanger.exchange(taskStr);
                System.out.println("交换完成，task4获得：" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/

        executorService.shutdown();
    }
}
