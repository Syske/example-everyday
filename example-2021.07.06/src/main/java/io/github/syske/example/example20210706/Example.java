package io.github.syske.example.example20210706;

import com.google.common.collect.Interner;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Example {
    public static void main(String[] args) {
        TestVo testVo = new TestVo();
        List<TestVo> testVoList = Lists.newArrayList(testVo);
        Map<String, Integer> collectMap = testVoList.stream().collect(Collectors.toMap(TestVo::getName,
                t -> {
                    if (t.getAge() == null) {
                        return 0;
                    } else {
                        return t.getAge();
                    }
                }));
//        Map<Integer, String> collectMap = testVoList.stream().collect(Collectors.toMap(TestVo::getAge, TestVo::getName));
        System.out.println(collectMap);

        Integer a = null;
        int b = a;
    }

    private void testInt(Integer i) {
        System.out.println(i + i);
    }

    private void testInt(int i) {
        System.out.println(i * i);
    }
}
