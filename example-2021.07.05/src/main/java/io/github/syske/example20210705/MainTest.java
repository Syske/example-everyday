package io.github.syske.example20210705;

public class MainTest {
    public static void main(String[] args) {
        Example a = new Example("A");
        for (int i = 0; i < 30; i++) {
            new Thread(a, "t" + i).start();
        }
    }
}
