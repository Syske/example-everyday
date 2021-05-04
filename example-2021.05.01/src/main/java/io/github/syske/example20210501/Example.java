package io.github.syske.example20210501;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 * @program: example-2021.05.01
 * @description: 2021-05-01-example
 * @author: syske
 * @date: 2021-05-01 17:30
 */
public class Example implements Readable {

    private static Random random = new Random(47);
    private static final char[] alls =
        "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ@#!$%*&/".toCharArray();
    /**
     * 生成字密码的数量
     */
    private int count;
    /**
     * 生成密码长度
      */
    private int length;

    /**
     * 随机密码生成器
     *
     * @param count
     * @param length
     */
    public Example(int count,int length) {
            this.count = count;
            this.length = length;
        }

    @Override
    public int read(CharBuffer cb) {
        if (count-- == 0) {
            return -1;
        }
        for (int i = 0; i < length; i++) {
            cb.append(alls[random.nextInt(alls.length)]);
        }
        cb.append(" ");
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new Example(3, 9));
        while (sc.hasNext()) {
            System.out.println(sc.next());
        }
    }

}
