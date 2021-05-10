package io.github.syske.examp20210509;

import com.sun.xml.internal.messaging.saaj.util.Base64;
import io.github.syske.example20210509.Example;
import org.junit.Test;
import sun.misc.BASE64Encoder;

/**
 * @program: example-2021-05-09
 * @description: test
 * @author: syske
 * @create: 2021-05-10 07:47
 */
public class ExampleTest {
    private Example example = new Example();

    @Test
    public void base64EncodeTest() {
        String str = "test unit";
        String encodeBase64 = example.encodeBase64(str);
        System.out.println(encodeBase64);
        String s = example.decodeBase64(encodeBase64);
        System.out.println(s);
    }

    @Test
    public void md5Test() {
        System.out.println(example.md5Encode("test md5"));
    }
}
