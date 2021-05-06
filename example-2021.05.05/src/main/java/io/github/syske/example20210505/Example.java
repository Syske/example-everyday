package io.github.syske.example20210505;

import java.util.*;

/**
 * @program: example-2021.05.05
 * @description: 2020-05-05 example
 * @author: syske
 * @date: 2021-05-05 10:39
 */
public class Example {
    public int[] twoSum(int[] nums, int target) {
        for(int i=0; i < nums.length; i++) {
            if (nums[i] <= target) {
                for (int j = i + 1 ; j < nums.length; j++) {
                    if (nums[j] == target - nums[i]) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return new int[] {};
    }



    public static void main(String[] args) {
        int[] nums = {3, 2, 4};

        System.out.println(new Example().twoSum(nums, 6)[0]);
    }
}
