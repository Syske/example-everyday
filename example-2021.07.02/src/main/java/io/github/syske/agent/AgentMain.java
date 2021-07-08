package io.github.syske.agent;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Arrays;

/**
 * @program: example-2021.07.02
 * @description: 在主程序之后运行的Agent
 * @author: syske
 * @date: 2021-07-02 12:38
 */
public class AgentMain {

    static ClassFileTransformer classFileTransformer = (loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
        System.out.println("loader: " + loader);
        System.out.println("className: "  + className);
        System.out.println("classBeingRedefined: " + classBeingRedefined);
        System.out.println("protectionDomain: " + protectionDomain);
        System.out.println("classfileBuffer: " + classfileBuffer);
        ClassPool aDefault = ClassPool.getDefault();
        try {
            CtClass ctClass = aDefault.get(className.replace('/' ,'.'));
            CtClass ctClass1 = aDefault.get("java.lang.String");
            CtField ctField = new CtField(ctClass1, "hello", ctClass);
            ctClass.addField(ctField);
            CtField[] fields = ctClass.getFields();
            System.out.println("fields" + Arrays.toString(fields));
            CtMethod toString = aDefault.getMethod("java.lang.String", "toString");
            toString.setBody();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }

        return new byte[0];
    };
    public static void agentmain(String args, Instrumentation instrumentation) {
        System.out.println("AgentMainTest.agentmain start");
        System.out.println("args: " + args);
        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
//        for (Class allLoadedClass : allLoadedClasses) {
//        System.out.println("AgentMainTest LoadedClass: " + allLoadedClasses[0].getName());
//        }

        instrumentation.addTransformer(classFileTransformer);
    }
}
