package io.github.syske.example20210521;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * 2021-05-21 example
 *
 * @author sysker
 * @version 1.0
 * @date 2021-05-22 10:16
 */
public class Example {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = dateFormat.parse("1970-01-01 00:00:00");
        System.out.println(parse.getTime());
        System.out.println(new Date().getTime());
        Date x = new Date();
        x.setTime(0);
        System.out.println(x);
        Date date1 = new Date();
        Date date2 = new Date(date1.getTime() + 1);
        System.out.println(date1.equals(date2));

        Date date3 = new Date();
        Date date4 = new Date(date1.getTime() + 5);
        System.out.println(date3.compareTo(date4));

        Instant instant = Instant.now();
        System.out.println(Date.from(instant));
        Instant instant1 = date1.toInstant();
        System.out.println(instant1);

    }
}
