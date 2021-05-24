package io.github.syske.example2021052;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @program: example-2021.05.23
 * @description: 2021-05-23 example
 * @author: syske
 * @date: 2021-05-24 7:06
 */
public class Example {
    public static void main(String[] args) throws Exception {
        String testStr = "optianal";
        Optional<String> testStr1 = Optional.of(testStr);
        // 如果不为空执行
        testStr1.ifPresent(System.out::println);
        // 值不为空为true，否则为false
        if (testStr1.isPresent()) {
            System.out.println("testStr1的值为空");
        }
        Optional<String> s3 = Optional.of(null);
        // 如果值不为空，则返回其值，否则将传入的值返回
        String s = s3.orElse("null");
        System.out.println("s:" + s);
        // 如果值不为空，则返回其值，否则获取传入的值
        String s1 = s3.orElseGet(String::new);
        System.out.println("s1:" +s1);
        // 如果值不为空，则返回其值，否则抛出一个异常
        String s2 = s3.orElseThrow(Exception::new);
        System.out.println("s2:" +s2);

        Optional<String> sdf = s3.filter(v -> v.equals("sdf"));
        System.out.println(sdf);
        Optional<Boolean> test = s3.map(v -> v.equals("test"));
        Optional<String> s4 = s3.map(v -> v + "123123");
        System.out.println(s4);
        System.out.println(test);



    }
}

class testCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        return null;
    }
}
