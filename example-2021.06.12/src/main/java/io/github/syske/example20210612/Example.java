package io.github.syske.example20210612;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @program: example-2021.06.12
 * @description: 2021.06.12每日一例
 * @author: syske
 * @date: 2021-06-15 7:48
 */
public class Example {

    public static void main(String[] args) {
        System.out.println("当前时间为：" + System.currentTimeMillis());
        Timer timer = new Timer();
        timer.schedule(new MyTask(), 10000);

        System.out.println("当前时间为：" + System.currentTimeMillis());
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(10);
        service.execute(new MyTask());
    }


    static class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("任务执行了，时间为：" + System.currentTimeMillis());
        }
    }
}


