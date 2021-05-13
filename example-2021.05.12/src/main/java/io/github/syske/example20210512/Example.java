package io.github.syske.example20210512;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.github.syske.example20210512.entity.ChildEntriy;
import io.github.syske.example20210512.entity.TestEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: example-2021.05.12
 * @description: 2021-05-12 example
 * @author: syske
 * @create: 2021-05-13 07:19
 */
public class Example {
    public static void main(String[] args) {
        TestEntity testEntity = new TestEntity();
        ChildEntriy childEntriy = new ChildEntriy();
        testEntity.setChildEntriy(childEntriy);
        testEntity.setAge(20);
        testEntity.setId(34124234L);
        testEntity.setName("name-test");
        TestEntity testEntity2 = new TestEntity();
        ChildEntriy childEntriy2 = new ChildEntriy();
        testEntity2.setChildEntriy(childEntriy2);
        testEntity2.setAge(12);
        testEntity2.setId(34124234L);
        testEntity2.setName("name-test2");
        List<TestEntity> testEntityList = Lists.newArrayList(testEntity, testEntity2);
        List<TestEntity> collect = testEntityList.stream().filter(t -> t.getAge() > 18).collect(Collectors.toList());
        System.out.println(collect);

        long count = testEntityList.stream().count();
        System.out.println(count);
        Map<String, TestEntity> entityMap = Maps.newHashMap();
        entityMap.put("test1", testEntity);
        entityMap.put("test2", testEntity2);
    }

    private static void listStreamTest1() {
        TestEntity testEntity = new TestEntity();
        ChildEntriy childEntriy = new ChildEntriy();
        testEntity.setChildEntriy(childEntriy);
        testEntity.setAge(20);
        testEntity.setId(34124234L);
        testEntity.setName("name-test");
        TestEntity testEntity2 = new TestEntity();
        ChildEntriy childEntriy2 = new ChildEntriy();
        testEntity2.setChildEntriy(childEntriy2);
        testEntity2.setAge(12);
        testEntity2.setId(34124234L);
        testEntity2.setName("name-test2");
        List<TestEntity> testEntityList = Lists.newArrayList(testEntity, testEntity2);
        List<TestEntity> collect = testEntityList.stream().filter(t -> t.getAge() > 18).collect(Collectors.toList());
        System.out.println(collect);
        long count = testEntityList.stream().filter(t -> t.getAge() > 18).count();
        Map<Long, String> collect1 = testEntityList.stream().filter(t -> t.getAge() > 18).collect(Collectors.toMap(TestEntity::getId, TestEntity::getName));
        Map<Long, String> collect3 = testEntityList.stream().filter(t -> t.getAge() > 18).collect(Collectors.toMap(t -> t.getId(), t -> t.getName()));
        Map<Long, TestEntity> collect2 = testEntityList.stream().filter(t -> t.getAge() > 18).collect(Collectors.toMap(TestEntity::getId, t -> t));
    }

    private static void listStreamTest2() {
        TestEntity testEntity = new TestEntity();
        ChildEntriy childEntriy = new ChildEntriy();
        testEntity.setChildEntriy(childEntriy);
        testEntity.setAge(20);
        testEntity.setId(34124234L);
        testEntity.setName("name-test");
        TestEntity testEntity2 = new TestEntity();
        ChildEntriy childEntriy2 = new ChildEntriy();
        testEntity2.setChildEntriy(childEntriy2);
        testEntity2.setAge(12);
        testEntity2.setId(34124234L);
        testEntity2.setName("name-test2");
        List<TestEntity> testEntityList = Lists.newArrayList(testEntity, testEntity2);
        List<TestEntity> collect = testEntityList.stream().filter(t -> t.getAge() > 18).collect(Collectors.toList());
        List<TestEntity> collect1 = testEntityList.stream().collect(Collectors.toList());
        long count = testEntityList.stream().filter(t -> t.getAge() > 18).count();
        Map<Long, String> collect2 = testEntityList.stream().collect(Collectors.toMap(TestEntity::getId, TestEntity::getName));
        Map<Long, String> collect3 = testEntityList.stream().collect(Collectors.toMap(t -> t.getId(), t -> t.getName()));
        Map<Long, TestEntity> collect4 = testEntityList.stream().collect(Collectors.toMap(TestEntity::getId, t -> t));
        System.out.println(collect);

    }
}
