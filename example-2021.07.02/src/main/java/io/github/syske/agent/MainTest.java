package io.github.syske.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.io.IOException;
import java.util.List;

public class MainTest {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> machineDescriptorList = VirtualMachine.list();
        for (VirtualMachineDescriptor virtualMachineDescriptor : machineDescriptorList) {
            if ("io.github.syske.agent.MainTest".equals(virtualMachineDescriptor.displayName())) {
                String id = virtualMachineDescriptor.id();
                VirtualMachine virtualMachine = VirtualMachine.attach(id);
                virtualMachine.loadAgent("D:\\workspace\\learning\\example-everyday\\example-2021.07.02\\target\\example-2021.07.02-1.0-SNAPSHOT.jar",
                        "syske agentmain");
//                virtualMachine.detach();
            }
        }
        System.out.println("MainTest start");
    }
}
