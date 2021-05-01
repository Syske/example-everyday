package io.github.syske.example20210430;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author syske
 * @date 2021-05-01 9:34
 */
public class Example {
    private static ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(5, 10, 1,
                    TimeUnit.SECONDS, new LinkedBlockingQueue<>(5));
    /**
     * 发送消息
     * @param messageList 消息列表
     * @return
     */
    public AtomicInteger sendMessage(List<String> messageList) {
        AtomicInteger successCount = new AtomicInteger();
        messageList.forEach(m -> {
            System.out.println("发送消息：" + m);
            successCount.getAndIncrement();
        });
        return successCount;
    }

    /**
     * 核心业务处理
     * @return
     */
    public String deal() {
        List<String> messageList = new ArrayList<>();
        // doSomeThing() 其他业务处理
        System.out.println("开始组装消息~Start");
        for (int i = 0; i < 1000; i++) {
            int randomInt1 = new Random().nextInt();
            messageList.add("发送数字信息：" + randomInt1);
        }
        System.out.println("组装消息完成~End");
        System.out.println("开始发送消息~Start");
        threadPoolExecutor.submit(() -> this.sendMessage(messageList));
        /*threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                sendMessage(messageList);
            }
        });*/
        System.out.println("发送消息完成~End");
        return "业务处理完成";
    }

    public static void main(String[] args) {
        Example example = new Example();
        String result = example.deal();
        System.out.println(result);
        threadPoolExecutor.shutdown();
    }
}
