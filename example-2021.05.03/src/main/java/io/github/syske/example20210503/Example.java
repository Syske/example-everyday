package io.github.syske.example20210503;

import java.io.File;
import java.net.URL;
import java.util.Properties;

/**
 * @program: example-2021.05.03
 * @description: 2021.05.03每日一例
 * @author: syske
 * @date: 2021-05-03 8:09
 */
public class Example {
    public static void main(String[] args) {
        //路径为空
        File file = new File("");
        // 返回结果：D:\workspace\learning\example-everyday\example-2021.05.03
        System.out.println(file.getAbsoluteFile());

        // 获取当前项目所在磁盘的根目录(linux环境没试过)
        File fileRoot = new File("/");
        // 返回结果：D:\
        System.out.println(fileRoot.getAbsoluteFile());

        // 路径为当前相对目录./时
        File fileCurrent = new File("./");
        // 返回结果：D:\workspace\learning\example-everyday\example-2021.05.03\.
        System.out.println(fileCurrent.getAbsoluteFile());

        // 路径为当前相对目录.时
        File fileCurrent2 = new File(".");
        // 返回结果：D:\workspace\learning\example-everyday\example-2021.05.03\.
        System.out.println(fileCurrent2.getAbsoluteFile());

        // 路径为当前相对目录..时
        File fileparent = new File("..");
        // 返回结果：D:\workspace\learning\example-everyday\example-2021.05.03\.
        System.out.println(fileparent.getAbsoluteFile());

        // 获取当前类的路径
        String resourcePath = Example.class.getResource("").getPath();
        // 返回结果：/D:/workspace/learning/example-everyday/example-2021.05.03/target/classes/io/github/syske/example20210503/
        System.out.println(resourcePath);

        // 获取classes的路径
        String path = Example.class.getResource("/").getPath();
        // 返回结果：/D:/workspace/learning/example-everyday/example-2021.05.03/target/classes/
        System.out.println(path);

        // 返回系统信息
        Properties props = System.getProperties();
        props.list(System.out);
    }
}
