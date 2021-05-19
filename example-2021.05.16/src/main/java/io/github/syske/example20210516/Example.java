package io.github.syske.example20210516;

import java.util.Arrays;

/**
 * @program: example-2021.05.16
 * @description: example 2021-05-16
 * @author: syske
 * @date: 2021-05-17 7:10
 */
public class Example {
    public static void main(String[] args) {
        System.out.println(new Solution().xorOperation(4, 3));
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(new Solution().runningSum(nums)));
    }
}

class Solution {
    public int xorOperation(int n, int start) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result ^=  start + 2*i;
        }
        return result;
    }

    public int[] runningSum(int[] nums) {
        int result = 0;
        int[] results = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                result = 0;
            } else {
                result = results[i - 1];
            }
            results[i] = result + nums[i];
        }
        return results;
    }
}
