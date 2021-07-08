# 原子类、lock、synchronized简单性能对比

### 前言

前几天，我们分享了原子类相关的知识点，展示了原子类的一些用法，之前也分享了`lock`相关的应用，但是我一直有一个困惑，就是在多线程数据安全的几个常用解决方案中，到底哪一个性能最好，我们在实际应用开发中应该如何选择，今天我们就来简单探讨下这个问题。

### 性能对比

开始之前，我们先看这样一段代码：

```java
public class CountDownLatchTest {
    static Integer count = 0;
    private static final int SIZE = 100;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread(new TaskPortion(startTime)).start();
        }
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
            try {
                Thread.sleep(1000);
                System.out.println(count++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

从代码大家应该能看出了，我原本是要分享`CountDownLatch`相关知识的，但是在实际操作的时候，我发现`countDownLatch`并不简单，至少我现在还对它了解不够，所以就临时换频道，分享各种多线程线程安全解决方案的性能测试。

今天我们主要测试三种解决方案的性能，分别是原子类、`lock`和`synchronized`。从上面的代码中，我们也可以看出了，以上代码是线程不安全的，所以接下来我们就要分别通过这三种解决方案来优化上面的代码，然后分别测试运行时间。

#### lock

我们先看第一种`lock`，这里我们主要是应用可重入锁，然后优化代码：

```java
public class CountDownLatchTest {
    static Integer count = 0;
    private static final int SIZE = 100;
    // 可重入锁
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread(new TaskPortion(startTime)).start();
        }
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
            lock.lock();
            try {
                Thread.sleep(1000);
                System.out.println(count++);
                if (count == 99) {
                    System.out.println("用时:" + (System.currentTimeMillis() - startTime));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
```

我们定义了一个可重入锁，在`run`方法中加锁，然后在`finally`代码块中释放锁，运行以上代码，我们会得到如下运行结果：

![](https://gitee.com/sysker/picBed/raw/master/20210707085239.png)

整个运行过程特别慢，大概需要`90`秒，具体取决于电脑性能。

#### synchronized

然后我们再来看下`synchronized`加持下的运行性能，调整后的代码如下：

```java
public class CountDownLatchTest {
    static Integer count = 0;
    private static final int SIZE = 100;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread(new TaskPortion(startTime)).start();
        }
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
            try {
                Thread.sleep(1000);
                synchronized (count){
                    System.out.println(count++);
                    if (count == 99) {
                        System.out.println("用时:" + (System.currentTimeMillis() - startTime));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

关于`synchrronized`这里就不过多说明了，应该算特别基础的知识，它能修饰变量、方法、代码块，在应用层面也比较灵活。

![](https://gitee.com/sysker/picBed/raw/master/20210707085427.png)

运行结果很感人，时间直接比`lock`快了`90`倍，但是运行过程中出现了并发的情况（翻车了），因为`i++`操作不是原子的，所以单`synchrioned`并不能保证线程安全。继续往后看，后面有最终解决方案。

#### 原子类

接下来，我们来看最后一种——原子类，代码调整如下：

```java
public class CountDownLatchTest {
    static AtomicInteger count = new AtomicInteger(0);
    private static final int SIZE = 100;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread(new TaskPortion(startTime)).start();
        }
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
            try {
                Thread.sleep(1000);
                System.out.println(count.getAndAdd(1));
                if (count.get() == 99) {
                    System.out.println("用时:" + (System.currentTimeMillis() - startTime));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

运行结果如下，性能方面确实要比`lock`优秀的多，但是运行结果依然翻车，出现了线程安全问题。

![](https://gitee.com/sysker/picBed/raw/master/20210707085555.png)

然后再一次测试的机缘巧合之下，我发现把原子类和`synchronized`组合一下，就可以完美地解决这个问题，代码调整如下：

```java
public class CountDownLatchTest {
    static AtomicInteger count = new AtomicInteger(0);
    private static final int SIZE = 100;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread(new TaskPortion(startTime)).start();
        }
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
            try {
                Thread.sleep(1000);
                synchronized (count){
                    System.out.println(count.getAndAdd(1));
                    if (count.get() == 99) {
                        System.out.println("用时:" + (System.currentTimeMillis() - startTime));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

然后运行结果就正常了，而且性能一点也不弱，可以吊打`lock`了

![](https://gitee.com/sysker/picBed/raw/master/images/20210707131336.png)

### 总结

在以前的认知里，我一直觉得`synchronized`比显式的`lock`要重，但是通过今天的测试，我发现自己以前的认知是有问题的，`synchronized`要比显式的`lock`性能好的多，同时也意识到单独使用原子类或者`synchronized`都是存在线程安全问题的，所以在日常开发中，更多时候是需要把两者完美组合的。

在此之前，我一直以为原子类是可以单独使用的，但是踩了今天的坑才知道，就算你用了`synchronized`或者原子类线程安全问题依然存在。总之，凡事多实践多总结，共勉！