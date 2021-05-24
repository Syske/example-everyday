package io.github.syske.example20210520;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2021-05-19 example
 *
 * @author sysker
 * @version 1.0
 * @date 2021-05-21 7:05
 */
public class Example {

    public static void main(String[] args) throws ParseException {
        List<String> stringList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            stringList.add("str" + i);
        }

        // for循环
        long startTime1 = System.currentTimeMillis();
        for (String s : stringList) {
            System.out.println(s);
        }
        long endTime1 = System.currentTimeMillis();
        long startTime2 = System.currentTimeMillis();
        stringList.forEach(System.out::println);
        System.out.println("for循环用时：" + (endTime1 - startTime1));
        System.out.println("lamudb表达式用时：" + (System.currentTimeMillis() - startTime2));
        int x = 0, y = 2;
        System.out.println("Row 1 :" + x + "， "+ y);
        System.out.printf("Row 1 :%d， %d\n", x, y);
        System.out.printf("Row 1 :%d， %d\n", x, y);
        System.out.format("Row 1 :%d， %d\n", x, y);
        String format = String.format("x = %d, y = %d%%, x + y = %d", x, y, x + y);
        System.out.println(format);

        String str = "0000-00-00";
        String pattern = "^\\d\\d\\d\\d-\\d\\d-\\d\\d";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(str)));
    }
}

