package io.github.syske.example20210610;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * 每日一例20210610
 *
 * @author sysker
 * @version 1.0
 * @date 2021-06-11 8:11
 */
public class Example {
    public static void main(String[] args) {
        List<Integer> longList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            longList.add(i);
        }
        System.out.println("随机排序前："+ longList);
        long startTime = System.currentTimeMillis();
//        Collections.shuffle(longList);
        shuffle(longList);
        long useTime = System.currentTimeMillis() - startTime;
        System.out.println("用时：" + useTime);
        System.out.println("随机排序后："+ longList);
      /*  Random random = new Random(10L);
        Collections.shuffle(longList, random);
        System.out.println("随机排序后："+ longList);*/
    }

    private static void shuffle(List list) {
        List newList = Lists.newArrayList(list.size());
        Random random = new Random();
        random.setSeed(list.size());
        for (int i = 0; i < list.size(); i++) {
            int randomIndex = random.nextInt(list.size());
                Object element = list.get(randomIndex);
                Object element1 = list.get(i);
                list.set(i, element);
                list.set(randomIndex, element1);
        }

    }
}
