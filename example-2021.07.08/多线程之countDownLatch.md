# 多线程之countDownLatch

### 前言

原本是昨天分享`countDownLatch`相关知识点的，但是昨天粗略看写了，发现自己对`countDownLatch`的认知还不够，所以就半道分享了常用的三种多线程线程安全解决方案的性能比较，虽然过程中翻车了，但是还是有收获的，也不亏。今天又去看了下`count`的相关知识，然后做了一个小`demo`，感觉有点眉目了，所以我们来继续看`countDownLatch`。

### countDownLatch

`countDownLath`是`jdk1.5`引入的一个新特性，它的出现主要是为了解决某些应用场景下多线程运行顺序的问题。我们在定义`countDownLatch`的时候，需要指定它的`count`大小，它就是通过这个`count`来控制多线程运行顺序的。

它有两个核心的方法`countDown`和`await`，`countDown`的作用是当线程执行完后把`count`减一，只能用一次；`await`方法是判断`count`是否减为`0`，这个方法本身是阻塞的，如果`count`不是`0`，`await`后面的代码是不会被执行的。

接下来，我们通过一段示例代码来看下`countDownLatch`的基本用法：

```java
public class Example {
    private static AtomicInteger count = new AtomicInteger(0);
    private static final int SIZE_FIRST = 100;
    private static final int SIZE_SECOND = 50;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(SIZE_FIRST);
        for (int i = 0; i < SIZE_FIRST; i++) {
            executorService.execute(new Task1(countDownLatch));
        }
        countDownLatch.await();
        final CountDownLatch countDownLatch2 = new CountDownLatch(SIZE_SECOND);
        for (int i = 0; i < SIZE_SECOND; i++) {
            executorService.execute(new Task2(countDownLatch2));
        }
        countDownLatch2.await();
        for (int i = 0; i < SIZE_SECOND; i++) {
            executorService.execute(new Task3());
        }
        executorService.shutdown();
    }


    static class Task1 implements Runnable {
        private final CountDownLatch countDownLatch;

        Task1(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }


        @Override
        public void run() {
            System.out.println("Task1: " + count.getAndIncrement());
            this.countDownLatch.countDown();
        }
    }

    static class Task2 implements Runnable {
        private final CountDownLatch countDownLatch;

        public Task2(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("Task2: " + count.getAndIncrement());
            this.countDownLatch.countDown();
        }
    }

    static class Task3 implements Runnable {
        @Override
        public void run() {
            System.out.println("Task3: " + count.getAndIncrement());
        }
    }

}
```

这里我分别定义了三个线程，前两个线程构造的时候都需要传入`countDownLatch`，然后在`run`方法的尾部执行`countDown`方法，也就是每启动一个线程，`count`减一。

我们这里还定义了两个`countDownLatch`，初始值分别是`100`和`50`，他们分别对应线程`task1`和`task2`的执行次数。

在线程`task1`和`task2`之间我们执行第一个`countDownLatch`的`await`方法，控制线程`task1`和`task2`的执行顺序，确保线程`task1`先执行；

在线程`task2`和线程`task3`之间我们执行第二个`countDownLatch`的`await`方法，控制线程`task2`和`task3`的执行顺序，确保线程`task2`先执行。

然后，我们运行上面的代码，会得到如下结果：

![](https://gitee.com/sysker/picBed/raw/master/20210708085806.png)

![](https://gitee.com/sysker/picBed/raw/master/20210708085829.png)

在以上结果中，我们可以看到，不论你执行多少次，线程运行顺序都是`task1`、`task2`、`task3`，当然`await`方法之前的执行顺序我们是没有办法控制的。

### 总结

从上面演示结果来看，`countDownLatch`主要是用来控制多线程运行顺序的，特别适合多个线程协同运行但是有顺序要求的业务，更多应用场景大家可以好好研究，我们今天的内容就到这里吧！

