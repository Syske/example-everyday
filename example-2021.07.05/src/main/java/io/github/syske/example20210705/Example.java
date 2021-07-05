package io.github.syske.example20210705;

import java.util.concurrent.atomic.AtomicInteger;

public class Example extends Thread{
    private final AtomicInteger count = new AtomicInteger(0);
//    private int count = 0;

    public Example(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
//        count--;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("name: " + this.getName() + ", count: " + count.getAndAdd(1));
//        System.out.println("name: " + this.getName() + ", count: " + count++);
    }

    public static void main(String[] args) {
        Example a = new Example("A");
        for (int i = 0; i < 50; i++) {
            new Thread(a, "t" + i).start();
        }
    }
}
