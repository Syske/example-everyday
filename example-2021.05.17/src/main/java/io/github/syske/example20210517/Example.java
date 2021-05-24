package io.github.syske.example20210517;

import com.google.common.collect.Lists;
import io.github.syske.example20210517.entity.User;

import java.util.Comparator;
import java.util.List;

/**
 * example 2021-05-17
 *
 * @author sysker
 * @version 1.0
 * @date 2021-05-18 7:46
 */
public class Example {

    public static void main(String[] args) {
        User user1 = new User(15, "张三");
        User user2 = new User(12, "王五");
        User user3 = new User(14, "里斯");
        User user4 = new User(18, "赵六");
        User user5 = new User(20, "六七");
        User user6 = new User(23, "郑八");
        List<User> userList = Lists.newArrayList(user1, user2, user3, user4, user5, user6);

        System.out.println(maxAgeUser1(userList));
        System.out.println(maxAgeUser2(userList));
        String[] strs = {"flower","flow","flight"};
        System.out.println(new Solution().longestCommonPrefix(strs));
    }

    private static User maxAgeUser2(List<User> userList) {
        return userList.stream().max(Comparator.comparing(User::getAge)).get();
    }

    private static User minAgeUser2(List<User> userList) {
        return userList.stream().min(Comparator.comparing(User::getAge)).get();
    }

    private static User maxAgeUser1(List<User> userList) {
        User maxAgeUser = new User();
        maxAgeUser.setAge(0);
        for (User user : userList) {
            if (user.getAge() > maxAgeUser.getAge()) {
                maxAgeUser = user;
            }
        }
        return maxAgeUser;
    }

    private static int maxInt2(List<Integer> integerList) {
        return integerList.stream().max(Integer :: max).get();
    }

    private static int minInt2(List<Integer> integerList) {
        return integerList.stream().min(Integer :: min).get();
    }

    private static int maxInt1(List<Integer> integerList) {
        int max = 0;
        for (Integer integer : integerList) {
            if (integer > max) {
                max = integer;
            }
        }
        return max;
    }
}


class Solution {
    public String longestCommonPrefix(String[] strs) {
        char[] chars = strs[0].toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append(chars[i]);
            for (String str : strs) {
                if (!str.contains(stringBuilder.toString())) {
                    return stringBuilder.deleteCharAt(i).toString();
                }
            }
        }
        return stringBuilder.toString();

    }
}