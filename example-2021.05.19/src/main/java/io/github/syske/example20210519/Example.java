package io.github.syske.example20210519;

/**
 * example 2021-05-19
 *
 * @author sysker
 * @version 1.0
 * @date 2021-05-20 7:28
 */
public class Example {
    public static void main(String[] args) {
        System.out.println(new Example().lengthOfLastWord("123456 "));
    }

    public int lengthOfLastWord(String s) {
//        if (s.lastIndexOf(' ') == -1 && s.length() > 0) {
//            return s.length();
//        }
//        return s.length() - (s.lastIndexOf(" ") + 1);
//        return s.split(" ").length==0?0:s.split(" ")[s.split(" ").length-1].length();
        char[] chars = s.toCharArray();
        int length = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                return chars.length - (i + 1);
            }
            length++;
        }
        return length;
    }


}

