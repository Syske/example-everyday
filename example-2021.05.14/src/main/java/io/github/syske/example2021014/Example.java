package io.github.syske.example2021014;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: example-2021.05.14
 * @description: 2021-05-14 example
 * @author: syske
 * @date: 2021-05-15 8:40
 */
public class Example {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.romanToInt("MCMXCIV"));
        String test = "testsetwet";
        System.out.println(test.substring(2) + test.substring(0, 2));
    }
}

class Solution {
    private static final Map<Character, Integer> charIntMap = new HashMap<>();
    static {
        charIntMap.put('I', 1);
        charIntMap.put('V', 5);
        charIntMap.put('X', 10);
        charIntMap.put('L', 50);
        charIntMap.put('C', 100);
        charIntMap.put('D', 500);
        charIntMap.put('M', 1000);
    }

    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i >= 1 && chars[i - 1] == 'I' && (chars[i] == 'V' || chars[i] == 'X')) {
                sum += charIntMap.get(chars[i]) - 2;
            } else if (i >= 1 && chars[i - 1] == 'X' && (chars[i] == 'L' || chars[i] == 'C')) {
                sum += charIntMap.get(chars[i]) - 20;
            } else if (i >= 1 && chars[i - 1] == 'C' && (chars[i] == 'D' || chars[i] == 'M')) {
                sum += charIntMap.get(chars[i]) - 200;
            } else  {
                sum += charIntMap.get(chars[i]);
            }
        }
        return sum;
    }
}
