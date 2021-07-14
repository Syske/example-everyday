## 多线程之Exchanger

### 前言

今天我们来分享最后多线程最后一个工具类组件，之后我们会继续探索多线程的相关知识：线程池、并发容器和框架，然后就是总结和查漏补缺。

今天的内容很简单，内容也不太多，但是应用场景很典型，可以解决我们实际开发中数据对比的应用需求，好了，我们直接开始吧。

### Exchanger

`exchanger`也是`jdk1.5`引入的，主要用来解决线程之间数据交换的问题，和它的字面意思一样，`exchanger`主要是用来交换数据的，需要注意的是，交换数据的时候只能是两个（一对）线程两两交换，下面我们直接看示例代码：

```java
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
                String exchange = exchanger.exchange(taskStr);
                System.out.println("交换完成，task3获得：" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            String taskStr = "40只鸭";
            try {
                System.out.println("我是task4，正在等待交换，我有" + taskStr );
                String exchange = exchanger.exchange(taskStr);
                System.out.println("交换完成，task4获得：" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}
```

在上面的代码中，我们定义了一个交换器`Exchanger`，它本身是支持泛型的，这里我们定义的是`string`，然后定义了一个线程池，通过线程池分别启动四个线程，在四个线程中，都有这样一行代码：

```java
String exchange = exchanger.exchange(taskStr);
```

它的作用就是和其他线程交换数据，并拿到交换后的数据，然后我们运行示例代码：

![](https://gitee.com/sysker/picBed/raw/master/20210713084546.png)

数据交换前，他们分别拥有`10`只羊，一头牛，`50`只鸡，`40`只鸭，交换后`task2`拿到`task1`的牛，`task1`拿到`task2`的羊，其他的也一样，之所以用这个例子，是因为它的交换过程确实很像原始社会的以物易物。

这里需要注意的是，交换数据的线程数量必须为双数，否则线程会一直被`exchange`方法阻塞，这里我们把最后一个线程先删除掉，然后运行：
![](https://gitee.com/sysker/picBed/raw/master/20210713085134.png)

因为没有线程再与`task3`进行数据交换，所以线程被阻塞了。

当然有时候，阻塞并非是人为的，而是在某些特殊情况下发生，为了避免因为这种情况导致线程持续阻塞，我们可以用`exchanger`的另一个方法：

![](https://gitee.com/sysker/picBed/raw/master/20210713085420.png)

这个方法支持设定超时时间，如果到设定时间依然没有数据交换，该方法会抛出`TimeoutException`异常：

![](https://gitee.com/sysker/picBed/raw/master/20210713085835.png)

关于`exchanger`的应用场景，我能想到的就两个，一个就是数据校验，两个线程同时操作同一批数据，我们可以对数据最终的一致性做校验，互相验证，比如两个`excel`的数据比对；另外一个场景和这个很类似，就是我们在实际开发经常会遇到方法`A`的运行条件需要根据`B`的运行结果进行优化调整，这时候我们就可以通过`exchanger`来来进行数据交换，然后再继续触发相关操作。



### 总结

`exchanger`最大的优点是她可以在运行的过程中交换数据，其他的应用场景在遇到具体问题的时候再进一步分析吧。好了，`exchanger`的相关内容就到这里。

今天还要补充一个小知识，是关于`mysql`的，是一个小知识点，也是线上发现的一个小问题，具体来说就是`mysql`的求和语句，如果求和字段的值全部为`null`，那么最终的求和结果是`null`，而不是`0`，这样就会有潜在的空指针异常：

```
select course_type, sum(order_id) from course GROUP BY course_type
```

因为`order_id`都是`null`，所以最终`sum(order_id)`就是`null`:

![](https://gitee.com/sysker/picBed/raw/master/images/20210713130829.png)

这样如果你用包装类接收`sum(order_id)`就是`null`，后续在操作它的时候一定要做空指针校验，否则就是个线上`bug`。好了，就这么多，`over`！