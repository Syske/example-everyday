package io.gtihub.syske.angent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.io.IOException;

/**
 * @program: example-2021.07.01
 * @description:
 * @author: syske
 * @date: 2021-07-01 13:38
 */
public class TestMian {
        public static void main(String[] args) throws AttachNotSupportedException,
                IOException, AgentLoadException, AgentInitializationException {
            //   vm.loadAgent("/Users/jiangbo/Workspace/code/java/javaagent/loadagent.jar");
            for (VirtualMachineDescriptor descriptor : VirtualMachine.list()) {
                if (descriptor.displayName().equals("io.gtihub.syske.angent.TestMian")) {
                    VirtualMachine virtualMachine = VirtualMachine.attach(descriptor.id());
                    virtualMachine.loadAgent("D:/workspace/learning/example-everyday/example-2021.07.01/target/example-2021.07.01-1.0-SNAPSHOT.jar",
                            "io.gtihub.syske.angent.TestMian");
                    // 传入agent的jar包路径，test.Task是一个String agentArgs，就像main方法的String[] args，使用户传入的，test.Task表示要热修改test.Task类
                    virtualMachine.detach();
                }
            }
        }

}
