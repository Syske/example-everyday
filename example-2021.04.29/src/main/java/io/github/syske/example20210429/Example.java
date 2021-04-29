package io.github.syske.example20210429;

import java.util.ArrayList;
import java.util.List;

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
        stringList.forEach(s ->
                System.out.println(s));

        // 等价于
        for (String s : stringList) {
            System.out.println(s);
        }
    }
}
