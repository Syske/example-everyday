package io.github.syske.example20210522;

import java.time.Instant;
import java.util.Date;

/**
 * @program: example-2021.05.22
 * @description: 2021-05-22 example
 * @author: syske
 * @date: 2021-05-23 15:55
 */
public class Example {
    public static void main(String[] args) {
        Instant now = Instant.now();
        Instant instant = Instant.ofEpochSecond(24*3600L);
        Instant instant2 = Instant.ofEpochSecond(24*3600L, 100L);
        System.out.println(instant.getNano());
        System.out.println(instant2.getNano());
        System.out.println(instant.toString());
        System.out.println(now.getEpochSecond());
        System.out.println(now.getNano());
    }
}
