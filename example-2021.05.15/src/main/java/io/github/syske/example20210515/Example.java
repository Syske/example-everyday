package io.github.syske.example20210515;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @program: example-2021.05.15
 * @description: example 2021-05-15
 * @author: syske
 * @date: 2021-05-16 7:38
 */
public class Example {
    public static void main(String[] args) {
        System.out.println(new Solution().reverseLeftWords4("lrloseumgh", 6));
    }
}

class Solution {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    public String reverseLeftWords2(String s, int n) {
        return new StringBuilder(s.substring(n)).append(s.substring(0, n)).toString();
    }
    public String reverseLeftWords3(String s, int n) {
        char[] chars = s.toCharArray();
        return new String(Arrays.copyOfRange(chars, n, s.length())) + new String(Arrays.copyOfRange(chars, 0, n));
    }
    public String reverseLeftWords4(String s, int n) {
        char[] chars = s.toCharArray();
        char[] targetChars = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (i >= chars.length - n) {
                targetChars[i] = chars[i - (chars.length - n)];
            } else {
                 targetChars[i] = chars[i + n];
            }
        }
        return new String(targetChars);
    }
}
