package io.github.syske.agent;

import java.lang.instrument.Instrumentation;

/**
 * 在主程序之前运行的Agent
 */
public class PremainAgent {
    public static void premain(String preArgs, Instrumentation instrumentation) {
        System.out.println("premainAgent.premain start");
        System.out.println("preArgs: " + preArgs);
        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
//        for (Class allLoadedClass : allLoadedClasses) {
            System.out.println("premainAgent LoadedClass: " + allLoadedClasses[0].getName());
//        }
    }
}
