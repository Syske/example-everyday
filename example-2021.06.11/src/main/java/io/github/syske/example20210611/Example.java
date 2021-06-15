package io.github.syske.example20210611;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: example-2021.06.11
 * @description: 正则表达式
 * @author: syske
 * @date: 2021-06-12 16:58
 */
public class Example {

    public static void main(String[] args) throws Exception{
        String pattern = "^[1]\\d{10}";
        String input = "1350123456w";
        System.out.println(pattern(pattern, input));

        String pattern2 = "^\\d{4}-\\d{1,2}-\\d{1,2}";
        String input2 = "2021-6-1";
        System.out.println(pattern(pattern2, input2));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse("2021-00-00");
        System.out.println(simpleDateFormat.format(parse));
    }

    /**
     * 校验数据格式
     * @param pattern 正则格式
     * @param targetStr 目标值
     * @return
     */
    private static boolean pattern(String pattern, String targetStr) {
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(targetStr);
        return matcher.matches();
    }
}
