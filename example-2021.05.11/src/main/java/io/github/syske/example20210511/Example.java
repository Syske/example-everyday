package io.github.syske.example20210511;

/**
 * @program: example-2021.05.11
 * @description: 2021-05-11 example
 * @author: syske
 * @create: 2021-05-12 06:49
 */
public class Example {
    /**
     * 回文数
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int n = 0;
        int temp = x;
        while (x != 0) {
            n = n * 10 + x % 10;
            x = x / 10;
        }
        return n == temp;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        char[] chars = String.valueOf(x).toCharArray();
        for (int i = 0; i <= chars.length / 2; i++) {
            if (!(chars[i] == chars[chars.length -1 -i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(121));
        System.out.println(Integer.toBinaryString(122));
        System.out.println(Integer.toBinaryString(123));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(10));
        System.out.println(Integer.toBinaryString(0));
        System.out.println(new Example().isPalindrome2(0));
    }
}
