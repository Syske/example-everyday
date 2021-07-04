package io.gtihub.syske.angent;

import java.lang.instrument.Instrumentation;

/**
 * @program: example-2021.07.01
 * @description:
 * @author: syske
 * @date: 2021-07-01 13:34
 */
public class TestAgentMain {
    public static void agentmain(String args, Instrumentation inst){
        System.out.println("agentmain ^ _ ^");
        Class[] classes = inst.getAllLoadedClasses();
        for(Class cls :classes){
            System.out.println(cls.getName());
        }
    }
}
