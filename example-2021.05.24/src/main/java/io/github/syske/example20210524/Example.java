package io.github.syske.example20210524;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * example 2021-05-24
 *
 * @author sysker
 * @version 1.0
 * @date 2021-05-25 7:32
 */
public class Example {
    public static void main(String[] args) {
        int initSize = 100;
        List<String> stringList = new LinkedList();
        for (int i = 0; i < initSize; i++) {
            stringList.add(String.format("%d%s", i, "str"));
        }
        // forEach
        long startTimeForEach = System.currentTimeMillis();
        stringList.forEach(System.out::println);
        long endTImeForEach = System.currentTimeMillis();
        // for item
        long startTimeForItem= System.currentTimeMillis();
        for (String s : stringList) {
            System.out.println(s);
        }
        long endTimeForItem= System.currentTimeMillis();

        // for i
        long startTimeForI= System.currentTimeMillis();
        for (int i = 0; i < stringList.size(); i++) {
            System.out.println(stringList.get(i));
        }
        long endTimeForI= System.currentTimeMillis();

        System.out.println(String.format("List为%d时，forEach遍历用时：%d", initSize, (endTImeForEach - startTimeForEach)));
        System.out.println(String.format("List为%d时，forItem遍历用时：%d", initSize, (endTimeForItem - startTimeForItem)));
        System.out.println(String.format("List为%d时，forI遍历用时：%d", initSize, (endTimeForI - startTimeForI)));
    }

}

class SimpleHttpServer {
    // 处理HttpRequest地线程池
    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<HttpRequestHandler>(1);

    static class HttpRequestHandler implements Runnable {

        @Override
        public void run() {

        }
    }
}

class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    private static final int MAX_WORKER_NUMBERS = 10;

    public DefaultThreadPool() {

    }

    public DefaultThreadPool(int num) {

    }



    @Override
    public void execute(Job job) {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void addWorkers(int num) {

    }

    @Override
    public void removeWorker(int num) {

    }

    @Override
    public int getJobSize() {
        return 0;
    }
}

interface ThreadPool<Job extends Runnable> {
    // 执行一个job
    void execute(Job job);
    // 关闭线程池
    void shutdown();
    // 增加工作者线程
    void addWorkers(int num);
    // 减少工作者线程
    void removeWorker(int num);
    // 得到正在等待执行地任务数量
    int getJobSize();
}


