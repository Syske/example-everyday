package io.github.syske.example20210510;

/**
 * @program: example-2021.05.10
 * @description: 2021-05-10 example
 * @author: syske
 * @create: 2021-05-10 22:24
 */
public class Example {
    public static void main(String[] args) {
        int reverse = 12333;
        int i = -1;
//        reverse(reverse);
    printOut(reverse);
//        StringBuilder stringBuilder = new StringBuilder();
//        System.out.println(stringBuilder.append(reverse2(reverse)));
//        System.out.println(reverse(-2147483412));
    }


    public static int reverse(int i) {
        long result = 0;
        while ( i != 0) {
            int yu = i % 10;
            result = result * 10 + yu;
            i = i / 10;
        }
        return (int)result == result ? (int)result : 0;
    }

    public static void printOut(int n) {
        if (n >= 10) {
            printOut(n / 10);
        }
        System.out.println(n % 10);
    }

    public static int reverse2(int n) {
        if (n >= 10) {
            reverse(n / 10);
        }
        return (n % 10);
    }



}

class Solution {
        public int reverse(int x) {
            Integer.reverse(x);
            if (x == 0) {
                return 0;
            }
            String valueOf = String.valueOf(x);
            char[] chars;
            StringBuilder numberBuilder;
            if (valueOf.startsWith("-") || valueOf.startsWith("+")) {
                chars = valueOf.substring(1).toCharArray();
                numberBuilder = new StringBuilder(valueOf.substring(0, 1));
            } else {
                chars = valueOf.toCharArray();
                numberBuilder = new StringBuilder();
            }
            for (int i = chars.length - 1; i > 0; i --) {
                numberBuilder.append(chars[i]);
            }
            int result = 0;
            try {
                result = Integer.parseInt(numberBuilder.toString());
            } catch (NumberFormatException e) {
                return result;
            }
            return result;
        }
}
