package io.github.syske.agent;

import java.lang.instrument.Instrumentation;

/**
 * @program: example-2021.07.02
 * @description: 在主程序之后运行的Agent
 * @author: syske
 * @date: 2021-07-02 12:38
 */
public class AgentMain {
    public static void agentmain(String args, Instrumentation instrumentation) {
        System.out.println("AgentMainTest.agentmain start");
        System.out.println("args: " + args);
        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
//        for (Class allLoadedClass : allLoadedClasses) {
        System.out.println("AgentMainTest LoadedClass: " + allLoadedClasses[0].getName());
//        }
    }
}
