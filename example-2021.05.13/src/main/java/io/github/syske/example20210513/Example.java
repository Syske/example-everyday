package io.github.syske.example20210513;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * @program: example-2021.05.13
 * @description: 2021-0-13 example
 * @author: syske
 * @create: 2021-05-14 06:59
 */
public class Example {

    public static void main(String[] args) {
        streamMapTest();
    }

    private static void streamMapTest() {
        List<String> stringList = Lists.newArrayList("test1", "test2", "test3", "test1");
//        Map<String, String> collect = stringList.stream().collect(Collectors.toMap(s -> s, s -> s));
        Map<String, String> collect2 = stringList.stream().collect(Collectors.toMap(s -> s, s -> s, (a, b) -> a));
        Map<String, String> collect3 = stringList.stream().collect(Collectors.toMap(String :: toUpperCase, s -> s, (a, b) -> a));
        Map<String, String> collect7 = stringList.stream().map(String :: toUpperCase).collect(Collectors.toMap(s -> s, s -> s, (a, b) -> a));
        Map<String, String> collect4 = stringList.stream().collect(Collectors.toMap(s -> s.toUpperCase(), s -> s, (a, b) -> a));
        List<String> collect1 = stringList.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
        List<String> collect5 = stringList.stream().map(String :: toUpperCase).collect(Collectors.toList());
        List<Integer> collect6 = stringList.stream().map(String::length).collect(Collectors.toList());
        List<String> collect = stringList.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);

        System.out.println(collect3);
        System.out.println(collect1);

        TestEntity testEntity = new TestEntity();
        testEntity.setAge(1231);
        testEntity.setId(124143545L);
        testEntity.setName("阿波罗");
        ChildEntriy childEntriy = new ChildEntriy();
        childEntriy.setId(15252L);
        childEntriy.setName("apollo");
        testEntity.setChildEntriy(childEntriy);
        ArrayList<TestEntity> testEntities = Lists.newArrayList(testEntity, testEntity);
        System.out.println(testEntities);
        List<TestEntity> collect8 = testEntities.stream().distinct().collect(Collectors.toList());
        System.out.println(collect8);

        ArrayList<String> strings = Lists.newArrayList("w", "b", "d", "l", "o", "g");
        System.out.println(strings);
        List<String> collect9 = strings.stream().sorted().collect(Collectors.toList());
        System.out.println(collect9);
        List<String> collect10 = strings.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(collect10);
    }

}
