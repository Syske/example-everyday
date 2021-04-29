# 2021.04.29 lamubda表达式之forEach

## 说明

给位小伙伴，大家好呀，我是志哥（我觉得我也得有个昵称了，全称太拗口，以后就叫志哥了），从今天开始，我要开一个小的板块，名字暂定为每日一例，内容以`java`基础知识为主，篇幅尽可能短小。

原因的话，有两点，一个是平时创作时间有限（我不想再想昨天那样，肝到凌晨两点，我发现自己写东西的时候话真多），碎片化的内容创作更符合我目前的节奏，同时降低创作内容的粒度也可以保证我的更新频率，可以保证日更，每日读书札记已经很好地说明了一点；

另一个是目前内容太过单一，全靠大篇幅的技术总结来撑牌面，除此之外创作就很有限了，所以关于内容我也要开始新的一轮改革和实验了，其实我想分享的内容还是蛮多的，我想把我觉得一切有趣有价值的东西都分享出来，包括摄影、美食、树莓派、好的观点、积极的价值观、个人的思考、日常生活中的一些选择（比如买东西前的测评对比、选东西的技巧）等，但是目前好多内容没法落地，未来我会多做这方面的探索。

## lamubda表达式之forEach

面试的时候，经常有面试官问道`jdk1.8`的一些新特性，`lamubda`表达式就是其中一个特别重要的特性，也是`java`官方针对函数式编程的一次变革，今天我们就来简单看一下其中的一个很常用的`lamubda`表达式的应用——`forEach`。

`forEach`可以让你的循环更简洁：

```java
/**
 * @program: example-2021.04.29
 * @description: example-everyday
 * @author: syske
 * @create: 2021-04-29 23:10
 */
public class Example {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("test1");
        stringList.add("test2");
        stringList.add("test3");
        stringList.add("test4");
        stringList.add("test5");
        stringList.add("test6");
        stringList.add("test7");
        stringList.add("test8");
        stringList.add("test9");
        stringList.add("test10");
        // lamubda表达式forEach
        stringList.forEach(s -> System.out.println(s));
    }
}
```

正如上面代码展示的那样，我们的`List`循环遍历只变成了简单的一行，是不是很高级，其实它等同于下面的写法：

```java
        // 等价于
        for (String s : stringList) {
            System.out.println(s);
        }
```

如果有用`jdk1.8`及以上版本的小伙伴，赶紧用起来吧，让你的代码更简洁，更高级，更牛批。更多`lamubda`的应用我们后面再来分享，像`steam`这种就更高级了，用起来也贼爽。

以后内容也会尽可能简短，代码量控制在`100`行内，让你看完就会，就能用。