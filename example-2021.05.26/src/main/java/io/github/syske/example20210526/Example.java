package io.github.syske.example20210526;

/**
 * example-2021-05-26
 *
 * @author sysker
 * @version 1.0
 * @date 2021-05-26 22:55
 */
public class Example {
    private static String a = "a";
    private static String b = "b";

    public static void main(String[] args) {
        new Example().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("b1");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (b) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println("a2");
                }
            }
        });
        t2.start();
        t1.start();
    }

}
