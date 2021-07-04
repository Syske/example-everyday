package io.gtihub.syske.angent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Arrays;

/**
 * @program: example-2021.07.01
 * @description: agent测试
 * @author: syske
 * @date: 2021-07-01 12:45
 */
public class TestPreAgent {
    public static void premain(String argentArgs, Instrumentation instrumentation) {
        System.out.println("premain start");
        System.out.println(argentArgs);
        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
        for (Class allLoadedClass : allLoadedClasses) {
            System.out.println("premain oadedClass:" + allLoadedClass);
        }
    }

}
