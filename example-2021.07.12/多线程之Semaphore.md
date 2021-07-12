# 多线程之Semaphore

### 前言

最近一段时间，我们一直都是在分享多线程相关的知识点，截止到今天我们已经分享过锁、计数器等相关知识，主要分享了一些常用的多线程控制方式，今天我们来继续分享另一个多线程控制组件——`Semaphore`。

### Semaphore

`Semaphore`的字面意思是信号量，但是但从字面翻译我们是无法得知它的作用的，根据官方注释以及网上的一些解释，`semaphore`简单来说就是多线程运行控制器，它的主要作用就是控制统一时间并发的线程数量，从这一特性上看，它最适用的场景就是限流，下面我们通过一段简单的代码来说明它的用法：

```java
public class Example {
    private static final int THREAD_SIZE = 30;
    private static final Semaphore s = new Semaphore(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);
        for (int i = 0; i < THREAD_SIZE*2; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    s.acquire();
                    System.out.println(Thread.currentThread().getName() + " currentTimeMillis: "+ System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("thread count: " + finalI);
                    s.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
```

在上面的代码中，我们定义了一个线程池，线程池大小为`30`，同时我们还定义了一个线程运行控制器，控制器的大小我们设定为`5`，也就是说，在当前控制器的作用下，同一时间只允许`5`个线程运行；

在线程内部，在线程运行最开始，我们通过`Semaphore`的`acquire`方法获取运行许可（拿不到运行凭证是无法运行的），你也可以通过`tryAcquire`方法获取运行凭证，两个方法的区别是`tryAcquire`有一个布尔的返回值，是非阻塞的，而`acquire`是阻塞的，如果拿不到会一直等，关于这一点，官方文档说的很清楚：

![](https://gitee.com/sysker/picBed/raw/master/20210712081920.png)

![](https://gitee.com/sysker/picBed/raw/master/20210712082048.png)

线程内部我们休眠了两秒，然后通过`release`方法释放`Semaphore`资源，这样其他的线程才能拿到这个凭证。

最后，我们用一个`for`循环来运行线程，循环次数我们设定为线程池大小的两倍，然后我们运行上面的代码，运行结果大致如下：

![](https://gitee.com/sysker/picBed/raw/master/20210712082442.png)

从运行结果中我们可以看到，虽然线程池大小是`30`，但是同一时间运行的线程只有`5`个，也就是我们`Semaphore`的初始化大小。我们可以试着把`Semaphore`的大小修改下看下运行结果：

##### 初始化大小为`10`

![](https://gitee.com/sysker/picBed/raw/master/20210712082743.png)

同一时间有`10`个线程在运行

##### 初始化大小为2

![](https://gitee.com/sysker/picBed/raw/master/20210712082900.png)

同一时间有`2`个线程在运行。

#### 作用范围

下面我们把代码做一些简单调整：

![](https://gitee.com/sysker/picBed/raw/master/20210712083610.png)

我们分别在`acquire`方法前和`release`方法后加一行代码，这时候我们的`semaphore`初始化还是`2`，然后运行下：

![](https://gitee.com/sysker/picBed/raw/master/20210712084001.png)

我们发现，虽然`acquire`方法前和`release`方法之间以及之后的代码虽然同一时间只有两个线程在运行，但是之前的代码同一时间是有多于两个线程在运行的，这就是说`Semaphore`只会影响`acquire`方法前和`release`方法之间和之后区域的线程并发数，影响之后的代码是因为`acquire`方法是阻塞的，如果我们换成`tryAcquire`应该就是另外一番场景了：

![](https://gitee.com/sysker/picBed/raw/master/20210712085555.png)

然后运行：

![](https://gitee.com/sysker/picBed/raw/master/20210712085705.png)