# 多线程之CyclicBarrier

### 前言

昨天我们分享了多线程里面的一个计数器`countDownLatch`，它的主要作用是控制线程执行顺序，确保上一个操作完成后，下一个线程才能启动运行，但是某些情况下`countDownLatch`并不能满足我们的需求，比如执行`A`线程`10`次后，我们需要执行`B`线程，然后再执行`A`线程`10`次，循环往复，为了应付这样的应用场景，`jdk`也为我们提供了相应的解决方案——另一个计数器`CyclicBarrier`，今天我们就一起来看看吧。

### CyclicBarrier

`CyclicBarrier`中文的意思是循环格栅，循环屏障，循环关卡，循环分界线，我觉得叫循环分界线应该更好理解，因为它起的作用就是分隔。它和`countDownLatch`一样，也是`jdk1.5`引入的。它的作用有点像触发器，当达到设定的数值时，我们可以触发一个操作。

这样干巴巴讲，大家可能想不来，那我们先看这样一段示例代码：

```java
public class Example {
    static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args) {
        AtomicInteger count2 = new AtomicInteger(0);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
                System.out.println("多线程循环完成" + count2.getAndIncrement());
            });
        for (int i = 0; i < 100; i++) {
            new Thread(new Task(cyclicBarrier)).start();
        }
    }

    static class Task implements Runnable {
        private final CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                synchronized (count) {
                    System.out.println(count.getAndIncrement());
                }
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
```

上面的代码中，我们定义了一个原子整数，初始值为`0`；

我们定义了一个`cyclicBarrier`，触发值我们设定为`10`，在触发操作里我们打印提示信息；

我们还定义了一个线程，在线程内部我们对原子整数`count`加一并打印。

然后我们在`main`方法中循环启动`100`个线程，运行上面的代码，结果大致如下：

![](https://gitee.com/sysker/picBed/raw/master/20210709083035.png)

根据运行结果，我们可以看出来，`count`每增加`10`，也就是启动十个线程，会触发`CyclicBarrier`中我们定义的操作，这个数值也就是我们在`CyclicBarrier`指定的触发值。

如果我们把触发值设置为`5`，那应该每隔`5`次就会打印一次，我们验证下：

![](https://gitee.com/sysker/picBed/raw/master/20210709084125.png)

事实也确实如此，我想到这里大家应该都清楚`CyclicBarrier`的用法了吧。

关于`CyclicBarrier`的构造方法我在多说两句，`CyclicBarrier`有两种构造方法，但是第二种最常用，也就是我们演示的这种：

![](https://gitee.com/sysker/picBed/raw/master/20210709084457.png)

入参有两个，一个就是触发次数，一个就是触发操作。

另外还需要注意的是，它的`await()`方法和`countDownLatch`的方法是不一样的，在它的`await()`方法中，有一个`--count`的操作，也就是每次都会把我们设定的数值减一，直至为零，如果`--count`为`0`，它就会触发我们的`barrierAction`：

![](https://gitee.com/sysker/picBed/raw/master/20210709084928.png)

对于它的应用场景，我想大家应该能够想到很多，比如固定条数保存数据，多线程提交保存操作，然后达到固定条数提交数据库保存，当然还有很多其他的应用场景，大家可以结合自己的应用需求，好好想一想。

### 总结

虽然一开始我拿`countDownLatch`和`CyclicBarrier`做比较，但是事实上，它们两个不具备任何可比性，而且适用的场景也是不一样的。`countDownLatch`核心方法是`countDown`和`aWait`，主要用于控制线程执行顺序；`CyclicBarrier`主要用于有回调需求的场景，而且它的`await`方法也不一样，但是它们都很有用。

最后，希望大家多动手实现，多练习，毕竟学习这件事，还是实践出真知，多线程想要学的好，`juc`下面的类少不了，一起加油吧！
