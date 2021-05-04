package io.github.syske.example20210502;

import java.io.*;
import java.util.Objects;

/**
 * @program: example-2021.05.02
 * @description: 20201.05.02每日一例
 * @author: syske
 * @date: 2021-05-03 7:59
 */
public class Example {
    public static void main(String[] args) {
        readFile();
//        readFile2();
//        testInputStreamWithClose();
    }

    /**
     * 读取文件
     */
    private static void readFile() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("target/classes/test.txt");
            byte[] bytes = new byte[1024];
            // 读取文件
            while (fileInputStream.read(bytes) != -1) {
                System.out.println(new String(bytes));
            }
            System.out.println(1/0);
            System.out.println(fileInputStream.available());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } /*finally {
            // 关闭资源
            if (Objects.nonNull(fileInputStream)) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
        System.out.println("结束");
    }

    /**
     *
     */
    private static void readFile2() {
        try (FileInputStream fileInputStream = new FileInputStream("target/classes/test.txt");) {
            byte[] bytes = new byte[1024];
            // 读取文件
            while (fileInputStream.read(bytes) != -1) {
                System.out.println(new String(bytes));
            }
            throw new FileNotFoundException("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
