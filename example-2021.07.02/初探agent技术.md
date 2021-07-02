# 初探Java agent技术

### 前言

不知道各位小伙伴在此之前，是否有听过或者了解过`agent`相关技术，没有听说过也没有关系，我们今天的目的就是介绍`agent`的相关技术，探讨`agent`的应用场景，分享一些实际开发中的应用案例。

印象中，我第一次了解`agent`技术，是在分享`skywalking`这款工具的时候，`skywalking`与我们项目的绑定就是通过`agent`来实现的。好了，先说这么多，接下来我们就来详细介绍下`agent`的一些技术点。

### Agent

#### Agent是什么

`Agent`中文含义代理，但是在`java`中我跟喜欢称它为探针而非代理，尽管他也属于代理技术，但是代理本身并不能体现`agent`的作用。

`agent`技术是在`JDK1.5`引入的，通过`agent`技术，我们可以构建一个独立于应用程序的代理程序，用来协助监测、运行甚至替换其他`JVM`上的程序。使用它可以实现虚拟机级别的`AOP`功能。

`Agent`分为两种，一种是在主程序之前运行的`Agent`，一种是在主程序之后运行的`Agent`（前者的升级版，`1.6`以后提供），稍后我们会有具体实例展示。

#### Agent能干什么

首先它最大的作用就是解耦，比如`skywalking`中的应用，我们不需要对我们的程序做任何修改，只需要通过`agent`技术引入`skywalking`的代理包即可；其次最常应用的场景就是`jvm`级的`AOP`，比如`jvm`的监测；另一种就是类似热部署这样的字节码动态操作。

#### Agent技术演示

说了这么多好多小伙伴肯定看的云里雾里的，接下来我们通过两个简单示例，来演示下`Agent`技术的神奇之处。

先看第一种，也就是在主程序之前运行的`Agent`。

##### 在主程序之前运行的`Agent`

首先我们创建一个`maven`项目，编写这样一个`Agent`类：

```java
import java.lang.instrument.Instrumentation;
/**
 * 在主程序之前运行的Agent
 */
public class PremainAgent {
    public static void premain(String preArgs, Instrumentation instrumentation) {
        System.out.println("premainAgent.premain start");
        System.out.println("preArgs: " + preArgs);
        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
        for (Class allLoadedClass : allLoadedClasses) {
            System.out.println("premainAgent LoadedClass: " + allLoadedClass.getName());
        }
    }
}
```

这里的方法名和参数列表是固定的，根据方法名我们能看出这个方法应该是运行在`main`方法之前的，等下测试下就知道了。

接着，我们在`pom.xml`文件中增加如下内容：

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <version>2.4</version>
            <configuration>
                <archive>
                    <manifest>
                        <addClasspath>true</addClasspath>
                    </manifest>
                    <manifestEntries>
                        <Premain-Class>io.github.syske.agent.PremainAgent</Premain-Class>
                    </manifestEntries>
                </archive>
            </configuration>
        </plugin>
    </plugins>
</build>
```

上面这些内容是配置我们构建时生成的`MANIFEST`文件，通常我们打的`jar`包都有这个文件。最核心的配置就是`Premain-Class`，这里配置的是我们探针的类名，如果没有这个配置，我们的`premain`方法是不会被识别的。

![](https://gitee.com/sysker/picBed/raw/master/20210702082813.png)

然后我们通过`maven`把我们当前项目打成一个`jar`包，打完包之后的`jar`文件如上图，打开`MANIFEST.MF`文件，你会发现我们指定的`Premain-Class`也被写入了，这时候我们的包就是打好了，下面就是运行测试了。

![](https://gitee.com/sysker/picBed/raw/master/20210702083413.png)

运行也很简单，只需要找到一个可运行的`jar`包，比如一个`springboot`项目的包，然后在`jar`文件的启动命令中，增加如下配置即可：

```sh
--javaagent:你的agent文件完整路径/agent文件名.jar
# 例如我的：D:\workspace\learning\example-everyday\example-2021.07.02\target\example-2021.07.02-1.0-SNAPSHOT.jar
```

这里我用之前的一个`springboot`项目演示：

```java
java -javaagent:D:\workspace\learning\example-everyday\example-2021.07.02\target\example-2021.07.02-1.0-SNAPSHOT.jar -jar 
```

大家注意，在`javaagent`和`agent`文件之间不能有空格，否则会报如下错误

![](https://gitee.com/sysker/picBed/raw/master/20210702084512.png)

如果你的配置和启动命令都没有问题，在启动控制台应该会显示如下信息：

![](https://gitee.com/sysker/picBed/raw/master/20210702084845.png)

我们可以看到`premain`方法在我们`springboot`项目启动前被执行了，但是`preArgs`是`null`，这是由于我们没有注入参数，所以显示为空，我们可以通过这样的方式为`preArgs`注入参数：

```sh
java -javaagent:D:\workspace\learning\example-everyday\example-2021.07.02\target\example-2021.07.02-1.0-SNAPSHOT.jar=syske -jar  .\springboot-learning-0.0.1-SNAPSHOT.jar
```

也就是在我们的`agent`包后面直接`=需要注入的参数值`就可以了，然后再次执行你会发现参数已经被注入了：

![](https://gitee.com/sysker/picBed/raw/master/20210702085308.png)

关于`Instrumentation`这个参数，今天先不讲了，我们说的字节码操都是基于这个参数进行操作的。下面我们看下第二种方式



#### Agent扩展



### 总结

