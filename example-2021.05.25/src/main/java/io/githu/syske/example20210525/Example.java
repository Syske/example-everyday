package io.githu.syske.example20210525;

/**
 * example 2021-05-25
 *
 * @author sysker
 * @version 1.0
 * @date 2021-05-26 7:51
 */
public class Example {
    private static final long count = 100000000L;

    public static void main(String[] args) throws Exception{
        concurrentcy();;
        serial();
    }
    private static void concurrentcy() throws Exception {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println(String.format("concurrecy: %dms, b=%d", time, b));
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b --;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(String.format("serial: %dms, b=%d", time, b));
    }
}
